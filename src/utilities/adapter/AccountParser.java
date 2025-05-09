package utilities.adapter;


import models.Cuenta;

public interface AccountParser {
    Cuenta parse(String line);
}
