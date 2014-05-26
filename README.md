Traffic Incidents Monitor
=======

Innation team: 
* Albert Bergés
* Carlos de Dios

Repository:
https://github.com/rhizomik/ination

Google App Engine URL:
https://alertestransit.appspot.com

Traffic Incidents Monitor

The objective of this subproject is to develop a component of the Google App Engine application for personalised traffic incidents alerts. This component is responsible for monitoring the traffic incidents published at:

- http://dadesobertes.gencat.cat/ca/cercador/detall-cataleg/?id=430

This is an XML document ("incidenciesGML.xml") that changes at least daily and that should be monitored to create incident instances in the application. The monitor component periodically downloads this file and processes it using an XQuery to retrieve the required details for each new incident. New incident object instances are then created and persisted in the application.

For example, a XQuery Java sample code showing how to process XML from an input URL using XQuery and using the result to generate new objects see:

- https://gist.github.com/rogargon/7073e017e7b5f297e125

The monitoring should be performed automatically given a certain schedule (at least daily) using the Cron service in Google App Engine:

- https://developers.google.com/appengine/docs/java/config/cron

This component will form part of a Google App Engine that will also implement the use cases specified in the document:

- TrafficAlertsTwitter.pdf

The differences with what was initially specified are that the monitor component will be in charge of feeding the incidents to the application and that for user authentication and for sending alerts to users Google services will be used instead of Twitter ones (particularly GMail).

Finally, for a sample Google App Engine + JPA project showing how to create persistent object using JPA, concretely a Web Glossary with persistent glossary entries, see:

- https://github.com/rogargon/webglossary

Deliverable

This project intermediate deliverable should feature, at least, the monitor component and be capable of persisting new incidents. It is recommended that it provides a web service (for instance servlet at "/process") responsible for performing the scheduled task (called by cron), which also provides as HTML output a list of all the processed incidents (highlighting which ones are new and have been persisted and the discarded ones because they are already stored). Additionally, it should also provide a different service (servlet at "/list") that lists all the incidents currently stored.

The delivery will be performed through the private GitHub repository for each project team. The last commit before the deadline will be considered for evaluation purposes. The repository should feature also a README file providing at least:

The names of the team members.
The URL of the GitHub repository.
The URL where the application is deployed in Google App Engine.
Therefore, in addition to the source code in GitHub, the project should be also running in Google App Engine.

Finally, the same README file should be also delivered using Campus Virtual by one of the team members.
