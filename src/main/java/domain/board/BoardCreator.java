package domain.board;

public class BoardCreator {
    private final SquaresGenerator squaresGenerator = new SquaresGenerator();

    public Board create() {
        return new Board(squaresGenerator.generate());
    }
}
