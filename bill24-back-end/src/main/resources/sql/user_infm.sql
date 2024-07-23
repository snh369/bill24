create table user_infm (
   usr_id serial not null,
   act_yn varchar(255),
   eml varchar(255) unique,
   lock_yn varchar(255),
   lst_lgn_dtm varchar(255),
   mod_dt varchar(255),
   mod_id varchar(255),
   reg_dt varchar(255),
   reg_id varchar(255),
   tel varchar(255),
   usr_img varchar(255),
   usr_nm varchar(255),
   usr_pw varchar(255),
   primary key (usr_id)
)