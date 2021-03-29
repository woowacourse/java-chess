package chess.domain;

public class User {
    private String name;
    private String age;
    private String image = "black_bishop.png";

    public String getImage() {
        return "white_bishop.png";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
