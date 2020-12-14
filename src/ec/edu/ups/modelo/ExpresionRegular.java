/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Paul Idrovo
 */
public class ExpresionRegular {

    private Pattern patron;
    private Matcher corpus;

    public void ingresarRegex(String regex) {
        patron = Pattern.compile(regex);
    }

    public boolean validar(String texto) {
        corpus = patron.matcher(texto);
        return corpus.find();
    }

    public int obtenerId(String texto) {
        corpus = patron.matcher(texto);
        while (corpus.find()) {
            int id = Integer.parseInt(corpus.group(0));
            return id;
        }
        return 0;
    }
    public String obtenerTexto(String texto) {
        corpus = patron.matcher(texto);
        while (corpus.find()) {
            String id = (corpus.group(0));
            return id;
        }
        return "0";
    }

    public Set <String> obtenerUrlGoogle(String paginaTexto) {
        Set <String> resutaldos = new HashSet();
        corpus = patron.matcher(paginaTexto);
        while (corpus.find()) {
            resutaldos.add(corpus.group(0));
        }
        return resutaldos;
    }
    
    public String obtenertitulo(String paginaTexto) {
        corpus = patron.matcher(paginaTexto);
        while (corpus.find()) {
            String titulo = (corpus.group(0));
            return titulo;
        }
        return "TITULO NO ENCONTRADO";
    }

    public Pattern getPatron() {
        return patron;
    }

    public void setPatron(Pattern patron) {
        this.patron = patron;
    }

    public Matcher getCorpus() {
        return corpus;
    }

    public void setCorpus(Matcher corpus) {
        this.corpus = corpus;
    }

}
