package tn.esprit.twin.projetsc2.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClient")
    private Long idClient;

    private String identifiant;

    @Temporal(TemporalType.DATE)
    private Date datePremiereVisite;

    @Transient
    Integer difference;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Commande> commandes;

    // ✅ Ajoute ces méthodes :
    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }

    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }

    public Date getDatePremiereVisite() { return datePremiereVisite; }
    public void setDatePremiereVisite(Date datePremiereVisite) { this.datePremiereVisite = datePremiereVisite; }

    public Integer getDifference() { return difference; }
    public void setDifference(Integer difference) { this.difference = difference; }

    public List<Commande> getCommandes() { return commandes; }
    public void setCommandes(List<Commande> commandes) { this.commandes = commandes; }
}
