# Laboratorio 2 — MSG Foundation (JavaFX)
## Parte D: Auditoría Individual y Respuestas de Cierre

---

### Parte D — Auditoría Individual

#### 1. Cuatro problemas identificados en el código generado por IA

1. **Inversión de Dependencias (DIP) y Acoplamiento Directo (Líneas 3 y 4):**
   * *Código:*
     ```java
     MortgageRepository mortgages = new InMemoryMortgageRepository();
     InvestmentRepository investments = new InMemoryInvestmentRepository();
     ```
   * *Principio / Regla violada:* Viola el principio de Inversión de Dependencias (DIP) y la regla explícita de arquitectura del laboratorio: *"FundsController no hace new de casos de uso ni de repositorios: los recibe"*.
   * *Consecuencia:* Acopla la interfaz gráfica a implementaciones concretas en memoria. Además, crea repositorios vacíos desacoplados del estado real de la aplicación, haciendo imposible visualizar los datos persistidos o precargados.

2. **Violación de Responsabilidad Única (SRP) y Fuga de Lógica de Negocio (Líneas 6 a 14):**
   * *Código:*
     ```java
     for (Investment i : investments.findAll()) { total += i.weeklyReturn(); }
     for (Mortgage m : mortgages.findAll()) { total += m.weeklyCoupleContribution(); total -= m.weeklyEscrow(); }
     ```
   * *Principio / Regla violada:* Violación de la separación de capas y de SRP. La capa de presentación (`infrastructure.ui`) usurpa y duplica la lógica de negocio que le corresponde exclusivamente a la capa de aplicación (`EstimateFundsForWeek`).
   * *Consecuencia:* Duplicación de lógica entre la aplicación de consola y la aplicación gráfica. Si las reglas de negocio o deducciones cambian, se deben modificar múltiples archivos de interfaz, elevando el riesgo de inconsistencias.

3. **Uso de Números Mágicos y Fórmulas en la Vista (Línea 15):**
   * *Código:*
     ```java
     total -= 39000.0 / 52;
     ```
   * *Principio / Regla violada:* Viola la restricción arquitectónica: *"Ningún archivo de infrastructure.ui contiene una fórmula, ni el número 52, ni 0.28"*.
   * *Consecuencia:* Código frágil y poco mantenible. Si los gastos operativos anuales ($39,000) o las semanas fiscales (52) se parametrizan o varían, la interfaz fallará silenciosamente mostrando cifras erróneas.

4. **Violación del Contrato de Presentación y Formato (Línea 16):**
   * *Código:*
     ```java
     totalLabel.setText("$ " + total);
     ```
   * *Principio / Regla violada:* Omite el uso del componente centralizado `MoneyFormat` acordado en el contrato de equipo.
   * *Consecuencia:* Salida sin formato estándar de dos cifras decimales ni localización monetaria controlada (e.g., `-750.0` en lugar de `$ -750.00`), incumpliendo los criterios de aceptación y las pruebas de usuario.

---

#### 2. Resultado en Pantalla y Causa de la Discrepancia

* **Valor mostrado:** Se visualiza **`$ -750.0`** (o `$ -750.00` según el formateo implícito de Double).
* **Causa:** Al instanciar nuevos repositorios (`new InMemory...`), estos inician sin datos (tamaño 0). Como las colecciones están vacías, los ciclos `for` nunca suman retornos ni contribuciones. La variable `total` permanece en `0` hasta la deducción final:
  $$0 - \frac{39000.0}{52} = -750.0$$
  No muestra `1160.00` porque los datos reales cargados durante el arranque de la aplicación nunca fueron inyectados al controlador.

---

#### 3. Reescritura del Método (5 líneas o menos)

```java
@FXML
private void onEstimate() {
    totalLabel.setText(MoneyFormat.format(estimateFunds.execute()));
}
```

*Nota: La dependencia `EstimateFundsForWeek estimateFunds` se inyecta a través del constructor de `FundsController` conforme al contrato arquitectónico.*

---

### Cierre y Entrega (Preguntas de Discusión Grupal)

1. **¿Cuántos archivos de `domain` y `application` tocaron? Si la respuesta es cero, ¿qué dice eso del diseño del Laboratorio 1?**
   * *Respuesta:* Se tocaron **cero** archivos. Esto demuestra un alto nivel de adherencia al Principio Abierto/Cerrado (OCP) y a los principios de Arquitectura Limpia: el núcleo de dominio y los casos de uso están desacoplados de detalles de entrega o frameworks visuales (JavaFX), permitiendo cambiar de consola a interfaz gráfica sin alterar la lógica de negocio.

2. **Si en la próxima clase los datos pasan a una base de datos H2, ¿qué archivos creen que cambian?**
   * *Respuesta:* Solo cambiarán componentes en la capa de `infrastructure` (nuevas implementaciones de los repositorios como `H2MortgageRepository` y `H2InvestmentRepository`) y la raíz de composición (`App.java` / `Main.java`) para cablear las dependencias. Ni las entidades de `domain` ni los casos de uso de `application` sufrirán modificaciones.

3. **En la Parte D, la IA escribió código que compila. ¿Por qué eso no basta para aceptarlo, y quién responde por lo que entra al repositorio?**
   * *Respuesta:* Que un código compile solo verifica corrección léxica y sintáctica, pero no garantiza corrección lógica, cohesión, adherencia arquitectónica, manejo de concurrencia ni cumplimiento de pruebas. La responsabilidad legal, ética y técnica recae única y exclusivamente en el **desarrollador que comete o el revisor (reviewer) que aprueba el Pull Request**, nunca en el asistente generativo.