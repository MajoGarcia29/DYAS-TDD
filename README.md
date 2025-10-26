# Proyecto: Registraduría - Taller TDD

## Descripción
Servicio simple de registraduría (dominio) que valida e inscribe votantes. El proyecto está orientado a practicar TDD (Red → Green → Refactor) y pruebas unitarias en una arquitectura limpia (dominio sin dependencias de infraestructura).

## Reglas de negocio implementadas
- `person == null` -> `INVALID`
- `alive = false` -> `DEAD`
- `id <= 0` -> `INVALID`
- `age < 0 || age > 120` -> `INVALID_AGE`
- `age < 18` -> `UNDERAGE`
- `id` duplicado -> `DUPLICATED`
- otherwise -> `VALID`

## Estructura del proyecto
src/
main/java/edu/eci/cvds/tdd/registry/
Person.java
Gender.java
RegisterResult.java
Registry.java
test/java/edu/eci/cvds/tdd/registry/
RegistryTest.java
pom.xml
README.md
defectos.md
integrantes.txt
.gitignore


## Cómo compilar y ejecutar pruebas
1. Compilar:
```bash
mvn clean compile

Ejecutar pruebas:

mvn test

Generar reporte de cobertura JaCoCo:

mvn verify
# o
mvn jacoco:report
---

-Luego abrir target/site/jacoco/index.html en un navegador para ver la cobertura.

-Luego abrir target/site/jacoco/index.html en un navegador para ver la cobertura.

Evidencia de TDD (Historia TDD)

Ejemplo de commits que documentan el ciclo TDD -:

-test: add null person test (RED)

-feat: return INVALID when person is null (GREEN)

-refactor: extract constants MIN_AGE/MAX_AGE (REFACTOR)

-test: add dead person test (RED)

-feat: handle dead person (GREEN)

-refactor: rename method registerVoter (REFACTOR)

-test: add duplicate id test (RED)

-feat: add registeredIds set to detect duplicates (GREEN)


##  Matriz de pruebas (Clases de equivalencia y valores límite)

| Test (método) | Entrada (`Person`) | Resultado esperado |
|----------------|--------------------|--------------------|
| `shouldReturnInvalidWhenPersonIsNull` | `null` | `INVALID` |
| `shouldRejectDeadPerson` | `alive = false` | `DEAD` |
| `shouldRejectWhenIdIsZeroOrNegative` | `id = 0` o `id = -5` | `INVALID` |
| `shouldRejectInvalidNegativeAge` | `age = -1` | `INVALID_AGE` |
| `shouldRejectAgeOverMax` | `age = 121` | `INVALID_AGE` |
| `shouldRejectUnderageAt17` | `age = 17` | `UNDERAGE` |
| `shouldAcceptAdultAt18` | `age = 18` | `VALID` |
| `shouldAcceptMaxAge120` | `age = 120` | `VALID` |
| `shouldDetectDuplicatedId` | mismo `id` registrado dos veces | `DUPLICATED` (en el segundo intento) |

---

##  Cobertura

Genera el reporte con:

```bash
mvn verify
# o
mvn jacoco:report
Genera el reporte con mvn verify o mvn jacoco:report.

Revisa target/site/jacoco/index.html.

Objetivo: ≥ 80% global y ≥ 80% en edu.eci.cvds.tdd.registry. Si falta cobertura, añade tests para los caminos no cubiertos (por ejemplo: combinaciones de edad/id/estado).

Gestión de defectos

Ver defectos.md.

Reflexiones / Qué no se cubrió

No se cubrieron pruebas de concurrencia (Registry no es thread-safe).

No se cubrieron formatos/longitudes del name.

No se probaron casos extremos de gender más allá del enum.

Para facilitar pruebas futuras se sugiere extraer una interfaz RegistryRepository e inyectarla (mockear el repo en tests).

Cómo mejorar Registry para pruebas

Extraer RegistryRepository (interfaz) para la persistencia; inyectarla en Registry y mockear en tests unitarios.

Hacer Registry thread-safe si será usado en entornos concurrentes.


---