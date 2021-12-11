create database Student_Management;
use Student_Management;

create table Add_Student(
Admission_NO int identity(1, 1) Primary key,
FullName varchar(50),
Date_Of_Birth datetime,
Gender varchar(10) constraint chkGen check (Gender IN ('Male', 'Female')),
Grade varchar(10) constraint chkGrd check (Grade IN('JSS1', 'JSS2', 'JSS3', 'SS1', 'SS2', 'SS3')),
Contact_Number nvarchar(20) constraint chkNum check(Contact_Number like('[+][2][3][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')),
Contact_Address varchar(100),
)

select * from Add_Student
insert into Add_Student(FullName, Date_Of_Birth, Gender, Grade, Contact_Number, Contact_Address)
insert into dbo.Add_Student values(
	'Ibrahim Musa',
	getdate(),
	'Male',
	'JSS1',
	'+231234567890',
	'klhjldkhjlikfhsolfhjdlslkfjdkols');

create table Registration_Form (
FullName varchar(50),
Administrator_ID nvarchar(30),
Contact_Number nvarchar(30),
Email nvarchar(30),
User_Password nvarchar(30)
constraint pkUsrId primary key clustered (Administrator_ID Asc)
)

Insert into Registration_Form values (
	'Iliya Ikasi',
	12345,
	0987654321,
	'iliyaikasi@gmail.com',
	'password'
)
select * from Registration_Form

create table User_login(
Administrator_ID int identity(1, 1) not null,
User_Password nvarchar(30) not null
constraint fkAdmId foreign key (Administrator_ID Asc)
)

