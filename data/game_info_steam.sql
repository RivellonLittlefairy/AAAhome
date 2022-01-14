create table game_info_steam(
id int not null primary key auto_increment,
game_name varchar(200) not null,
price_now int not null,
price_lowest int not null,
coverimage_url text not null,
detail_page text not null,
relase_date varchar(500) not null,
nearly_lowest_timestamp int not null,
mark1 varchar(200) not null default('0'),
mark2 text not null default('empty')
)engine=innodb default charset=utf8;
