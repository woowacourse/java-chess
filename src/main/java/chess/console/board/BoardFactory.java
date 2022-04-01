package chess.console.board;

import chess.console.piece.Piece;
import java.util.Map;

public final class BoardFactory {
    private BoardFactory() {
    }

    public static Board newInstance() {
        return new Board(InitialBoard.getInstance());
    }

    public static Board newInstance(Map<Position, Piece> board) {
        return new Board(board);
    }
}
