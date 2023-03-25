package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.square.Square;
import chess.domain.square.Team;

public interface State {
    State start();

    State move(final Square source, final Square target);

    State end();

    Board getBoard();

    boolean isRunning();

    double calculateScore(final Team team);

    boolean isEnd();

    Team getWinner();
}
