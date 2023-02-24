package Control;
import Modelo.Vino;
import Vista.Interfaz;
/**
 *
 * @author Estudiantes
 */
public class Launcher {

    public static void main(String[] args) { //Static porque es un hilo de ejecución de todas las clases del proyecto, void porque no retorna valor, main porque es principal

        
        Vino modelo = new Vino();
        Interfaz vista = new Interfaz();
        Controlador control = new Controlador(modelo, vista);
        control.iniciar();

    }
}
