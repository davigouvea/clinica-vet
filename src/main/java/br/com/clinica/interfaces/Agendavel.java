package br.com.clinica.interfaces;

import java.util.Date;

public interface Agendavel {
    void agendar(Date data);
    void cancelar();
}