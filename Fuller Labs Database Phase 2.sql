-- Preet Patel, Ronit Kapoor


create or replace view labManagers as
    select acronym
    from Titles
    where titleName not like '%Lab%';
-- did not join Locations to office on the assumption that all Locations that are offices are in Offices table

create or replace view NoLabMgr_v as
    select Offices.officeID, count(unique CSStaff.accountName) as CS_Staff_Count
    from Offices Join CSStaff
    on Offices.officeID = CSStaff.officeID
    Join CSStaffTitles on CSStaffTitles.accountName = CSStaff.accountName 
    join labManagers on CSStaffTitles.acronym = labManagers.acronym
    group by Offices.officeID
    having count(unique CSStaff.accountName) > 1;
 -- use full name , multiple views  

insert into CSStaff values ('jdoe', 'John', 'Doe', '144');     -- Add a lab manager to an office with already 2 people
insert into CSStaffTitles values ('jdoe', 'Lab1');             -- There should be no change

insert into CSStaff values ('msmith', 'Mary', 'Smith', '139'); -- Add two lab managers to an office with only one professor
insert into CSStaffTitles values ('msmith', 'Lab2');           -- There should be no change
insert into CSStaff values ('npine', 'Nancy', 'Pine', '139'); 
insert into CSStaffTitles values ('npine', 'Lab1');

insert into CSStaff values ('telm', 'Tim', 'Elm', '244');      -- Add two non lab managers to an office with a lab manager
insert into CSStaffTitles values ('telm', 'Asst Prof');        -- This office should be listed with two staff
insert into CSStaff values ('anovice', 'Art', 'Novice', '244');
insert into CSStaffTitles values ('anovice', 'Admin 5');




    
create or replace Procedure NumberOfStaff(office_ID varchar2) IS
cnt number (5);
Begin

select count(accountName) into cnt
from CSStaff
where officeID = office_ID;

dbms_output.put_line('Number of staff: ' || cnt);

End;

set serveroutput on;
exec NumberOfStaff(245);
exec NumberOfStaff(233);
exec NumberOfStaff(144);
exec NumberOfStaff(250);




create Trigger NoSameLocations
Before Insert ON Edges
for each row
Begin
    IF(:new.startingLocationID = :new.endingLocationID) THEN
        raise_application_error(-20400, 'Cannot insert an edge with same location as start and end.');
    End IF;
End; 

insert into Edges values ('222_222', '222', '222');

--test cases
insert into Locations values ('321', 'FL321', 'Office', 450, 200, '3');
insert into Locations values ('322', 'FL322', 'Office', 480, 240, '3');
delete from Locations where locationID = '321';
delete from Locations where locationID = '322';

insert into Edges values('321_322', '321', '322'); 
insert into Edges values ('321_321', '321', '321');
delete from Edges where edgeID = '321_322';
delete from Edges where edgeID = '321_321';






//CrossFloorEdge Trigger
create or Replace Trigger CrossFloorEdge
Before Insert ON Edges
for each row
Declare
startID varchar2(10); startFloor char(1); startType varchar2(15); endID varchar2(10); endFloor char(1); endType varchar2(15);
Begin

select locationID, mapFloor, locationType into startID, startFloor, startType
from Locations
where locationID = :new.startingLocationID;

select locationID, mapFloor, locationType into endID, endFloor, endType
from Locations
where locationID = :new.endingLocationID;

if (startFloor != endFloor) Then
    if((startType = 'Staircase' And endType = 'Staircase') OR (startType = 'Elevator' And endType = 'Elevator')) Then
        startID := startID;
    else
        raise_application_error(-20401, 'Cannot insert an edge with start location and end location having different floors and not be both either staircases or elevators.');
    End IF;
End IF;
End;

//fails
insert into Edges values ('137_237', '137', '237'); -- offices on two different floors
insert into Edges values ('2H2_3H4', '2H2', '3H4'); -- hallways on two different floors
insert into Edges values ('144_222', '144', '222'); -- office to lab on a different floor
insert into Edges values ('3S1_2E1', '3S1', '2E1'); -- staircase on one floor to elevator on other floor
insert into Edges values ('2S1_137', '2S1', '137'); -- staircase to office on a different floor
insert into Edges values ('3H5_2S2', '3H5', '2S2'); -- hallway to staircase on a different floor
insert into Edges values ('3E1_2S2', '3E1', '2S2'); -- elevator on one floor to staircase on other floor
insert into Edges values ('2E1_PHLWR', '2E1', 'PHLWR'); -- elevator to lecture hall on a different floor
insert into Edges values ('320_2E1', '320', '2E1'); -- office to elevator on a different floor

//pass
insert into Edges values ('130_135', '130', '135'); -- two offices on the same floor
insert into Edges values ('2H3_2H9', '2H3', '2H9'); -- two hallways on the same floor
insert into Edges values ('312_3H2', '312', '3H2'); -- office to hallway on the same floor
insert into Edges values ('222_PHUPR', '222', 'PHUPR'); -- two non-elevator/staircases on the same floor




--my test cases
INSERT INTO Locations (LOCATIONID, LOCATIONNAME, LOCATIONTYPE, XCOORD, YCOORD,
MAPFLOOR) VALUES ('2S20', 'FL2H1', 'Staircase', 835.3, 200.3, '2');

INSERT INTO Locations (LOCATIONID, LOCATIONNAME, LOCATIONTYPE, XCOORD, YCOORD,
MAPFLOOR) Values ('3S18', 'FL2H1', 'Elevator', 835.3, 200.3, '3');

INSERT INTO Locations (LOCATIONID, LOCATIONNAME, LOCATIONTYPE, XCOORD, YCOORD,
MAPFLOOR) Values ('3S20', 'FL2H1', 'Elevator', 835.3, 200.3, '2');

Delete from Locations where locationID = '2S20';
delete from Locations where locationID = '3S18';
delete from Locations where locationID = '3S20';

Insert into Edges values('2S20_3S18','2S20','3S18');
insert into Edges values('2S20_3S20', '2S20', '3S20'); 
insert into Edges values('3S18_3S20', '3S18', '3S20');

delete from Edges where edgeID = '2S20_3S18';
delete from Edges where edgeID = '2S20_3S20';
delete from Edges where edgeID = '3S18_3S20';







create or replace trigger MustBeInOffice 
After Insert on Offices
Declare
cursor existingOffices is 
select locationType 
from Locations Join Offices on Locations.locationID = Offices.officeID;

Begin
 
for rec in existingOffices Loop
    if(rec.locationType != 'Office') Then
    raise_application_error(-20402, 'Cannot have an office that is not of type Office in Locations table');
    End IF;
    End Loop;
    
End;

-- Before compiling the trigger, do the following insert to put a lab into the Offices table
INSERT INTO Offices VALUES ('A21');   

-- Compile the trigger
-- Insert the new office which should result in an error because of A21 in the table
INSERT INTO Locations VALUES ('322', 'FL322', 'Office', 925.0, 670.0, '3');
INSERT INTO Offices VALUES ('322');

DELETE FROM Offices WHERE officeID = 'A21';         -- delete the lab

INSERT INTO Offices VALUES ('A21');                              -- Lab location should result in error
INSERT INTO Offices VALUES ('320');                               -- Lecture hall should result in error
INSERT INTO Offices VALUES ('3H7');                               -- Hallway should result in error


--my test cases
INSERT INTO Locations (LOCATIONID, LOCATIONNAME, LOCATIONTYPE, XCOORD, YCOORD,
MAPFLOOR) Values ('246', 'FL246', 'Staircase', 560, 300, '2');
INSERT INTO Locations (LOCATIONID, LOCATIONNAME, LOCATIONTYPE, XCOORD, YCOORD,
MAPFLOOR) Values ('247', 'FL247', 'Conference Room', 565, 330, '2');
INSERT INTO Locations (LOCATIONID, LOCATIONNAME, LOCATIONTYPE, XCOORD, YCOORD,
MAPFLOOR) Values ('248', 'FL248','Office', 578, 335, '2');
INSERT INTO Locations (LOCATIONID, LOCATIONNAME, LOCATIONTYPE, XCOORD, YCOORD,
MAPFLOOR) Values ('249', 'FL249','Office', 586, 353, '2');

Insert into Offices(officeID) values ('246');
delete from Offices where officeID = '246';
Insert into Offices(officeID) values ('247');
Insert into Offices(officeID) values('248');

update Locations 
set locationType= 'Hallway'
where locationID = '248';

insert into Offices(officeID) values('249');
delete from Offices where officeID ='249';









create or replace trigger TitleLimit
After Insert or update on CSStaffTitles
Declare
cursor isMorethanThree Is
select accountName 
from CSStaffTitles 
where accountName in (select accountName from CSStaffTitles where acronym like '%Prof%')
group by accountName
having count(*) > 3;

name varchar2(15);
Begin 

open isMorethanThree;
Fetch isMorethanThree into name;

if(isMorethanThree%Found) Then
    raise_application_error(-20403, 'Cannot have a professor with more than 3 titles');
End IF;
close isMorethanThree;

End; 

-- Before compiling the trigger. Enter titles for a professor so there are 4 titles
INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM)   VALUES ('cew', 'Dir-DS');
INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM) VALUES ('cew', 'Dir-LST');
            -- Now compile the trigger
            INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM) VALUES ('mvoorhis', 'SrInst'); -- Adding a different person without professor titles should still result in an error because cew has four titles and one is a professor


-- reset database and compile trigger
delete from CSStaffTitles where accountName = 'cew' and acronym = 'Dir-DS'; 
             delete from CSStaffTitles where accountName = 'cew' and acronym = 'Dir-LST';

-- Add two titles to a professor who already has two titles should result in an error
INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM) VALUES ('cew', 'Dir-DS');
INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM) VALUES ('cew', 'Dir-LST');

-- Adding a fourth title that is a professor title should result in an error
INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM) VALUES ('ciaraldi', 'Lab1');
INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM)VALUES ('ciaraldi', 'Admin 5');
INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM)VALUES ('ciaraldi', 'Prof');

-- set up for the update test 
INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM) VALUES ('mvoorhis', 'SrInst');
      INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM) VALUES ('mvoorhis', 'Dir-DS');
INSERT INTO CSStaffTitles (ACCOUNTNAME, ACRONYM) VALUES ('mvoorhis', 'Admin 6');
-- When a person has four non-professor titles and one gets updated to be a professor title, then an update event error should result

update CSStaffTitles set acronym = 'Prof'  where accountname = 'mvoorhis' and acronym = 'SrInst';  



--my test cases
insert into CSStaffTitles values('cew', 'TProf');
insert into CSStaffTitles values('cew', 'Admin 5');
insert into CSStaffTitles values('cew', 'Admin 6');

delete from CSStaffTitles where accountName = 'cew' AND acronym = 'TProf';
delete from CSStaffTitles where accountName = 'cew' AND acronym = 'Admin 5';

  
