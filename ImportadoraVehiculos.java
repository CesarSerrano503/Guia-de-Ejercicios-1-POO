// Importaciones para la interfaz gráfica.
import javax.swing.*;
import java.awt.*;

/**
 * Clase principal que representa la importadora de vehículos.
 * botones y un área de resultados. Permite calcular el costo de importación
 */
public class ImportadoraVehiculos extends JFrame {
    // Componentes de la interfaz gráfica para capturar los datos del vehículo.
    private final JTextField marcaField, anioField;
    private final JComboBox<String> tipoCombo, origenCombo;
    private final JTextArea resultadoArea;

    /**
     * Constructor de la clase que inicializa la interfaz gráfica.
     * Configura el tamaño, los paneles de entrada, el botón de cálculo
     * y el área de resultados.
     */
    public ImportadoraVehiculos() {
        setTitle("Importadora de Vehículos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con campos de entrada organizados en una cuadrícula.
        JPanel panelEntrada = new JPanel(new GridLayout(5, 2, 10, 10));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Datos del Vehículo"));

        panelEntrada.add(new JLabel("Tipo de vehículo:"));
        String[] tipos = {"Sedan", "Pickups", "Microbuses", "Motos"};
        tipoCombo = new JComboBox<>(tipos);
        panelEntrada.add(tipoCombo);

        panelEntrada.add(new JLabel("Marca:"));
        marcaField = new JTextField();
        panelEntrada.add(marcaField);

        panelEntrada.add(new JLabel("Año de fabricación:"));
        anioField = new JTextField();
        panelEntrada.add(anioField);

        panelEntrada.add(new JLabel("País de origen:"));
        String[] origenes = {"China", "Estados Unidos"};
        origenCombo = new JComboBox<>(origenes);
        panelEntrada.add(origenCombo);

        add(panelEntrada, BorderLayout.NORTH);

        // Panel central con el botón de cálculo estilizado.
        JPanel panelBoton = new JPanel();
        JButton calcularBtn = new JButton("Calcular");
        calcularBtn.setFont(new Font("Arial", Font.BOLD, 14));
        calcularBtn.setBackground(new Color(34, 45, 200));
        calcularBtn.setForeground(Color.WHITE);
        calcularBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        calcularBtn.setFocusPainted(false);
        panelBoton.add(calcularBtn);
        add(panelBoton, BorderLayout.CENTER);

        // Área de texto para mostrar los resultados.
        resultadoArea = new JTextArea(10, 50);
        resultadoArea.setEditable(false);
        resultadoArea.setBorder(BorderFactory.createTitledBorder("Resultado"));
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        scrollPane.setPreferredSize(new Dimension(580, 200));
        add(scrollPane, BorderLayout.SOUTH);

        // Calcular el costo de importación.
        calcularBtn.addActionListener(e -> calcularCosto());
    }



     //Se usa SwingUtilities.invokeLater para la correcta ejecución de la interfaz gráfica.

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImportadoraVehiculos().setVisible(true));
    }

    /**
     * Validar los datos ingresados y se calcula el impuesto, costo de flete y matrícula.
     * Al final se muestra el resultado en el área de texto.
     */
    private void calcularCosto() {
        try {
            String tipo = (String) tipoCombo.getSelectedItem();
            String marca = marcaField.getText();
            int anio = Integer.parseInt(anioField.getText());
            String origen = (String) origenCombo.getSelectedItem();

            // Validaciones de datos ingresados
            if (marca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la marca del vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (anio > 2025 || anio < 2010) {
                JOptionPane.showMessageDialog(this, "Solo se aceptan vehículos entre 2010 y 2025.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int edad = 2025 - anio;
            if (edad < 1 || edad > 15) {
                JOptionPane.showMessageDialog(this, "Solo se aceptan vehículos con edades entre 1 y 15 años.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Determina el porcentaje de impuesto según la edad del vehículo
            double impuestoPorcentaje = (edad <= 4) ? 0.11 :
                    (edad <= 9) ? 0.13 : 0.16;

            // Calcula el flete basado en el país de origen y tipo de vehículo
            double flete = calcularFlete(origen, tipo);

            // Determina el costo de la matrícula según el año de fabricación
            double matricula = anio >= 2020 ? 11.99 : anio >= 2016 ? 13.99 : 15.99;

            // Cálculo final del impuesto y total de importación
            double impuesto = impuestoPorcentaje * 10000;
            double total = impuesto + flete + matricula;

            // Muestra los resultados  en el área de texto
            resultadoArea.setText(String.format("""
                Datos del Vehículo:
                Tipo: %s
                Marca: %s
                Año: %d
                
                Datos de Importación:
                Porcentaje de Impuesto: %.2f%%
                Costo de Impuesto: $%.2f
                Lugar de Flete: %s
                Costo de Flete: $%.2f
                Matrícula inicial: $%.2f
                Total de Importación: $%.2f
                """,
                    tipo, marca, anio, impuestoPorcentaje * 100, impuesto, origen, flete, matricula, total));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**.
     * Se usa una estructura switch anidada para definir los valores correspondientes.
     */
    private double calcularFlete(String origen, String tipo) {
        if (origen == null || tipo == null) return 0;
        return switch (origen) {
            case "China" -> switch (tipo) {
                case "Sedan" -> 1700;
                case "Pickups" -> 1900;
                case "Microbuses" -> 2400;
                case "Motos" -> 1300;
                default -> 0;
            };
            case "Estados Unidos" -> switch (tipo) {
                case "Sedan" -> 1200;
                case "Pickups" -> 1500;
                case "Microbuses" -> 1700;
                case "Motos" -> 900;
                default -> 0;
            };
            default -> 0;
        };
    }
}
