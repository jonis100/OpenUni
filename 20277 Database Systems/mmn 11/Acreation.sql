create table inspector
(Iid int,
Iname varchar(20),
Bdate date,
Sdate date,
Mobile numeric(9,0),
primary key (Iid)
);

create table restaurant
(Lno int,
Rname varchar(20),
Address varchar(20),
City varchar(10),
Phone numeric(9,0),
Odate date,
Mid int,
check (Mid<>NULL),
 primary key (Lno)
);
 
 create table manager
 (Mid int,
  Mname varchar(20),
  Address varchar(20),
  City varchar(10),
  Mobile numeric (9,0),
  Email varchar(20),
  primary key(Mid)
 );
 
 create table inspection(
 Lno int,
 Idate date,
 Iid int,
 Stime time,
 Passed int,
 Violations varchar(100),
 check (passed >= 0 and passed < 2),	 
 primary key(Lno, Idate),
 foreign key (Lno) references restaurant 	 
 );