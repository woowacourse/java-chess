package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.dto.BoardDto;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<GameStatus, Consumer<List<String>>> gameStatusMap;
    private Board board;


    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameStatusMap = new EnumMap<>(GameStatus.class);
        initGameStatusMap();
    }

    private void initGameStatusMap() {
        outputView.printInitialMessage();
        gameStatusMap.put(GameStatus.START, this::start);
        gameStatusMap.put(GameStatus.MOVE, this::move);
        gameStatusMap.put(GameStatus.END, this::end);
    }

    private void start(final List<String> input) {
        board = BoardFactory.generate();
        outputView.printBoard(BoardDto.create(board.getBoard()));
    }

    private void move(final List<String> input) {
        board.move(parseToPosition(input.get(1)), parseToPosition(input.get(2)));
        outputView.printBoard(BoardDto.create(board.getBoard()));
    }

    private void end(final List<String> input) {
        outputView.printEndMessage();
    }

    public GameStatus run() {
        final String input = inputView.readCommand();
        final List<String> collect = Arrays.stream(input.split(" "))
                .collect(Collectors.toList());
        final GameStatus gameStatus = GameStatus.from(input);
        gameStatusMap.get(gameStatus)
                .accept(collect);

        return gameStatus;
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
