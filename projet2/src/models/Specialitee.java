package models;
// Generated Aug 23, 2019 9:15:04 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javafx.collections.ObservableList;

/**
 * Specialitee generated by hbm2java
 */
public class Specialitee  implements java.io.Serializable {


     private Integer id;
     private String nomS;
     private Set etudiants = new HashSet(0);
     private Set courses = new HashSet(0);

    public Specialitee() {
    }

	
    public Specialitee(String nomS) {
        this.nomS = nomS;
    }
    public Specialitee(String nomS, Set etudiants, Set courses) {
       this.nomS = nomS;
       this.etudiants = etudiants;
       this.courses = courses;    }

    public Specialitee(ObservableList row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNomS() {
        return this.nomS;
    }
    
    public void setNomS(String nomS) {
        this.nomS = nomS;
    }
    public Set getEtudiants() {
        return this.etudiants;
    }
    
    public void setEtudiants(Set etudiants) {
        this.etudiants = etudiants;
    }
    public Set getCourses() {
        return this.courses;
    }
    
    public void setCourses(Set courses) {
        this.courses = courses;
    }




}


