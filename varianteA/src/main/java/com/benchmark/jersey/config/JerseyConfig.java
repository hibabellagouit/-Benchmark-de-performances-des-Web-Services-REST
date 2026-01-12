package com.benchmark.jersey.config;

import com.benchmark.jersey.repository.CategoryRepository;
import com.benchmark.jersey.repository.ItemRepository;
import com.benchmark.jersey.service.CategoryService;
import com.benchmark.jersey.service.ItemService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ServerProperties;

/**
 * Configuration de l'application Jersey
 * 
 * Cette classe configure l'application Jersey avec les fonctionnalités nécessaires :
 * - Détection automatique des ressources REST
 * - Configuration de Jackson pour le format JSON
 * - Activation de la validation des beans
 * - Configuration de l'injection de dépendances
 */
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        // Scanner les packages pour détecter les ressources REST
        packages("com.benchmark.jersey.resource");
        
        // Enregistrer Jackson pour la sérialisation/désérialisation JSON
        register(JacksonFeature.class);
        register(new JacksonConfig());
        
        // Activer la validation des beans (JSR-380)
        register(org.glassfish.jersey.server.validation.ValidationFeature.class);
        
        // Désactiver WADL (non nécessaire pour le benchmark)
        property(ServerProperties.WADL_FEATURE_DISABLE, true);
        
        // Enregistrer les liaisons d'injection de dépendances
        register(new DependencyBinder());
    }

    /**
     * Configuration de l'injection de dépendances avec HK2
     * 
     * Cette classe interne configure les dépendances qui seront injectées
     * dans les ressources et services de l'application.
     */
    private static class DependencyBinder extends AbstractBinder {
        @Override
        protected void configure() {
            // Créer l'EntityManagerFactory comme un singleton
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("benchmark-pu");
            bind(emf).to(EntityManagerFactory.class);
            
            // Lier les repositories pour l'injection
            bindAsContract(CategoryRepository.class);
            bindAsContract(ItemRepository.class);
            
            // Lier les services pour l'injection
            bindAsContract(CategoryService.class);
            bindAsContract(ItemService.class);
        }
    }
}
