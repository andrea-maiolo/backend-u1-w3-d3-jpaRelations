package org.example;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.MyDao;
import org.example.entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u1-w3-d3-eventicomplessi");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker(Locale.ITALY);
        Random random = new Random();

        Supplier<Persona> personaSupp = () -> {
            String sNome = faker.backToTheFuture().character();
            String sCognome = faker.gameOfThrones().character();
            String sEmail = faker.internet().emailAddress();
            Sesso sSesso = Sesso.values()[random.nextInt(0, 2)];
            LocalDate sDOB = LocalDate.of(random.nextInt(1980, 2000), random.nextInt(1, 12),
                    random.nextInt(1, 20));

            Persona newPersona = new Persona(sNome, sCognome, sEmail, sDOB, sSesso);
            return newPersona;
        };

        List<Persona> listaPersone = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            listaPersone.add(personaSupp.get());
        }

        Supplier<Luogo> luogoSupp = () -> {
            String sNome = faker.address().streetName();
            String sCitta = faker.address().cityName();
            Luogo newLuogo = new Luogo(sNome, sCitta);
            return newLuogo;
        };

        List<Luogo> listaLuoghi = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            listaLuoghi.add(luogoSupp.get());
        }

        Supplier<Evento> eventSupp = () -> {
            String sTitolo = faker.esports().game();
            int sNMP = random.nextInt(3, 50);
            String sDescri = faker.esports().event();
            TipoEvento sTipo = TipoEvento.values()[random.nextInt(0, 2)];
            LocalDate sLd = LocalDate.of(random.nextInt(2025, 2031), random.nextInt(1, 12),
                    random.nextInt(1, 20));
            Luogo randomLuogo = listaLuoghi.get(random.nextInt(0, 5));

            Evento eventSupplied = new Evento(sTitolo, sDescri, sNMP, sLd, sTipo, randomLuogo);
            return eventSupplied;
        };

        List<Evento> listaEventi = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            listaEventi.add(eventSupp.get());
        }


        Supplier<Partecipazione> partecipazioneSupplier = () -> {
            Stato sStato = Stato.values()[random.nextInt(0, 2)];
            Evento randomEvento = listaEventi.get(random.nextInt(0, 5));
            Persona randomPersona = listaPersone.get(random.nextInt(0, 5));
            Partecipazione newPartecipazione = new Partecipazione(sStato, randomEvento, randomPersona);
            return newPartecipazione;
        };

        List<Partecipazione> listaPartecipazioni = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            listaPartecipazioni.add(partecipazioneSupplier.get());
        }


        MyDao myDao = new MyDao(em);
//        myDao.save(listaPersone.get(1));
//        myDao.save(listaLuoghi.get(3));
//        myDao.save(listaPartecipazioni.get(2));

        System.out.println(listaPersone.get(1).getId());

    }

}
