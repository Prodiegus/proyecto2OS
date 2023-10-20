public class Main {
    public static void main(String[] args) {
        Switch sw = new Switch();
        Router r = new Router();
        // haremos un menu con un switch para que el usuario pueda elegir que hacer
        /**
         * 1. Agregar un consumidor
         * 2. Agregar un productor
         * 0. Salir
         */
        while (true) {
            System.out.println("1. Agregar un consumidor");
            System.out.println("2. Agregar un productor");
            System.out.println("0. Salir");
            int opcion = Integer.parseInt(System.console().readLine());
            switch (opcion) {
                case 1:
                    Consumidor c = new Consumidor();
                    sw.addConsumidor(c);
                    break;
                case 2:
                    Productor p = new Productor();
                    sw.addProductor(p);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}