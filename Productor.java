public class Productor implements Runnable {
    public Productor() {
    }

    public  void abrirTerminal() {
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();
        
        try {
            if (sistemaOperativo.contains("nix") || sistemaOperativo.contains("nux") || sistemaOperativo.contains("mac")) {
                // Comando para abrir una terminal en sistemas Unix/Linux
                ProcessBuilder pb = new ProcessBuilder("gnome-terminal");
                pb.start();
            } else if (sistemaOperativo.contains("win")) {
                // Comando para abrir una terminal en sistemas Windows
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start cmd.exe");
                pb.start();
            } else {
                System.out.println("Sistema operativo no compatible");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(){
        System.out.println("Iniciando Productor");
        // haremos un menu de acciones para este productor
        /**
         * 1. Saludar
         * 2. Despedirse
         * 0. Salir (Cierra el hilo)
         */
        while (true) {
            System.out.println("1. Saludar");
            System.out.println("2. Despedirse");
            System.out.println("0. Salir");
            int opcion = Integer.parseInt(System.console().readLine());
            switch (opcion) {
                case 1:
                    System.out.println("Hola");
                    break;
                case 2:
                    System.out.println("Adios");
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

    @Override
    public void run() {
        abrirTerminal();
        init();
    }
}
