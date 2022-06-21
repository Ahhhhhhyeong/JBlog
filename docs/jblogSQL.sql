-- scheme
show tables;
desc user;
desc blog;
desc category;
desc post;


select * from user;

	-- WEHRE b.id IS NULL;
    
select * From blog;

select * from user;
select * from category;
select * from post;


SELECT id, name
	FROM user
WHERE id = 'dkdud111@gmail.com'
	AND password = '1234';
    
    
SELECT no, name, description
 FROM category	
where blog_id = 'admin';


select a.no, a.title,a.contents, a.category_no, a.reg_date
	from post a left join category b on a.category_no = b.no
where b.blog_id = 'admin'
order by a.no desc limit 1;

 SELECT no, title, reg_date, category_no
				FROM post
			WHERE category_no = 2
				ORDER BY no desc;
                
 SELECT a.no, a.title, a.contents, a.reg_date, a.category_no FROM post  a WHERE a.category_no  = 2 ORDER BY a.no desc limit 1