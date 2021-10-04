## Versions:
The first merge from the dev branch to main included a v1.0 jar build. Along with the documentation added in the second push to main this makes the minimum viable product of the project, meeting all points of the deliverables checklist.

The next merge to main delivers build v1.1 with all necessary documentation. This build adds the stretch goal of custom queries.

The next merge to main delivers build v1.2 with all necessary documentation. This build adds the stretch goal of custom exceptions.
 

--------------

## Usage 

This application is intended for storing and manipulating data about films in a database.

The database should be called "film_Collection" and should be on port 3306 (u/n: root, p/w: Root). 

The application listens on port 9001, example API calls are shown below.

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

As anticipated, I found that the project went well. I wasn't entirely confident in crafting good user stories but ended up happy enough with what I achieved in that regard. I also had a little trouble with the jar build process but a quick bit of research cleared that up completely. 

--------------

## Possible future features!

* More attributes held by the database
* ~~Custom queries~~ Implemented in v1.1
* ~~Custom exceptions~~ Implemented in v1.2
* Implementation of Data Transfer Objects

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
Service layer unit test v1.2
<img src="https://user-images.githubusercontent.com/19336480/135895553-47779e8a-acd2-4308-95a2-2b70f45c8e13.png" width="90%"></img> 

Controller layer unit test v1.2
<img src="https://user-images.githubusercontent.com/19336480/135895577-91392c97-4d6c-4a49-9c6c-605043d2dfea.png" width="90%"></img> 

Integration test v1.2 
<img src="https://user-images.githubusercontent.com/19336480/135895586-1be495b6-618c-4a3e-8efb-3692937366ba.png" width="90%"></img> 

Full test suite v1.2
<img src="https://user-images.githubusercontent.com/19336480/135895595-62bc0f4d-a4d9-4589-8581-c404706c4240.png" width="90%"></img> 

Test results for previous versions can be found in the documentation folder.

--------------

Evidence of persistent database
<img src="https://user-images.githubusercontent.com/19336480/135731282-13c7755b-0075-467e-9c77-78585dfc770d.png" width="90%"></img> 