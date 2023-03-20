package chess.controller;

import chess.board.Board;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.board.dto.BoardDto;
import chess.game.GameCommand;
import chess.game.GameStatus;
import chess.piece.AllPiecesGenerator;
import chess.piece.Pieces;
import chess.piece.Side;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ChessController {
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private final InputView inputView;

    public ChessController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        GameStatus gameStatus = GameStatus.INIT;
        final Board board = setUp();

        while(!(gameStatus == GameStatus.END)) {
            final GameStatus finalGameStatus = gameStatus;
            gameStatus = repeatUntilReturnValue(() -> handleCommand(finalGameStatus, board));
        }
    }

    private <T> T repeatUntilReturnValue(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return repeatUntilReturnValue(supplier);
        }
    }

    private Board setUp() {
        Board board = new Board(new Pieces(new AllPiecesGenerator()), Side.WHITE);
        OutputView.printGameStartMessage();
        OutputView.printGameCommandInputMessage();
        return board;
    }

    private GameStatus handleCommand(GameStatus gameStatus, final Board board) {
        final List<String> splitGameCommand = inputGameCommand();
        final GameCommand gameCommand = GameCommand.of(splitGameCommand.get(0));

        if (GameCommand.START == gameCommand) {
            return handleStartCommand(gameStatus, board);
        }
        if (GameCommand.MOVE == gameCommand) {
            return handleMoveCommand(gameStatus, board, splitGameCommand);
        }
        return GameStatus.END;
    }

    private List<String> inputGameCommand() {
        return repeatUntilReturnValue(inputView::inputGameCommand);
    }

    private GameStatus handleStartCommand(GameStatus gameStatus, final Board board) {
        checkGameAlreadyStart(gameStatus);
        gameStatus = GameStatus.START;
        OutputView.printBoard(new BoardDto(board));
        return gameStatus;
    }

    private void checkGameAlreadyStart(final GameStatus gameStatus) {
        if (gameStatus == GameStatus.START) {
            throw new IllegalArgumentException("[ERROR] 게임 플레이 중에는 다시 시작할 수 없습니다.");
        }
    }

    private GameStatus handleMoveCommand(GameStatus gameStatus, final Board board, final List<String> moveCommand) {
        checkGameNotStart(gameStatus);
        final Position sourcePosition = generatePosition(moveCommand.get(SOURCE_POSITION_INDEX));
        final Position targetPosition = generatePosition(moveCommand.get(TARGET_POSITION_INDEX));

        board.movePiece(sourcePosition, targetPosition);
        OutputView.printBoard(new BoardDto(board));
        return gameStatus;
    }

    private void checkGameNotStart(final GameStatus gameStatus) {
        if (gameStatus != GameStatus.START) {
            throw new IllegalArgumentException("[ERROR] 게임 시작 이후에 말을 이동할 수 있습니다.");
        }
    }

    private Position generatePosition(final String positionInput) {
        final List<String> splitSourcePosition = Arrays.asList(positionInput.split(""));
        final File sourceFile = File.of(splitSourcePosition.get(0));
        final Rank sourceRank = extractRank(splitSourcePosition);
        return new Position(sourceFile, sourceRank);
    }

    private Rank extractRank(final List<String> splitSourcePosition) {
        try {
            return Rank.of(Integer.parseInt(splitSourcePosition.get(SOURCE_POSITION_INDEX)));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 랭크 값은 숫자여야 합니다.");
        }
    }
}
