package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard;

    public Board() {
        chessBoard = BoardInitializer.initializeBoard();
    }

    public Map<Position, Piece> unwrap() {
        return chessBoard;
    }

    public void move(final Position source, final Position target) {
        // 위치가 가능한지 물어보는 기능
        chessBoard.put(target, chessBoard.get(source));
        chessBoard.put(source, new Blank());
    }
}
