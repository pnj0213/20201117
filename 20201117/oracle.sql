CREATE TABLE board(
   no number(10) primary key,
   title varchar2(100),
   content varchar2(500),
   author varchar2(100),
   nal varchar2(10),
   readcount number(10)
  
)
create sequence board_no
select * from board
