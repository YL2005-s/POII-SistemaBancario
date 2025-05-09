package models;

import utilities.adapter.AccountParser;
import utilities.adapter.AccountParserFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountModel {
    private static final String FILE = "cuentas.txt";
    private static final String SEP = "|";
    private static final String SEP_CREDITO = "||";
    private final List<Cuenta> cuentas = new ArrayList<>();
    private String notice;

    public String saveAccount(Cuenta cuenta) {
        String sep = cuenta.getTipo().equals("Crédito") ? SEP_CREDITO : SEP;
        String account = cuenta.getId() + sep + cuenta.getTipo() + sep + cuenta.getSaldo() + sep + cuenta.getLimite() + "\n";

        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(account);
            cuentas.add(cuenta);
            notice = "Cuenta creada exitosamente";
        } catch (FileNotFoundException fnfe) {
            notice = "Archivo no encontrado";
        } catch (Exception ex) {
            notice = "Error al guardar la cuenta";
        }

        return notice;
    }

    public String updateAccount(Cuenta updated) {
        boolean exists = cuentas.stream().anyMatch(c -> c.getId() == updated.getId());
        if (!exists) {
            return notice = "Cuenta no encontrada";
        }

        cuentas.replaceAll(c -> c.getId() == updated.getId() ? updated : c);

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            List<String> lines = br.lines()
                    .map(line -> {
                        AccountParser parser = AccountParserFactory.getParser(line);
                        Cuenta cuenta = parser.parse(line);
                        if (cuenta.getId() == updated.getId()) {
                            String sep = updated.getTipo().equals("Crédito") ? SEP_CREDITO : SEP;
                            return updated.getId() + sep + updated.getTipo() + sep + updated.getSaldo() + sep + updated.getLimite();
                        }
                        return line;
                    })
                    .toList();
            try (FileWriter fw = new FileWriter(FILE)) {
                for (String line : lines) {
                    fw.write(line + "\n");
                }
            }
        } catch (FileNotFoundException fnfe) {
            notice = "Archivo no encontrado";
        } catch (IOException e) {
            notice = "Error al actualizar cuenta: " + e.getMessage();
        }

        return notice;
    }

    public String loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            cuentas.addAll(br.lines()
                    .map(this::parseAccount)
                    .filter(Objects::nonNull)
                    .toList());
        } catch (FileNotFoundException fnfe) {
            notice = "Archivo no encontrado";
        } catch (IOException e) {
            notice = "Error al cargar cuentas";
        }

        return notice;
    }

    private Cuenta parseAccount(String line) {
        try {
            AccountParser parser = AccountParserFactory.getParser(line);
            return parser.parse(line);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }
}
