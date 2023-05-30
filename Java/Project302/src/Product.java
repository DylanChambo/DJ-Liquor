enum Category {
    BEER,
    WINE,
    CIDER,
    SPIRIT,
    LIQUEUR,
    RTD
}

public class Product {

    private int idNumber;
    private float abv;
    private float cost;
    private String description;
    private Category category;

    public Product(int idNumber, float abv, float cost, String description, Category category) {
        this.idNumber = idNumber;
        this.abv = abv;
        this.cost = cost;
        this.description = description;
        this.category = category;
    }

    public int getIdNumber() {
        return this.idNumber;
    }

    public float getAbv() {
        return this.abv;
    }

    public float getCost() {
        return this.cost;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

}
