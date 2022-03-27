package console.command;

import chess.ChessBoard;
import chess.piece.Color;
import console.view.OutputView;

public class StatusCommand implements Command {

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public ChessBoard execute(ChessBoard chessBoard) {
        OutputView.printScores(chessBoard.getScore(Color.WHITE).getValue(),
            chessBoard.getScore(Color.BLACK).getValue());
        return chessBoard;
    }
}
