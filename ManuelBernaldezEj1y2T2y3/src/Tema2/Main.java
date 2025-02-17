package Tema2;

public class Main {

    //Entendi que Pide crear 2 personas que son hilos las cuales hacen operaciones que estan
    //en la clase saldo y tienen que estar sincronizados para que no de errores y
    // manejar errores de que no se pase de 700 ni baje de 0

    //Funciona ya que añade y retira dinero pero no consegui que la cantidad sea aleatoria
    //en cada operacion ya que por ejemplo la persona 1 siempre añade y retira la cantidad x
    //y la persona 2 siempre añade y retira la cantidad y


    public static void main(String[] args) {
        Saldo saldo = new Saldo(40);

        System.out.println("Saldo inicial: " + saldo);


        //Creamos y hacemos las operaciones con las personas

        Persona Manuel = new Persona("Manuel");
        Persona Sonia = new Persona("Sonia");

        Manuel.start();
        Sonia.start();


    }

}
