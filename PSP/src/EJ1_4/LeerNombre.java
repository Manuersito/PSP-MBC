package EJ1_4;

import java.io.IOException;



public class LeerNombre {

        public static void main(String[] args) throws IOException {


            if (args.length <= 0) {

                System.err.println("No hay argumentos: ");

                System.exit(-1);

            }

            System.out.println("nombre: "+args[0]);
            System.exit(1);


        }

    }

