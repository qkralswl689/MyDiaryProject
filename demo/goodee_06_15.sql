drop table companion;
drop table diaryuser;
drop table expenditure;
drop table file_upload;
drop table full;
drop table plus;
drop table spending;
drop table tplan;
drop table tplan_c;
drop table wishlist;
drop table calendarvo;
drop table diaryvo;


commit;

create table spending(
spendingid number(4) primary key,
userid number(4) not null,
spenddate date not null,
spendname varchar2(20) not null,
spending number(10) not null
);

create table plus(
plusid number(4) primary key,
userid number(4) not null,
plusdate date not null,
plusname varchar2(20) not null,
plusmoney number(10) not null
);

create table expenditure(
minusid number(4) primary key,
userid number(4) not null,
minusdate date not null,
minusname varchar2(20) not null,
iscard varchar2(5) not null,
minusmoney number(10) not null
);

create table diaryuser(
userid number(4) primary key,
useremail varchar2(50) not null,
userpw varchar2(100) not null,
username varchar2(20) not null,
userbirth date not null,
userimage varchar2(500)
);

create table tplan(
    tplan_id int primary key,
    tplan_start_date Date,
    tplan_end_date Date,
    tplan_name varchar2(50),
    companion varchar2(50),
    tplan_budget int
);

create table tplan_d(
  tplan_id varchar2(20) primary key,
  tplan_d_start date,
  tplan_d_end date,
  tplan_d_contents varchar2(100),
  tplan_note varchar2(100)
);

create table wishlist(
  tplan_id varchar2(20) primary key,
  wishlist varchar2(100)
);

create table tplan_c(
  tplan_id varchar2(20) primary key,
  tplan_c_contents varchar2(100),
  tplan_c_cost int,
  tplan_c_category varchar2(50)
);

create table companion(
  companion_id varchar2(20) primary key,
  companion_email varchar2(50)
);

create table diaryvo (
        id number(19,0) primary key,
        content varchar2(500) not null,
        created_date date,
        delete_time date,
        delete_yn varchar2(255),
        modified_date date,
        secret_yn varchar2(255),
        title varchar2(100) not null   
);

create table file_upload(
ID number(19,0),
FILE_PATH varchar2(255 char) ,
FILENAME varchar2(255 char),
ORIG_FILENAME varchar2(255 char)
);


create sequence spending_seq
start with 1
increment by 1;

create sequence spending_seq
start with 1
increment by 1;

create sequence minus_seq
start with 1
increment by 1;

create sequence diaryuser_seq
start with 1
increment by 1;


create sequence companion_seq
start with 1
increment by 1;

create sequence tplan_seq
start with 1
increment by 1;

create sequence tplanc_seq
start with 1
increment by 1;

create sequence wishlist_seq
start with 1
increment by 1;

alter table diaryvo drop column delete_time;
alter table diaryvo drop column delete_yn;
alter table diaryvo drop column secret_yn;

alter table diaryvo add userid number;
alter table diaryvo add username varchar2;
commit;

drop table tplan;
drop table tplan_c;
drop table wishlist;
drop table tplan_d;
drop table companion;

create table tplan(
    tplan_id int primary key,
    user_id int,
    tplan_start_date Date,
    tplan_end_date Date,
    tplan_name varchar2(50),
    tplan_budget int
);

create table tplan_d(
  id varchar2(20) primary key,
  tplan_id varchar2(20),
  tplan_d_start date,
  tplan_d_contents varchar2(100),
  tplan_note varchar2(100)
);

create table wishlist(
  id varchar2(20) primary key,
  tplan_id varchar2(20) primary key,
  wishlist varchar2(100)
);

create table tplan_c(
  id varchar2(20) primary key,
  tplan_id varchar2(20),
  tplan_c_contents varchar2(100),
  tplan_c_cost int,
  tplan_c_category varchar2(50)
);