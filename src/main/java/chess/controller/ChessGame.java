package chess.controller;

import chess.domain.Turn;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.dto.BoardStatus;
import chess.dto.CommandInfo;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private static final int INITIAL_TRY_COUNT = 0;
    private static final int MAXIMUM_TRY_COUNT = 100;
    private static final String GAME_NOT_STARTED = "아직 게임이 시작되지 않았습니다.";
    private static final String GAME_ALREADY_STARTED = "게임 도중 start 명령어를 입력할 수 없습니다.";
    private static final String INVALID_TRY_COUNT = "시도 횟수 제한 초과로 게임을 종료합니다.";

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(final InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printGameStartMessage();
        CommandInfo commandInfo = readStartCommand(INITIAL_TRY_COUNT);
        Turn turn = Turn.first();
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.getInstance());
        play(commandInfo, turn, chessBoard);
    }

    private CommandInfo readStartCommand(final int tryCount) {
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

    private static void validateStartCommand(final CommandInfo commandInfo) {
        if (!commandInfo.command().isStart()) {
            throw new IllegalArgumentException(GAME_NOT_STARTED);
        }
    }

    private void play(CommandInfo commandInfo, final Turn turn, final ChessBoard chessBoard) {
        while (!commandInfo.command().isEnd()) {
            BoardStatus boardStatus = chessBoard.status();
            outputView.printChessBoard(boardStatus);
            commandInfo = readCommand(INITIAL_TRY_COUNT);
            if (commandInfo.command().isMove()) {
                playTurn(turn, chessBoard, commandInfo, INITIAL_TRY_COUNT);
                turn.next();
            }
        }
    }

    private void playTurn(final Turn turn, final ChessBoard chessBoard, final CommandInfo commandInfo, final int tryCount) {
        try {
            chessBoard.move(commandInfo.source().get(), commandInfo.target().get(), turn);
        } catch (IllegalArgumentException e) {
            outputView.printGameErrorMessage(e.getMessage());
            validateTryCount(tryCount);
            playTurn(turn, chessBoard, commandInfo, tryCount + 1);
        }
    }

    private CommandInfo readCommand(final int tryCount) {
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
            throw new IllegalArgumentException(GAME_ALREADY_STARTED);
        }
    }

    private void validateTryCount(final int tryCount) {
        if (tryCount >= MAXIMUM_TRY_COUNT) {
            throw new IllegalArgumentException(INVALID_TRY_COUNT);
        }
    }
}
