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

    private static void validateStartCommand(final CommandInfo commandInfo) {
        if (!commandInfo.command().isStart()) {
            throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
        }
    }

    public void start() {
        outputView.printGameStartMessage();
        CommandInfo commandInfo = readStartCommand(0);
        Turn turn = Turn.first();
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.getInstance());
        play(commandInfo, turn, chessBoard);
    }

    private CommandInfo readStartCommand(int tryCount) {
        try {
            CommandInfo commandInfo = inputView.readCommand();
            validateStartCommand(commandInfo);
            return commandInfo;
        } catch (IllegalArgumentException e) {
            outputView.printGameErrorMessage(e.getMessage());
            validateTryCount(tryCount);
            return readStartCommand(tryCount + 1);
        }
    }

    private void play(CommandInfo commandInfo, final Turn turn, final ChessBoard chessBoard) {
        while (!commandInfo.command().isEnd()) {
            BoardStatus boardStatus = chessBoard.status();
            outputView.printChessBoard(boardStatus);
            commandInfo = playTurn(turn, chessBoard, 0);
        }
    }

    private CommandInfo playTurn(final Turn turn, final ChessBoard chessBoard, final int tryCount) {
        try {
            CommandInfo commandInfo;
            commandInfo = readCommand(0);
            if (commandInfo.command().isMove()) {
                chessBoard.move(commandInfo.source().get(), commandInfo.target().get(), turn);
                turn.next();
            }
            return commandInfo;
        } catch (IllegalArgumentException e) {
            outputView.printGameErrorMessage(e.getMessage());
            validateTryCount(tryCount);
            return playTurn(turn, chessBoard, tryCount + 1);
        }
    }

    private CommandInfo readCommand(int tryCount) {
        try {
            CommandInfo commandInfo = inputView.readCommand();
            validateInvalidCommand(commandInfo);
            return commandInfo;
        } catch (IllegalArgumentException e) {
            outputView.printGameErrorMessage(e.getMessage());
            validateTryCount(tryCount);
            return readCommand(tryCount + 1);
        }
    }

    private void validateInvalidCommand(final CommandInfo commandInfo) {
        if (commandInfo.command().isStart()) {
            throw new IllegalArgumentException("게임 도중 start 명령어를 입력할 수 없습니다.");
        }
    }


    private void validateTryCount(final int tryCount) {
        if (tryCount >= 100) {
            throw new IllegalArgumentException("시도 횟수 제한 초과로 게임을 종료합니다.");
        }
    }
}
