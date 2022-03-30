package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Team;
import java.util.List;

public interface Piece {
    boolean canMove(Piece targetPiece, Position from, Position to);

    boolean isSameTeam(Piece targetPiece);

    boolean isSameTeamOrEmpty(Team team);

    boolean isKing();

    boolean isPiece();

    String getName();

    Team getTeam();

    List<Position> calculateRoute(Position from, Position to);


    boolean isPawn();

    double getScore();
}
