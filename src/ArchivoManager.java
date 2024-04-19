import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class ArchivoManager {

    // Método para crear archivo
    public void crearArchivo(String directorio, String fichero) {
        // Crear la ruta completa para el archivo desde la carpeta 'src'
        String rutaCompleta = "src/" + directorio + "/" + fichero;

        // Crear el directorio si no existe
        File dir = new File("src/" + directorio);
        if (!dir.exists()) {
            boolean dirCreado = dir.mkdirs();
            if (!dirCreado) {
                System.out.println("Error al crear directorio");
                return;
            }
        } else {
            System.out.println("El directorio ya existe.");
        }

        // Crear el archivo en la ruta completa
        File archivo = new File(rutaCompleta);

        // Lista de textos precargados
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Perro");
        lista.add("Gato");
        lista.add("Juan");
        lista.add("Daniel");
        lista.add("Juan");
        lista.add("Gato");
        lista.add("Perro");
        lista.add("Camila");
        lista.add("Daniel");
        lista.add("Camila");

        // Escribir textos en el archivo
        try (FileWriter writer = new FileWriter(archivo)) {
            Iterator<String> it = lista.iterator();
            while (it.hasNext()) {
                String texto = it.next();
                writer.write(texto + "\n");
            }
            System.out.println("Archivo creado y llenado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    // Método para buscar texto en un archivo
    public void buscarTexto(String nombreFichero, String texto) {
        // Asegúrate de que la ruta completa de archivo se inicie desde 'src'
        String rutaCompleta = "src/" + nombreFichero;

        File fichero = new File(rutaCompleta);

        // Validar que el fichero exista
        if (!fichero.exists()) {
            System.out.println("El fichero ingresado no existe");
            return;
        }

        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String line;
            // Leer línea por línea
            while ((line = reader.readLine()) != null) {
                // Comparar cada línea con el texto ingresado
                if (line.equals(texto)) {
                    count++;
                }
            }
            // Mostrar la cantidad de repeticiones del texto
            System.out.println("Cantidad de repeticiones del texto -> " + count);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso de los métodos
        ArchivoManager manager = new ArchivoManager();

        // Crear archivo con el método crearArchivo
        manager.crearArchivo("directorio", "fichero.txt");

        // Buscar texto en el archivo con el método buscarTexto
        manager.buscarTexto("directorio/fichero.txt", "Juan");
    }
}
