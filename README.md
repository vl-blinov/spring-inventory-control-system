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


### Built With
The following technologies are used in this project:
* Spring Boot
* Spring Security
* Thymeleaf
* Bootstrap
* PostgreSQL
* Flyway
* Docker

## Getting Started


## Acknowledgements

