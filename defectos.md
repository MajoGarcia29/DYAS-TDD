### Defecto 01
- Caso: edad = -1
- Esperado: INVALID_AGE
- Obtenido: VALID (antes de agregar validación)
- Causa probable: falta de validación de límites en Registry.registerVoter
- Estado: Cerrado

### Defecto 02 
- Caso: registro duplicado (mismo id)
- Esperado: DUPLICATED en el segundo intento
- Obtenido: VALID (antes de introducir la verificación de ids duplicados)
- Causa probable: falta de almacenamiento/consulta de IDs ya registrados
- Estado: Cerrado
