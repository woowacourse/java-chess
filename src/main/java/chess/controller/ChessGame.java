package chess.controller;

import chess.domain.Turn;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.dto.BoardStatus;
import chess.dto.CommandInfo;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(final InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printGameStartMessage();

        Turn turn = Turn.first();
        CommandInfo commandInfo = inputView.readCommand();
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.getInstance());

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
                chessBoard.move(commandInfo.source().get(), commandInfo.target().get(), turn);
            }
        }
    }
}
