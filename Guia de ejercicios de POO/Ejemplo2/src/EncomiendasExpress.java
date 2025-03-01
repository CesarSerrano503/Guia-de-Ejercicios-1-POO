import java.util.Scanner;

public class EncomiendasExpress {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita datos al usuario
        System.out.print("Ingrese el peso del paquete (kg): ");
        double peso = scanner.nextDouble();

        System.out.print("Ingrese la distancia del envío (km): ");
        double distancia = scanner.nextDouble();

        System.out.print("¿Es envío express? (Sí/No): ");
        String envioExpressStr = scanner.next();
        boolean envioExpress = envioExpressStr.equalsIgnoreCase("Sí");

        System.out.print("¿Posee tarjeta VIP? (Sí/No): ");
        String tarjetaVIPStr = scanner.next();
        boolean tarjetaVIP = tarjetaVIPStr.equalsIgnoreCase("Sí");


        if (peso <= 0 || distancia <= 0) {
            System.out.println("Error: El peso y la distancia deben ser valores positivos.");
            return;
        }

        // Calcula las tarifas
        double tarifaPeso = calcularTarifaPeso(peso);
        double tarifaDistancia = calcularTarifaDistancia(distancia);

        // Calcula los subtotales
        double subtotalPeso = peso * tarifaPeso;
        double subtotalDistancia = distancia * tarifaDistancia;
        double subtotalGeneral = subtotalPeso + subtotalDistancia;

        // Calcula los costo envío express
        double costoEnvioExpress = 0;
        if (envioExpress) {
            costoEnvioExpress = subtotalGeneral * 0.13;
        }

        // Calcula los descuento de la tarjeta VIP
        double descuentoVIP = 0;
        if (tarjetaVIP) {
            descuentoVIP = (subtotalGeneral + costoEnvioExpress) * 0.10;
        }

        // Calcula el precio total
        double precioTotal = subtotalGeneral + costoEnvioExpress - descuentoVIP;

        // Mostrar resultados
        System.out.println("\nDatos del paquete: " + peso + " kg, $" + tarifaPeso + "/kg");
        System.out.println("Datos del envío: " + distancia + " km, $" + tarifaDistancia + "/km");

        System.out.println("\nSubtotal peso: $" + String.format("%.2f", subtotalPeso));
        System.out.println("Subtotal distancia: $" + String.format("%.2f", subtotalDistancia));
        System.out.println("Subtotal general: $" + String.format("%.2f", subtotalGeneral));

        if (envioExpress) {
            System.out.println("Costo envío express: $" + String.format("%.2f", costoEnvioExpress));
        }

        if (tarjetaVIP) {
            System.out.println("Descuento tarjeta VIP: $" + String.format("%.2f", descuentoVIP));
        }

        System.out.println("Precio total a pagar: $" + String.format("%.2f", precioTotal));

        scanner.close();
    }

    //  calcula la tarifa por peso
    public static double calcularTarifaPeso(double peso) {
        if (peso <= 4) {
            return 1.20;
        } else if (peso <= 10) {
            return 1.35;
        } else if (peso <= 20) {
            return 1.60;
        } else {
            return 1.75;
        }
    }

    //  calcula la tarifa por distancia
    public static double calcularTarifaDistancia(double distancia) {
        if (distancia <= 20) {
            return 0.20;
        } else if (distancia <= 40) {
            return 0.08;
        } else if (distancia <= 60) {
            return 0.06;
        } else {
            return 0.04;
        }
    }
}