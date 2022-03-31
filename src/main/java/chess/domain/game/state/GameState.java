package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.game.score.ScoreResult;
import chess.domain.position.Position;

public interface GameState {
    GameState start();

    GameState move(Position from, Position to);

    ScoreResult status();

    Board getBoard();
}
