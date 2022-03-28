package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;

public interface GameState {
    GameState movePiece(Board board, Position fromPosition, Position toPosition);

    boolean isFinish();

    boolean isWaiting();
}
