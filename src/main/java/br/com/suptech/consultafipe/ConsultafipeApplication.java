package br.com.suptech.consultafipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.suptech.consultafipe.principal.Principal;

@SpringBootApplication
public class ConsultafipeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConsultafipeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal p = new Principal();

        p.getMenu();

    }

}
