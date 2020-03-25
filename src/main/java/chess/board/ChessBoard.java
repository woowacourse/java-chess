package chess.board;

import java.util.Map;

public class ChessBoard {
    private final Map<Coordinate, Tile> chessBoard;

    public ChessBoard(BoardGenerator boardGenerator) {
        chessBoard = boardGenerator.generate();
    }
}
