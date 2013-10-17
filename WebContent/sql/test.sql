select * from DEONGEORI_MEMBER;

select * from deongeori_join_member;

select * from deongeori;

insert into deongeori_invite_message 
values(dng_inv_seq.nextval, 'g1371740447786', 'm1371737784211', 'm1372087437889', 'N', sysdate);

SELECT COUNT(dng_inv_to) 
FROM   deongeori_invite_message 
WHERE  dng_inv_isallow = 'N'    
AND    dng_inv_to = 'm1372087437889'      


select * from free_Board;