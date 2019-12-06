--select * from course;
delete from review;
delete from course;
delete from student;
delete from passport;
delete from student_course;

insert into course (id, name, created_date, updated_date) values (10001, 'JPA in 50 steps', sysdate(), null);
insert into course (id, name, created_date, updated_date) values (10002, 'Springboot has you beat', sysdate(), null);
insert into course (id, name, created_date, updated_date) values (10003, 'Springboot in steps', sysdate(), null);
insert into course (id, name, created_date, updated_date) values (10004, 'Steps with boots', sysdate(), null);

insert into passport (id, number) values (30001, 'E654321');
insert into passport (id, number) values (30002, 'P123456');
insert into passport (id, number) values (30003, 'Q123456');

insert into student (id, first_name, last_name) values (20001, 'Jarrod', 'Kallis');
insert into student (id, first_name, last_name, passport_id) values (20002, 'Tessa', 'Kallis', 30002);
insert into student (id, first_name, last_name, passport_id) values (20003, 'Ben', 'Kallis', 30003);

update student set passport_id = 30001 where id = 20001;

insert into student_course (student_id, course_id) values (20001, 10001);
insert into student_course (student_id, course_id) values (20002, 10001);
insert into student_course (student_id, course_id) values (20003, 10001);
insert into student_course (student_id, course_id) values (20001, 10003);
insert into student_course (student_id, course_id) values (20001, 10002);
insert into student_course (student_id, course_id) values (20002, 10002);

insert into review (id, rating, description, course_id, student_id) values (40001, '4.5', 'WOW!', 10001, 20001);
insert into review (id, rating, description, course_id, student_id) values (40002, '2', 'Not so WOW!', 10001, 20002);
insert into review (id, rating, description, course_id, student_id) values (40003, '3', null, 10003, 20001);
