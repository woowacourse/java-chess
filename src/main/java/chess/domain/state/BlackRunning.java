package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;

public class BlackRunning extends Running {

    public BlackRunning(Board board) {
        super(board, Color.BLACK);
    }

    @Override
    protected ChessState move(Position start, Position target) {
        board.move(start, target, color);
        return new WhiteRunning(board);
    }
}
