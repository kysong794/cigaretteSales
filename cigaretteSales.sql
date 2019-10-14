CREATE table t_product(
p_no number not null PRIMARY KEY,
p_name nvarchar2(50),
p_cnt_sum number default 0,
p_cnt_rem number default 0,
p_price number default 0
);

insert into t_product (p_no,p_name,p_cnt_sum,p_cnt_rem,p_price)
VALUES (1,'말보로 레드',158,5,4500);

insert into t_product (p_no,p_name,p_cnt_sum,p_cnt_rem,p_price)
VALUES (2,'말보로 미디움',142,17,4500);

insert into t_product (p_no,p_name,p_cnt_sum,p_cnt_rem,p_price)
VALUES (3,'말보로 골드',143,10,4500);

insert into t_product (p_no,p_name,p_cnt_sum,p_cnt_rem,p_price)
VALUES (4,'말보로 실버',108,26,4500);

commit;

CREATE table t_sell(
s_no number not null PRIMARY KEY,
p_no number,
s_q number default 0,
s_date date default sysdate
);

insert into t_sell (s_no,p_no,s_q,s_date)
VALUES (1,1,1,'20190724');

insert into t_sell (s_no,p_no,s_q,s_date)
VALUES (2,2,2,'20190724');

insert into t_sell (s_no,p_no,s_q,s_date)
VALUES (3,3,1,'20190724');

insert into t_sell (s_no,p_no,s_q,s_date)
VALUES (4,4,1,'20190724');

CREATE table t_import(
i_no number not null PRIMARY KEY,
p_no number,
i_cnt number default 0,
i_date date default sysdate
);

insert into t_import (i_no,p_no,i_cnt,i_date)
VALUES (1,1,100,'20190720');

insert into t_import (i_no,p_no,i_cnt,i_date)
VALUES (2,1,58,'20190720');

insert into t_import (i_no,p_no,i_cnt,i_date)
VALUES (3,2,142,'20190720');

insert into t_import (i_no,p_no,i_cnt,i_date)
VALUES (4,3,143,'20190721');

CREATE table t_account(
a_id nvarchar2(255) not null PRIMARY KEY,
a_pw NVARCHAR2(255)
);

insert into t_account(a_id,a_pw)
VALUES ('admin','hkit2019');

commit;

select * from t_import;

select * from t_product;

select * from t_sell;


--판매 상품 관리--
select * from t_product
order by p_no desc;

--판매 상품 정정--
UPDATE t_product 
set p_name = '' ,p_price = ''
where p_no = 1;

--판매 상품 삭제--
DELETE t_product
WHERE p_no = 1;

--판매 상품 입고-- 
insert into t_import (i_no,p_no,i_cnt)
VALUES ((select nvl(max(i_no),0)+1 from t_import),1,10);

--입고시 입고수량과 누적입고,잔존수량 셋이 동시에 오름--
UPDATE t_product 
set p_cnt_rem = 1/*i_cnt*/ ,p_cnt_sum = 1/*i_cnt*/
where p_no = 1;

--신규상품 추가--
insert into t_product (p_no,p_name,P_price)
VALUES ((select nvl(max(p_no),0)+1 from t_product),'이름',1000);

--매출 관리--
select to_char(a.i_date,'yyyy-mm-dd')as i_date,
a.i_no,
1 as typ,
a.p_no,
b.p_name,
b.p_cnt_sum,
(a.i_cnt*b.p_price)*-0.8 as totalsum
from t_import a
inner join t_product b on a.p_no = b.p_no
union all
select to_char(a.s_date,('yyyy-mm-dd'))as s_date,
a.s_no,
2 as typ,
a.p_no,
b.p_name,
a.s_q,
(a.s_q*b.p_price) as totalsum
from t_sell a
inner join t_product b on b.p_no = a.p_no;

--신규매출--
insert into t_sell (s_no,p_no,s_q)
VALUES ((select nvl(max(s_no),0)+1 from t_sell),1,10);

--신규매출이 성공시 잔존수량,누적수량 변경--
update t_product
set p_cnt_sum = p_cnt_sum - 10 , p_cnt_rem = p_cnt_rem - 10
where p_no;

select * from t_sell;

delete t_sell
where s_no = 4; 

commit;

insert into t_sell (s_no,p_no,s_q)
VALUES ((select nvl(max(s_no),0)+1 from t_sell),1,1);

--로그인--
select a_pw from t_account
where a_id;
