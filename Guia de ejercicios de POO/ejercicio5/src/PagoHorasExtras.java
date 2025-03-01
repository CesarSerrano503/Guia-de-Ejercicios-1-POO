import java.util.Scanner;

public class PagoHorasExtras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de empleados: ");
        int numEmpleados = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numEmpleados; i++) {
            System.out.println("\nEmpleado " + (i + 1) + ":");

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Salario: ");
            double salario = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Departamento (Gerencia, Auditoria, Tecnologia, Contabilidad): ");
            String departamento = scanner.nextLine();

            System.out.print("Horas realizadas en el mes (máximo 20): ");
            int horasRealizadas = scanner.nextInt();
            scanner.nextLine();

            if (horasRealizadas > 20) {
                System.out.println("Error: El empleado no puede registrar más de 20 horas extras.");
                continue; //restriccion de horas extras
            }

            // Calcula el bono por hora según el departamento
            double bonoPorHora = calcularBonoPorHora(departamento);

            // Calcula el pago de horas extras
            double pagoHorasExtras = (salario / 30) * horasRealizadas + bonoPorHora;

            // Muestra los resultados de los datos ingresados
            System.out.println("\nResultados para " + nombre + ":");
            System.out.println("Nombre: " + nombre);
            System.out.println("Salario: $" + salario);
            System.out.println("Departamento: " + departamento);
            System.out.println("Horas realizadas: " + horasRealizadas);
            System.out.println("Pago total de horas extras: $" + pagoHorasExtras);
        }

        scanner.close();
    }

    // calcula el bono por hora según el departamento
    public static double calcularBonoPorHora(String departamento) {
        switch (departamento.toLowerCase()) {
            case "gerencia":
                return 3.50;
            case "auditoria":
                return 1.75;
            case "tecnologia":
                return 2.25;
            case "contabilidad":
                return 2.00;
            default:
                return 0; // Si el departamento no coincide, no hay bono
        }
    }
}