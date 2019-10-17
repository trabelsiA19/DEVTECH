package models;
// Generated Aug 23, 2019 9:15:04 PM by Hibernate Tools 4.3.1



/**
 * Etudiant generated by hbm2java
 */
public class Etudiant  implements java.io.Serializable {


     private Integer id;
     private Specialitee specialitee;
     private String nom;
     private String prenom;
     private String genere;
     private String email;
     private String dateins;
     private String payment;
     private int cin;

    public Etudiant() {
    }

	
    public Etudiant(Specialitee specialitee, int cin) {
        this.specialitee = specialitee;
        this.cin = cin;
    }
    public Etudiant(Specialitee specialitee, String nom, String prenom, String genere, String email, String dateins, String payment, int cin) {
       this.specialitee = specialitee;
       this.nom = nom;
       this.prenom = prenom;
       this.genere = genere;
       this.email = email;
       this.dateins = dateins;
       this.payment = payment;
       this.cin = cin;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Specialitee getSpecialitee() {
        return this.specialitee;
    }
    
    public void setSpecialitee(Specialitee specialitee) {
        this.specialitee = specialitee;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getGenere() {
        return this.genere;
    }
    
    public void setGenere(String genere) {
        this.genere = genere;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDateins() {
        return this.dateins;
    }
    
    public void setDateins(String dateins) {
        this.dateins = dateins;
    }
    public String getPayment() {
        return this.payment;
    }
    
    public void setPayment(String payment) {
        this.payment = payment;
    }
    public int getCin() {
        return this.cin;
    }
    
    public void setCin(int cin) {
        this.cin = cin;
    }




}


