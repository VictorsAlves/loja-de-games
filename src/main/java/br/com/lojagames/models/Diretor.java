package br.com.lojagames.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author victor
 */
public class Diretor extends Funcionario {
    
    public Diretor(String email, String senha,
                   int id, String nome, String endereco, String cpf,
                   String cep, Date dataNascimento, Timestamp dataInclusao) {
        
        super(email, senha, id, nome, endereco, cpf, cep, dataNascimento, dataInclusao);
    }
    
    public Diretor(String email, String senha,
                   String nome, String endereco, String cpf,
                   String cep, Date dataNascimento, Timestamp dataInclusao) {
        
        super(email, senha, nome, endereco, cpf, cep, dataNascimento, dataInclusao);
    }
    
    public Diretor() {}
    
}
