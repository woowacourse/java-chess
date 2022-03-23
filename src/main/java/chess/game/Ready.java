package chess.game;

import chess.board.Board;
import chess.board.BoardGenerator;
import chess.piece.Piece;

public class Ready extends Started {

    public Ready() {
        super(Board.of(new BoardGenerator()));
    }

    @Override
    public GameState start() {
        return new Running(board);
    }

    @Override
    public GameState finish() {
        return new Finished(board);
    }
}
