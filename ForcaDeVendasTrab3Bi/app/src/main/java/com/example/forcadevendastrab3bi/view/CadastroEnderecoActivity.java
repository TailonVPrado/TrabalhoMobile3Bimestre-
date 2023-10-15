package com.example.forcadevendastrab3bi.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forcadevendastrab3bi.R;
import com.example.forcadevendastrab3bi.controller.EnderecoController;
import com.example.forcadevendastrab3bi.model.Endereco;

import java.util.ArrayList;

public class CadastroEnderecoActivity extends AppCompatActivity {
    private EditText codigoEndereco;
    private EditText estadoEndereco;
    private EditText cidadeEndereco;
    private EditText bairroEndereco;
    private EditText numeroEndereco;
    private EditText logradouroEndereco;
    private Button botaoSalvarEndereco;
    private Context context;
    private EnderecoController enderecoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);
        setTitle("Cadastrar Endereço");

        codigoEndereco = findViewById(R.id.codigoEndereco);
        estadoEndereco = findViewById(R.id.estadoEndereco);
        cidadeEndereco = findViewById(R.id.cidadeEndereco);
        bairroEndereco = findViewById(R.id.bairroEndereco);
        numeroEndereco = findViewById(R.id.numeroEndereco);
        logradouroEndereco = findViewById(R.id.logradouroEndereco);

        botaoSalvarEndereco = findViewById(R.id.btSalvarEndereco);
        enderecoController = new EnderecoController(this);


        botaoSalvarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarEndereco();
            }
        });
    }

    private void salvarEndereco(){
        String validacao = enderecoController.validaEndereco(
                logradouroEndereco.getText().toString(),
                numeroEndereco.getText().toString(),
                bairroEndereco.getText().toString(),
                cidadeEndereco.getText().toString(),
                estadoEndereco.getText().toString()
        );

        if(!validacao.equals("")){
            if(validacao.toUpperCase().contains("LOGRADOURO")){
                logradouroEndereco.setError(validacao);
            }
            if(validacao.toUpperCase().contains("NÚMERO")){
                numeroEndereco.setError(validacao);
            }
            if(validacao.toUpperCase().contains("BAIRRO")){
                bairroEndereco.setError(validacao);
            }
            if(validacao.toUpperCase().contains("CIDADE")){
                cidadeEndereco.setError(validacao);
            }
            if(validacao.toUpperCase().contains("ESTADO")){
                estadoEndereco.setError(validacao);
            }
        }else{
            if(codigoEndereco.getText().toString() == null || codigoEndereco.getText().toString().equals("")){
                ArrayList<Endereco> enderecos = enderecoController.retornarTodosEnderecos();
                if(enderecoController.salvarEndereco(
                        String.valueOf(enderecos.size() +1),
                        logradouroEndereco.getText().toString(),
                        numeroEndereco.getText().toString(),
                        bairroEndereco.getText().toString(),
                        cidadeEndereco.getText().toString(),
                        estadoEndereco.getText().toString()
                ) > 0){
                    Toast.makeText(this,
                            "Endereço cadastrado com sucesso!!",
                            Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,
                            "Erro ao cadastrar Endereço, verifique LOG.",
                            Toast.LENGTH_LONG).show();
                }
            }else{
                if(enderecoController.atualizarEndereco(
                        codigoEndereco.getText().toString(),
                        logradouroEndereco.getText().toString(),
                        numeroEndereco.getText().toString(),
                        bairroEndereco.getText().toString(),
                        cidadeEndereco.getText().toString(),
                        estadoEndereco.getText().toString()
                )>0){
                    Toast.makeText(this,
                            "Endereço atualizado com sucesso!!",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,
                            "Erro ao atualizar Enderço, verifique LOG.",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
