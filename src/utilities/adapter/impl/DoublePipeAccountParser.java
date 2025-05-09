package utilities.adapter.impl;

import models.Cuenta;
import utilities.adapter.AccountParser;

public class DoublePipeAccountParser implements AccountParser {

    @Override
    public Cuenta parse(String line) {
        String[] partes = line.split("\\|\\|");
        if (partes.length != 4) return null;
        return new Cuenta.Builder()
                .id(Integer.parseInt(partes[0]))
                .tipo(partes[1])
                .saldo(Double.parseDouble(partes[2]))
                .limite(Double.parseDouble(partes[3]))
                .build();
    }
}