package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;

public class Empty extends Piece {
    private static final String EMPTY_ERROR = "[ERROR] 기물이 존재하지 않습니다.";
    public static final Empty EMPTY = new Empty(".", Color.NONE);

    public Empty(String name, Color color) {
        super(name, color, new Score(0), ((source, target, chessBoard) -> {
            throw new IllegalArgumentException(EMPTY_ERROR);
        }));
    }

    @Override
    public boolean canMove(Position source, Position target, ChessBoard chessBoard) {
        throw new IllegalArgumentException(EMPTY_ERROR);
    }
}
