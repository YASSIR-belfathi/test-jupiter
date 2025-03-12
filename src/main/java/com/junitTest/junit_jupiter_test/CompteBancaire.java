package com.junitTest.junit_jupiter_test;

public class CompteBancaire {
    private double solde;
    private SendNotification sendNotification;

    public CompteBancaire(double soldeInitial, SendNotification sendNotification) {
        if (soldeInitial < 0) {
            throw new IllegalArgumentException("Le solde initial ne peut pas être négatif.");
        }
        this.solde = soldeInitial;
        this.sendNotification = sendNotification;
    }

    public double getSolde() {
        return solde;
    }

    public void deposer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être positif.");
        }
        solde += montant;
        sendNotification.sendNotification("Dépôt de " + montant + " effectué.");
    }

    public void retirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif.");
        }
        if (montant > solde) {
            throw new IllegalArgumentException("Fonds insuffisants.");
        }
        solde -= montant;
        sendNotification.sendNotification("Rerait" + montant + "effectué");
    }

    public void transfersVers(CompteBancaire compte, double montant) {
        compte.deposer(montant);
        this.retirer(montant);
    }
}