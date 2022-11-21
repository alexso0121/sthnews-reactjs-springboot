# sthnews (news website)
www.sosthweb.com

month 3 project 

frontend server: reactjs__javascript (with react bootstrap)

backend server: springboot_java

database: mysql

cloud server: aws elastic bean + aws amilify


# Version 1
-basic authentication

-web scraping news every day (HKT 08:00) and store to the database in springboot scheduler

-weather forecast by hk observation free api

-store news function after autheticate

-read history authentication after authenticate

# Version 2
-springboot security to protect the database being accessed 

-json web token is used to allow authentication to backend server

-search news function

-send email to the client after sign up when valid

-clear outdate news with springboot schedular

# Springboot architecture
main script for spring boot --> main ->springbootbackend --> src

package schedular: springboot schedlar

package model: all entities

package service: all business logic

package controller: all rest api

package security: springboot security configuration


