/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/3/1 12:22:18                           */
/*==============================================================*/


drop table if exists Book;

drop table if exists Borrow;

drop table if exists Reader;

drop table if exists ReaderType;

/*==============================================================*/
/* Table: Book                                                  */
/*==============================================================*/
create table Book
(
    bkId     int not null auto_increment, # Id
    bkName   varchar(30),                 # 书名
    bkAuthor varchar(30),                 # 作者
    bkPress  varchar(30),                 # 出版社
    bkPrice  decimal(5, 2),               # 价格
    bkNum    int,                         # 数量
    bkState  boolean default 1,           # 状态 默认可借
    bkImageUrl  varchar(30),                 # 图片
    CHECK (
        bkNum >= 0
        ),
    primary key (bkId)
);

/*==============================================================*/
/* Table: Borrow                                                */
/*==============================================================*/
create table Borrow
(
    rdId         int not null,     # 人Id
    bkId         int not null,     # 书Id
    dateBorrow   datetime,         # 借书
    dateLendPlan datetime,         # 还书期限
    dateLendAct  datetime,         # 实际归还
    state        boolean default 0 # 是否逾期状态
);

/*==============================================================*/
/* Table: Reader                                                */
/*==============================================================*/
create table Reader
(
    rdId        int not null auto_increment, # 读者Id
    rdType      int not null,                # 读者类别
    rdName      varchar(20),                 # 读者姓名
    rdDept      varchar(40),                 # 读者学院 或者 部门
    rdBorrowQty int     default 0,           # 已借书数量
    rdState     boolean default 0,           # 读者信息是否有效  (删除该读者时置1) 读者活跃则为0
    primary key (rdId)
);

/*==============================================================*/
/* Table: ReaderType                                            */
/*==============================================================*/
create table ReaderType
(
    rdType     int not null auto_increment, # 读者类别Id
    rdTypeName varchar(20) unique ,         # 读者类别名
    canLendQty int,                         # 可借数量
    canLendDay int,                         # 可借天数(Day)
    primary key (rdType)
);

create table Account
(
    identification varchar(20) unique, # 账号(注册时自定义)
    password       varchar(20),        # 密码(自定义) 后面也可更改
    root           boolean default 0,  # 权限 1(管理员)  权限 0(用户)
    QQ             varchar(13) default '',        # QQ
    rdId           int                 # 绑定学号
);

# 判断是否逾期
# 事件每隔 1 分钟触发
CREATE event if not exists event_borrow
    ON SCHEDULE EVERY 1 MINUTE
    ON COMPLETION PRESERVE
    COMMENT '判断记录是否逾期'
    DO
    update borrow
    set state = 1
    where dateLendAct is null AND state = 0 && NOW() > dateLendPlan;


# 触发器
# 用于借书还书时自动变化书的数量 和 读者借阅的图书数量
CREATE TRIGGER lendBorrow
    AFTER INSERT
    ON borrow
    FOR EACH ROW
BEGIN
    update reader set rdBorrowQty = rdBorrowQty + 1 where rdId = NEW.rdId;
    update book set bkNum = bkNum - 1 where bkId = NEW.bkId;
end;

CREATE TRIGGER returnBorrow
    AFTER UPDATE
    ON borrow
    FOR EACH ROW
BEGIN
    update reader set rdBorrowQty = rdBorrowQty - 1 where rdId = NEW.rdId;
    update book set bkNum = bkNum + 1 where bkId = NEW.bkId;
end;