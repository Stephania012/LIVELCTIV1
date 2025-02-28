package fr.urssaf.livelcti.livelctibe;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

//import fr.urssaf.livelcti.livelctibe.dto.livelcti.v3.EntrepriseType;
import fr.urssaf.livelcti.livelctibe.service.EntrepriseService;

/**
 * Classe principale de l'application Spring Boot pour lancement en mode JAR
 * (grâce au main) et WAR (grace au
 * SpringBootServletInitializer)
 * <p>
 * UserDetailsServiceAutoConfiguration.class n'est pas utilisé et la
 * configuration est mise en oeuvre via la classe
 * fr.urssaf.infrastructure.prisme.PrismeSecurityConfiguration de la librairie
 * fullsatack-security-starter
 * <p>
 * En raison d'une problématique liée au TLS du Kafka Registry la classe
 * implémente une surcharge de la configuration du
 * Truststore JVM
 */
@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = "fr.urssaf")
public class LivelctiApplication extends SpringBootServletInitializer {

    // Clés de properties contenant les valeurs spécifiques TLS
    public static final String KEY_CONF_TRUSTSTORE = "fullstack.commons.truststore.location";
    public static final String KEY_CONF_PASSWORD = "fullstack.commons.truststore.password";

    // Properties JVM liées à setter
    public static final String SYSTEM_TRUSTSTORE = "javax.net.ssl.trustStore";
    public static final String SYSTEM_PASSWORD = "javax.net.ssl.trustStorePassword";

    private static final Logger LOG = LoggerFactory.getLogger(LivelctiApplication.class);

    //Code rajouté 030225

    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    private Environment env;

    public static void main(final String[] args) {

        SpringApplication.run(LivelctiApplication.class, args);
    }

    @PostConstruct
    private void setTLSJVMConfiguration() {

        String truststoreValue = env.getProperty(KEY_CONF_TRUSTSTORE);
        String passwordValue = env.getProperty(KEY_CONF_PASSWORD);

        if (truststoreValue != null && !truststoreValue.isEmpty()) {
            System.setProperty(SYSTEM_TRUSTSTORE, truststoreValue);
            LOG.info("An exception occurred with message: {} = {}", SYSTEM_TRUSTSTORE, truststoreValue);
        }

        if (passwordValue != null && !passwordValue.isEmpty()) {
            System.setProperty(SYSTEM_PASSWORD, passwordValue);
            LOG.info("TLS JVM configuration de la clé  {}", SYSTEM_PASSWORD);
        }
    }

    /*@Override
    public void run(final String... args) throws Exception {
        Iterable<EntrepriseType> entreprise = entrepriseService.getEntreprise();
        System.out.println(entreprise);
        //throw new UnsupportedOperationException("Unimplemented method 'run'");
    }*/

}
