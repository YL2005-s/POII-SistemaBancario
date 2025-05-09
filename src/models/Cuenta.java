package models;

public class Cuenta {
    private int id;
    private String tipo;
    private double saldo;
    private double limite;

    private Cuenta(Builder builder) {
        this.id = builder.id;
        this.tipo = builder.tipo;
        this.saldo = builder.saldo;
        this.limite = builder.limite;
    }

    public static class Builder {
        private int id;
        private String tipo;
        private double saldo;
        private double limite = 0.0;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder saldo(double saldo) {
            this.saldo = saldo;
            return this;
        }

        public Builder limite(double limite) {
            this.limite = limite;
            return this;
        }

        public Cuenta build() {
            return new Cuenta(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
