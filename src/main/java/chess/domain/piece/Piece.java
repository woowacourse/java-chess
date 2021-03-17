package chess.domain.piece;

import chess.domain.MoveVO;
import chess.domain.Team;

public interface Piece {

    MoveVO strategy();

    void canMove();

    String getName();

    Team getTeam();

    void checkTurn(Team team);

    boolean isSameTeam(Team team);
}
