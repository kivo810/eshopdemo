insert into product (name, description, price, available) values ( 'BMW', 'red', 12.40, 55 );
insert into product (name, description, price, available) values ( 'Audi', 'blue', 65.7, 67 );
insert into product (name, description, price, available) values ( 'Volvo', 'gray', 999.57, 1054056 );
insert into product (name, description, price, available) values ( 'Fiat', 'wg', 12.00, 22 );

insert into shop_order (address, card_number, completed_at, customer_id, email, final_price, name, age) values ( '87 New St', '7878787979877', CURRENT_TIMESTAMP() , 2, 'new@new.nw', 47.88, 'New One', 19  );
insert into shop_order (address, card_number, completed_at, customer_id, email, final_price, name, age) values ( '10 down st', '46231564', current_timestamp() , 2, 'nm@new.po', 45.77, 'Old One', 55  );
insert into shop_order (address, card_number, completed_at, customer_id, email, final_price, name, age) values ( 'no where', '65849856156', current_timestamp() , 56, 'new@po.sd', 1.38, 'New This', 100  );

insert into SHOP_ORDER_PRODUCT_LIST (shop_order_id, product_list_id) values ( 1, 3 );
insert into SHOP_ORDER_PRODUCT_LIST (shop_order_id, product_list_id) values ( 1, 2 );
insert into SHOP_ORDER_PRODUCT_LIST (shop_order_id, product_list_id) values ( 2, 4 );