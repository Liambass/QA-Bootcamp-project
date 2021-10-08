## Versions:
The first merge from the dev branch to main included a v1.0 jar build. Along with the documentation added in the second push to main this makes the minimum viable product of the project, meeting all points of the deliverables checklist.

The next merge to main delivers build v1.1 with all necessary documentation. This build adds the stretch goal of custom queries. Usage of these can be seen in the "API call examples" section.

The next merge to main delivers build v1.2 with all necessary documentation. This build adds the stretch goal of custom exceptions, this does not change the usage of the application, but gives the user more useful error messages when they make bad requests.
 
The next merge, anticipated to be the final one to main, delivers build v1.3 with all necessary documentation. This build adds a date transfer object, functionally this version is identical to the last but the implementation of a DTO allows future-proofing for the case where the entity contains sensitive data.

--------------

## Experimental branches

### (frontend)
Due to finishing far ahead of schedule my trainer supplied me with a front-end for a similar project that he had written to see if I could adapt it for use in mine. 

For transparency, the original front-end files have been supplied in the "Documentation" folder.

To adapt the supplied files I first made some very basic edits to the supplied HTML/JS such as changing headings and button names, and pointing API calls to the right address. Once these changes were manually tested we reached v1.4, it is not intended that the experimental branch will be merged to main working builds will however be merged with dev. 

Once I had adapted basic CRUD functionality I implemented an "Search" button with brings up a form allowing the user to search by title, genre, year range, and min/max duration. The results of these searches replace the list of all records (by design), so I then implemented a "Back" button to the search results which will reload the full list, I also and a heading to the full list along with a "Refresh" button.

I then implemented some validation for the search to ensure that blank requests would no be sent. 

Finally, before v1.5, I added some handling so that relevant exception messages from the back-end would be shown on the front-end.

This work has been merged to the dev branch. 

### (anotherEntity)
After experimenting with the front-end it was suggested that I attempt to implement a second entity with some relation to the first. As my original entity held data about films I chose to make a new entity about bookings, whereby each booking would have a date, a time, and a reference to a film. Each booking would have one and only one film but a film can be part of multiple bookings.
 
<img src="https://user-images.githubusercontent.com/19336480/136581053-84d73cca-cedd-4d3f-8368-be90ac877058.png" width="45%"></img>

Currently the controller for this entity does not have full CRUD, only a "read all" that returns a list of bookings with the film_id replaced by the relevant title. This is achieved with a GET call to http://localhost:9001/booking/readAll . Build v1.6 represents the project at this point. 


--------------

## Usage 

This application is intended for storing and manipulating data about films in a database.

The database should be called "film_Collection" and should be on port 3306 (u/n: root, p/w: Root). 

The application listens on port 9001, example API calls are shown below.

<br>

#### Experimental branch:
The front-end is accessed at http://localhost:9001/index.html

--------------

## Why am I doing this?

The purpose of this project is to demonstrate the knowledge and skills that I have gained during the DfE Skills bootcamp provided by QA. 

I shall be using the Spring Boot framework to build a Java application that that interfaces with a local MySQL relational database, a h2 in-memory database will be used for testing. 

Project planning will be achieved using [this](https://liambass1.atlassian.net/jira/software/projects/QBP/boards/1) Jira board.

--------------

## How I expected the challenge to go...

Overall I expected the challenge to go fairly smoothly as generally I have gained a fair understanding of the topics covered throughout this course, particularly in terms of actual coding. 

--------------

## What went well? What didn't?

As anticipated, I found that the project went well on the whole. 

I wasn't entirely confident in crafting good user stories but ended up happy enough with what I achieved in that regard. 

I also had a little trouble with the jar build process but a quick bit of research cleared that up completely. 

I was stuck for a short while on testing after implementing a DTO, I got around it by changing how I was mocking returns, but this solution felt rather hacky to me so after a little more research I discovered that it was all due to a missing equals() in my DTO. 

--------------

## Possible future features!

* More attributes held by the database
* ~~Custom queries~~ Implemented in v1.1
* ~~Custom exceptions~~ Implemented in v1.2
* ~~Implementation of Data Transfer Objects~~ Implemented in v1.3
* ~~Front-end~~ Implemented on experimental branch

--------------
 

## API call examples 

Add a record to the database
<img src="https://user-images.githubusercontent.com/19336480/135731271-5fb42f47-3c1a-433d-a452-c33c72cb86a9.png" width="90%"></img> 

Read all records from the database
<img src="https://user-images.githubusercontent.com/19336480/135731276-458c1ae0-3d7c-4576-b446-410e2a52bbe1.png" width="90%"></img> 

Read a single record from the database by ID
<img src="https://user-images.githubusercontent.com/19336480/135731277-ed200f4d-f028-40cd-b7d6-567ab1f2c0b6.png" width="90%"></img> 

Update a record in the database
<img src="https://user-images.githubusercontent.com/19336480/135731278-f5e4e363-e7aa-49a8-8238-e8bf4d333f3f.png" width="90%"></img> 

Delete a record in the database
<img src="https://user-images.githubusercontent.com/19336480/135731280-c1474d79-6163-415f-b075-4242f49311f4.png" width="90%"></img> 

Find films by title (requires v1.1+)
<img src="https://user-images.githubusercontent.com/19336480/135852372-01538b25-7664-4036-98cf-3c39788efdcd.png" width="90%"></img> 

Find films by genre (requires v1.1+)
<img src="https://user-images.githubusercontent.com/19336480/135852366-9beded86-2e90-48ac-95ea-a1b99406a308.png" width="90%"></img> 

Find films by year (requires v1.1+)
<img src="https://user-images.githubusercontent.com/19336480/135852376-d347798b-fafd-4f16-a5e8-d3edd92c6638.png" width="90%"></img> 

Find films by year range (requires v1.1+)
<img src="https://user-images.githubusercontent.com/19336480/135852379-1d9eb1e6-77ad-4917-a51d-117bba2d6a02.png" width="90%"></img> 

Find films by minimum duration (requires v1.1+)
<img src="https://user-images.githubusercontent.com/19336480/135852370-80f38a34-e4fe-4c59-ac5c-a53081d80f6c.png" width="90%"></img> 

Find films by maximum duration (requires v1.1+)
<img src="https://user-images.githubusercontent.com/19336480/135852369-2e84540d-c2fc-4736-a640-e758466b5c3f.png" width="90%"></img> 

--------------

## Test Results
Service layer unit test v1.3
<img src="https://user-images.githubusercontent.com/19336480/136013284-bfaf5530-9aae-41a7-bccd-2771f4080a85.png" width="90%"></img>

Controller layer unit test v1.3
<img src="https://user-images.githubusercontent.com/19336480/136013295-831ad172-05cb-47f4-b4f1-9063c94caa05.png" width="90%"></img> 

Integration test v1.3 
<img src="https://user-images.githubusercontent.com/19336480/136013311-2c610b57-585d-4f7b-947a-6e429ad908e4.png" width="90%"></img>  

Full test suite v1.3
<img src="https://user-images.githubusercontent.com/19336480/136013321-4b4bcde0-73d8-482a-91d1-a27014c59faf.png" width="90%"></img>

Test results for previous versions can be found in the documentation folder.

--------------

Evidence of persistent database
<img src="https://user-images.githubusercontent.com/19336480/135731282-13c7755b-0075-467e-9c77-78585dfc770d.png" width="90%"></img> 

--------------

## Front-end

<img src="https://user-images.githubusercontent.com/19336480/136417860-1c613f84-b0d8-4671-a2eb-29057aa814a8.png" width="90%">
</img> <img src="https://user-images.githubusercontent.com/19336480/136417866-10737ac0-99ca-4a8e-82cd-a658ebe3462b.png" width="90%">
</img> <img src="https://user-images.githubusercontent.com/19336480/136417877-28a4d0e9-c653-4ee6-9046-4e0824fb9f3d.png" width="90%"></img> 