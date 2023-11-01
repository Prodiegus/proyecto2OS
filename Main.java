public class Main {
    public static void main(String[] args) {
        // por alrgumentos se pedira el numero del flujo a ejecutar y este sera correspondiente a la misma cartelera
        /**
         * -flujo <numero>
         * -help
         */
        int flujo = 0;
        if (args.length > 0) {
            if (args[0].equals("-flujo")) {
                // se ejecutara el flujo correspondiente
                flujo = Integer.parseInt(args[1]);
            } else if (args[0].equals("-help")) {
                System.out.println("Argumentos validos:");
                System.out.println("-flujo <numero>");
                System.out.println("-help");
            } else {
                System.out.println("Argumentos no validos");
                System.out.println("Argumentos validos:");
                System.out.println("-flujo <numero>");
                System.out.println("-help");
                System.exit(0);
            }
        }else{
            System.out.println("Argumentos no validos");
            System.out.println("Argumentos validos:");
            System.out.println("-flujo <numero>");
            System.out.println("-help");
            System.exit(0);
        }
        // cargaremos el objeto encargado de orgenar los datos de los archivos
        try {
            FileParser parser = new FileParser(flujo);
            CineVictoria cine = new CineVictoria(parser.getCartelera(), parser.getClientes());
            cine.run();
        } catch (Exception e) {
            System.err.println("Error al cargar los archivos: \n"+e.getMessage());
        }
    }
}