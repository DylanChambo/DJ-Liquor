public class Cider extends Product {

    private String type;

    public Cider(int idNumber, float abv, float cost, String description, int volume, String producer, String type) {
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
