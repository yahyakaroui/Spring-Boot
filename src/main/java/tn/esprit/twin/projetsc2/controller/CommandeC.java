package tn.esprit.twin.projetsc2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.projetsc2.entities.Commande;
import tn.esprit.twin.projetsc2.services.CommandeInterface;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commandes")
public class CommandeC {
    @Autowired
    private CommandeInterface service;

    // ---------- CRUD ----------
    @GetMapping
    public List<Commande> getAll() {
        return service.retrieveAllCommandes();
    }

    @GetMapping("/{id}")
    public Commande getOne(@PathVariable Long id) {
        return service.retrieveCommande(id);
    }

    @PostMapping
    public Commande create(@RequestBody Commande c) {
        return service.addCommande(c);
    }

    @PutMapping("/{id}")
    public Commande update(@RequestBody Commande c) {
        return service.updateCommande(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeCommande(id);
    }

    @PostMapping("/batch")
    public List<Commande> createBatch(@RequestBody List<Commande> commandes) {
        return service.addCommandes(commandes);
    }

    // ---------- Keyword & JPQL ----------
    @GetMapping("/by-montant")
    public List<Commande> findByMontant(@RequestParam Float montant) {
        return service.retrieveAllCommandes()
                .stream()
                .filter(c -> c.getTotalCommande() != null && c.getTotalCommande() > montant)
                .toList();
    }

    @GetMapping("/by-date")
    public List<Commande> findByDateRange(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return service.retrieveAllCommandes()
                .stream()
                .filter(c -> c.getDateCommande() != null &&
                        (c.getDateCommande().isAfter(from) || c.getDateCommande().isEqual(from)) &&
                        (c.getDateCommande().isBefore(to) || c.getDateCommande().isEqual(to)))
                .toList();
    }
}
