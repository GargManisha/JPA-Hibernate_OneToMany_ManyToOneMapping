# JPA-Hibernate_OneToMany_ManyToOneMapping
This project is used to demonstrate the mapping of two tables using JPA/Hibernate with **@OneToMany** and **@ManyToOne** mapping annotation.

For running this application, use **spring boot** version 3.2 and **JAVA** of version 17.
Create tables instructor, instructor_detail, course and review. For their columns, create according to column defination in entity class respectively.

This example will show following mapping techniques. <br> 
**@OneToOne** mapping <br> 
**@OneToMany**, **@ManyToOne** mapping <br> 

For focusing on understanding the implementation of **@OneToMany** mapping, look for review and course tables.
<br> 

By the use of both the annotations, it becomes **bi-directional**.

<br>

In main application file(AdvanceMappingApplication), there are many methods created in commandLineRunner method.
These methods are used to demonstrate CRUD operation in both the tables.

This example not only demonstrate the joining of tables using **_@OneToMany_** and **_@ManyToOne_** mapping, but also shows the use of cascading and fetching.

**Cascading** is use to perform same operations in both the tables.
<br> 
There are different types of cascading.
<br> 
**All** -> it is use to perform all the CRUD operation which is perform on 1 table to be performed on the other table also. <br> 
**Remove** -> If delete operation is performed on 1 table, then it will also be performed on the other table. <br> 
**Persist** -> if save operation is performed on 1 table, then it will also be performed on the other table. <br> 
and many more..

<br>

**FetchMode** defines how Hibernate will fetch the data. <br>
There are two type: Eager and Lazy.

In **eager loading** strategy, if we load one table data, it will also load up all other data associated with it and will store it in a memory.
<br>
In **lazy loading** , if we load one table data, it will not load up other data associated with it into a memory until we make an explicit call to it.

<br>

<br>

**Note:** If you get an error **"filename is too long"** while taking a pull or while committing your code by using command prompt, then use the below command before adding your files to git.
<br>
git config core.logpaths true
