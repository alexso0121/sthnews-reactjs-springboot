# sthnewsweb (news website)
month 3-4 project 

frontend server: reactjs__javascript (with react bootstrap)

backend server: springboot__java

database: mysql

cloud server: aws elastic bean + aws amplify

frontend script: https://github.com/alexso0121/newsreact

website: www.sosthweb.com

(mainly designed based on mobile phone)

# Version 1
-basic authentication

-web scraping news (jsoup) every day (HKT 08:00) and store to the database by springboot scheduler

-weather forecast by hk observation free api

-store news function after autheticate

-read history function after authenticate

-personal profile

# Version 2
-springboot security to protect the database from being accessed 

-json web token is used to allow authentication to backend server

-search news function

-send email to the client after sign up when valid

-clear outdate news with springboot schedular


# Springboot architecture
main script for spring boot --> main ->springbootbackend --> src

package schedular: springboot schedular

package model: all entities

package service: all business logic

package controller: all rest api

package repository: sql query for accessing the mysql database

package security: springboot security configuration


