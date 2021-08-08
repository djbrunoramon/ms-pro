package br.com.publicacoesonline.mspro.utils;

import java.io.Serializable;
/*
    Classe para conversao da descricao do erro para json
 */
public class ExceptionJsonError implements Serializable {

    private String error;

    public ExceptionJsonError(String error) {
        this.error = error;
    }

    public String getError(){
        return error;
    }

}