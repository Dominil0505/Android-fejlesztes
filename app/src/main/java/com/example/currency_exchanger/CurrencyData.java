package com.example.currency_exchanger;

public class CurrencyData {
    protected String fromCurrency;
    protected String toCurrency;
    protected String amount;
    public String BASE_URL = "https://api.exchangerate.host/convert?from=";

    public CurrencyData() {
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
