# AMICS
AMICS (Advanced Manufacturing Inventory Control System).
This project is based on a production application that is actively used at several manufacturing plants.
There are far less functionality that the real application has, but some of it is presented in this project.

## About The Project

### How It Works In Production


### What We Have So Far
Currently the application has only one main functionality which is to register all equipment and accessories
that will be purchased for further use at a manufacturing plant.

### Organization Structure
The chart shows an organization structure with departments using AMICS.
![org-structure](src/main/resources/static/images-readme/org-structure.png)
There are three roles with authorities used:
1. [ROLE_ADMIN]: create/view/reuse inventory cards, create/view/update/delete users, view/update profile
1. [ROLE_MASTER]: create/view/reuse/delete inventory cards, view/update profile
1. [ROLE_USER]: create/view/reuse inventory cards, view/update profile

### Folder Structures

```bash
│   .gitignore
│   docker-compose.yml
│   mvnw
│   mvnw.cmd
│   pom.xml
│   README.md
│
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


### Built With

* Spring Boot
* Spring Security
* Thymeleaf
* Bootstrap
* PostgreSQL
* Flyway
* Docker

## Getting Started


## Acknowledgements

