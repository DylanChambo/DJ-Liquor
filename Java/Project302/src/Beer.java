public class Beer extends Product {

    private String type;

    public Beer(int idNumber, float abv, float cost, String description, int volume, String producer, String Type) {
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

}
