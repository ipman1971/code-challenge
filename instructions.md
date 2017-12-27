# Instrucciones de uso:

## Requisitos

+ Maven 3.3.9 o superior
+ JDK 8

# Para construir el proyecto

```
mvn clean package
```

## Para ejecutar el proyecto

```
mvn springboot:run
```

o mediante

```
cd target
java -jar code-challenge-1.0.0-SNAPSHOT.jar
```

## Para ejecutar como contenedor docker

+ Requisito: tener instalado Docker y conexión a internet

```
mvn clean package -P BUILD_DOCKER
```

y luego ejecutar

```
docker run -d --rm -p 8085:8085 ipman1971/code-challenge
```

## Trazas

El microservicio muestra toda la informacion solicitada, especialmente los Eventos generados a partir de los envios y su seguimiento por el sistema de log, de la forma:

```
[CONTROLLER]: data recived => Shipment{reference=ABCD123456, parcels=[Parcel{weight=1, width=10, height=10, length=0}, Parcel{weight=2, width=20, height=20, length=0}]}
[CONTROLLER]: data recived => Tracking{parcels=2, status=WAITING_IN_HUB, weight=2, reference=ABCD123456}
[REPOSITORY]: executed method: create() => Event{status=INCOMPLETE, reference=ABCD123456}
[SERVICE]: generated event => Event{status=INCOMPLETE, reference=ABCD123456}
[CONTROLLER]: data recived => Tracking{parcels=0, status=DELIVERED, weight=30, reference=ABCD123456}
[REPOSITORY]: executed method: create() => Event{status=INCOMPLETE, reference=ABCD123456}
[SERVICE]: generated event => Event{status=INCOMPLETE, reference=ABCD123456}

```
