package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.square.Square;
import chess.domain.square.Team;

public interface State {
    State start();

    State move(final Square source, final Square target);

    State end();

    double calculateScore(final Team team);

    boolean isEnd();

    boolean isKingDead();

    Team getWinner();

    Board getBoard();
}
