package chess.domain.state;

import chess.Command;
import chess.domain.board.Board;
import chess.domain.piece.Color;

public class Running implements ChessState {

    private final Board board;
    private final Color color;

    public Running(Board board, Color color) {
        this.board = board;
        this.color = color;
    }

    @Override
    public ChessState execute(Command command) {
        return null;
    }
}
