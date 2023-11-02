import java.util.ArrayList;

public class CineVictoria {
    private ArrayList<String[]> cartelera;
    private ArrayList<String[]> clientes;
    private Switch switchCine;

    public CineVictoria(ArrayList<String[]> cartelera, ArrayList<String[]> clientes) {
        this.cartelera = cartelera;
        this.clientes = clientes;
        this.switchCine = new Switch(cartelera);
    }

    public void run(){
        String detalle = "";
        String fecha = "";
        System.out.println("Bienvenido a Cine Victoria");
        System.out.println("Peliculas en Cartelera: "+cartelera.size());
        System.out.println("Clientes en el flujo: "+clientes.size());
        System.out.println("Dias de simulacion: "+getDias());
        // ahora recorreremos el flujo de clientes
        for (String[] cliente : clientes) {
            if (cliente[5].equals("Web")) {
                try {
                    detalle = switchCine.vlanWeb(cliente);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (cliente[5].equals("Caja")) {
                try {
                    detalle = switchCine.vlanCaja(cliente);
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
        ArrayList<String> fecha = new ArrayList<>();
        for (String[] cliente : clientes) {
            date = cliente[0]+"-"+cliente[1]+"-"+cliente[2];
            if (!fecha.contains(date)) {
                fecha.add(date);
                dias++;
            }
        }
        return dias;
    }

    private void printCartelera(){
        for (String[] pelicula : cartelera) {
            System.out.println("Pelicula: "+pelicula[3]);
            System.out.println("-\tEstreno: "+pelicula[0]+"-"+pelicula[1]+"-"+pelicula[2]);
            System.out.println("-\tTickets disponibles: "+pelicula[4]);
            System.out.println("");
        }
    }
    
}
