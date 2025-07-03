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


        MyDao myDao = new MyDao(em);

//        for (int i = 0; i < listaPersone.size(); i++) {
//            myDao.save(listaPersone.get(i));
//            myDao.save((listaLuoghi.get(i)));
//        }

        Luogo luogoFromDb = (Luogo) myDao.getById(Luogo.class, "ebfeb4b5-79bf-4648-aa8a-8a01ecca636d");
        System.out.println(luogoFromDb);
        Evento newEvento = new Evento("festa", "compleanno", 20,
                LocalDate.of(2025, 11, 03), TipoEvento.PUBBLICO, luogoFromDb);

        myDao.save(newEvento);


        //  "f654852b-5ddb-4265-9cef-82df3be8d648"  persona id
    }

}
