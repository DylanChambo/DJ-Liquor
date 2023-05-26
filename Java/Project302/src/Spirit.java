public class Spirit extends Product {

    private String type;
    private String country;

    public Spirit(int idNumber, float abv, float cost, String description, int volume, String producer, String type, String country) {
        super.idNumber = idNumber;
        super.abv = abv;
        super.cost = cost;
        super.description = description;
        super.volume = volume;
        super.producer = producer;
        this.type = type;
        this.country = country;
    }

    public String getType() {
        return this.type;
    }

    public String getCountry() {
        return this.country;
    }

}