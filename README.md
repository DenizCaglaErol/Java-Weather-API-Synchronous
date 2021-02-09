# Java-Weather-API-Synchronous
On the opening screen of the application, there was be 2 buttons, 1 textbox to provide a list and data entry. City names that will be entered in this textbox will be recorded in the database. Afterwards, the weather forecast of any selected record will be queried and written to the database via WeatherAPI.

<a href="https://ibb.co/nsy9mkQ"><img src="https://i.ibb.co/LzymRp9/Ads-z.png" alt="Ads-z" border="0"></a>

JSONRead.java class is used to send Java HTTP request and parse JSON. In the functions in this class, it will send the arguments we give to the server address, which is the address that contains the html form in our case. As you can see from the code, you don't have to deal with question marks. Then, in parseObject(JSONObject jo) method, JSON is parsing. The json-simple library is meant to be a free lightweight utility for deserializing and serializing Javascript Object Notation (JSON). The json-simple-1.1.jar file must be added to the classpath.

For Connect.java class, first of all, downloaded jar file sqlite-jdbc-3.27.2.1.jar added in class path. The connect() method shows how to connect to an existing database. In insertion part, it was showed how to create records in database table. Lastly, in updating part, it was showed how to use UPDATE statement to update any record and then fetch and display the updated records from the database table.

In AnaMenu.java class it was created a textfield and 2 buttons to provide a data table and data entry. In createTable() method a table model and table based on it were created. Then, connecting to database inside the try-catch. A statement has been created with the server, the desired query was executed and the result was returned. GUI was created and temp row was removed. Then, a temporary object array was created to hold the result for each row. For each row returned, values were added to the temporary row and the temporary row added to the table. In receate() method, frame was redrawed. 
The actionPerformed(ActionEvent e) method is a sequence of code that specifies what actions will occur when the buttons are pressed. 
If the action command equals to "Query the selected one", the method named sendGetRequest() in the JSONRead.java class is called, then json is parsed and the Update method in the Connect.java class is used using the created Connect object. Finally, using the AnaMenu object, the createTable() method and the recreate() method are called.
To summarize, this code sequence is used to show the current weather temperature and current the weather type of a city selected from the table.


<a href="https://ibb.co/GVnBMbL"><img src="https://i.ibb.co/dQj1pzq/Ads-z.png" alt="Ads-z" border="0"></a>

<a href="https://ibb.co/FVpwMf1"><img src="https://i.ibb.co/zJdRYLg/Ads-z.png" alt="Ads-z" border="0"></a>

<a href="https://ibb.co/yRv5ysG"><img src="https://i.ibb.co/Jd627k0/Ads-z.png" alt="Ads-z" border="0"></a>

<a href="https://ibb.co/WpW9FSq"><img src="https://i.ibb.co/JFRL2X0/Ads-z.png" alt="Ads-z" border="0"></a>

<a href="https://ibb.co/mJC6cf7"><img src="https://i.ibb.co/PxZw6q8/Ads-z.png" alt="Ads-z" border="0"></a>


If the action command equals to "Add", the method named sendGetRequest() in the JSONRead.java class is called, then json is parsed and insert method in the Connect.java class is used using created Connect object. Then, as above, using the AnaMenu object, the createTable() method and the recreate() method are called.
This code sequence allows the current weather information of the city, written in the textfield section, to be added to the database and updated in the table.

<a href="https://ibb.co/jhm5QhX"><img src="https://i.ibb.co/sjnPNjY/Ads-z.png" alt="Ads-z" border="0"></a>

<a href="https://ibb.co/sWSF62k"><img src="https://i.ibb.co/BnDgrtd/Ads-z.png" alt="Ads-z" border="0"></a>
