package chessgame;

public class Pawn {
    private final String name;
    public Pawn(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
