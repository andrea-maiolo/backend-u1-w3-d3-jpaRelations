package org.example.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "partecipazioni")
public class Partecipazione {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Stato stato;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;


    public Partecipazione() {
    }

    public Partecipazione(Stato stato, Evento evento, Persona persona) {
        this.evento = evento;
        this.persona = persona;
        this.stato = stato;
    }

    public UUID getId() {
        return id;
    }


    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Evento getEvento() {
        return evento;
    }


    public Persona getPersona() {
        return persona;
    }

    @Override
    public String toString() {
        return "Partecipazione{" +
                "id=" + id +
                ", stato=" + stato +
                ", evento=" + evento +
                ", persona=" + persona +
                '}';
    }
}
