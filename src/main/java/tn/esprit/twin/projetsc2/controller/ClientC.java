package tn.esprit.twin.projetsc2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin.projetsc2.entities.Client;
import tn.esprit.twin.projetsc2.services.ClientInterface;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
@Tag(name = "Client Management", description = "API pour la gestion des clients (CRUD + requêtes personnalisées)")
public class ClientC {
    @Autowired
    private ClientInterface service;

    // ---------- CRUD ----------
    @Operation(
            summary = "Lister tous les clients",
            description = "Retourne la liste complète des clients enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<Client> getAll() {
        return service.retrieveAllClients();
    }

    @Operation(
            summary = "Récupérer un client par ID",
            description = "Retourne les informations d’un client selon son identifiant"
    )
    @ApiResponse(responseCode = "200", description = "Client trouvé")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @GetMapping("/{id}")
    public Client getOne(
            @Parameter(description = "ID du client à rechercher") @PathVariable Long id) {
        return service.retrieveClient(id);
    }

    @Operation(
            summary = "Créer un nouveau client",
            description = "Ajoute un nouveau client dans la base de données"
    )
    @ApiResponse(responseCode = "201", description = "Client créé avec succès")
    @PostMapping
    public Client create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objet Client à créer",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Client.class))
            )
            @RequestBody Client c) {
        return service.addClient(c);
    }

    @Operation(
            summary = "Mettre à jour un client",
            description = "Met à jour les informations d’un client existant"
    )
    @ApiResponse(responseCode = "200", description = "Client mis à jour avec succès")
    @PutMapping("/{id}")
    public Client update(
            @Parameter(description = "ID du client à mettre à jour") @PathVariable Long id,
            @RequestBody Client c) {
        return service.updateClient(id, c);
    }

    @Operation(
            summary = "Supprimer un client",
            description = "Supprime un client de la base via son identifiant"
    )
    @ApiResponse(responseCode = "204", description = "Client supprimé avec succès")
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "ID du client à supprimer") @PathVariable Long id) {
        service.removeClient(id);
    }

    @Operation(
            summary = "Créer plusieurs clients en une seule requête",
            description = "Ajoute une liste de clients simultanément"
    )
    @PostMapping("/batch")
    public List<Client> addBatch(@RequestBody List<Client> clients) {
        return service.addClients(clients);
    }

    // ---------- Keyword & JPQL ----------
    @Operation(summary = "Rechercher par identifiant (keyword)")
    @GetMapping("/search")
    public List<Client> search(
            @Parameter(description = "Fragment d'identifiant à rechercher") @RequestParam String identifiant) {
        return service.searchByIdentifiant(identifiant);
    }

    @Operation(summary = "Rechercher les clients entre deux dates (keyword)")
    @GetMapping("/by-date")
    public List<Client> byDate(
            @Parameter(description = "Date de début") @RequestParam Date from,
            @Parameter(description = "Date de fin") @RequestParam Date to) {
        return service.findByDateRange(from, to);
    }

    @Operation(summary = "Rechercher les clients ayant un nombre minimum de commandes (JPQL)")
    @GetMapping("/with-min-orders")
    public List<Client> withMinOrders(@RequestParam int min) {
        return service.withMinOrders(min);
    }
}
