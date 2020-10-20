<h1>MASTER THESIS</h1>

MODULES:
- dto
- reactive
- servlet

<h2>Usage</h2>

<h2>REACTIVE:</h2>
```bash
cd reactive
```
```bash
mvn clean install
```
```bash
docker build -t master-thesis/reactive .
```
```bash
docker-compose up
```

<h2>SERVLET:</h2>
```bash
cd servlet
```
```bash
mvn clean install
```
```bash
docker build -t master-thesis/servlet .
```
```bash
docker-compose up
```

<h2>GATLING TESTS:</h2>
```bash
cd performance-tests
```

```bash
mvn clean gatling:test -Dgatling.simulationClass=pl.sggw.niczyporuk.performance.tests.simulations.InvalidCommentSimulation
```
```bash
mvn clean gatling:test -Dgatling.simulationClass=pl.sggw.niczyporuk.performance.tests.simulations.ValidCommentSimulation
```
```bash 
mvn clean gatling:test -Dgatling.simulationClass=pl.sggw.niczyporuk.performance.tests.simulations.PostSimulation
```

<h3>report in target/gatling</h3>