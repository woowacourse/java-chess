package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.player.Team;

public interface State {

    State start(String param);

    State move(MoveParameter moveParameter);

    State end(String param);

    Board getBoard();

    double getPoints(Team team);

    boolean isEnd();

    Team getWinner();
}
