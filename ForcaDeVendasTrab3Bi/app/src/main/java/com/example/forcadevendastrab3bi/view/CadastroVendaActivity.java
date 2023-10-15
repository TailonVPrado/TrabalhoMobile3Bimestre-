package com.example.forcadevendastrab3bi.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forcadevendastrab3bi.R;
import com.example.forcadevendastrab3bi.controller.ClienteController;
import com.example.forcadevendastrab3bi.controller.EnderecoController;
import com.example.forcadevendastrab3bi.controller.ItemController;
import com.example.forcadevendastrab3bi.controller.PedidoVendaController;
import com.example.forcadevendastrab3bi.model.Cliente;
import com.example.forcadevendastrab3bi.model.Endereco;
import com.example.forcadevendastrab3bi.model.Item;

import java.util.ArrayList;

public class CadastroVendaActivity extends AppCompatActivity {

    private Spinner spEnderecoVenda;
    private EditText enderecoVenda;
    private Spinner spClienteVenda;
    private EditText clienteVenda;
    private Spinner spItemVenda;
    private EditText itemVenda;
    private EditText quantidadeVenda;
    private RadioGroup radioGroup;
    private EditText quantidadeParcelasVenda;
    private EditText valorTotalVenda;
    private Button botaoSalvarVenda;

    private PedidoVendaController pedidoVendaController;
    private EnderecoController enderecoController;
    private ItemController itemController;
    private ClienteController clienteController;

    private ArrayAdapter adapterEndereco;
    private ArrayAdapter adapterItem;
    private ArrayAdapter adapterCliente;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_venda);
        setTitle("Cadastrar Venda");

        spEnderecoVenda = findViewById(R.id.spEnderecoVenda);
        enderecoVenda = findViewById(R.id.edEnderecoVenda);
        spClienteVenda = findViewById(R.id.spClienteVenda);
        clienteVenda = findViewById(R.id.edClienteVenda);
        spItemVenda = findViewById(R.id.spItemVenda);
        itemVenda = findViewById(R.id.edItemVenda);
        quantidadeVenda = findViewById(R.id.quantidadeVenda);
        radioGroup = findViewById(R.id.radioGroup);
        quantidadeParcelasVenda = findViewById(R.id.quantidadeParcelasVenda);
        valorTotalVenda = findViewById(R.id.valorFinalVenda);
        botaoSalvarVenda = findViewById(R.id.btSalvarVenda);

        pedidoVendaController = new PedidoVendaController(this);
        enderecoController = new EnderecoController(this);
        clienteController = new ClienteController(this);
        itemController = new ItemController(this);

        ArrayList<Endereco> enderecos = enderecoController.retornarTodosEnderecos();
        ArrayList<Item> itens = itemController.retornarTodositens();
        ArrayList<Cliente> clientes = clienteController.retornarTodosClientes();

        String[] vetorEnderecos = new String[enderecos.size()];
        String[] vetorItens = new String[itens.size()];
        String[] vetorClientes = new String[clientes.size()];

        for (int i = 0; i < enderecos.size(); i++){
            vetorEnderecos[i] = enderecos.get(i).getLogradouro() +" / " + enderecos.get(i).getNumero();
        }
        for (int i = 0; i < itens.size(); i++){
            vetorItens[i] = itens.get(i).getDescricao();
        }
        for (int i = 0; i < clientes.size(); i++){
            vetorClientes[i] = clientes.get(i).getNome();
        }
        adapterEndereco = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorEnderecos);
        adapterItem = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorItens);
        adapterCliente = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorClientes);

        spEnderecoVenda.setAdapter(adapterEndereco);
        spItemVenda.setAdapter(adapterItem);
        spClienteVenda.setAdapter(adapterCliente);

        spEnderecoVenda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                enderecoVenda.setText(String.valueOf(i+1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spItemVenda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemVenda.setText(String.valueOf(i+1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spClienteVenda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                clienteVenda.setText(String.valueOf(i+1));
                calculaValorTotalVenda();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbAvista:
                        quantidadeParcelasVenda.setVisibility(View.GONE);
                        break;
                    case R.id.rbParcelado:
                        quantidadeParcelasVenda.setVisibility(View.VISIBLE);
                        break;
                }
                calculaValorTotalVenda();
            }
        });

        botaoSalvarVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarVenda();
            }
        });
    }

    private void salvarVenda(){

    }

    private void calculaValorTotalVenda(){
        Double valorFrete = 0.0;
        Double valorItens = 0.0;
        Double valorFinal = 0.0;
        if(enderecoVenda.getText().toString() != null || !enderecoVenda.getText().toString().isEmpty()){
            Endereco endereco = enderecoController.retornarEndereco(Integer.parseInt(enderecoVenda.getText().toString()));
            if(endereco.getCidade().toUpperCase() != "PARANÁ"){
                valorFrete = 50.0;
            }else if(endereco.getCidade().toUpperCase() != "TOLEDO"){
                valorFrete = 20.0;
            }
        }

        if(!itemVenda.getText().toString().isEmpty() ){
            System.out.println(itemVenda.getText().toString());
            System.out.println(!itemVenda.getText().toString().isEmpty());
            Item item = itemController.retornarItem(Integer.parseInt(itemVenda.getText().toString()));
            valorItens = item.getValorUnit();

            if(quantidadeVenda.getText().toString() != null || !quantidadeVenda.getText().toString().isEmpty()){
                valorItens = valorItens * Double.parseDouble(quantidadeVenda.getText().toString());
            }
        }

        valorFinal = valorFrete + valorItens;
        int checkedId = radioGroup.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rbAvista:
                valorFinal = valorFinal * 0.95;
                break;
            case R.id.rbParcelado:
                break;
        }
        valorTotalVenda.setText("R$ "+valorFinal);
    }

}
