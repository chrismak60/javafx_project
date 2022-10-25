
package javafxapplication4;

import java.time.LocalDate;



// H KLASH POU 8A XRHSIMOPOIHSOUME WS OBJECT STHN LISTA MAS ME KATALLHLA STOIXEIA POU ANTISTOIXOUNE ME AUTA THS VASHS MAS
public class emergency {
    
    private int id;
    private String name,surname,address,telephone,description,firearms,police,ambulance;
    private LocalDate date;
    
    // O CONSTRUCTORAS TOU OBJECT MAS 
    public emergency(int id,LocalDate date,String name, String surname, String address, String telephone, String description, String firearms, String police, String ambulance) {
        this.id=id;
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
        this.description = description;
        this.firearms = firearms;
        this.police = police;
        this.ambulance = ambulance;
        
    }
    
    
    // GETTERS KAI SETTERS
    public int getID()
    {
        return id;
    }
    
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the firearms
     */
    public String isFirearms() {
        return firearms;
    }

    /**
     * @param firearms the firearms to set
     */
    public void setFirearms(String firearms) {
        this.firearms = firearms;
    }

    /**
     * @return the police
     */
    public String isPolice() {
        return police;
    }

    /**
     * @param police the police to set
     */
    public void setPolice(String police) {
        this.police = police;
    }

    /**
     * @return the ambulance
     */
    public String isAmbulance() {
        return ambulance;
    }

    /**
     * @param ambulance the ambulance to set
     */
    public void setAmbulance(String ambulance) {
        this.ambulance = ambulance;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
