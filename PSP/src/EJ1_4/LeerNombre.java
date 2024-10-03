package EJ1_4;

import java.io.IOException;



public class LeerNombre {

        public static void main(String[] args) throws IOException {

            //mira si hay argumentos en args
            if (args.length <= 0) {
            //si no existe salta -1
                System.err.println("No hay argumentos: ");

                System.exit(-1);

            }
            //si existe salta 1
            System.out.println("nombre: "+args[0]);
            System.exit(1);


        }

    }

