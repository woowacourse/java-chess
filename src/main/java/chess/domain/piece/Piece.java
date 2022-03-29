package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Team;
import java.util.List;

public interface Piece {
    boolean canMove(Piece targetPiece, Position from, Position to);

    boolean isSameTeam(Piece targetPiece);

    boolean isOppositeTeam(Team team);

    boolean isKing();

    boolean isPiece();

    String getName();

    Team getTeam();

    List<Position> getRoute(Position from, Position to);
}
