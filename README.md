# Sistema de Administración de Alquiler de Vestuario - "Los Atuendos"

## Descripción
Sistema de gestión para el negocio de alquiler de vestidos para dama, caballero y disfraces, implementando patrones de diseño estructurales y de creación.

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

### Patrón de Comportamiento (Adicional)

#### 5. Priority Queue (Strategy implícito)
- **Clase**: `PrendaLavanderia` con `PriorityQueue`
- **Justificación**: Gestiona la priorización de prendas para lavandería según criterios específicos (manchas, delicadeza, orden de llegada).

## Estructura del Proyecto

```
src/
├── Main.java                    # Aplicación principal con menú interactivo
├── modelo/
│   ├── Persona.java            # Clase base para Cliente y Empleado
│   ├── Cliente.java            # Información de clientes
│   ├── Empleado.java           # Información de empleados
│   ├── Prenda.java             # Clase abstracta (Composite)
│   ├── VestidoDama.java        # Tipo específico de prenda
│   ├── TrajeCaballero.java     # Tipo específico de prenda
│   ├── Disfraz.java            # Tipo específico de prenda
│   └── ServicioAlquiler.java   # Registro de alquileres
├── factory/
│   └── PrendaFactory.java      # Factory Method para crear prendas
├── facade/
│   └── FacadeNegocio.java      # Facade para operaciones del negocio
├── negocio/
│   └── NegocioAlquiler.java    # Singleton - Lógica central del negocio
└── lavanderia/
    └── PrendaLavanderia.java   # Gestión de cola de lavandería
```

## Funcionalidades

1. **Registro de prendas**: Vestidos de dama, trajes de caballero y disfraces
2. **Registro de clientes y empleados**: Gestión de personas
3. **Registro de servicios de alquiler**: Con validación de disponibilidad
4. **Consulta de servicios**: Por número, cliente o fecha
5. **Consulta de prendas por talla**: Agrupadas por tipo
6. **Gestión de lavandería**: Registro con prioridad y envío por tandas

## Requisitos

- Java 17 o superior
- IDE compatible (IntelliJ IDEA, NetBeans, Eclipse)

## Ejecución

1. Compilar el proyecto
2. Ejecutar la clase `Main.java`
3. Interactuar con el menú de opciones

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

## Autor

Actividad 4 - Patrones de Diseño Estructurales y de Creación
