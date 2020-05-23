-- Add categories
insert into categories( name ) values ( 'Programming' );    -- 1
insert into categories( name ) values ( 'Design' );         -- 2
insert into categories( name ) values ( 'Art' );            -- 3
insert into categories( name ) values ( 'Engineering' );    -- 4
insert into categories( name ) values ( '3D-modeling' );    -- 5
insert into categories( name ) values ( 'Copyright' );      -- 6

-- Add payment methods
insert into payment_methods( name ) values ( 'Cash' );  -- 1
insert into payment_methods( name ) values ( 'Card' );  -- 2

-- Add users
-- 1
insert into users( name, login, password, registeredAt, description, balance, role ) values ( 'FroZo', 'FroZo', '123456', '2020-05-22', 'Description of user', 0, 'ROLE_USER' );
-- 2
insert into users( name, login, password, registeredAt, description, balance, role ) values ( 'Jack', 'Jack', '654321', '2020-05-22', 'Super Jack!', 0, 'ROLE_USER' );
-- 3
insert into users( name, login, password, registeredAt, description, balance, role ) values ( 'Daniel', 'daniel_user', 'password', '2020-05-22', 'Simple user', 0, 'ROLE_USER' );
-- 4
insert into users( name, login, password, registeredAt, description, balance, role ) values ( 'Michael', 'Jackson', 'Jackson', '2020-05-22', 'Another user', 0, 'ROLE_USER' );
-- 5
insert into users( name, login, password, registeredAt, description, balance, role ) values ( 'Super', 'hola', 'my_password', '2020-05-22', 'A description...', 0, 'ROLE_USER' );
-- 6
insert into users( name, login, password, registeredAt, description, balance, role ) values ( 'Last', 'qwerty', 'qwerty', '2020-05-22', 'Qwerty!', 0, 'ROLE_USER' );

-- Add customer rating
insert into user_customer_rating_values( user_id, customerRating ) values ( 1, 5 ), ( 1, 4 ), ( 1, 5 ), ( 1, 3 ), ( 1, 5 );
insert into user_customer_rating_values( user_id, customerRating ) values ( 2, 4 ), ( 2, 4 ), ( 2, 3 );
insert into user_customer_rating_values( user_id, customerRating ) values ( 3, 3 ), ( 3, 3 ), ( 3, 2 ), ( 3, 2 ), ( 3, 4 );
insert into user_customer_rating_values( user_id, customerRating ) values ( 4, 4 ), ( 4, 3 ), ( 4, 4 ), ( 4, 3 ), ( 4, 4 );

-- Add executor rating
insert into executor_ratings( user_id, category_id ) values ( 1, 1 ), ( 1, 2 ), ( 1, 4 );
insert into executor_ratings_values( ExecutorRating_user_id, ExecutorRating_category_id, rating ) values ( 1, 1, 5 ), ( 1, 1, 5 ), ( 1, 1, 5 );
insert into executor_ratings_values( ExecutorRating_user_id, ExecutorRating_category_id, rating ) values ( 1, 2, 4 ), ( 1, 2, 3 ), ( 1, 2, 2 );
insert into executor_ratings_values( ExecutorRating_user_id, ExecutorRating_category_id, rating ) values ( 1, 4, 4 ), ( 1, 4, 4 ), ( 1, 4, 5 );

insert into executor_ratings( user_id, category_id ) values ( 2, 3 ), ( 2, 5 );
insert into executor_ratings_values( ExecutorRating_user_id, ExecutorRating_category_id, rating ) values ( 2, 3, 4 ), ( 2, 3, 3 );
insert into executor_ratings_values( ExecutorRating_user_id, ExecutorRating_category_id, rating ) values ( 2, 5, 4 ), ( 2, 5, 5 );

insert into executor_ratings( user_id, category_id ) values ( 5, 2 ), ( 5, 5 ), ( 5, 6 );
insert into executor_ratings_values( ExecutorRating_user_id, ExecutorRating_category_id, rating ) values ( 5, 2, 3 ), ( 5, 2, 4 ), ( 5, 2, 4 );
insert into executor_ratings_values( ExecutorRating_user_id, ExecutorRating_category_id, rating ) values ( 5, 5, 4 ), ( 5, 5, 5 ), ( 5, 5, 4 );
insert into executor_ratings_values( ExecutorRating_user_id, ExecutorRating_category_id, rating ) values ( 5, 6, 2 ), ( 5, 6, 3 ), ( 5, 6, 3 );

-- Add tasks
-- 1
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Super task', 'A very simple task', 1, 5000, '2020-05-22', '2020-12-31', 2, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Simple task', 'Very-very simple', 1, 500, '2020-05-22', '2020-12-31', 2, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Hello there!', 'No desc...', 1, 1000, '2020-05-20', '2020-12-31', 1, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Program this', 'Not a ver easy', 2, 10000, '2020-04-15', '2020-12-31', 2, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'I pay for this', 'I promise!', 3, 500000, '2020-05-10', '2020-12-31', 1, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Happy new year', 'Jingle bells...', 3, 500, '2020-04-22', '2020-12-31', 2, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Good task', 'Yeah', 2, 9000, '2020-05-20', '2020-12-31', 1, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Give me a fantasy', 'Please', 4, 1, '2020-01-15', '2020-12-31', 2, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Dream task', 'Dream team', 4, 12345, '2020-02-01', '2020-12-31', 1, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Make stackoverflow', 'Please', 3, 1000000, '2020-04-05', '2020-12-31', 1, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'I need a social network', 'Right now!', 4, 10000000, '2020-05-21', '2020-12-31', 2, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Check this', 'Or ahead', 2, 9999, '2020-05-22', '2020-12-31', 1, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Complete me!', 'Very long description', 4, 999, '2020-04-07', '2020-12-31', 2, 'PROCESS' );
insert into tasks( name, description, customer_id, budget, createdAt, deadline, paymentMethod_id, status ) values ( 'Need a executor...', 'Very need', 1, 5000, '2020-05-01', '2020-12-31', 1, 'PROCESS' );

-- Add task categories
insert into tasks_categories( task_id, category_id ) values ( 1, 1 ), ( 1, 2 ), ( 1, 4 );
insert into tasks_categories( task_id, category_id ) values ( 2, 3 ), ( 2, 6 );
insert into tasks_categories( task_id, category_id ) values ( 3, 3 ), ( 3, 4 ), ( 3, 5 );
insert into tasks_categories( task_id, category_id ) values ( 4, 1 ), ( 4, 2 ), ( 4, 4 );
insert into tasks_categories( task_id, category_id ) values ( 5, 3 ), ( 5, 5 );
insert into tasks_categories( task_id, category_id ) values ( 6, 1 ), ( 6, 5 ), ( 6, 6 );
insert into tasks_categories( task_id, category_id ) values ( 7, 2 ), ( 7, 3 ), ( 7, 6 );
insert into tasks_categories( task_id, category_id ) values ( 8, 2 ), ( 8, 5 ), ( 8, 6 );
insert into tasks_categories( task_id, category_id ) values ( 9, 3 ), ( 9, 6 );
insert into tasks_categories( task_id, category_id ) values ( 10, 1 ), ( 10, 2 ), ( 10, 4 );
insert into tasks_categories( task_id, category_id ) values ( 11, 1 );
insert into tasks_categories( task_id, category_id ) values ( 12, 6 );
insert into tasks_categories( task_id, category_id ) values ( 13, 2 ), ( 13, 3 ), ( 13, 4 );
insert into tasks_categories( task_id, category_id ) values ( 14, 2 ), ( 14, 5 );

-- Add links
insert into task_links( task_id, links ) values ( 1, 'https://github.com/developfrozo' );
insert into task_links( task_id, links ) values ( 2, 'https://ya.ru' ), ( 2, 'https://github.com' );
insert into task_links( task_id, links ) values ( 3, 'https://google.com' );
insert into task_links( task_id, links ) values ( 4, 'https://vk.com' ), ( 4, 'http://domic.isu.ru' );
insert into task_links( task_id, links ) values ( 7, 'https://educa.isu.ru' );
insert into task_links( task_id, links ) values ( 8, 'https://medium.org' ), ( 8, 'https://wikipedia.org' );
insert into task_links( task_id, links ) values ( 9, 'https://stackoverflow.com' ), ( 9, 'https://google.com' );
insert into task_links( task_id, links ) values ( 11, 'http://domic.isu.ru' );
insert into task_links( task_id, links ) values ( 13, 'https://github.com' ), ( 13, 'https://wikipedia.org' );
insert into task_links( task_id, links ) values ( 14, 'https://educa.isu.ru' ), ( 14, 'http://domic.isu.ru' );

-- Add docs

-- Add solutions
