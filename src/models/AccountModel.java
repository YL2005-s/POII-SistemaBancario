package models;

import entities.Cuenta;
import models.repository.impl.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class AccountModel {
    private final AccountRepository repository;
    private final List<Cuenta> cuentas = new ArrayList<>();
    private String notice;

    public AccountModel() {
        this.repository = new AccountRepository();
    }

    public String saveAccount(Cuenta cuenta) {
        try {
            repository.create(cuenta);
            cuentas.add(cuenta);
            notice = "Cuenta creada exitosamente";
        } catch (Exception ex) {
            notice = "Error al guardar la cuenta";
        }

        return notice;
    }

    public String updateAccount(Cuenta cuenta) {
        try {
            repository.update(cuenta);
        } catch (Exception ex) {
            notice = "Error al guardar la cuenta";
        }

        return notice;
    }

    public String loadAccounts() {
        try {
            cuentas.addAll(repository.list());
        } catch (Exception ex) {
            notice = "Error al cargar las cuentas";
        }

        return notice;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }
}
