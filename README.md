﻿# jpa-advanced

<b>@OneToOne</b> & <b>@ManyToOne</b> relationships default to eagerly fetched<br/>
<b>@OneToMany</b> & <b>@ManyToMany</b> relationships default to lazily fetched<br/>
<b>PersistenceContext</b> acts as a storage place during a transaction. It is killed when the transaction finishes and everything has been written to the DB.<br/>
<b>em.flush()</b> writes to the DB, but if something still went wrong afterwards within the same transaction Hibernate would roll everything back.<br/>

# Inheritance Types
1. <b>SINGLE TABLE</b>: Everything is the same table, but with columns that will be nullable. There is a Discriminator column which will distinguish which type of entity is inserted in that row. Default Discriminator column name is `DTYPE`. Best option to go for if data integrity is not an issue, but performance is.
2. <b>TABLE PER CLASS</b>: Each concrete class has its own table. Common columns are repeated across all tables. A union SQL is performed to retrieved the data no matter if you're interested in 1 row or all rows.
3. <b>JOINED</b>: There are separate tables for each discriminating type, and a join is performed to get the details. Best option to go for if data integrity is very important, but speed is not.
4. <b>MAPPED SUPER CLASS</b>: It's as if there is no inheritance relationship between the very similar classes. Each class gets their own table. The super class can't be an entity and only serves to store common fields.

# Transactions
## ACID
A - Atomicity<br/>
C - Consistency<br/>
I - Isolation<br/>
D - Durability<br/>

## Isolation
### Problems
1. <b>Dirty Read</b>: Reading a record of another transaction that hasn't completed yet.
2. <b>Non Repeatable Read</b>: Getting back different values for the same select statement at different times in the same transaction. IOW Someone else changed the record.
3. <b>Phantom Read</b>: Getting back a different set of records for the same select statement at different times in the same transaction. IOW Someone else inserted/removed a record.

### Levels of Isolation
1. <b>Uncommitted Read</b>: Any transaction can read any update row before it is committed. This isn't solving any of the above problems.
2. <b>Committed Read</b>: Outside transactions can only read the updated value after the transaction has completed. This solves `Dirty Read`. Locks the modified value being changed from even being read.
3. <b>Repeatable Read</b>: This locks the rows being read by a transaction. This solves `Dirty Read` & `Non Repeatable Read`.
4. <b>Serializable</b>: This locks the entire row that matches the select statement's constraint until the transaction commits. IOW If you do a `select *` then the entire table is locked. You will not be able to insert into the table that matches the select statement's criteria.

### @Transactional
<b>JPA Transaction Management:</b> Can handle a transaction to 1 DB.</br>
<b>Spring Transaction Management: </b> Can handle a transaction across multiple DBs.
