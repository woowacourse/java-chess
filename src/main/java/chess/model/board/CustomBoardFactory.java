package chess.model.board;

import java.util.List;

public class CustomBoardFactory implements BoardFactory {

    private final List<String> pieces;
    private final int turnCount;

    public CustomBoardFactory(List<String> pieces, int turnCount) {
        this.pieces = pieces;
        this.turnCount = turnCount;
    }

    @Override
    public Board generate() {
        return new Board(generatePieces(pieces), turnCount);
    }
}
