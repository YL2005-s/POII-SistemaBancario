package models;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MovementModel {
    private static final String FILE = "movimientos.txt";
    private final List<Movimiento> movimientos = new ArrayList<>();
    private String notice;

    public String saveMovement(Movimiento mov) {
        try (FileWriter fw = new FileWriter(FILE, true)) {
            String formattedDate = MovementUtils.formatDate(mov.getFecha());
            fw.write(String.format("%s|Transferencia|%.2f|%d\n", formattedDate, mov.getMonto(), mov.getCuentaId()));
            movimientos.add(mov);
        } catch (FileNotFoundException fnfe) {
            notice = "Archivo no encontrado";
        } catch (IOException ex) {
            notice = "Error al guardar movimiento en archivo\n";
        }
        return notice;
    }

    public String loadMovements() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            movimientos.addAll(br.lines()
                    .filter(Objects::nonNull)
                    .map(linea -> {
                        String[] partes = linea.split("\\|");
                        try {
                            return new Movimiento(
                                    MovementUtils.parseDate(partes[0]),
                                    partes[1],
                                    Double.parseDouble(partes[2]),
                                    Integer.parseInt(partes[3])
                            );
                        } catch (Exception e) {
                            System.out.println("Error al parsear movimiento: " + e.getMessage());
                            return null;
                        }
                    })
                    .toList());
        } catch (FileNotFoundException fnfe) {
            notice = "Archivo no encontrado";
        } catch (IOException e) {
            notice = "Error al cargar movimientos";
        } catch (Exception e) {
            notice = "Error general al cargar movimientos: " + e.getMessage();
        }

        return notice;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }
}
