package br.com.publicacoesonline.mspro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class HomeController {

    @Autowired
    private DataSource source;

    @GetMapping
    public String getHome(){
        return "API de Processos";
    }

    @GetMapping("/conexao")
    public String getConnection(){
        String status = "";

        Connection con = null;
        try {
            con = source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(con != null){
            status = "Conexao ok";
        }else{
            status = "Erro ao conectar!";
        }

        return status;
    }
}
