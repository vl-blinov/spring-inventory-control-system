# AMICS
AMICS (Advanced Manufacturing Inventory Control System).
This project is based on a production application that is actively used at several manufacturing plants.
There are far less functionality that the real application has, but some of it is presented in the project.

## About The Project

### How It Works
Roughly speaking, the process looks like this:
1. An employee initiates a purchasing process and makes a request for budget approval for a desired product.
2. When everything is approved, it is time for creating a new inventory card with full information about the product in an e-catalogue of the ICS (inventory control system).
3. After that, the employee makes a PR (purchase requisition) in an ERP (enterprise resource planning) system which is connected to the ICS.
4. A specialist from the Purchasing Department checks the PR and organize further steps of the purchasing process.
5. At the end, when the product is purchased and delivered, it must be registered in the ERP system.

Each employee of the organization who has access to the ICS can view all equipment, accessories, tools and materials registered in the catalogue.
It is very useful to do before your own purchasing as it might help to find a product similar to what you need that has been already purchased.
Instead of long searching in the Internet you can contact with your colleague who created a card with a desired product and discuss its quality, characteristics, vendors, prices etc.
In case the product is exactly what is needed you just skip creation of a new inventory card and go to the next step.
If the product has similar characteristics you can reuse an existing inventory card with small changes.
Both cases will definitely save your time and speed up the process.

### What We Have So Far
Currently the application has only one main functionality which is to register equipment and accessories of specific categories
(Air conditioning, Electrical, Electronics, Gas, Heating, Hydraulics, Mechanical, Ventilation).

### Organizational Chart
The diagram shows the structure of an organization with departments, employees and their roles.

![org-structure](src/main/resources/static/images-readme/org-structure.png)

There are three roles with authorities used:
1. [ROLE_ADMIN]: create/view/reuse inventory cards, create/view/update/delete users, view/update profile.
2. [ROLE_MASTER]: create/view/reuse/delete inventory cards, view/update profile.
3. [ROLE_USER]: create/view/reuse inventory cards, view/update profile.

### Built With

* Spring Boot
* Spring Security
* Thymeleaf
* Bootstrap
* JQuery
* PostgreSQL
* Flyway
* Docker

### Folder Structure

```bash
│   .gitignore
│   docker-compose.yml
│   mvnw
│   mvnw.cmd
│   pom.xml
│   README.md
│
└───src
   ├───main
   │   ├───java
   │   │   └───ru
   │   │       └───blinov
   │   │           └───control
   │   │               └───inventory
   │   │                   │   InventoryControlSystemApplication.java
   │   │                   │
   │   │                   ├───config
   │   │                   │       InventoryWebMvcConfig.java
   │   │                   │       SecurityConfig.java
   │   │                   │
   │   │                   ├───controller
   │   │                   │       GlobalExceptionController.java
   │   │                   │       HomePageController.java
   │   │                   │       InventoryCardController.java
   │   │                   │       UserController.java
   │   │                   │
   │   │                   ├───entity
   │   │                   │       InventoryCard.java
   │   │                   │       User.java
   │   │                   │
   │   │                   ├───enums
   │   │                   │       Authority.java
   │   │                   │       Department.java
   │   │                   │       Enabled.java
   │   │                   │       InventoryCardClass.java
   │   │                   │       Position.java
   │   │                   │
   │   │                   ├───repository
   │   │                   │       InventoryCardRepository.java
   │   │                   │       UserRepository.java
   │   │                   │
   │   │                   ├───service
   │   │                   │       InventoryControlService.java
   │   │                   │
   │   │                   └───util
   │   │                           IdentifierGenerator.java
   │   │                           InventoryFileHandler.java
   │   │
   │   └───resources
   │       │   application.properties
   │       │
   │       ├───db
   │       │   └───migration
   │       │           V1__schema.sql
   │       │           V2__data.sql
   │       │
   │       ├───images
   │       ├───static
   │       │   │   index.html
   │       │   │
   │       │   ├───images-readme
   │       │   │       db-diagram.png
   │       │   │       org-structure.png
   │       │   │
   │       │   ├───images-source
   │       │   │   └───products
   │       │   │       ├───04h84995e175
   │       │   │       │       FE2.png
   │       │   │       │
   │       │   │       ├───09h70728e593
   │       │   │       │       6ES7155-6AR00-0AN0.png
   │       │   │       │
   │       │   │       ├───10h75036e635
   │       │   │       │       485B1-2.png
   │       │   │       │
   │       │   │       ├───11h41221e688
   │       │   │       │       Kachelbild-ZAPEX-ZW.png
   │       │   │       │
   │       │   │       ├───29h64868e615
   │       │   │       │       6ES7155-6AU01-0BN0.png
   │       │   │       │
   │       │   │       ├───30h52569e428
   │       │   │       │       TPRST009.png
   │       │   │       │
   │       │   │       ├───39h43745e361
   │       │   │       │       1FT7084-5AC71-1FB1.png
   │       │   │       │
   │       │   │       ├───44h88767e534
   │       │   │       │       LRD08.png
   │       │   │       │
   │       │   │       ├───48h65573e356
   │       │   │       │       P15L.png
   │       │   │       │
   │       │   │       ├───56h10157e430
   │       │   │       │       RC1BOX10.png
   │       │   │       │
   │       │   │       ├───56h35862e359
   │       │   │       │       Kachelbild-N-ARPEX.png
   │       │   │       │
   │       │   │       ├───58h23915e201
   │       │   │       │       LUB12.png
   │       │   │       │
   │       │   │       ├───66h12772e021
   │       │   │       │       PUHZ-ZRP.png
   │       │   │       │
   │       │   │       ├───67h95110e543
   │       │   │       │       CCR1.png
   │       │   │       │
   │       │   │       ├───76h83496e201
   │       │   │       │       GV2ME01.png
   │       │   │       │
   │       │   │       ├───82h16047e756
   │       │   │       │       R9F64320.png
   │       │   │       │
   │       │   │       ├───83h39851e755
   │       │   │       │       RCPRFG.png
   │       │   │       │
   │       │   │       ├───88h27958e046
   │       │   │       │       SF0801-4420.png
   │       │   │       │
   │       │   │       ├───91h40514e543
   │       │   │       │       Kachelbild-ZAPEX-ZN.png
   │       │   │       │
   │       │   │       └───99h47459e398
   │       │   │               FFM525G1-3.png
   │       │   │
   │       │   └───js
   │       │       ├───inventory-card
   │       │       │       display-image.js
   │       │       │       reload-page.js
   │       │       │       view-card.js
   │       │       │
   │       │       └───user-form
   │       │               reload-page.js
   │       │               update-user.js
   │       │
   │       └───templates
   │           │   error.html
   │           │   home-page.html
   │           │
   │           ├───fragments
   │           │       inventory-card.html
   │           │       navigation-bar.html
   │           │       user-form.html
   │           │
   │           ├───inventory
   │           │       catalogue.html
   │           │
   │           └───users
   │                   list-users.html
   │                   user-profile.html
   │
   └───test
       ├───java
       │   └───ru
       │       └───blinov
       │           └───control
       │               └───inventory
       │                   ├───controller
       │                   │       GlobalExceptionControllerTest.java
       │                   │       HomePageControllerTest.java
       │                   │       InventoryCardControllerTest.java
       │                   │       UserControllerTest.java
       │                   │
       │                   ├───repository
       │                   │       InventoryCardRepositoryTest.java
       │                   │       UserRepositoryTest.java
       │                   │
       │                   ├───service
       │                   │       InventoryControlServiceTest.java
       │                   │
       │                   └───util
       │                           InventoryFileHandlerTest.java
       │
       └───resources
           └───images
                   GV2ME02.png
```

### Database Diagram
![org-structure](src/main/resources/static/images-readme/db-diagram.png)

## Getting Started

### Prerequisites
The following items should be installed in your system:
* JDK 17 or newer
* Eclipse IDE
* Git command line tool (https://help.github.com/articles/set-up-git)
* Docker (https://docs.docker.com/get-docker)

### Steps

1) On the command line:

```bash
	git clone https://github.com/vl-blinov/spring-inventory-control-system.git
```
2) Inside Eclipse IDE:

```
	File -> Import -> Maven -> Existing Maven Projects
```
3) On the command line in the project root directory:

```bash
	docker-compose up
```
4) Inside Eclipse IDE:

```
	src/main/java/ru.blinov.control.inventory/InventoryControlSystemApplication.java -> Run As -> Java Application
```

5) Navigate to AMICS:

```
	Visit [http://localhost:8080] in your browser
```

6) Log in as one of the following users (password: "123" for everyone):

| #  | Username         | Role        | 
| -- | ---------------- | ----------- |
| 1  | jackobrien  		| ROLE_ADMIN  |
| 2  | gracecarrol 		| ROLE_MASTER |
| 3  | danielcollins    | ROLE_USER   |
| 4  | conorfarrel      | ROLE_USER   |
| 5  | noahoconnor      | ROLE_USER   |
| 6  | finnmartin       | ROLE_USER   |
| 7  | hannahcampbell   | ROLE_USER   |
| 8  | alexodonnell     | ROLE_USER   |
| 9  | ryanoneill       | ROLE_USER   |
| 10 | gaelmccarthy     | ROLE_USER   |
| 11 | roryhayes        | ROLE_USER   |
| 12 | lorcanfitzgerald | ROLE_USER   |
| 13 | quilloreilly     | ROLE_USER   |
| 14 | chloeclarke      | ROLE_USER   |
| 15 | briendoyle       | ROLE_USER   |
| 16 | torinkennedy     | ROLE_USER   |
| 17 | pauricgallagher  | ROLE_USER   |
| 18 | saeranwilson     | ROLE_USER   |
| 19 | kateburke        | ROLE_USER   |
| 20 | charliejohnston  | ROLE_USER   |
| 21 | liambrown        | ROLE_USER   |
| 22 | adamflynn        | ROLE_USER   |
| 23 | lukehealy        | ROLE_USER   |
| 24 | haileyregan      | ROLE_USER   |

## Important Notes

1. Spring Security is used per se only for authorization.
A password for all existing and newly created users is "123" by default.
2. Postgres database runs inside of a docker container after "docker-compose up" command is executed.
There is no need to install it locally.
3. A separate test container with database is launched during the tests where required.
4. Schema and data migration is implemented automatically by Flyway.

## Acknowledgements

* [Spring & Hibernate Course](https://www.udemy.com/course/spring-hibernate-tutorial)
* [PostgreSQL Course](https://www.udemy.com/course/sql-and-postgresql)
* [Baeldung](https://www.baeldung.com)
* [Bootstrap](https://getbootstrap.com)
