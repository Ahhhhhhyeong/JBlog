-- scheme
show tables;
desc user;
desc blog;
desc category;
desc post;


select * from user;


INSERT INTO blog (id, title, logo) 
	SELECT a.id, concat(a.name, '의 블로그') as title, ''
FROM user a LEFT JOIN blog b on a.id = b.id;
	-- WEHRE b.id IS NULL;
    
select * From blog;
select * from user;
select * from category;


SELECT id, name
	FROM user
WHERE id = 'dkdud111@gmail.com'
	AND password = '1234';