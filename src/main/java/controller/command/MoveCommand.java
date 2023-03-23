package controller.command;

import domain.ChessBoard;
import domain.Square;
import controller.GameStatus;
import view.OutputView;

public class MoveCommand implements GameCommand {
    private final OutputView outputView;
    private final ChessBoard chessBoard;
    private final Square src;
    private final Square dest;

    public MoveCommand(OutputView outputView, ChessBoard chessBoard, Square src, Square dest) {
        this.outputView = outputView;
        this.chessBoard = chessBoard;
        this.src = src;
        this.dest = dest;
    }

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        if (gameStatus != GameStatus.RUNNING) {
            throw new IllegalArgumentException("먼저 게임을 시작해주세요");
        }
        chessBoard.move(src, dest);
        outputView.printChessBoard(chessBoard);
        return GameStatus.RUNNING;
    }

}
