package chess.model.board;

import static chess.model.material.Color.WHITE;

import java.util.List;

public final class InitialBoardFactory extends BoardFactory {

    private static final List<String> INITIAL_PIECES = List.of(
        "RNBQKBNR",
        "PPPPPPPP",
        "........",
        "........",
        "........",
        "........",
        "pppppppp",
        "rnbqkbnr"
    );

    @Override
    public Board generate() {
        return new Board(generatePieces(INITIAL_PIECES), WHITE);
    }
}
