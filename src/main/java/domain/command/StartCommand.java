package domain.command;

import domain.ChessBoard;

public class StartCommand implements ChessCommand {

    @Override
    public boolean run(final ChessBoard chessBoard) {

        return true;
    }
}
