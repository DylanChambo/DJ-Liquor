public class RTD extends Product {

    private String baseSpirit;

    public RTD(int idNumber, float abv, float cost, String description, int volume, String producer, String baseSpirit) {
        super.idNumber = idNumber;
        super.abv = abv;
        super.cost = cost;
        super.description = description;
        super.volume = volume;
        super.producer = producer;
        this.baseSpirit = baseSpirit;
    }

    public String getBaseSpirit() {
        return this.baseSpirit;
    }

}