package domain.command;

import domain.ChessBoard;

public interface ChessCommand {

    boolean run(ChessBoard chessBoard);
}
