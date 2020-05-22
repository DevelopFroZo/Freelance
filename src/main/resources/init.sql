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
insert into users( name, login, password, registeredAt, description, balance ) values ( 'Test', 'Test', '123456', 'now()', 'Description', 10000 );

-- Add executor rating

-- Add tasks

-- Add task docs

-- Add solutions

-- Add solution docs
