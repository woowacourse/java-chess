package chess.domain.gamestatus;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;

public abstract class Started implements GameStatus {
    protected Board board = BoardFactory.createInitially();
}
