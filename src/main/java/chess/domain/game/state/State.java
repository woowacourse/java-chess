package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.position.Position;

public interface State {
    State startGame();

    State endGame();

    State move(Position from, Position to);

    State showStatus();

    Board getBoard();
}
