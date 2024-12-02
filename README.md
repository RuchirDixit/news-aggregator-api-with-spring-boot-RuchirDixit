# News Aggregator API project

Java & Springboot (Backend-only) News aggregator project to register new user, authenticate the user by JWT during login and provide token and fetch news using Third-party API for the categories set by User.

<br>
<hr>

### Endpoints:
1. <b>POST /api/register:</b> Register a new user.

2. <b>POST /api/login:</b> Log in a user and get the JWT token.

3. <b>GET /api/preferences:</b> Retrieve the news preferences for the logged-in user by using the generated Token.

4. <b>PUT /api/preferences:</b> Update the news preferences for the logged-in user by using the generated Token.

5. <b>GET /api/news:</b> Fetch news articles based on the logged-in user's preferences.

<hr>

### Third-Party API Used:

Third party API by <i>GNews API</i> is used to fetch news articles.<br>
Website : https://gnews.io/docs/v4#introduction

<br><br>
URL : https://gnews.io/api/v4/top-headlines?category=business,entertainment&lang=en&apikey={API_KEY}
<br><br>
Parameters:
  - category(categories of news articles): can be one of the following -> general, world, nation, business, technology, entertainment, sports, science and health
  -  lang(language of news articles): can be one of the following -> Arabic	ar,
Chinese	zh,
Dutch	nl,
English	en,
French	fr,
German	de,
Greek	el,
Hebrew	he,
Hindi	hi,
Italian	it,
Japanese	ja,
Malayalam	ml,
Marathi	mr,
Norwegian	no,
Portuguese	pt,
Romanian	ro,
Russian	ru,
Spanish	es,
Swedish	sv,
Tamil	ta,
Telugu	te,
Ukrainian	uk
  - apikey : API Key provided by GNews after signup


<hr>

### Steps to setup the project: <br>
1. Clone the GitHub Project locally.
2. Create 2 environment variables in System variables: <br>
  i.  API_KEY : Api key provided by GNews API after creating account. <br>
  ii. SECRET_KEY : Secret key to be used while generating token.
3. Run the <b>NewsAggregatorAppApplication.java</b> class to start the Springboot project using embedded Apache Tomcat server.
