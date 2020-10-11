MASTER THESIS

MODULES:
- dto
- reactive
- servlet

<h3>Usage</h3>

<h4>REACTIVE:</h4>
- cd reactive
- mvn clean install
- docker build -t master-thesis/reactive .
- docker-compose up

<h4>SERVLET:</h4>
- cd servlet
- mvn clean install
- docker build -t master-thesis/servlet .
- docker-compose up

<h4>GATLING TESTS:</h4>
- cd performance-tests
- mvn clean gatling:test -Dgatling.simulationClass=pl.sggw.niczyporuk.performance.tests.simulations.CommentSimulation
- mvn clean gatling:test -Dgatling.simulationClass=pl.sggw.niczyporuk.performance.tests.simulations.PostSimulation
- report in target/gatling