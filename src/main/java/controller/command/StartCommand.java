package controller.command;

import domain.ChessBoard;
import controller.GameStatus;
import view.OutputView;

public class StartCommand implements GameCommand {
    private final OutputView outputView;
    private final ChessBoard chessBoard;

    public StartCommand(OutputView outputView, ChessBoard chessBoard) {
        this.outputView = outputView;
        this.chessBoard = chessBoard;
    }

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        if (gameStatus != GameStatus.INIT) {
            throw new IllegalArgumentException("게임이 이미 진행 중 입니다.");
        }
        outputView.printChessBoard(chessBoard);
        return GameStatus.RUNNING;
    }
}
