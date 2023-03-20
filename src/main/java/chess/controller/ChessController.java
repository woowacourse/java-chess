package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class ChessController {

    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<GameStatus, Function<List<String>, GameStatus>> gameStatusMap;
    private Board board;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameStatusMap = new EnumMap<>(GameStatus.class);
        initGameStatusMap();
    }

    private void initGameStatusMap() {
        gameStatusMap.put(GameStatus.START, this::start);
        gameStatusMap.put(GameStatus.MOVE, this::move);
        gameStatusMap.put(GameStatus.END, this::end);
    }

    public void process() {
        outputView.printInitialMessage();
        GameStatus gameStatus = GameStatus.INIT;
        while (!gameStatus.isEnd()) {
            gameStatus = play(gameStatus);
        }
    }

    private GameStatus play(final GameStatus gameStatus) {
        try {
            final List<String> input = inputView.readCommand();
            final GameStatus newGameStatus = GameStatus.from(input);
            return gameStatusMap.get(newGameStatus).apply(input);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return gameStatus;
        }
    }

    private GameStatus start(final List<String> input) {
        board = BoardFactory.generate();
        outputView.printBoard(BoardDto.create(board.getBoard()));
        return GameStatus.MOVE;
    }

    private GameStatus move(final List<String> input) {
        board.move(
                parseToPosition(input.get(SOURCE_INDEX)),
                parseToPosition(input.get(TARGET_INDEX)));
        outputView.printBoard(BoardDto.create(board.getBoard()));
        return GameStatus.MOVE;
    }

    private GameStatus end(final List<String> input) {
        outputView.printEndMessage();
        return GameStatus.END;
    }

    private Position parseToPosition(final String command) {
        final int fileOrder = getFileOrder(command);
        final int rankOrder = getRankOrder(command);
        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }

    private int getFileOrder(final String command) {
        final int charToIntDifference = 96;
        return command.charAt(0) - charToIntDifference;
    }

    private int getRankOrder(final String command) {
        final char charToIntDifference = '0';
        return command.charAt(1) - charToIntDifference;
    }
}
