package chess.domain.game.state;

import chess.domain.board.position.Position;

public interface State {

    State start();

    State move(Position source, Position target);

    State end();
}
