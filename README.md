# News Aggregator API project

Java & Springboot (Backend-only) News aggregator project to register new user, authenticate the user by JWT during login and provide token and fetch news using Third-party API for the categories set by User.

<br>

## Features : <br>

### 1. User Authentication: <br>
- `POST /api/register` : Register a new user.
- `POST /api/signin` : Authenticate a user and issue a JWT token. <br>

### 2. News Preferences: <br>
- `GET /api/preferences` : Retrieve the logged-in user's news preferences.
- `PUT /api/preferences` : Update the logged-in user's news preferences. <br>

### 3. News Articles: <br>
- `GET /api/news` : Fetch personalized news articles from external APIs based on user preferences.
<br>

## Technologies Used : <br>
- Spring Boot: Core framework.
- Spring Security: JWT-based authentication.
- Maven: Build tool.
- In-Memory Database (H2 Database): Stores user data and preferences.
- External News APIs: Integrates with services like GNews API.
- Spring Web-Client's RestTemplate : Fetches news articles asynchronously. <br>

<br>

## API Endpoints:
1. User Registration: <br>
  `POST /api/register`<br>
   Example Payload:
    ```json
    {
        "username": "example_user",
        "password": "password123",
        "email": "user@example.com"
    }
    ```

2. User Login: <br>
  `POST /api/signin`<br>
    Example Request:
    ```json
    {
        "username": "example_user",
        "password": "password123"
    }
    ```

3. Retrieve Preferences: <br>
  `GET /api/preferences` <br>
  Requires Bearer Token in Authorization Header.

4. Update Preferences: <br>
  `PUT /api/preferences`<br>
  Requires Bearer Token in Authorization Header.<br><br>
    Example Payload:
    ```json
    {
        "categories" : ["Technology", "Health"]
    }
   
5. Fetch News: <br>
  `GET /api/news`<br>
  Requires Bearer Token in Authorization Header.<br><br>
  Fetches articles based on the user's preferences.

<br>

## Third-Party API Used:

Third party API by <i>GNews API</i> is used to fetch news articles.<br>
Website : https://gnews.io/docs/v4#introduction <br>

> Signing up is required on these platforms to obtain API keys.

<br>
URL : https://gnews.io/api/v4/top-headlines?category=business,entertainment&lang=en&apikey={API_KEY}
<br><br>

Parameters:
  - <b>category</b>(categories of news articles): can be one of the following -> general, world, nation, business, technology, entertainment, sports, science and health
  - <b>lang</b>(language of news articles): can be one of the following -> Arabic	[ar],
Chinese	[zh],
Dutch	[nl],
English	[en],
French	[fr],
German	[de],
Greek	[el],
Hebrew	[he],
Hindi	[hi],
Italian	[it],
Japanese	[ja],
Malayalam	[ml],
Marathi	[mr],
Norwegian	[no],
Portuguese	[pt],
Romanian	[ro],
Russian	[ru],
Spanish	[es],
Swedish	[sv],
Tamil	[ta],
Telugu	[te],
Ukrainian	[uk]
  - <b>apikey</b> : API Key provided by GNews after signup

<br>

## Steps to setup the project: <br>

1. Pre-requisites:
    - Java 17 or later
    - IntelliJ or similar IDE
    - Maven
  
2. Clone the GitHub Project locally.
3. Create 2 environment variables in System variables: <br>
  i.  API_KEY : Api key provided by GNews API after creating account. <br>
  ii. SECRET_KEY : Secret key to be used while generating token.
4. Run the <b>NewsAggregatorAppApplication.java</b> class to start the Springboot project using embedded Apache Tomcat server.
