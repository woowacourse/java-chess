package practice;

public class Product {

    private String id;
    private String name;
    private Float price;

    public Product(){
    }

    public Product(String name, Float price) {
        super();
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

}