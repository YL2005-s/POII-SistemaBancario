package models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class CuentaUtils {
    private static final LinkedHashMap<Predicate<Cuenta>, String> accountsType = new LinkedHashMap<>();

    static {
        accountsType.put(
                cuenta -> cuenta.isTieneLimite() && cuenta.isGeneraInteres(),
                "Crédito"
        );
        accountsType.put(
                cuenta -> cuenta.isTieneLimite() && !cuenta.isGeneraInteres(),
                "Corriente"
        );
        accountsType.put(
                cuenta -> !cuenta.isTieneLimite() && cuenta.isGeneraInteres(),
                "Ahorro"
        );
        accountsType.put(
                cuenta -> true,
                "Desconocido"
        );
    }

    public static String getType(Cuenta cuenta) {
        return accountsType.entrySet().stream()
                .filter(entry -> entry.getKey().test(cuenta))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("Desconocido");
    }

}
