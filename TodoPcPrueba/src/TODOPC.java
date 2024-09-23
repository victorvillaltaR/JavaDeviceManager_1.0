import javax.swing.*;
import java.util.ArrayList;

public class TODOPC {

    static ArrayList<Equipo> equipos = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        do {
            String[] opciones = {"Registrar equipo", "Ver equipos", "Salir"};
            opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Device Manager 1.0",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:  
                    registrarEquipo();
                    break;
                case 1:  
                    verEquipos();
                    break;
                case 2:  
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema Device Manager.");
                    break;
                default:
                    break;
            }
        } while (opcion != 2);
    }
    
 // Método para obtener una cadena no vacía
 // Método para obtener una cadena no vacía o permitir cancelar
    public static String obtenerEntradaNoVacia(String mensaje) {
        String entrada;
        do {
            entrada = JOptionPane.showInputDialog(mensaje);
            if (entrada == null) {  // Si se presiona "Cancelar"
                return null;
            }
            if (entrada.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Este campo no puede quedar vacío. Por favor, ingrese un valor.");
            }
        } while (entrada.trim().isEmpty());
        return entrada.trim();
    }

    // Método para obtener un número entero válido o permitir cancelar
    public static Integer obtenerNumeroValido(String mensaje) {
        Integer numero = null;
        boolean valido = false;
        do {
            try {
                String entrada = JOptionPane.showInputDialog(mensaje);
                if (entrada == null) {  // Si se presiona "Cancelar"
                    return null;
                }
                numero = Integer.parseInt(entrada);
                if (numero > 0) {
                    valido = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número mayor que 0.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Ingrese un número.");
            }
        } while (!valido);
        return numero;
    }

    // Método para obtener un número decimal válido o permitir cancelar
    public static Double obtenerDoubleValido(String mensaje) {
        Double numero = null;
        boolean valido = false;
        do {
            try {
                String entrada = JOptionPane.showInputDialog(mensaje);
                if (entrada == null) {  // Si se presiona "Cancelar"
                    return null;
                }
                numero = Double.parseDouble(entrada);
                if (numero > 0) {
                    valido = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número mayor que 0.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Ingrese un número decimal.");
            }
        } while (!valido);
        return numero;
    }



    public static void registrarEquipo() {
        String[] tiposEquipo = {"Desktop", "Laptop", "Tablet"};
        int tipoEquipo = JOptionPane.showOptionDialog(null, "Seleccione el tipo de equipo", "Registrar Equipo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tiposEquipo, tiposEquipo[0]);

        String fabricante = obtenerEntradaNoVacia("Ingrese el fabricante:");
        if (fabricante == null) return;  // Cancelar

        String modelo = obtenerEntradaNoVacia("Ingrese el modelo:");
        if (modelo == null) return;  // Cancelar

        String microprocesador = obtenerEntradaNoVacia("Ingrese el microprocesador:");
        if (microprocesador == null) return;  // Cancelar

        switch (tipoEquipo) {
            case 0:  // Desktop
                String tarjetaGrafica = obtenerEntradaNoVacia("Ingrese la tarjeta gráfica:");
                if (tarjetaGrafica == null) return;  // Cancelar

                Integer capacidadDiscoDuro = obtenerNumeroValido("Ingrese la capacidad del disco duro (GB):");
                if (capacidadDiscoDuro == null) return;  // Cancelar

                Integer tamanoDeTorre = obtenerNumeroValido("Ingrese el tamaño de la torre (pulgadas):");
                if (tamanoDeTorre == null) return;  // Cancelar

                Integer memoria = obtenerNumeroValido("Ingrese la memoria (GB):");
                if (memoria == null) return;  // Cancelar

                equipos.add(new Desktop(fabricante, modelo, microprocesador, memoria, tarjetaGrafica, capacidadDiscoDuro, tamanoDeTorre));
                JOptionPane.showMessageDialog(null, "Desktop registrado exitosamente.");
                break;

            case 1:  // Laptop
                Double tamanoPantalla = obtenerDoubleValido("Ingrese el tamaño de la pantalla (pulgadas):");
                if (tamanoPantalla == null) return;  // Cancelar

                capacidadDiscoDuro = obtenerNumeroValido("Ingrese la capacidad del disco duro (GB):");
                if (capacidadDiscoDuro == null) return;  // Cancelar

                Integer memoria2 = obtenerNumeroValido("Ingrese la memoria (GB):");
                if (memoria2 == null) return;  // Cancelar

                equipos.add(new Laptop(fabricante, modelo, microprocesador, memoria2, tamanoPantalla, capacidadDiscoDuro));
                JOptionPane.showMessageDialog(null, "Laptop registrada exitosamente.");
                break;

            case 2:  // Tablet
                tamanoPantalla = obtenerDoubleValido("Ingrese el tamaño de la pantalla (pulgadas):");
                if (tamanoPantalla == null) return;  // Cancelar

                String tipoPantalla = obtenerEntradaNoVacia("¿Pantalla capacitiva o resistiva?");
                if (tipoPantalla == null) return;  // Cancelar

                Integer capacidadMemoriaNAND = obtenerNumeroValido("Ingrese la capacidad de memoria NAND (GB):");
                if (capacidadMemoriaNAND == null) return;  // Cancelar

                String sistemaOperativo = obtenerEntradaNoVacia("Ingrese el sistema operativo:");
                if (sistemaOperativo == null) return;  // Cancelar

                equipos.add(new Tablet(fabricante, modelo, microprocesador, tamanoPantalla, tipoPantalla, capacidadMemoriaNAND, sistemaOperativo));
                JOptionPane.showMessageDialog(null, "Tablet registrada exitosamente.");
                break;
        }
    }

    
    

    
    public static void verEquipos() {
        String[] opcionesVer = {"Desktop", "Laptop", "Tablet"};
        int tipoVer = JOptionPane.showOptionDialog(null, "Seleccione el tipo de equipo que desea ver", "Ver Equipos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesVer, opcionesVer[0]);

        StringBuilder salida = new StringBuilder();
        boolean equipoEncontrado = false; // Variable para verificar si se encontró al menos un equipo del tipo seleccionado

        switch (tipoVer) {
            case 0:  // Ver Desktops
                salida.append("Equipos tipo Desktop:\n\n");
                for (Equipo equipo : equipos) {
                    if (equipo instanceof Desktop) {
                        equipoEncontrado = true; // Se encontró al menos un equipo de tipo Desktop
                        String[] info = equipo.getInfo();
                        salida.append("Fabricante: ").append(info[0]).append("\n")
                                .append("Modelo: ").append(info[1]).append("\n")
                                .append("Microprocesador: ").append(info[2]).append("\n")
                                .append("Memoria RAM: ").append(info[3]).append("\n")
                                .append("Tarjeta gráfica: ").append(info[4]).append("\n")
                                .append("Capacidad de disco duro: ").append(info[5]).append("\n")
                                .append("Tamaño de la torre: ").append(info[6]).append("\n\n");
                    }
                }
                break;
            case 1:  // Ver Laptops
                salida.append("Equipos tipo Laptop:\n\n");
                for (Equipo equipo : equipos) {
                    if (equipo instanceof Laptop) {
                        equipoEncontrado = true; // Se encontró al menos un equipo de tipo Laptop
                        String[] info = equipo.getInfo();
                        salida.append("Fabricante: ").append(info[0]).append("\n")
                                .append("Modelo: ").append(info[1]).append("\n")
                                .append("Microprocesador: ").append(info[2]).append("\n")
                                .append("Memoria RAM: ").append(info[3]).append("\n")
                                .append("Tamaño de pantalla: ").append(info[4]).append("\n")
                                .append("Capacidad de disco duro: ").append(info[5]).append("\n\n");
                    }
                }
                break;
            case 2:  // Ver Tablets
                salida.append("Equipos tipo Tablet:\n\n");
                for (Equipo equipo : equipos) {
                    if (equipo instanceof Tablet) {
                        equipoEncontrado = true; // Se encontró al menos un equipo de tipo Tablet
                        String[] info = equipo.getInfo();
                        salida.append("Fabricante: ").append(info[0]).append("\n")
                                .append("Modelo: ").append(info[1]).append("\n")
                                .append("Microprocesador: ").append(info[2]).append("\n")
                                .append("Tamaño de pantalla: ").append(info[3]).append("\n")
                                .append("Tipo de pantalla: ").append(info[4]).append("\n")
                                .append("Capacidad de memoria NAND: ").append(info[5]).append("\n")
                                .append("Sistema operativo: ").append(info[6]).append("\n\n");
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                return;
        }

        // Mostrar los equipos seleccionados si se encontró alguno
        if (equipoEncontrado) {
            JOptionPane.showMessageDialog(null, salida.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No hay equipos registrados de este tipo.");
        }
    }


}
