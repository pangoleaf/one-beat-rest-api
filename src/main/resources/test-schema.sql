create table album (id integer generated by default as identity, length integer, name varchar(255), year integer, artist_id integer, primary key (id))
create table artist (id integer generated by default as identity, country varchar(255), end_year integer, name varchar(255), start_year integer, primary key (id))
create table track (id integer generated by default as identity, length integer, name varchar(255), track_number integer, album_id integer, primary key (id))