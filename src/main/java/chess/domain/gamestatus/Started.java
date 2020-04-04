package chess.domain.gamestatus;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.score.Score;
import java.util.Map;

public abstract class Started implements GameStatus {

    protected Board board;

    protected Started() {
        this.board = BoardFactory.createInitially();
    }

    protected Started(Board board) {
        this.board = board;
    }

    @Override
    public Score scoring() {
        return board.calculateScore();
    }

    @Override
    public String getBoardString() {
        return board.toString();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
