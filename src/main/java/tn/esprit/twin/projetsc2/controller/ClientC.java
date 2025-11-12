package tn.esprit.twin.projetsc2.controller;

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
public class ClientC {
    @Autowired
    private  ClientInterface service;

    // ---------- CRUD ----------
    @GetMapping
    public List<Client> getAll() {
        return service.retrieveAllClients();
    }

    @GetMapping("/{id}")
    public Client getOne(@PathVariable Long id) {
        return service.retrieveClient(id);
    }

    @PostMapping
    public Client create(@RequestBody Client c) {
        return service.addClient(c);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client c) {
        return service.updateClient(id, c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeClient(id);
    }

    @PostMapping("/batch")
    public List<Client> addBatch(@RequestBody List<Client> clients) {
        return service.addClients(clients);
    }

    // ---------- Keyword & JPQL ----------
    @GetMapping("/search")
    public List<Client> search(@RequestParam String identifiant) {
        return service.searchByIdentifiant(identifiant);
    }

    @GetMapping("/by-date")
    public List<Client> byDate(@RequestParam Date from, @RequestParam Date to) {
        return service.findByDateRange(from, to);
    }

    @GetMapping("/with-min-orders")
    public List<Client> withMinOrders(@RequestParam int min) {
        return service.withMinOrders(min);
    }
}
