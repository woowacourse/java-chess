package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;

public class Pawn extends Piece {
    public Pawn(final String team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position current, Position destination, ChessBoard chessBoard) {
        return false;
    }
}
