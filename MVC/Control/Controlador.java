package Control;

import Modelo.Vino;
import Vista.Interfaz;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Estudiantes
 */
public class Controlador implements ActionListener {

    //Objetos vista y modelo
    private Interfaz vista;
    private Vino modelo;
    ArrayList<Vino> lista = new ArrayList<Vino>();
    private int posicion = -1;

    //Constructor para inicializar la vista
    public Controlador(Vino modelo, Interfaz vista) {
        this.modelo = modelo;
        this.vista = vista;
        //le envia la referencia del listener al boton de la vista
        this.vista.btnInsertar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
    }

    public void iniciar() {
        //colocarle un titulo a la ventana
        this.vista.setTitle("Gestión de vinos");
        //Se le indica la posicion --> null para que la ventana inicie en la posicion 0 es decir en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.vista.btnInsertar) {

            int edad = 0;
            boolean comprobar;
            edad = Integer.parseInt(this.vista.insertEdad.getText()); //Convierte el texto de edad en entero
            //Llama al método crear vino, enviando como parámetros lo que se llene en los JTextField, crea el vino y retorna un booleano
            comprobar = crearVino(this.vista.insertId.getText(), this.vista.insertMarca.getText(), this.vista.insertColor.getText(), edad);

            //Envía el booleano comprobar, para enviar el mensaje de si se pudo añadir o no
            this.vista.msgdevino(comprobar);
        }
        if (e.getSource() == this.vista.btnLimpiar) {
            this.vista.blanquearCampos();
        }
        if (e.getSource() == this.vista.btnSalir) {
            this.vista.setVisible(false);
            this.vista.dispose();
        }
    }

//Recibe datos de la vista para crear el modelo
    public boolean crearVino(String id, String marca, String color, int edad) {
        
        int comprobar = -1;

        //Se crea el vino ingresado
        for (int i = 0; i < lista.size(); i++) { //Recorre los elementos de la lista para comprobar que no se repita ID
            if (lista.get(i).getId().equals(id)) {
                comprobar = 0; //Cambia el valor de comprobar para identificar que ya existe un vino con ese ID
            }
        }

        if (comprobar == -1) { //Si la variable comprobar no ha cambiado, significa que el vino aún no existe
            modelo = new Vino();
            modelo.setId(id);
            modelo.setMarca(marca);
            modelo.setColor(color);
            modelo.setEdad(edad);
            
            lista.add(modelo);  //Añade a la lista de vinos el vino ingresado
            return true; //True, para indicar que se pudo agregar
        } else {
            return false; //False, para indicar que no se agregó porque ya existe
        }
    }

    //Busca cliente
    public void buscarVino(String id) {
        String idaux = "";
        posicion = -1; //Cada que vuelva a iniciar se reinicia ese valor para no volver a mostrar
        for (int i = 0; i < lista.size(); i++) { //Recorre los elementos de la lista
            idaux = lista.get(i).getId();
            if (idaux.equals(id)) { //Compara si en algún elemento de la lista ya está ingresado algún ID del mismo tipo
                posicion = i; //Guarda la posición en la que se encuentra el ID
            }
        }
    }

}
