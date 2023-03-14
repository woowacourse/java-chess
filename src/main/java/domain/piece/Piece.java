package domain.piece;

abstract class Piece implements Moveable{
    private final String name;

    Piece(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
