package chess.domain.piece;

import chess.domain.Strategy;
import chess.domain.Team;

public interface Piece {

    Strategy strategy();

    void canMove();

    String getName();

    Team getTeam();

    void checkTurn(Team team);

    boolean isSameTeam(Team team);

    boolean isPawn();

    boolean isKing();

    double getPoint();
}
