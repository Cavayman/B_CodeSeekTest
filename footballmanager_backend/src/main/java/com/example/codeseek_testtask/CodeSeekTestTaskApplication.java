package com.example.codeseek_testtask;

import com.example.codeseek_testtask.model.FootballTeam;
import com.example.codeseek_testtask.model.Player;
import com.example.codeseek_testtask.service.FootballTeamService;
import com.example.codeseek_testtask.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class CodeSeekTestTaskApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CodeSeekTestTaskApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Autowired
    private FootballTeamService footballTeamService;

    @Autowired
    private PlayerService playerService;

    @Override
    public void run(String... args) throws Exception {
        /**
         * DB initialization
         */
        FootballTeam footballTeam1 = new FootballTeam("FC Barcelona", "Spain", 17000000, 7.5F);

        Player player1 = new Player("Marc-André ter Stegen", 29, (short) 134, "Germany");
        Player player2 = new Player("Neto", 32, (short) 161, "Brazil");
        Player player3 = new Player("Iñaki Peña", 22, (short) 106, "Spain");
        Player player4 = new Player("Ronald Araújo", 22, (short) 187, "Uruguay");
        Player player5 = new Player("Eric García", 20, (short) 75, "Spain");
        Player player6 = new Player("Clément Lenglet", 26, (short) 117, "France");
        Player player7 = new Player("Óscar Mingueza", 22, (short) 168, "Spain");
        Player player8 = new Player("Gerard Piqué", 34, (short) 75, "Spain");
        Player player9 = new Player("Samuel Umtiti", 28, (short) 144, "France");
        Player player10 = new Player("Jordi Alba", 32, (short) 70, "Spain");

        FootballTeam footballTeam2 = new FootballTeam("Manchester United", "England", 12000000, 6.8F);
        Player player11 = new Player("Dean Henderson", 24, (short) 94, "England");
        Player player12 = new Player("David de Gea", 31, (short) 136, "Spain");
        Player player13 = new Player("Tom Heaton", 35, (short) 16, "England");
        Player player14 = new Player("Lee Grant", 38, (short) 35, "England");
        Player player15 = new Player("Raphaël Varane", 28, (short) 63, "France");
        Player player16 = new Player("Harry Maguire", 28, (short) 45, "England");
        Player player17 = new Player("Victor Lindelöf", 27, (short) 159, "Sweden");
        Player player18 = new Player("Eric Bailly", 27, (short) 179, "Cote d'Ivoire");
        Player player19 = new Player("Phil Jones", 29, (short) 193, "England");
        Player player20 = new Player("Luke Shaw", 26, (short) 161, "England");

        footballTeamService.saveTeam(footballTeam1);
        footballTeamService.saveTeam(footballTeam2);

        playerService.savePlayer(player1, 1);
        playerService.savePlayer(player2, 1);
        playerService.savePlayer(player3, 1);
        playerService.savePlayer(player4, 1);
        playerService.savePlayer(player5, 1);
        playerService.savePlayer(player6, 1);
        playerService.savePlayer(player7, 1);
        playerService.savePlayer(player8, 1);
        playerService.savePlayer(player9, 1);
        playerService.savePlayer(player10, 1);

        playerService.savePlayer(player11, 2);
        playerService.savePlayer(player12, 2);
        playerService.savePlayer(player13, 2);
        playerService.savePlayer(player14, 2);
        playerService.savePlayer(player15, 2);
        playerService.savePlayer(player16, 2);
        playerService.savePlayer(player17, 2);
        playerService.savePlayer(player18, 2);
        playerService.savePlayer(player19, 2);
        playerService.savePlayer(player20, 2);
    }
}
