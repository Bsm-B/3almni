/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Entite;

/**
 *
 * @author SBS
 */
public class User {

    private int id;
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private String pwd;
    private String date_naiss;
    private int locked;
    private int expired;
    private String role;
    
    public User(){
        
    }
    public User(int id ,String nom, String prenom, String username, String email, String pwd , String date_naiss, int locked, int expired, String role){    
        this.id= id;
        this.nom= nom;
        this.prenom= prenom;
        this.username= username;
        this.email= email;
        this.pwd= pwd;
        this.date_naiss=date_naiss;
        this.locked=locked;
        this.expired=expired;
        this.role=role;
    }
    public User(String nom, String prenom, String username, String email, String pwd , String date_naiss, int locked, int expired, String role){
        this.nom= nom;
        this.prenom= prenom;
        this.username= username;
        this.email= email;
        this.pwd= pwd;
        this.date_naiss=date_naiss;
        this.locked=locked;
        this.expired=expired;
        this.role=role;
    }

    public String getId() {
        return nom;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom(){
        return prenom;
    }
    
    public void SetPrenom(String prenom){
        this.prenom=prenom;
    }
      
    public String getUsername(){
        return username;
    }
    
    public void SetUsername(String username){
        this.username=username;
    }

    public String getEmail(){
        return email;
    }
    
    public void SetEmail(String email){
        this.email=email;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public String getDatenaiss() {
        return date_naiss;
    }
    public void setDatenaiss(String date_naiss) {
        this.date_naiss = date_naiss;
    }

    public int getLocked() {
        return locked;
    }
    public void setLocked(int locked) {
        this.locked = locked;
    }
    
    public int getExpired() {
        return expired;
    }
    
    public void setExpired(int expired) {
        this.expired = expired;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public  String toString(){
        return "User : Id :"+id+" Nom :" +nom+ " Prenom :" +prenom+ "  User Name :"+username+" Email : "+email+" pwd :"+pwd+" Date de Naissonce : "+date_naiss+" Locked :"+locked+" Expired : "+expired+" Role : "+role;
    }

}
