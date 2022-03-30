package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.position.Position;

public interface GameState {
    GameState start();

    GameState status();

    GameState move(Position from, Position to);

    Board getBoard();
}
