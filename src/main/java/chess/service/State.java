package chess.service;

import chess.domain.board.Board;
import chess.domain.position.Position;

public interface State {

    State start();

    State move(final Board board, final Position from, final Position to);

    State end();
}
