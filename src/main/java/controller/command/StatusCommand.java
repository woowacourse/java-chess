package controller.command;

import controller.GameStatus;
import domain.ChessBoard;
import view.OutputView;

public class StatusCommand implements GameCommand {
    private final OutputView outputView;
    private final ChessBoard chessBoard;

    public StatusCommand(OutputView outputView, ChessBoard chessBoard) {
        this.outputView = outputView;
        this.chessBoard = chessBoard;
    }

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        outputView.printChessBoard(chessBoard);
        outputView.printScore(chessBoard);
        return gameStatus;
    }
}