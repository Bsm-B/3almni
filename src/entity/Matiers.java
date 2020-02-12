
package Entite;

/**
 *
 * @author Bsm
 */
public class Matiers {
    private int id;
    private String nom;
    private double coefficient;
    private String chef_module;
    
    public Matiers(){
        
    }
    public Matiers(int id ,String nom ,int coefficient,String chef_module){
        this.id= id;
        this.nom=nom;
        this.coefficient=coefficient;
        this.chef_module=chef_module;
    }
    
    public Matiers(String nom ,int coefficient,String chef_module){
        this.nom=nom;
        this.coefficient=coefficient;
        this.chef_module=chef_module;
    }
    
    
    
    
    
    public int getId(){
        return id;
    }
    
    public void SetId(int id){
        this.id=id;
    }
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getCoefficient(){
        return coefficient;
    }
    
    public void SetCoefficient(double coefficient){
        this.coefficient=coefficient;
    }
    public String getChef_module() {
        return chef_module;
    }
    public void setChef_module(String chef_module) {
        this.nom = chef_module;
    }
    
    
    @Override
    public  String toString(){
        return "Matier : Id :" + id + " Nom :" +nom+ "Coeffiecient :"+coefficient+" Chef Module :" + chef_module;
    }
}
