package com.example.forcadevendastrab3bi.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forcadevendastrab3bi.R;
import com.example.forcadevendastrab3bi.controller.ItemController;
import com.example.forcadevendastrab3bi.model.Endereco;
import com.example.forcadevendastrab3bi.model.Item;

import java.util.ArrayList;

public class CadastroItemActivity extends AppCompatActivity {

    private EditText codigoItem;
    private EditText descricaoItem;
    private EditText valorUnitarioItem;
    private EditText unidadeMedidaItem;
    private Button botaoSalvarItem;

    private ItemController itemController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);
        setTitle("Cadastrar Item");

        codigoItem = findViewById(R.id.codigoItem);
        descricaoItem = findViewById(R.id.descricaoItem);
        valorUnitarioItem = findViewById(R.id.valorUnitarioItem);
        unidadeMedidaItem = findViewById(R.id.unidadeMedidaItem);
        botaoSalvarItem = findViewById(R.id.btSalvarItem);
        itemController = new ItemController(this);

        botaoSalvarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarItem();
            }
        });
    }

    private void salvarItem(){
        String validacao = itemController.validaItem(
                descricaoItem.getText().toString(),
                valorUnitarioItem.getText().toString(),
                unidadeMedidaItem.getText().toString()
        );

        if(!validacao.equals("")){
            if(validacao.toUpperCase().contains("DESCRIÇÃO")){
                descricaoItem.setError(validacao);
            }
            if(validacao.toUpperCase().contains("VALOR UNITÁRIO")){
                valorUnitarioItem.setError(validacao);
            }
            if(validacao.toUpperCase().contains("UNIDADE DE MEDIDA")){
                unidadeMedidaItem.setError(validacao);
            }
        }else{
            if(codigoItem.getText().toString() == null || codigoItem.getText().toString().equals("")){
                ArrayList<Item> items = itemController.retornarTodositens();
                if(itemController.salvarItem(
                        String.valueOf(items.size() +1),
                        descricaoItem.getText().toString(),
                        valorUnitarioItem.getText().toString(),
                        unidadeMedidaItem.getText().toString()
                ) > 0){
                    Toast.makeText(this,
                            "Item cadastrado com sucesso!!",
                            Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,
                            "Erro ao cadastrar Item, verifique LOG.",
                            Toast.LENGTH_LONG).show();
                }
            }else{
                if(itemController.atualizarItem(
                        codigoItem.getText().toString(),
                        descricaoItem.getText().toString(),
                        valorUnitarioItem.getText().toString(),
                        unidadeMedidaItem.getText().toString()
                )>0){
                    Toast.makeText(this,
                            "Item atualizado com sucesso!!",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,
                            "Erro ao atualizar Item, verifique LOG.",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
