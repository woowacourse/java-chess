package chess.domain.state;

import chess.domain.Board;
import chess.domain.piece.Color;

public interface State {

    State start();

    State end();

    State move(final String[] commands);

    State status();

    boolean isEnded();

    boolean isStarted();

    Color getColor();

    Board getBoard();
}
