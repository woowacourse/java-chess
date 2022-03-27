package console.command;

import chess.ChessBoard;
import chess.position.Position;
import console.view.OutputView;

public class MoveCommand implements Command {

    private final Position from;
    private final Position to;

    public MoveCommand(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public ChessBoard execute(ChessBoard chessBoard) {
        ChessBoard newChessBoard = chessBoard.transfer(from, to);
        OutputView.printChessBoard(newChessBoard.getPieces());
        return newChessBoard;
    }
}
