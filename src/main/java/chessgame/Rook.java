package chessgame;

public class Rook {
    private final String name;
    public Rook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
