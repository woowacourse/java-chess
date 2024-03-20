package controller;

import domain.ChessBoard;
import domain.piece.PiecesGenerator;
import dto.BoardStatus;
import dto.CommandInfo;
import view.InputView;
import view.OutputView;

public class ChessGame {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(final InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printGameStartMessage();

        CommandInfo commandInfo = inputView.readCommand();
        ChessBoard chessBoard = new ChessBoard(PiecesGenerator.getInstance());

        if (!commandInfo.command().isStart()) {
            throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
        }
        while (!commandInfo.command().isEnd()) {
            BoardStatus boardStatus = chessBoard.status();
            outputView.printChessBoard(boardStatus);

            commandInfo = inputView.readCommand();
            if (commandInfo.command().isStart()) {
                throw new IllegalArgumentException("게임 도중 start 명령어를 입력할 수 없습니다.");
            }
            if (commandInfo.command().isMove()) {

            }
        }
        // 게임 종료
    }
}
