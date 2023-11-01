import java.util.ArrayList;

public class CineVictoria {
    private ArrayList<String[]> cartelera;
    private ArrayList<String[]> clientes;

    public CineVictoria(ArrayList<String[]> cartelera, ArrayList<String[]> clientes) {
        this.cartelera = cartelera;
        this.clientes = clientes;
    }

    public void run(){
        System.out.println("Bienvenido a Cine Victoria");
        System.out.println("Cartelera:");
        printCartelera();
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
