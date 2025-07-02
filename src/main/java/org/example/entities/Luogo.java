package org.example.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "luoghi")
public class Luogo {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String citta;

    @OneToMany(mappedBy = "luogoEvento")
    private List<Evento> eventi;


    public Luogo() {
    }

    public Luogo(String nome, String citta) {
        this.citta = citta;
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public List<Evento> getEventi() {
        return eventi;
    }


    @Override
    public String toString() {
        return "Luogo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", citta='" + citta + '\'' +
                ", eventi=" + eventi +
                '}';
    }
}
