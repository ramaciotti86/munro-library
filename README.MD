Munro Library API
===========================================

The goal of this solution is to create a simple API which other software can use to sort and filter the munro data.

What did I use in this project
--------------------------
* @RequestMapping
* @Configuration
  * Load CSV File once (CsvLoadConfig)
* Lombok
  * turn dev life easier :)
  * lombok.config at root folder
* Content Negotiation - Support for Response Content Type as JSON and XML
  * find the dependency on pom.xml - jackson-dataformat-xml 
* HTTP Requests
  * find requests under "test/httpRequests"

Findings
--------------------------
<p>My findings while developing this app </p>
