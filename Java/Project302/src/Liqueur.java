public class Liqueur extends Product {

    private String type;
    private String country;

    public Liqueur(int idNumber, float abv, float cost, String description, int volume, String producer, String type, String country) {
        super.idNumber = idNumber;
        super.abv = abv;
        super.cost = cost;
        super.description = description;
        super.volume = volume;
        super.producer = producer;
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String getCountry() {
        return this.country;
    }

}