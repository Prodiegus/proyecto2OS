import java.util.ArrayList;

public class CineVictoria {
    private ArrayList<String[]> cartelera;
    private ArrayList<String[]> clientes;
    private ArrayList<String> fechas;
    private Switch switchCine;

    public CineVictoria(ArrayList<String[]> cartelera, ArrayList<String[]> clientes) {
        this.cartelera = cartelera;
        this.clientes = clientes;
        this.switchCine = new Switch(cartelera);
    }

    public void run(){
        // primero debemos obtener los dias diferentes que hay en el flujo de clientes
        getDias();
        // ahora debemos crear un gestor de salidas
        GestorSalidas gestorSalidas = new GestorSalidas(fechas);
        String detalle = "";
        String fecha = "";
        // ahora debemos imprimir los datos de la simulacion
        System.out.println("Bienvenido a Cine Victoria");
        System.out.println("Peliculas en Cartelera: "+cartelera.size());
        System.out.println("Clientes en el flujo: "+clientes.size());
        System.out.println("Dias de simulacion: "+fechas.size());
        System.out.println("Compras de estreno: "+getComprasEstreno());
        // ahora recorreremos el flujo de clientes
        for (String[] cliente : clientes) {
            fecha = cliente[0]+"-"+cliente[1]+"-"+cliente[2];
            if (cliente[5].equals("Web")) {
                // debemos enviar el cliente al switch en la vlan de web
                try {
                    detalle = switchCine.vlanWeb(cliente);
                    // el detalle lo agregamos
                    gestorSalidas.addDetalle(fecha, detalle);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (cliente[5].equals("Caja")) {
                // debemos enviar el cliente al switch en la vlan de caja
                try {
                    detalle = switchCine.vlanCaja(cliente);
                    // el detalle lo agregamos
                    gestorSalidas.addDetalle(fecha, detalle);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            fecha = cliente[0]+"-"+cliente[1]+"-"+cliente[2];
        }
        
    }

    /**
     * @return los dias diferentes que hay en el flujo de clientes
     */
    private int getDias(){
        int dias = 0;
        String date = "";
        this.fechas = new ArrayList<>();
        for (String[] cliente : clientes) {
            date = cliente[0]+"-"+cliente[1]+"-"+cliente[2];
            if (!fechas.contains(date)) {
                fechas.add(date);
                dias++;
            }
        }
        return dias;
    }

    private int getComprasEstreno(){
        int contador = 0;
        for (String[] pelicula : cartelera) {
            for (String[] cliente : clientes) {
                if (pelicula[3].equals(cliente[3]) && pelicula[0].equals(cliente[0]) && pelicula[1].equals(cliente[1]) && pelicula[2].equals(cliente[2])) {
                    contador++;
                }
            }   
        }
        return contador;
    }

    
}
