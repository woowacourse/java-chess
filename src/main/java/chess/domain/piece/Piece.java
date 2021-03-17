package chess.domain.piece;

import chess.domain.Team;

public interface Piece {

    void strategy();

    void canMove();

    String getName();

    Team getTeam();

    void checkTurn(Team team);
}
