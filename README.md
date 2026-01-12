# Benchmark de Performances des Web Services REST

Ce projet a pour objectif de comparer les performances de différentes implémentations d'API REST en Java, en mettant l'accent sur les performances de jointures et de requêtes.

## 📋 Aperçu du Projet

Le benchmark évalue trois variantes d'implémentation :

1. **Variante A** : Jersey + JPA (Hibernate)
2. **Variante C** : Spring Boot + @RestController
3. **Variante ** : Spring Boot + Spring Data REST
## 🚀 Prérequis

- Java 17 ou supérieur
- Maven 3.8+
- PostgreSQL 13+
- Python 3.8+ (pour le générateur de données)
- JMeter 5.6+ (pour les tests de charge)

. **Générer les données de test**
   ```bash
   cd database
   python data-generator.py
   ```

## 🏃 Exécution

### Variante A (Jersey)
```bash
cd varianteA
mvn clean install
java -jar target/varianteA-1.0-SNAPSHOT.jar
```

### Variante B (Spring Boot JPA)
```bash
cd varianteB
mvn spring-boot:run
```

### Variante C (Spring Boot JDBC)
```bash
cd varianteC
mvn spring-boot:run
```

## 🧪 Tests de Performance

Les scénarios de test JMeter se trouvent dans le dossier `jmeter/test-plans/` :

1. `1-basic-crud.jmx` - Tests CRUD de base
2. `2-join-filter.jmx` - Tests de jointures et filtres
3. `3-concurrent-requests.jmx` - Tests de charge concurrente

## 📊 Métriques Surveillées

- Temps de réponse moyen
- Débit (requêtes/seconde)
- Utilisation CPU/Mémoire
- Nombre d'erreurs
- Temps de réponse des requêtes SQL

## 📁 Structure du Projet

```
.
├── database/           # Scripts de génération de données
├── jmeter/             # Fichiers de test JMeter
│   ├── data/           # Données pour les tests
│   └── test-plans/     # Scénarios de test
├── monitoring/         # Scripts de surveillance
├── varianteA/          # Implémentation Jersey + JPA
├── varianteB/          # Implémentation Spring Boot + JPA
└── varianteC/          # Implémentation Spring Boot + JDBC
```

