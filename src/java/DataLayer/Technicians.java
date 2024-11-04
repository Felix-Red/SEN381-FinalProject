package DataLayer;


public class Technicians extends Management{
    private String technicianCity;
    private String technicianMobile;
    private String technicianEmail;

    public Technicians(int technicianId, String technicianName, String technicianCity, String technicianMobile, String technicianEmail) {
        super(technicianId, technicianName);
        this.technicianCity = technicianCity;
        this.technicianMobile = technicianMobile;
        this.technicianEmail = technicianEmail;
    }
    
    public String getTechnicianCity() { return technicianCity; }
    public String getTechnicianMobile() { return technicianMobile; }
    public String getTechnicianEmail() { return technicianEmail; }
    
}
