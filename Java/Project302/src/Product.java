public abstract class Product {

    protected int idNumber;
    protected float abv;
    protected float cost;
    protected String description;
    protected int volume;
    protected String producer;

    protected Product() {

    }

    protected Product(int idNumber, float abv, float cost, String description, int volume, String producer) {
        this.idNumber = idNumber;
        this.abv = abv;
        this.cost = cost;
        this.description = description;
        this.volume = volume;
        this.producer = producer;
    }

    protected int getIdNumber() {
        return this.idNumber;
    }

    protected float getAbv() {
        return this.abv;
    }

    protected float getCost() {
        return this.cost;
    }

    protected String getDescription() {
        return this.description;
    }

    protected int getVolume() {
        return this.volume;
    }

    protected String getProducer() {
        return this.producer;
    }

}
