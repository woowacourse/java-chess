package chess.domain.state.turn;

import chess.domain.Team;
import chess.domain.piece.Piece;

public interface State {

    boolean isFinished();

    State play(Piece target);

    Team getTeam();
}
