package com.example.currency_exchanger.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notifications_table")
public class EntityClass {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String oneCurrency;
    private String twoCurrency;
    private double currencyRate;
}
