import java.util.ArrayList;

public class Caja implements Runnable {
    private ArrayList<String[]> cartelera;
    private int id;
    private String[] cliente;
    private String detalle;
    private boolean ocupado;

    public Caja(ArrayList<String[]> cartelera, int id) {
        this.cartelera = cartelera;
        this.ocupado = false;
        this.id = id;
        this.cliente = new String[6];
        this.detalle = "Hilo Caja "+id+" no iniciado";
    }

    public boolean estaOcupado() {
        return ocupado;
    }

    public void getID(){
        System.out.println("hilo caja "+id);
    }

    public void setCliente(String[] cliente){
        this.cliente = cliente;
    }

    public String getDetalle(){
        return detalle;
    }

    public Thread getHilo(){
        return new Thread(this);
    }

    @Override
    public void run() {
        System.out.println("hilo caja "+id);
        for(int i = 0; i < cartelera.size(); i++){
            if (cliente[3].equals(cartelera.get(i)[3])) {
                if(Integer.parseInt(cartelera.get(i)[4]) > Integer.parseInt(cliente[4])){
                    cartelera.get(i)[4] = String.valueOf(Integer.parseInt(cartelera.get(i)[4])-Integer.parseInt(cliente[4]));
                    this.detalle = "Hilo Caja "+this.id+" compra "+cliente[4]+" tickets de "+cliente[3];
                }else{
                    this.detalle = "Hilo Caja "+this.id+" intenta comprar "+cliente[4]+" tickets de "+cliente[3]+" - No quedan disponibles (Sold Out)";
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Caja [cartelera=" + cartelera.size() + ", cliente=" + cliente[3] + ", detalle=" + detalle + ", id=" + id
                + ", ocupado=" + ocupado + "]";
    }


}
