package chess.domain.state.turn;

import chess.domain.piece.Piece;
import chess.domain.Team;

public interface State {

    boolean isFinished();

    State play(Piece target);

    Team getTeam();
}
