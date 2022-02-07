create table detail(
id int not null primary key auto_increment,
gameName varchar(200) not null default(''),
url text not null,
success int not null default(0),
highPrice int not null default(-1),
isDlc int not null default(0),
appraise varchar(50) not null default(''),
comments varchar(200) not null default(''),
DlcNames text not null default(''),
HDCoverImg text not null default(''),
tag varchar(1000) not null default(''),
HDImg text not null default(''),
mark1 varchar(200) not null default('0'),
mark2 text not null default('empty')
)engine=innodb default charset=utf8;
