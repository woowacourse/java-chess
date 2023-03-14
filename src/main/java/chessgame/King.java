package chessgame;

public class King {
    private final String name;
    public King(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
