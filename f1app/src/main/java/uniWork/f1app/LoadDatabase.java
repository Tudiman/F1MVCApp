package uniWork.f1app;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uniWork.f1app.Entities.*;
import uniWork.f1app.Repositories.*;
import uniWork.f1app.Services.*;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

@Configuration
public class LoadDatabase {
/*
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(DriverRepository driverRepository, TeamRepository teamRepository,
                                   CarRepository carRepository, TrackRepository trackRepository,
                                   ChampionshipRepository championshipRepository, RaceRepository raceRepository,
                                   RaceService r, ChampionshipService c, CarService car,
                                   DriverContractRepository driverContractRepository, DriverContractService d,
                                   ChampionshipRegistrationRepository championshipRegistrationRepository,
                                   ChampionshipRegistrationService cr) {
        driverRepository.deleteAll();
        teamRepository.deleteAll();
        carRepository.deleteAll();
        trackRepository.deleteAll();
        championshipRepository.deleteAll();
        raceRepository.deleteAll();
        driverContractRepository.deleteAll();
        championshipRegistrationRepository.deleteAll();
        return args -> {
            Driver Ham = new Driver("Lewis", "Hamilton", "Great Britain",
                    new GregorianCalendar(1985, GregorianCalendar.JANUARY, 7), 44);
            Driver Vet = new Driver("Sebastian", "Vettel", "Germany",
                    new GregorianCalendar(1987, GregorianCalendar.JULY, 3), 5);
            Driver Ver = new Driver("Max", "Verstappen", "Netherlands",
                    new GregorianCalendar(1997, GregorianCalendar.JULY, 30), 33);
            Driver Lec = new Driver("Charles", "Leclerc", "Monaco",
                    new GregorianCalendar(1997, GregorianCalendar.OCTOBER, 16), 33);
            Team Mercedes = new Team("Mercedes-AMG Petronas F1 Team", 1954,
                    "Great Britain", "Toto Wolff");
            Team Ferrari = new Team("Scuderia Ferrari", 1929,
                    "Italy", "Mattia Binotto");
            Car W11 = new Car(Mercedes.getName(), "W11 EQ Performance", "Mercedes", 10, 2020);
            Car SF1000 = new Car(Ferrari.getName(), "SF1000", "Ferrari", 5, 2020);
            Track Silverstone = new Track("Silverstone Circuit", 5891, "Great Britain", 1948);
            Track Monza = new Track("Autodromo Nazionale di Monza", 5793, "Italy", 1922);
            Championship C2020 = new Championship(2020);
            Championship C2010 = new Championship(2010);
            List<String> rankings = Arrays.asList(Ham.getName(), Vet.getName(), Ver.getName());
            List<String> rankings2 = Arrays.asList(Ham.getName(), Vet.getName(), Lec.getName(), Ver.getName());
            Race SilverstoneGP = new Race("British Grand Prix", C2020.getYear(), Silverstone.getName(), rankings);
            Race MonzaGP = new Race("Italian Grand Prix", C2020.getYear(), Monza.getName(), rankings2);
            DriverContract HamMerc = new DriverContract(Ham, Mercedes, 2015, 2020);
            DriverContract VerFerrari = new DriverContract(Ver, Ferrari, 2021, 2023);
            DriverContract VetFerrari = new DriverContract(Vet, Ferrari, 2017, 2021);
            DriverContract LecFerrari = new DriverContract(Lec, Ferrari, 2016, 2016);
            DriverContract HamFerrari = new DriverContract(Ham, Ferrari, 2021, 2023);
            DriverContract HamFerrari2 = new DriverContract(Ham, Ferrari, 2012, 2021);
            DriverContract HamMerc2 = new DriverContract(Ham, Mercedes, 2012, 2021);
            ChampionshipRegistration Ferrari2020 = new ChampionshipRegistration(Ferrari, C2020);
            ChampionshipRegistration Merc2020 = new ChampionshipRegistration(Mercedes, C2020);
            driverRepository.save(Ham);
            driverRepository.save(Vet);
            driverRepository.save(Ver);
            driverRepository.save(Lec);
            teamRepository.save(Mercedes);
            teamRepository.save(Ferrari);
            carRepository.save(W11);
            carRepository.save(SF1000);
            trackRepository.save(Silverstone);
            trackRepository.save(Monza);
            championshipRepository.save(C2020);
            championshipRepository.save(C2010);
            raceRepository.save(SilverstoneGP);
            raceRepository.save(MonzaGP);
            driverContractRepository.save(HamMerc);
            driverContractRepository.save(VetFerrari);
            //driverContractRepository.save(HamFerrari);
            driverContractRepository.save(VerFerrari);
            driverContractRepository.save(LecFerrari);
            championshipRegistrationRepository.save(Ferrari2020);
            championshipRegistrationRepository.save(Merc2020);
            System.out.println(c.mostTitles());
        };
    }*/
}
