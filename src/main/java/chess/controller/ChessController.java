package chess.controller;

import java.util.List;
import java.util.function.Supplier;

import chess.board.Board;
import chess.board.Position;
import chess.game.ChessGame;
import chess.game.GameStatus;
import chess.piece.Pieces;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private final InputView inputView;
    private final ChessGame chessGame;

    public ChessController(final InputView inputView) {
        this.inputView = inputView;
        this.chessGame = new ChessGame(new Board(new Pieces()));
    }

    public void run() {
        printInitMessage();
        GameStatus gameStatus = GameStatus.INIT;

        while (!(gameStatus == GameStatus.END)) {
            final GameStatus finalGameStatus = gameStatus;
            gameStatus = repeat(() -> handleCommand(finalGameStatus, chessGame));
        }
    }

    private void printInitMessage() {
        OutputView.printGameStartMessage();
        OutputView.printGameCommandInputMessage();
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return repeat(supplier);
        }
    }

    private GameStatus handleCommand(GameStatus gameStatus, final ChessGame chessGame) {
        final List<String> splitGameCommand = inputGameCommand();
        final String gameCommand = splitGameCommand.get(0);

        if (gameCommand.equals("start")) {
            return handleStartCommand(gameStatus, chessGame);
        }
        if (gameCommand.equals("move")) {
            return handleMoveCommand(gameStatus, chessGame, splitGameCommand);
        }
        return GameStatus.END;
    }

    private List<String> inputGameCommand() {
        return repeat(inputView::inputGameCommand);
    }

    private GameStatus handleStartCommand(GameStatus gameStatus, final ChessGame chessGame) {
        checkGameAlreadyStart(gameStatus);
        gameStatus = GameStatus.START;
        OutputView.printBoard(chessGame.generateBoardDto());
        return gameStatus;
    }

    private void checkGameAlreadyStart(final GameStatus gameStatus) {
        if (gameStatus == GameStatus.START) {
            throw new IllegalArgumentException("[ERROR] 게임 플레이 중에는 다시 시작할 수 없습니다.");
        }
    }

    private GameStatus handleMoveCommand(GameStatus gameStatus, final ChessGame chessGame, final List<String> moveCommand) {
        checkGameNotStart(gameStatus);
        final Position sourcePosition = chessGame.generatePosition(moveCommand.get(SOURCE_POSITION_INDEX));
        final Position targetPosition = chessGame.generatePosition(moveCommand.get(TARGET_POSITION_INDEX));
        chessGame.movePiece(sourcePosition, targetPosition);
        OutputView.printBoard(chessGame.generateBoardDto());
        return gameStatus;
    }

    private void checkGameNotStart(final GameStatus gameStatus) {
        if (gameStatus != GameStatus.START) {
            throw new IllegalArgumentException("[ERROR] 게임 시작 이후에 말을 이동할 수 있습니다.");
        }
    }
}
