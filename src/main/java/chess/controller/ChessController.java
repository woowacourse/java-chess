package chess.controller;

import chess.domain.ChessGame;
import chess.domain.position.Position;
import chess.dto.ChessBoardDto;
import chess.dto.CommandRequest;
import chess.service.ChessService;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final String WRONG_START_ERROR_MESSAGE = "게임이 진행 중 일 때는 시작할 수 없습니다.";
    private static final String START_NEED_ERROR_MESSAGE = "게임이 시작되어야 합니다.";
    private static final long NEW_GAME_OPTION = 0;

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessService chessService;

    public ChessController(final ChessService chessService) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.chessService = chessService;
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = startNewGame();
        play(chessGame);
    }

    private ChessGame startNewGame() {
        boolean isNotStarted = true;
        while (isNotStarted) {
            isNotStarted = repeatStartRequest();
        }
        ChessGame chessGame = loadOrCreateGame(
            inputView.readStartOption(NEW_GAME_OPTION, chessService.findAllGameIds()));
        printBoard(chessGame);
        return chessGame;
    }

    private boolean repeatStartRequest() {
        try {
            readStartCommand();
            return false;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
        }
        return true;
    }

    private void readStartCommand() {
        if (inputView.requestGameCommand().getCommand() != Command.START) {
            throw new IllegalArgumentException(START_NEED_ERROR_MESSAGE);
        }
    }

    private ChessGame loadOrCreateGame(final long startGameOption) {
        if (startGameOption == NEW_GAME_OPTION) {
            return chessService.createNewGame();
        }
        return chessService.loadSavedGame(startGameOption);
    }

    private void printBoard(final ChessGame chessGame) {
        outputView.printBoard(ChessBoardDto.from(chessGame.getChessBoard()));
    }

    private void play(final ChessGame chessGame) {
        CommandRequest request;
        while ((request = repeatProgressRequest(chessGame.isEnd())).getCommand() != Command.END) {
            progressByCommand(request, chessGame);
        }
    }

    private CommandRequest repeatProgressRequest(final boolean isGameEnd) {
        if (isGameEnd) {
            outputView.alertGameEnd();
        }
        CommandRequest request = null;
        while (request == null) {
            request = readProgressCommand();
        }
        return request;
    }

    private CommandRequest readProgressCommand() {
        try {
            return readCommandExceptStart();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
        }
        return null;
    }

    private CommandRequest readCommandExceptStart() {
        CommandRequest request = inputView.requestGameCommand();
        if (request.getCommand() == Command.START) {
            throw new IllegalArgumentException(WRONG_START_ERROR_MESSAGE);
        }
        return request;
    }

    private void progressByCommand(final CommandRequest request, final ChessGame chessGame) {
        if (request.getCommand() == Command.MOVE) {
            progressMove(request, chessGame);
        }
        if (request.getCommand() == Command.STATUS) {
            printGameResult(chessGame);
        }
    }

    private void progressMove(CommandRequest request, ChessGame chessGame) {
        try {
            chessService.move(chessGame, Position.from(request.getSource()),
                Position.from(request.getDestination()));
            printBoard(chessGame);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
        }
    }

    private void printGameResult(final ChessGame chessGame) {
        outputView.printCurrentScore(chessGame.getCurrentScore());
        try {
            outputView.printWinner(chessGame.findWinningTeam().name());
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
        }
    }

}
