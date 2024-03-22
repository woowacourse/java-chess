package domain.command;

import domain.ChessBoard;

public class EndCommand implements ChessCommand {

    @Override
    public boolean run(final ChessBoard chessBoard) {
        return false;
    }
}
