# SprinBoot-Oauth2
Basic Oauth2 implementation using Springboot

It will create a default user with credentials root/root. 

cUrl for Auth endpoint is - 


curl --location --request POST 'localhost:9191/oauth/token' \
--header 'Authorization: Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=root' \
--data-urlencode 'password=root'


ref- https://www.javainuse.com/spring/springboot-oauth2-password-grant
