# Sistema de Administración de Alquiler de Vestuario - "Los Atuendos"

## Descripción
Sistema de gestión para el negocio de alquiler de vestidos para dama, caballero y disfraces, implementando patrones de diseño de creación, estructurales y de comportamiento.

## Patrones de Diseño Implementados

### Patrones de Creación

#### 1. Singleton
- **Clase**: `NegocioAlquiler`
- **Justificación**: Garantiza una única instancia del negocio, centralizando el control de todas las operaciones y manteniendo la consistencia de los datos.

#### 2. Factory Method
- **Clase**: `PrendaFactory`
- **Justificación**: Encapsula la creación de diferentes tipos de prendas (VestidoDama, TrajeCaballero, Disfraz), facilitando la extensión del sistema con nuevos tipos.

### Patrones Estructurales

#### 3. Composite
- **Clase base**: `Prenda` (abstracta)
- **Clases hoja**: `VestidoDama`, `TrajeCaballero`, `Disfraz`
- **Justificación**: Permite tratar uniformemente diferentes tipos de prendas mediante una interfaz común, simplificando operaciones sobre colecciones heterogéneas.

#### 4. Facade
- **Clase**: `FacadeNegocio`
- **Justificación**: Proporciona una interfaz simplificada para las operaciones complejas del negocio, ocultando la complejidad de la lógica interna y facilitando el uso del sistema.

### Patrones de Comportamiento (Actividad 6)

#### 5. Iterator
- **Clases**: `PrendaIterator`, `PrendaCollection`, `PrendaIteratorImpl`, `PrendaIteratorPorTipo`
- **Justificación**: Permite recorrer colecciones de prendas sin exponer su representación interna, facilitando diferentes formas de iteración (por tipo, disponibilidad, etc.) sin modificar la estructura de datos.

#### 6. Command
- **Clases**: `Command`, `RegistrarAlquilerCommand`, `CommandInvoker`
- **Justificación**: Encapsula operaciones de alquiler como objetos, permitiendo deshacer/rehacer operaciones, mantener historial de transacciones y parametrizar solicitudes. Fundamental para auditoría y recuperación de errores.

#### 7. Observer
- **Clases**: `PrendaObserver`, `PrendaSubject`, `NotificadorInventario`, `NotificadorEmail`
- **Justificación**: Notifica automáticamente cambios de estado de prendas a múltiples observadores (inventario, email, logs), manteniendo bajo acoplamiento entre componentes y facilitando la extensión con nuevos tipos de notificaciones.

#### 8. Strategy
- **Clases**: `EstrategiaDescuento`, `CalculadoraPrecio`, `SinDescuento`, `DescuentoClienteFrecuente`, `DescuentoPorCantidad`
- **Justificación**: Define familia de algoritmos de descuento intercambiables, permitiendo cambiar la estrategia de pricing en tiempo de ejecución según el tipo de cliente o promoción activa, sin modificar el código del servicio.

## Estructura del Proyecto

```
src/
├── Main.java                           # Aplicación principal con menú interactivo
├── demo/
│   └── DemoPatronesComportamiento.java # Demostración de patrones
├── modelo/
│   ├── Persona.java                   # Clase base para Cliente y Empleado
│   ├── Cliente.java                   # Información de clientes
│   ├── Empleado.java                  # Información de empleados
│   ├── Prenda.java                    # Clase abstracta (Composite)
│   ├── VestidoDama.java               # Tipo específico de prenda
│   ├── TrajeCaballero.java            # Tipo específico de prenda
│   ├── Disfraz.java                   # Tipo específico de prenda
│   └── ServicioAlquiler.java          # Registro de alquileres
├── factory/
│   └── PrendaFactory.java             # Factory Method para crear prendas
├── facade/
│   └── FacadeNegocio.java             # Facade para operaciones del negocio
├── negocio/
│   └── NegocioAlquiler.java           # Singleton - Lógica central del negocio
├── lavanderia/
│   └── PrendaLavanderia.java          # Gestión de cola de lavandería
├── iterator/                           # Patrón Iterator
│   ├── PrendaIterator.java            # Interfaz del iterador
│   ├── PrendaIteratorImpl.java        # Iterador concreto
│   ├── PrendaIteratorPorTipo.java     # Iterador filtrado
│   └── PrendaCollection.java          # Colección iterable
├── command/                            # Patrón Command
│   ├── Command.java                   # Interfaz del comando
│   ├── RegistrarAlquilerCommand.java  # Comando concreto
│   └── CommandInvoker.java            # Invocador con historial
├── observer/                           # Patrón Observer
│   ├── PrendaObserver.java            # Interfaz del observador
│   ├── PrendaSubject.java             # Sujeto observable
│   ├── NotificadorInventario.java     # Observador concreto
│   └── NotificadorEmail.java          # Observador concreto
└── strategy/                           # Patrón Strategy
    ├── EstrategiaDescuento.java       # Interfaz de estrategia
    ├── CalculadoraPrecio.java         # Contexto
    ├── SinDescuento.java              # Estrategia concreta
    ├── DescuentoClienteFrecuente.java # Estrategia concreta
    └── DescuentoPorCantidad.java      # Estrategia concreta
```

## Funcionalidades

### Funcionalidades Base (AA4)
1. **Registro de prendas**: Vestidos de dama, trajes de caballero y disfraces
2. **Registro de clientes y empleados**: Gestión de personas
3. **Registro de servicios de alquiler**: Con validación de disponibilidad
4. **Consulta de servicios**: Por número, cliente o fecha
5. **Consulta de prendas por talla**: Agrupadas por tipo
6. **Gestión de lavandería**: Registro con prioridad y envío por tandas

### Nuevas Funcionalidades (AA6)
7. **Iteración flexible de prendas**: Recorrido por tipo, disponibilidad, etc.
8. **Deshacer/Rehacer operaciones**: Historial de comandos ejecutados
9. **Notificaciones automáticas**: Sistema de observadores para eventos
10. **Cálculo dinámico de descuentos**: Estrategias intercambiables de pricing

## Requisitos

- Java 17 o superior
- IDE compatible (IntelliJ IDEA, NetBeans, Eclipse)

## Ejecución

### Aplicación Principal
1. Compilar el proyecto
2. Ejecutar la clase `Main.java`
3. Interactuar con el menú de opciones

### Demostración de Patrones
Para ver los patrones de comportamiento en acción:
```bash
java demo.DemoPatronesComportamiento
```

Esta clase ejecuta ejemplos de:
- Patrón Iterator: Recorrido de colecciones
- Patrón Command: Deshacer/rehacer operaciones
- Patrón Observer: Notificaciones automáticas
- Patrón Strategy: Cálculo de descuentos

## Datos de Prueba

El sistema carga automáticamente:
- 1 empleado (E001 - Juan Pérez)
- 1 cliente (C001 - María García)
- 3 prendas (VD001, TC001, DF001)

## Convenciones de Código

- Uso de modificadores de acceso apropiados
- Empaquetamiento por responsabilidades
- Manejo de excepciones
- Nomenclatura según convenciones Java
- Documentación en código

## Documentación Adicional

- **demo/DemoPatronesComportamiento.java**: Ejemplos de uso de cada patrón

## Actividades

- **Actividad 4**: Patrones de Creación y Estructurales
- **Actividad 6**: Patrones de Comportamiento
