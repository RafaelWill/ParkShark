create table members(

   member_id  integer generated by default as identity
        constraint members_pkey
            primary key,
    first_name varchar(255),
    last_name varchar(255),
   address_id integer
       constraint fk_members_to_address
           references addresses,
   phone_number varchar(255),
   plate_number varchar(255),
   issuing_country varchar(255),
   registration_date date,
   account_id integer
                    constraint fk_members_to_accounts
                    references accounts
);

alter table members owner to student;