package com.example.forcadevendastrab3bi.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forcadevendastrab3bi.R;
import com.example.forcadevendastrab3bi.controller.ClienteController;
import com.example.forcadevendastrab3bi.controller.EnderecoController;
import com.example.forcadevendastrab3bi.model.Cliente;
import com.example.forcadevendastrab3bi.model.Endereco;

import java.util.ArrayList;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText codigoCliente;
    private EditText nomeCliente;
    private EditText cpfCliente;
    private Spinner spEnderecoCliente;
    private Button botaoSalvarCliente;
    private EditText codEndereco;

    private ArrayAdapter adapterEndereco;
    private ClienteController clienteController;
    private EnderecoController enderecoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        setTitle("Cadastrar Cliente");

        codigoCliente = findViewById(R.id.codigoCliente);
        nomeCliente = findViewById(R.id.nomeCliente);
        cpfCliente = findViewById(R.id.cpfCliente);
        codEndereco = findViewById(R.id.edCodEndereco);
        spEnderecoCliente = findViewById(R.id.spEnderecoCliente);
        botaoSalvarCliente = findViewById(R.id.btSalvarCliente);

        clienteController = new ClienteController(this);
        enderecoController = new EnderecoController(this);

        ArrayList<Endereco> enderecos = enderecoController.retornarTodosEnderecos();

        String[] vetorEndereco = new String[enderecos.size()];

        for (int i = 0; i < enderecos.size(); i++){
            vetorEndereco[i] = enderecos.get(i).getLogradouro() +" / " + enderecos.get(i).getNumero();
        }


        adapterEndereco = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorEndereco);
        spEnderecoCliente.setAdapter(adapterEndereco);

        spEnderecoCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                codEndereco.setText(String.valueOf(i+1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        botaoSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente();
            }
        });
    }

    private void salvarCliente(){

        String validacao = clienteController.validaClinte(
                nomeCliente.getText().toString(),
                cpfCliente.getText().toString(),
                codEndereco.getText().toString()
        );

        if(!validacao.equals("")){
            if(validacao.toUpperCase().contains("NOME")){
                nomeCliente.setError(validacao);
            }
            if(validacao.toUpperCase().contains("CPF")){
                cpfCliente.setError(validacao);
            }
            if(validacao.toUpperCase().contains("ENDEREÇO")){
                Toast.makeText(this,
                        validacao,
                        Toast.LENGTH_LONG).show();
            }
        }else{
            if(codigoCliente.getText().toString() == null || codigoCliente.getText().toString().equals("")){
                ArrayList<Cliente> clientes = clienteController.retornarTodosClientes();
                if(clienteController.salvarCliente(
                        String.valueOf(clientes.size() +1),
                        nomeCliente.getText().toString(),
                        cpfCliente.getText().toString(),
                        codEndereco.getText().toString()
                ) > 0){
                    Toast.makeText(this,
                            "Cliente cadastrado com sucesso!!",
                            Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,
                            "Erro ao cadastrar Cliente, verifique LOG.",
                            Toast.LENGTH_LONG).show();
                }
            }else{
                if(clienteController.atualizarCliente(
                        codigoCliente.getText().toString(),
                        nomeCliente.getText().toString(),
                        cpfCliente.getText().toString(),
                        codEndereco.getText().toString()
                )>0){
                    Toast.makeText(this,
                            "Cliente atualizado com sucesso!!",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,
                            "Erro ao atualizar Cliente, verifique LOG.",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
