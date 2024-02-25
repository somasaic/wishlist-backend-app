1.	Download the project from Git Hub repository https://github.com/somasaic/wishlist-backend-app

2.	Import the project to Spring Tool Suite.
3.	Provide schema name, username, and password of your MySQL database in applications. Properties file for making a connection.
4.	Update maven project to get all dependencies required for the project.
5.	 Run spring boot application, right click on project -> Run As -> Spring Boot App.
6.	Once the App starts and running successfully. 
7.	Open postman tool -> click on collections -> click on import -> paste below 

https://api.postman.com/collections/24661809-f50e8520-21c5-4c24-9bb0-cc4157a34b52?access_key=PMAT-01HQFCGJG9J8P87P5WM51YBZA4


8.	 signUp api
 ![image](https://github.com/somasaic/wishlist-backend-app/assets/80689758/5485dd55-c408-42d6-be28-93656a7d38c6)
9.	Login

![image](https://github.com/somasaic/wishlist-backend-app/assets/80689758/d8f201c4-8555-4300-8516-cb231e18507f)

 
10.	After login to app, for subsequent API request provide credentials of current logged in user in Basic Auth

    ![image](https://github.com/somasaic/wishlist-backend-app/assets/80689758/3c9fb14a-5a10-4111-9303-65627c34e41a)

 
12.	Wishlists – post method to add a wish item

    ![image](https://github.com/somasaic/wishlist-backend-app/assets/80689758/e1e89cce-f4ee-4733-a7e2-4eba8562e286)

 
13.	Wishlists – get method to fetch all wish items of current user.

   ![image](https://github.com/somasaic/wishlist-backend-app/assets/80689758/7172865f-06c4-4e83-b1db-e0b1de28487a)

 
14.	Wishlists/{id} – delete method to delete a wish item of current user,.
NOTE: Take the wish item id from GET Wishlist response. You can see all wishes of current user.

 ![image](https://github.com/somasaic/wishlist-backend-app/assets/80689758/d1765085-db92-4ea3-bcd6-0ca3c214388a)

