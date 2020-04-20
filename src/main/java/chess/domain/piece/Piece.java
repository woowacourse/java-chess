package chess.domain.piece;

import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

public interface Piece {
    Piece move(Position to, Piece exPiece);

    boolean hasHindrance(Position to, PiecesState piecesState);

    Team getTeam();

    Direction getForwardDirection();

    boolean isNotBlank();

    boolean isBlank();

    boolean isEnemy(Piece piece);

    boolean isKing();

    boolean attackedKing();

    boolean isSameTeam(Team team);

    boolean isSameTeam(Piece piece);

    Position getPosition();

    Score calculateScore(PiecesState piecesState);
}
