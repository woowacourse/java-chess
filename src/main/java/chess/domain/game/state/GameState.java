package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;

public interface GameState {

    GameState initBoard();

    GameState movePiece(Position fromPosition, Position toPosition);

    GameState end();

    boolean isFinish();

    Board getBoard();
}
