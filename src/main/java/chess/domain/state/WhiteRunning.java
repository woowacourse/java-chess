package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;

public class WhiteRunning extends Running {

    public WhiteRunning(Board board) {
        super(board, Color.WHITE);
    }

    @Override
    protected ChessState move(Position start, Position target) {
        board.move(start, target, color);
        return new BlackRunning(board);
    }
}
