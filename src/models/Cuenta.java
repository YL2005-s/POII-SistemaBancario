package models;

import java.util.Random;

public class Cuenta {
    private int id;
    private double saldo;
    private boolean generaInteres;
    private double tasaInteres;
    private double cuotaMantenimiento;
    private boolean tieneLimite;
    private double limite;
    private boolean estadoActiva;

    private Cuenta(Builder builder) {
        this.id = builder.id;
        this.saldo = builder.saldo;
        this.generaInteres = builder.generaInteres;
        this.tasaInteres = builder.tasaInteres;
        this.cuotaMantenimiento = builder.cuotaMantenimiento;
        this.tieneLimite = builder.tieneLimite;
        this.limite = builder.limite;
        this.estadoActiva = builder.estadoActiva;
    }

    public static class Builder {
        private int id;
        private double saldo;
        private boolean generaInteres = false;
        private double tasaInteres = 0.0;
        private double cuotaMantenimiento = 0.0;
        private boolean tieneLimite = false;
        private double limite = 0.0;
        private boolean estadoActiva = true;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder saldo(double saldo) {
            this.saldo = saldo;
            return this;
        }

        public Builder generaInteres(boolean generaInteres) {
            this.generaInteres = generaInteres;
            return this;
        }

        public Builder tasaInteres(double tasaInteres) {
            this.tasaInteres = tasaInteres;
            return this;
        }

        public Builder cuotaMantenimiento(double cuotaMantenimiento) {
            this.cuotaMantenimiento = cuotaMantenimiento;
            return this;
        }

        public Builder tieneLimite(boolean tieneLimite) {
            this.tieneLimite = tieneLimite;
            return this;
        }

        public Builder limite(double limite) {
            this.limite = limite;
            return this;
        }

        public Builder estadoActiva(boolean estadoActiva) {
            this.estadoActiva = estadoActiva;
            return this;
        }
        public Cuenta build() {
            return new Cuenta(this);
        }

        public static Builder cuentaAhorroBuilder() {
            Random random = new Random();
            double tasa = 0.005 + (0.02 - 0.005) * random.nextDouble();
            double cuota = random.nextDouble() < 0.1 ? 1 + 4 * random.nextDouble() : 0.0;

            return new Builder()
                    .id(random.nextInt(1000))
                    .generaInteres(true)
                    .tasaInteres(tasa)
                    .cuotaMantenimiento(cuota)
                    .estadoActiva(true)
                    .tieneLimite(false);
        }

        public static Builder cuentaCorrienteBuilder() {
            Random random = new Random();
            double cuota = 10 + (20 - 10) * random.nextDouble();

            return new Builder()
                    .id(random.nextInt(1000))
                    .generaInteres(false)
                    .tasaInteres(0.0)
                    .cuotaMantenimiento(cuota)
                    .estadoActiva(true)
                    .tieneLimite(true);
        }


        public static Builder cuentaCreditoBuilder() {
            Random random = new Random();
            double tasa = 0.25 + (0.45 - 0.25) * random.nextDouble();
            double cuota = 15 + (30 - 15) * random.nextDouble();

            return new Builder()
                    .id(random.nextInt(1000))
                    .generaInteres(true)
                    .tasaInteres(tasa)
                    .cuotaMantenimiento(cuota)
                    .estadoActiva(true)
                    .tieneLimite(true);
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public boolean isGeneraInteres() { return generaInteres; }
    public void setGeneraInteres(boolean generaInteres) { this.generaInteres = generaInteres; }

    public double getTasaInteres() { return tasaInteres; }
    public void setTasaInteres(double tasaInteres) { this.tasaInteres = tasaInteres; }

    public double getCuotaMantenimiento() { return cuotaMantenimiento; }
    public void setCuotaMantenimiento(double cuotaMantenimiento) { this.cuotaMantenimiento = cuotaMantenimiento; }

    public boolean isTieneLimite() { return tieneLimite; }
    public void setTieneLimite(boolean tieneLimite) { this.tieneLimite = tieneLimite; }

    public double getLimite() { return limite; }
    public void setLimite(double limite) { this.limite = limite; }

    public boolean isEstadoActiva() { return estadoActiva; }
    public void setEstadoActiva(boolean estadoActiva) { this.estadoActiva = estadoActiva; }
}
