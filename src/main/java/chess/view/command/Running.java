package chess.view.command;

import chess.domain.ChessBoard;

public class Running implements Command {

    private final ChessBoard chessBoard;

    public Running(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public Command run(final String command) {
        return null;
    }
}
