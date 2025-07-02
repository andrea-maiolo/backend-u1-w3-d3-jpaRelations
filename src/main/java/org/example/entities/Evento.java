package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue
    private UUID id;
    private String titolo;
    private LocalDate dataEvento;
    private String descrizione;
    private int numeroPartecipanti;
    @Enumerated(EnumType.STRING)
    private TipoEvento tipo;

    @ManyToOne
    @JoinColumn(name = "luogo_evento")
    private Luogo luogoEvento;


    @OneToMany(mappedBy = "evento")
    private List<Partecipazione> partecipazioni;

    public Evento() {
    }

    public Evento(String titolo, String descrizione, int numeroPartecipanti, LocalDate data, TipoEvento tipo, Luogo luogoEvento) {
        this.descrizione = descrizione;
        this.titolo = titolo;
        this.dataEvento = data;
        this.numeroPartecipanti = numeroPartecipanti;
        this.tipo = tipo;
        this.luogoEvento = luogoEvento;
    }

    public UUID getId() {
        return id;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public int getNumeroPartecipanti() {
        return numeroPartecipanti;
    }

    public void setNumeroPartecipanti(int numeroPartecipanti) {
        this.numeroPartecipanti = numeroPartecipanti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", dataEvento=" + dataEvento +
                ", descrizione='" + descrizione + '\'' +
                ", numeroPartecipanti=" + numeroPartecipanti +
                ", tipo=" + tipo +
                '}';
    }
}
