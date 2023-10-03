package verosimilitud;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * El siguiente programa comprueba la verosimilitud del numero de cedula ingresado por el usuario
 * Por lo que este programa depende de la clase <code>Scanner</code>
 * Las instrucciones y normas que se siguen son las siguientes:
 * 1. Se multiplica cada coeficiente con cada digito de la cedula sin tomar en cuenta el decimo digito
 * 2. Si el resultado de algunas de las multiplicaciones es mayor o igual a 10 se le resta 9
 * 3. Se suman todos los resultados
 * 4. A la suma de todos los resultados le restamos de la decena superior
 * 5. Si todo sale bien el ultimo digo tiene que ser igual a el resultado de la resta
 * Nota: Si el resultado de la suma de todos los resultados menos la decena superior es igual a 10, el decimo digito tiene que ser 0
 *
 * @author Ricardo Villarreal
 * @version 1.0.0.1 (Version Final. mejora del funcionamiento. solucion a bugs)
 * @since 04/08/2023
 * @see "Documentacion Tarea"
 */
public class AppVerificadora {
    public static void main(String[] args) {

        // Definimos variables a usar
        Scanner sc = new Scanner(System.in);

        // Menu de operaciones
        System.out.println("-------------------------------------------------");
        System.out.println("---------Programa Verificador de Cedulas---------");
        System.out.println("Digite el número de cédula: ");
        String cedula = sc.nextLine();
        String cedulaModificada = agregarSeparaciones(cedula); // metodo para agregar espacios al numero de cedula ingresado
        boolean esValida = verificarVerosimilitud(cedulaModificada); // metodo para comprobar la verosimilitud de la cedula ingresada

        // Impresion de resultados
        if (esValida) {
            System.out.println("El numero de cedula ingresado: " + cedula + " tiene verosimilitud y es valida en Ecuador");
        } else {
            System.out.println("El número de cedula ingresado: " + cedula + " no es válido");
        }

    }

    /**
     * Esta funcion permite agregar saltos de espacios despues de cada caracter ingresado
     * @param cedula ingresado por teclado
     * @return string
     */
    private static String agregarSeparaciones(String cedula) {
        StringBuilder palabraConEspacios = new StringBuilder();

        for (int i = 0; i < cedula.length(); i++) {
            palabraConEspacios.append(cedula.charAt(i));
            palabraConEspacios.append(" ");
        }

        return palabraConEspacios.toString();
    }

    /**
     * Esta funcion lleva a cabo todas las condiciones necesarias para comprobar la veracidad de una id
     * @param cedula
     * @return boleano
     */
    private static boolean verificarVerosimilitud(String cedula) {
        if (cedula.length()!=20){
            return false;
        }
        int suma = 0; // esta variable nos ayudara a almacenar las operaciones
        int[] coeficientes = {2,1,2,1,2,1,2,1,2}; // estos coeficientes son estandares, dispuestos por el gobierno

        // Definimos un StringTokenizer para separar los tokens de la cedula
        StringTokenizer tokenizer = new StringTokenizer(cedula," ");

        for(int i = 0; i<9;i++){
            int digito = Integer.parseInt(tokenizer.nextToken());
            int producto = digito*coeficientes[i];
            if (producto >= 10) {
                suma += producto - 9;
            } else {
                suma += producto;
            }
        }

        int resultado = 10 - (suma % 10);
        if (resultado == 10) {
            resultado = 0;
        }

        int ultimoDigito = Integer.parseInt(tokenizer.nextToken());
        boolean esValida = (resultado == ultimoDigito);

        return esValida;
    }
}
