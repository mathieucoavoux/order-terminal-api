delete from order_terminal where order_id=1;
delete from terminal where terminal_id=202301012;
delete from customer where customer_id=123456;
insert into customer (customer_id,title,firstname,lastname,email,address,zipcode) values ('123456','Mrs','Marie','Curie','marie.curie@email.org','1 rue de la Science, Paris','74190');
insert into terminal (terminal_id,manufacturer,model,version,serial_number) values('202301012','Cisco','VR1000','v1','SN-2022-213511');
insert into order_terminal (order_id,order_timestamp,price,customer_id) values(1,NOW(),200.00,123456);