package com.example.forcadevendastrab3bi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.forcadevendastrab3bi.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Força de Vendas");

        Button btEnderecos = findViewById(R.id.btEnderecos);
        Button btItens = findViewById(R.id.btItens);
        Button btClientes = findViewById(R.id.btClientes);
        Button btVenda = findViewById(R.id.btVendas);

        btEnderecos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroEnderecoActivity.class);
                startActivity(intent);
            }
        });

        btItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroItemActivity.class);
                startActivity(intent);
            }
        });

        btClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });

        btVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroVendaActivity.class);
                startActivity(intent);
            }
        });
    }
}