# Architecture — SonarCloud CI Pipeline

## Why I Built It This Way

The assignment required compiling Java, running tests, 
and publishing SonarCloud results to GitHub. I made 
three deliberate design decisions beyond the basics:

## Decision 1 — Quality Gate as a Hard Block
I kept `-Dsonar.qualitygate.wait=true` intentionally.
This means the pipeline FAILS if code doesn't meet the 
Quality Gate — merge is blocked. This is shift-left 
enforcement, not just reporting.

In my current role at Siemens I enforce similar gates 
across 15 engineering teams. A passing pipeline with 
failing quality is worse than a failing pipeline — it 
creates false confidence.

## Decision 2 — JaCoCo Before Sonar
JaCoCo runs during `mvn test` and generates an XML 
coverage report. SonarCloud consumes that XML — it 
does not measure coverage itself. Getting this order 
right (test → jacoco report → sonar:sonar) is a 
common failure point I debugged during setup.

Current coverage: 64.3% (controller layer only).
To reach 80%: add service layer integration tests.

## Decision 3 — Dual Cache
Both Maven local repo (~/.m2) and SonarCloud scanner 
data (~/.sonar/cache) are cached separately. This 
reduces pipeline runtime from ~4min to ~90sec on 
repeat runs.

## Pipeline Flow
push/PR → checkout (depth 0) → JDK 17 → cache → 
compile → test + JaCoCo → upload artifacts → 
SonarCloud scan → Job Summary

## What I Would Add in Production
- OIDC-based SonarCloud token rotation
- Coverage threshold enforcement at 80%
- PR decoration with inline code comments
- Slack notification on Quality Gate failure
