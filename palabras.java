import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class palabras {

    public static void main(String[] args) {
        String rutaArchivo = "C:\\Users\\franc\\Desktop\\PPCJ\\archivo_aleatorio.txt"; // Ruta al archivo de texto

        // Mapa concurrente para almacenar las frecuencias de cada palabra
        Map<String, Integer> frecuenciaPalabras = new ConcurrentHashMap<>();

        try {
            // Lee las líneas del archivo y las procesa en paralelo
            Files.lines(Paths.get(rutaArchivo))
                    .parallel()
                    .flatMap(linea -> Arrays.stream(linea.trim().split("\\s+"))) // Divide cada línea en palabras
                    .map(palabra -> palabra.toLowerCase().replaceAll("[^a-zA-Z]", "")) // Convierte a minúsculas y elimina caracteres no alfabéticos
                    .filter(palabra -> !palabra.isEmpty()) // Filtra palabras vacías
                    .forEach(palabra -> frecuenciaPalabras.merge(palabra, 1, Integer::sum)); // Cuenta la frecuencia de cada palabra

            // Imprime el resultado
            frecuenciaPalabras.forEach((palabra, frecuencia) -> 
                System.out.println("Palabra: " + palabra + " - Frecuencia: " + frecuencia));

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
