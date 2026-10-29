# Sistema de Gestión de Farmacia con Patrones de Diseño

Autor: Mateo Alejandro Merino Vidal

Sistema de consola hecho en Java para una farmacia pequeña. Permite registrar medicamentos en el inventario, venderlos según su tipo, cobrar con distintos métodos de pago y notificar al cliente de su compra.

## Módulos

- **Inventario**: guarda los medicamentos disponibles de la farmacia.
- **Ventas**: entrega el medicamento según su tipo, libre o controlado.
- **Pagos**: permite cobrar en efectivo o con QR.
- **Notificaciones**: avisa al cliente en la farmacia o por SMS.

## Patrones de diseño usados

| Tipo | Patrón | Clases |
|---|---|---|
| Creacional | Singleton | `Inventario` |
| Estructural | Adapter | `ServicioSMS`, `AdaptadorSMS` |
| Comportamiento | Strategy | `MetodoPago`, `PagoEfectivo`, `PagoQR` |

## Cómo ejecutarlo

Se necesita tener instalado el JDK de Java. El proyecto fue desarrollado en BlueJ, donde se abre el archivo, se compila y se ejecuta el método main de la clase SistemaFarmacia. También se puede ejecutar desde la terminal con los siguientes comandos.

```
javac SistemaFarmacia.java
java SistemaFarmacia
```

## Salida esperada

```
Se registro Paracetamol de 500 miligramos en el inventario
Se registro Amoxicilina de 500 miligramos en el inventario
Se entrego Paracetamol de 500 miligramos sin receta
Se pago 15.5 bolivianos en efectivo
Notificacion de compra de Paracetamol de 500 miligramos a Mateo Merino
Se verifico la receta y se entrego Amoxicilina de 500 miligramos
Se pago 42.5 bolivianos con QR
Se envio un SMS al 71234567 por la compra de Amoxicilina de 500 miligramos
Quedan 0 medicamentos en el inventario
```