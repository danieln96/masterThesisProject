#MASTER THESIS

##MODULES:
- dto
- reactive
- servlet

##Usage

##REACTIVE:
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

##SERVLET:
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

##GATLING TESTS:
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

#####report in target/gatling