public class Wine extends Product {

    private String type;
    private String country;
    private String region;
    private int vintage;

    public Wine(int idNumber, float abv, float cost, String description, int volume, String producer, String type, String country, String region, int vintage) {
        super.idNumber = idNumber;
        super.abv = abv;
        super.cost = cost;
        super.description = description;
        super.volume = volume;
        super.producer = producer;
        this.type = type;
        this.country = country;
        this.region = region;
    }

    public String getType() {
        return this.type;
    }

    public String getCountry() {
        return this.country;
    }

    public String getRegion() {
        return this.region;
    }

    public int getVintage() { return this.vintage;}

}
