# Spring Boot Hello World — SonarCloud CI Demo

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=chinu0105_assignment2-sonar-scan&metric=alert_status)](https://sonarcloud.io/dashboard?id=chinu0105_assignment2-sonar-scan)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=chinu0105_assignment2-sonar-scan&metric=coverage)](https://sonarcloud.io/dashboard?id=chinu0105_assignment2-sonar-scan)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=chinu0105_assignment2-sonar-scan&metric=bugs)](https://sonarcloud.io/dashboard?id=chinu0105_assignment2-sonar-scan)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=chinu0105_assignment2-sonar-scan&metric=code_smells)](https://sonarcloud.io/dashboard?id=chinu0105_assignment2-sonar-scan)

## Pipeline
| Stage | Tool |
|---|---|
| Compile | Maven |
| Test + Coverage | JUnit 5 + JaCoCo |
| Static Analysis | SonarCloud |
| Quality Gate | Enforced via `-Dsonar.qualitygate.wait=true` |
| CI | GitHub Actions |

## Endpoints
| Method | URL | Response |
|---|---|---|
| GET | `/api/hello` | `Hello, World!` |
| GET | `/api/hello/{name}` | `Hello, {name}!` |
| GET | `/actuator/health` | Spring Boot health |
