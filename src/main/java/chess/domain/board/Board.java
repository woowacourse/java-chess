package chess.domain.board;

import chess.domain.board.move.Angle;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import java.util.Map;

public final class Board {
    private final Map<Position, Piece> board;

    Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public boolean move(Position from, Position to) {
        final Piece piece = board.get(from);

        return Angle.validate(piece, from, to);
//                && validateDistance(from, to)
//                && validatePieceOnPath(from, to);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Map<Color, Double> getScore() {

        return null;
    }

    public boolean isFinished() {
        return board.values()
                .stream()
                .filter(piece -> piece instanceof King)
                .count() < 2;
    }
}
