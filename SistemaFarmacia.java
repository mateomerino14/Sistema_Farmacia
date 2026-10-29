import java.util.ArrayList;
import java.util.List;

abstract class Medicamento{
    protected String nombre;
    protected double precio;
    public Medicamento(String nombre,double precio){
        this.nombre=nombre;
        this.precio=precio;
    }
    public abstract void dispensar();
}

class MedicamentoLibre extends Medicamento{
    public MedicamentoLibre(String nombre,double precio){
        super(nombre,precio);
    }
    @Override
    public void dispensar(){
        System.out.println("Se entrego "+nombre+" sin receta");
    }
}

class MedicamentoControlado extends Medicamento{
    public MedicamentoControlado(String nombre,double precio){
        super(nombre,precio);
    }
    @Override
    public void dispensar(){
        System.out.println("Se verifico la receta y se entrego "+nombre);
    }
}

// Singleton: Inventario tiene una sola instancia para que toda la farmacia trabaje con el mismo stock
class Inventario{
    private static Inventario instancia;
    private List<Medicamento> medicamentos=new ArrayList<>();
    private Inventario(){
    }
    public static Inventario obtenerInstancia(){
        if(instancia==null){
            instancia=new Inventario();
        }
        return instancia;
    }
    public void agregar(Medicamento medicamento){
        medicamentos.add(medicamento);
        System.out.println("Se registro "+medicamento.nombre+" en el inventario");
    }
    public void quitar(Medicamento medicamento){
        medicamentos.remove(medicamento);
    }
    public int cantidad(){
        return medicamentos.size();
    }
}

// Strategy: MetodoPago define una forma comun de pagar y cada clase implementa su propia estrategia
interface MetodoPago{
    void pagar(double monto);
}

// Strategy: estrategia de pago en efectivo
class PagoEfectivo implements MetodoPago{
    @Override
    public void pagar(double monto){
        System.out.println("Se pago "+monto+" bolivianos en efectivo");
    }
}

// Strategy: estrategia de pago con QR
class PagoQR implements MetodoPago{
    @Override
    public void pagar(double monto){
        System.out.println("Se pago "+monto+" bolivianos con QR");
    }
}

interface Notificable{
    void notificar(String mensaje);
}

class Cliente implements Notificable{
    private String nombre;
    public Cliente(String nombre){
        this.nombre=nombre;
    }
    @Override
    public void notificar(String mensaje){
        System.out.println("Notificacion de "+mensaje+" a "+nombre);
    }
}

// Adapter: ServicioSMS es un servicio externo con un metodo distinto que no es compatible con Notificable
class ServicioSMS{
    public void enviarSMS(String numero,String texto){
        System.out.println("Se envio un SMS al "+numero+" por la "+texto);
    }
}

// Adapter: AdaptadorSMS convierte la llamada a notificar en una llamada a enviarSMS
class AdaptadorSMS implements Notificable{
    private ServicioSMS servicio=new ServicioSMS();
    private String numero;
    public AdaptadorSMS(String numero){
        this.numero=numero;
    }
    @Override
    public void notificar(String mensaje){
        servicio.enviarSMS(numero,mensaje);
    }
}

class GestorVentas{
    private Inventario inventario=Inventario.obtenerInstancia();
    public void vender(Medicamento medicamento,MetodoPago pago,Notificable notificable){
        medicamento.dispensar();
        pago.pagar(medicamento.precio);
        inventario.quitar(medicamento);
        notificable.notificar("compra de "+medicamento.nombre);
    }
}

// Clase principal para ejecutar el sistema
public class SistemaFarmacia{
    public static void main(String[] args){
        Inventario inventario=Inventario.obtenerInstancia();
        Medicamento paracetamol=new MedicamentoLibre("Paracetamol de 500 miligramos",15.50);
        Medicamento amoxicilina=new MedicamentoControlado("Amoxicilina de 500 miligramos",42.50);
        inventario.agregar(paracetamol);
        inventario.agregar(amoxicilina);
        GestorVentas ventas=new GestorVentas();
        ventas.vender(paracetamol,new PagoEfectivo(),new Cliente("Mateo Merino"));
        ventas.vender(amoxicilina,new PagoQR(),new AdaptadorSMS("71234567"));
        System.out.println("Quedan "+inventario.cantidad()+" medicamentos en el inventario");
    }
}