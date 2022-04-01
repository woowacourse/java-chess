package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.Map;

public final class BoardFactory {
    private BoardFactory() {
    }

    public static Board newInstance() {
        return new Board(InitialBoard.newInstance());
    }

    public static Board newInstance(Map<Position, Piece> board) {
        return new Board(board);
    }
}
