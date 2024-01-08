-- some constraint names were too long to follow format in instructions so we had to shorten some of them, we were given an error for it
drop table csEmployeeTitle;
drop table csEmployeePhoneExt;
drop table csEmployee;
drop table Title;
drop table orderedList;
drop table Hallway cascade constraints;
drop table Office cascade constraints;
drop table Edge;
drop table Location cascade constraints;
drop table Path;

create table Location (
    locationID varchar2(40),
    locationName varchar2(40),
    locationType varchar2(40) NOT NUll,
    xcoord number(5),
    ycoord number(5),
    floor varchar2(5) NOT NULL,
    Constraint Location_locationID_PK Primary Key(locationID),
    Constraint Location_xycoord_floor_UK Unique(xcoord, ycoord, floor),
    Constraint Location_floor check (floor in ('1', '2', '3', 'A', 'B')),
    Constraint Location_locationType check (locationType in ('Office', 'Staircase', 'Hallway', 'Elevator', 'Conference Room', 'Lecture Hall')));

-- we have this right now but do not need it because csEmployee does not need to refer to it anymore because Professor Taneja said an employee can be assigned to an office that does not exist until later in phase 2
create table Office(
    locationID varchar2(40),
    Constraint Office_locationID_PK Primary Key (locationID),
    Constraint Office_locationID_FK Foreign Key (locationID) References Location (locationID)
    On Delete Cascade);
    

create table Hallway (
    locationID varchar2(40),
    description varchar2(40),
    Constraint Hallway_locationID_PK Primary Key(locationID),
    Constraint Hallway_locationID_FK Foreign Key (locationID) References Location (locationID)
    On Delete Cascade);

-- no foreign key to Location because professor Taneja said that a csEmployee can be assigned to an office that does not exist
-- locationID is still there for csEmployee(s) that are assigned to an office that exists
create Table csEmployee (
    firstName varchar2(40) NOT NULL,
    lastName varchar2(40) NOT NULL,
    accountName varchar2(40),
    locationID varchar2(40),
    Constraint csEmployee_accountName_PK Primary Key (accountName));
  --  Constraint csEmployee_locationID_FK Foreign Key (locationID) References Office (locationID)); We will include this when we get full data for phase 2. Right now, it is not applicable because some employees are assigned to Offices that do not exist yet.
    
create Table Title (
    acronym varchar2(40),
    name varchar2(50) NOT NULL,
    Constraint Title_acronym_PK Primary Key (acronym),
    Constraint Title_name_UK Unique (name));
    
create Table csEmployeeTitle (
    accountName varchar2(40),
    acronym varchar2(40),
    Constraint csEmployeeTitle_accNameacro_PK Primary Key (accountName, acronym),
    Constraint csEmployeeTitle_accountName_FK Foreign Key (accountName) References csEmployee (accountName)
    On Delete Cascade,
    Constraint csEmployeeTitle_acronym_FK Foreign Key (acronym) References Title (acronym)
    On Delete Cascade );

create Table csEmployeePhoneExt (
    accountName varchar2(40),
    phoneExtension number(5),
    Constraint csEmployeePhoneExt_accphne_PK Primary Key (accountName, phoneExtension),
    Constraint csEmployeePhoneExt_actName_FK Foreign Key (accountName) References csEmployee (accountName)
    On Delete Cascade);
    
 create Table Edge (
    startingLocation varchar2(40),
    endLocation varchar2(40),
    Constraint Edge_startendLoc_PK Primary Key (startingLocation, endLocation),
    Constraint Edge_startLoc_FK Foreign Key (startingLocation) References Location (locationID)
    On Delete Cascade,
    Constraint Edge_endLoc_FK Foreign Key (endLocation) References Location (locationID)
    On Delete Cascade);
    
create Table Path (
    pathID number(5),
    Constraint Path_pathID_PK Primary Key (pathID));
    
-- orderNumber is based on assumption that user manually enters order correctly    
create Table orderedList (
    pathID number(5),
    locationID varchar2(40),
    orderNumber number(5) NOT NULL,
    Constraint orderedList_pathlocaID_PK Primary Key (pathID, locationID),
    Constraint orderedList_pathID_FK Foreign Key (pathID) References Path (pathID)
    On Delete Cascade,
    Constraint orderedList_locationID_FK Foreign Key (locationID) References Location (locationID)
    On Delete Cascade );
    
-- Location Data 
insert into Location values('307', 'FL307', 'Office', 900, 440, '3');
insert into Location values('308', 'FL308', 'Office', 900, 335, '3');
insert into Location values('311', 'FL311', 'Conference Room', 900, 375, '3');
insert into Location values('312', 'FL312', 'Office', 945, 510, '3');
insert into Location values('314', 'FL314', 'Office', 845, 660, '3');
insert into Location values('316', 'FL316', 'Office', 845, 760, '3');
insert into Location values('317', 'FL317', 'Office', 925, 670, '3');
insert into Location values('318', 'FL318', 'Office', 950, 700, '3');
insert into Location values('319', 'FL319', 'Office', 925, 735, '3');
insert into Location values('320', 'FL320', 'Lecture Hall', 900, 920, '3');
insert into Location values('3E1', '3rd Floor Elevator', 'Elevator', 820, 415, '3');
insert into Location values('3H1', 'FL3H1', 'Hallway', 790, 150, '3');
insert into Location values('3H2', 'FL3H2', 'Hallway', 790, 340, '3');
insert into Location values('3H3', 'FL3H3', 'Hallway', 790, 375, '3');
insert into Location values('3H4', 'FL3H4', 'Hallway', 790, 420, '3');
insert into Location values('3H5', 'FL3H5', 'Hallway', 790, 465, '3');
insert into Location values('3H6', 'FL3H6', 'Hallway', 900, 465, '3');
insert into Location values('3H7', 'FL3H7', 'Hallway', 900, 510, '3');
insert into Location values('3H8', 'FL3H8', 'Hallway', 790, 660, '3');
insert into Location values('3H9', 'FL3H9', 'Hallway', 790, 700, '3');
insert into Location values('3H10', 'FL3H10', 'Hallway', 925, 700, '3');
insert into Location values('3H11', 'FL3H11', 'Hallway', 790, 755, '3');
insert into Location values('3H12', 'FL3H12', 'Hallway', 790, 925, '3');
insert into Location values('3H13', 'FL3H13', 'Hallway', 840, 920, '3');
insert into Location values('3S1', '3rd Floor Staircase 1', 'Staircase', 835, 340, '3');
insert into Location values('3S2', '3rd Floor Staircase 2', 'Staircase', 840, 965, '3');

-- csEmployee Data
insert into csEmployee values('Carolina', 'Ruiz', 'ruiz', '232');	
insert into csEmployee values('Charles', 'Rich', 'rich', 'B25b');					
insert into csEmployee values('Christine', 'Caron', 'ccaron', '233');					
insert into csEmployee values('Craig', 'Shue', 'cshue', '236');					
insert into csEmployee values('Craig', 'Wills', 'cew', '234');					
insert into csEmployee values('Daniel', 'Dougherty', 'dd', '231');					
insert into csEmployee values('Douglas', 'Selent', 'deselent', '144');					
insert into csEmployee values('Elke', 'Rundensteiner', 'rundenst', '135');					
insert into csEmployee values('Emmanuel', 'Agu', 'emmanuel', '139');					
insert into csEmployee values('George', 'Heineman', 'heineman', 'B20');					
insert into csEmployee values('Glynis', 'Hamel', 'ghamel', '132');					
insert into csEmployee values('Hugh', 'Lauer', 'lauer', '144');					
insert into csEmployee values('John', 'Leveillee', 'jleveillee', '244');					
insert into csEmployee values('Joseph', 'Beck', 'josephbeck', '138');					
insert into csEmployee values('Kathryn', 'Fisler', 'kfisler', '130');					
insert into csEmployee values('Krishna', 'Venkatasubramanian', 'kven', '137');					
insert into csEmployee values('Mark', 'Claypool', 'claypool', 'B24');					
insert into csEmployee values('Micha', 'Hofri', 'hofri', '133');					 
insert into csEmployee values('Michael', 'Ciaraldi', 'ciaraldi', '129');					
insert into csEmployee values('Michael', 'Voorhis', 'mvoorhis', '245');					
insert into csEmployee values('Mohamed', 'Eltabakh', 'meltabakh', '235');					
insert into csEmployee values('Neil', 'Heffernan', 'nth', '237');					
insert into csEmployee values('Nicole', 'Caligiuri', 'nkcaligiuri', '233');					
insert into csEmployee values('Refie', 'Cane', 'rcane', '233');					
insert into csEmployee values('Thomas', 'Gannon', 'tgannon', '243');					
insert into csEmployee values('Wilson', 'Wong', 'wwong2', 'B19');									

-- Title Data
insert into Title values('Adj Assoc Prof', 'Adjunct Associate Professor');
insert into Title values('Admin 5', 'Administrative Assistant V');
insert into Title values('Admin 6', 'Administrative Assistant VI');
insert into Title values('Asst Prof', 'Assistant Professor');
insert into Title values('Asst TProf', 'Assistant Teaching Professor');
insert into Title values('Assoc Prof', 'Associate Professor');
insert into Title values('C-MGRG', 'Coordinator of Mobile Graphics Research Group');
insert into Title values('DeptHead', 'Department Head');
insert into Title values('Dir-DS', 'Director of Data Science');
insert into Title values('Dir-LST', 'Director of Learn Sciences and Technologies');
insert into Title values('GradAdmin', 'Graduate Admin Coordinator');
insert into Title values('Lab1', 'Lab Manager I');
insert into Title values('Lab2', 'Lab Manager II');
insert into Title values('Prof', 'Professor');
insert into Title values('SrInst', 'Senior Instructor');
insert into Title values('TProf', 'Teaching Professor');

-- csEmployeeTitle Data
insert into csEmployeeTitle values('ruiz', 'Assoc Prof');
insert into csEmployeeTitle values('rich', 'Prof');
insert into csEmployeeTitle values('ccaron', 'Admin 6');
insert into csEmployeeTitle values('cshue', 'Asst Prof');
insert into csEmployeeTitle values('cew', 'Prof');
insert into csEmployeeTitle values('dd', 'Prof');
insert into csEmployeeTitle values('deselent', 'Asst TProf');
insert into csEmployeeTitle values('rundenst', 'Prof');
insert into csEmployeeTitle values('emmanuel', 'Assoc Prof');
insert into csEmployeeTitle values('heineman', 'Assoc Prof');
insert into csEmployeeTitle values('ghamel', 'SrInst');
insert into csEmployeeTitle values('lauer', 'TProf');
insert into csEmployeeTitle values('jleveillee', 'Lab1');
insert into csEmployeeTitle values('josephbeck', 'Assoc Prof');
insert into csEmployeeTitle values('kfisler', 'Prof');
insert into csEmployeeTitle values('kven', 'Asst Prof');
insert into csEmployeeTitle values('claypool', 'Prof');
insert into csEmployeeTitle values('hofri', 'Prof');
insert into csEmployeeTitle values('ciaraldi', 'SrInst');
insert into csEmployeeTitle values('mvoorhis', 'Lab2');
insert into csEmployeeTitle values('meltabakh', 'Assoc Prof');
insert into csEmployeeTitle values('nth', 'Prof');
insert into csEmployeeTitle values('nkcaligiuri', 'Admin 5');
insert into csEmployeeTitle values('rcane', 'GradAdmin');
insert into csEmployeeTitle values('tgannon', 'Adj Assoc Prof');
insert into csEmployeeTitle values('wwong2', 'Asst Prof');
insert into csEmployeeTitle values('cew', 'DeptHead');
insert into csEmployeeTitle values('rundenst', 'Dir-DS');
insert into csEmployeeTitle values('emmanuel', 'C-MGRG');
insert into csEmployeeTitle values('nth', 'Dir-LST');

--csEmployeePhoneExt Data
insert into csEmployeePhoneExt values('ruiz', 5640);
insert into csEmployeePhoneExt values('rich', 5945);
insert into csEmployeePhoneExt values('ccaron', 5678);
insert into csEmployeePhoneExt values('cshue', 4933);
insert into csEmployeePhoneExt values('cew', 5357);
insert into csEmployeePhoneExt values('dd', 5621);
insert into csEmployeePhoneExt values('deselent', 5493);
insert into csEmployeePhoneExt values('rundenst', 5815);
insert into csEmployeePhoneExt values('emmanuel', 5568);
insert into csEmployeePhoneExt values('heineman', 5502);
insert into csEmployeePhoneExt values('ghamel', 5252);
insert into csEmployeePhoneExt values('lauer', 5493);
insert into csEmployeePhoneExt values('jleveillee', 5822);
insert into csEmployeePhoneExt values('josephbeck', 6156);
insert into csEmployeePhoneExt values('kfisler', 5118);
insert into csEmployeePhoneExt values('kven', 6571);
insert into csEmployeePhoneExt values('claypool', 5409);
insert into csEmployeePhoneExt values('hofri', 6911);
insert into csEmployeePhoneExt values('ciaraldi', 5117);
insert into csEmployeePhoneExt values('mvoorhis', 5669);
insert into csEmployeePhoneExt values('meltabakh', 6421);
insert into csEmployeePhoneExt values('nth', 5569);
insert into csEmployeePhoneExt values('nkcaligiuri', 5357);
insert into csEmployeePhoneExt values('rcane', 5357);
insert into csEmployeePhoneExt values('tgannon', 5357);
insert into csEmployeePhoneExt values('wwong2', 5706);
insert into csEmployeePhoneExt values('cew', 5622);
insert into csEmployeePhoneExt values('mvoorhis', 5674);

--Office Data
insert into Office values('307');
insert into Office values('308');
insert into Office values('312');
insert into Office values('314');
insert into Office values('316');
insert into Office values('317');
insert into Office values('318');
insert into Office values('319');

-- Edge Data
insert into Edge values('3H1', '3H2');		
insert into Edge values('3H2', '3S1');		
insert into Edge values('3H2', '3H3');		
insert into Edge values('3H3', '311');		
insert into Edge values('311', '308');		
insert into Edge values('3H3', '3H4');		
insert into Edge values('3H4', '3E1');		
insert into Edge values('3H4', '3H5');		
insert into Edge values('3H5', '3H6');		
insert into Edge values('3H6', '307');		
insert into Edge values('3H6', '3H7');		
insert into Edge values('3H7', '312');		
insert into Edge values('3H5', '3H8');		
insert into Edge values('3H8', '314');		
insert into Edge values('3H8', '3H9');		
insert into Edge values('3H9', '3H10');		
insert into Edge values('3H10', '317');		
insert into Edge values('3H10', '318');		
insert into Edge values('3H10', '319');		
insert into Edge values('3H9', '3H11');		
insert into Edge values('3H11', '316');		
insert into Edge values('3H11', '3H12');		
insert into Edge values('3H12', '3H13');		
insert into Edge values('3H13', '320');		
insert into Edge values('3H13', '3S2');		
insert into Edge values('3H2', '3H1');
insert into Edge values('3S1', '3H2');
insert into Edge values('3H3', '3H2');
insert into Edge values('311', '3H3');
insert into Edge values('308', '311');
insert into Edge values('3H4', '3H3');
insert into Edge values('3E1', '3H4');
insert into Edge values('3H5', '3H4');
insert into Edge values('3H6', '3H5');
insert into Edge values('307', '3H6');
insert into Edge values('3H7', '3H6');
insert into Edge values('312', '3H7');
insert into Edge values('3H8', '3H5');
insert into Edge values('314', '3H8');
insert into Edge values('3H9', '3H8');
insert into Edge values('3H10', '3H9');
insert into Edge values('317', '3H10');
insert into Edge values('318', '3H10');
insert into Edge values('319', '3H10');
insert into Edge values('3H11', '3H9');
insert into Edge values('316', '3H11');
insert into Edge values('3H12', '3H11');
insert into Edge values('3H13', '3H12');
insert into Edge values('320', '3H13');
insert into Edge values('3S2', '3H13');

-- Path Data
insert into Path values(1);
insert into Path values(2);
insert into Path values (3);

-- OrderedList Data
-- path from elevator 1 to 320
insert into orderedList values(1, '3E1', 1);
insert into orderedList values(1, '3H4', 2);
insert into orderedList values(1, '3H5', 3);
insert into orderedList values(1, '3H8', 4);
insert into orderedList values(1, '3H9', 5);
insert into orderedList values(1, '3H11', 6);
insert into orderedList values(1, '3H12', 7);
insert into orderedList values(1, '3H13', 8);
insert into orderedList values(1, '320', 9);
--  path from 312 to 319
insert into orderedList values(2, '312', 1);
insert into orderedList values(2, '3H7', 2);
insert into orderedList values(2, '3H6', 3);
insert into orderedList values(2, '3H5', 4);
insert into orderedList values(2, '3H8', 5);
insert into orderedList values(2, '3H9', 6);
insert into orderedList values(2, '3H10', 7);
insert into orderedList values(2, '319', 8);
-- path from bottom stairs S2 to 308
insert into orderedList values(3, '3S2', 1);
insert into orderedList values(3, '3H13', 2);
insert into orderedList values(3, '3H12', 3);
insert into orderedList values(3, '3H11', 4);
insert into orderedList values(3, '3H9', 5);
insert into orderedList values(3, '3H8', 6);
insert into orderedList values(3, '3H5', 7);
insert into orderedList values(3, '3H4', 8);
insert into orderedList values(3, '3H3', 9);
insert into orderedList values(3, '311', 10);
insert into orderedList values(3, '308', 11);


