package tn.esprit.twin.projetsc2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.twin.projetsc2.entities.Commande;
import tn.esprit.twin.projetsc2.repository.CommandeRepo;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommandeService implements CommandeInterface {
    @Autowired
    private  CommandeRepo commandeRepo;

    @Override
    public List<Commande> retrieveAllCommandes() {
        return commandeRepo.findAll();
    }

    @Override
    public Commande retrieveCommande(Long idCommande) {
        return commandeRepo.findById(idCommande).orElse(null);
    }

    @Override
    public Commande addCommande(Commande c) {
        return commandeRepo.save(c);
    }

    @Override
    public Commande updateCommande(Commande c) {
        if (c.getIdCommande() != null && commandeRepo.existsById(c.getIdCommande())) {
            return commandeRepo.save(c);
        }
        return null;
    }

    @Override
    public void removeCommande(Long idCommande) {
        commandeRepo.deleteById(idCommande);
    }

    @Override
    public List<Commande> addCommandes(List<Commande> commandes) {
        return commandeRepo.saveAll(commandes);
    }

    public CommandeService() {
    }
}
