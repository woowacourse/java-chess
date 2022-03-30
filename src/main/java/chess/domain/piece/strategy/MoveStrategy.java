package chess.domain.piece.strategy;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import java.util.List;

public abstract class MoveStrategy {
    public abstract boolean canMove(Team team, Piece targetPiece, Position from, Position to);

    public List<Position> calculateRoute(Position from, Position to) {
        return Position.calculateRoute(from, to);
    }
}
