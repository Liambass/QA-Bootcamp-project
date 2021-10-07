## Versions:
The first merge from the dev branch to main included a v1.0 jar build. Along with the documentation added in the second push to main this makes the minimum viable product of the project, meeting all points of the deliverables checklist.

The next merge to main delivers build v1.1 with all necessary documentation. This build adds the stretch goal of custom queries. Usage of these can be seen in the "API call examples" section.

The next merge to main delivers build v1.2 with all necessary documentation. This build adds the stretch goal of custom exceptions, this does not change the usage of the application, but gives the user more useful error messages when they make bad requests.
 
The next merge, anticipated to be the final one to main, delivers build v1.3 with all necessary documentation. This build adds a date transfer object, functionally this version is identical to the last but the implementation of a DTO allows future-proofing for the case where the entity contains sensitive data.

<br>

#### Experimental branches:

After achieving many of the stretch goals with lots of time to spare, I was provided with a front-end from a similar project to see if I could adapt it to work with my application, this work, including working builds can be found on the [Dev branch](https://github.com/Liambass/QA-Bootcamp-project/tree/dev). 

Next I intend to add another entity (with a relationship to my current one) to my application and work towards implementing that all the way through to the frontend, this branch will also include a ERD in the documentation folder. I do not  anticipate completely finishing this before the deadline and therefore do not expect to merge it back onto dev, any progress made can be found on the [anotherEntity branch](https://github.com/Liambass/QA-Bootcamp-project/tree/anotherEntity). 

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
* Front end

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
<img src="https://user-images.githubusercontent.com/19336480/136468974-c5408565-f2d9-4a39-90af-e70f28a8ece0.png" width="90%">

Controller layer unit test v1.3
</img> <img src="https://user-images.githubusercontent.com/19336480/136468990-0507e897-c513-46c5-a40d-d251c083be38.png" width="90%">

Integration test v1.3 
</img> <img src="https://user-images.githubusercontent.com/19336480/136469007-5c4e1f6a-d135-4d18-9a3e-d2affcc3b6f7.png" width="90%">

Full test suite v1.3
</img> <img src="https://user-images.githubusercontent.com/19336480/136469029-dab71483-a5d6-447f-b9d8-3ba098bff1b0.png" width="90%"></img> 

POJO tests v1.3
<img src="https://user-images.githubusercontent.com/19336480/136469304-f154e39a-3d1b-4e0b-bbb0-61911efc90fb.png" width="90%"></img> <img src="https://user-images.githubusercontent.com/19336480/136469309-f68d5a2a-ecb6-4c2c-8a3e-5b41611c9a17.png" width="90%"></img> 

Test results for previous versions can be found in the documentation folder.

--------------

Evidence of persistent database
<img src="https://user-images.githubusercontent.com/19336480/135731282-13c7755b-0075-467e-9c77-78585dfc770d.png" width="90%"></img> 
