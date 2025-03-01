import java.util.Scanner;

public class DescuentoMatricula {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita los datos del estudiante
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido del estudiante: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese el tipo de ingreso (Antiguo Ingreso/Nuevo Ingreso): ");
        String tipoIngreso = scanner.nextLine();

        System.out.print("Ingrese la edad del estudiante: ");
        int edad = scanner.nextInt();

        // Calcula el valor de la matrícula
        double valorMatricula = calcularValorMatricula(edad);

        // Calcula el descuento
        double descuento = 0;
        if (tipoIngreso.equalsIgnoreCase("Antiguo Ingreso")) {
            descuento = valorMatricula * 0.25;
        }

        // Calcula el valor final de la matrícula
        double valorFinalMatricula = valorMatricula - descuento;

        //  la información del estudiante
        System.out.println("\nInformación del estudiante:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Valor de la matrícula: $" + valorMatricula);
        System.out.println("Descuento obtenido: $" + descuento);
        System.out.println("Valor final de la matrícula: $" + valorFinalMatricula);

        scanner.close();
    }

    // calcula el valor de la matrícula según la edad
    public static double calcularValorMatricula(int edad) {
        if (edad > 15) {
            return 100;
        } else if (edad > 10) {
            return 125;
        } else {
            return 150;
        }
    }
}