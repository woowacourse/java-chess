package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.game.Turn;
import chess.domain.player.Team;

public interface State {

    State start();

    State move(MoveParameter moveParameter, Turn turn);

    State end();

    Board getBoard();

    double getPoints(Team team);

    boolean isEnd();

    Team getWinner();
}
