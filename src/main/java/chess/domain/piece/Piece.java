package chess.domain.piece;

import chess.domain.board.Strategy;
import chess.domain.board.Team;

public interface Piece {

    Strategy strategy();

    String getName();

    Team getTeam();

    void confirmTurn(Team team);

    boolean isSameTeam(Team team);

    boolean isPawn();

    boolean isKing();

    double getPoint();
}
