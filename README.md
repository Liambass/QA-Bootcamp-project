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

## Usage 

This application is intended for storing and manipulating data about films in a database.

The database should be called "film_Collection" and should be on port 3306 (u/n: root, p/w: Root). 

The application listens on port 9001, example API calls are shown below. 

--------------

## Possible future features!

* More attributes held by the database
* Custom queries
* Custom exceptions
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

--------------

## Test Results
Service layer unit test
<img src="https://user-images.githubusercontent.com/19336480/135731260-9a0bdb18-ef08-4ce6-a117-c31339234aa2.png" width="90%"></img> 

Controller layer unit test
<img src="https://user-images.githubusercontent.com/19336480/135731259-32ae1fce-e5b9-47ab-bfd4-23a2d158933d.png" width="90%"></img> 

Integration test
<img src="https://user-images.githubusercontent.com/19336480/135731258-a7fc7573-6dc3-482b-87b2-d640d8a9e3ee.png" width="90%"></img> 

Full test suite
<img src="https://user-images.githubusercontent.com/19336480/135731261-c783fa95-d2d1-45ce-a7b9-b40442d5a1b8.png" width="90%"></img> 

--------------

Evidence of persistent database
<img src="https://user-images.githubusercontent.com/19336480/135731282-13c7755b-0075-467e-9c77-78585dfc770d.png" width="90%"></img> 