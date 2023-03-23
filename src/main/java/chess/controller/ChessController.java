package chess.controller;

import chess.domain.ChessGame;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.board.maker.StartingPiecesFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ChessController {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final String DELIMITER = "";
    private final int COMMAND_INDEX = 0;
    private final int MOVE_CURRENT_POSITION_INDEX = 1;
    private final int MOVE_TARGET_POSITION_INDEX = 2;
    private final int VALID_COMMON_SIZE = 3;
    private final Map<GameState, Consumer<List<String>>> CONSUMER_BY_GAME_STATE = Map.of(
            GameState.READY, this::start,
            GameState.RUNNING, this::move,
            GameState.END, strings -> {
            }
    );

    private final InputView inputView;
    private final OutputView outputView;
    private ChessGame chessGame;

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    public void run() {
        outputView.printGameStartGuideMessage();

        GameState gameState = GameState.EMPTY;

        while (gameState != GameState.END) {
            gameState = play();
        }
    }

    private GameState play() {
        try {
            final List<String> commandInputs = inputView.readGameCommand();
            final GameState gameState = GameState.valueOfCommand(commandInputs.get(COMMAND_INDEX));
            CONSUMER_BY_GAME_STATE.get(gameState).accept(commandInputs);
            return gameState;
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            return GameState.EMPTY;
        }
    }

    private void start(final List<String> commandInputs) {
        validateChessGameSetting();
        chessGame = ChessGame.from(new StartingPiecesFactory());
        outputView.printBoard(chessGame.getExistingPieces());
    }

    private void validateChessGameSetting() {
        if (chessGame.isPlaying()) {
            throw new IllegalArgumentException("게임이 이미 실행되고 있습니다.");
        }
    }

    private void move(final List<String> commandInputs) {
        validateCommandSize(commandInputs);
        final Position currentPosition = generatePositionBy(commandInputs.get(MOVE_CURRENT_POSITION_INDEX));
        final Position targetPosition = generatePositionBy(commandInputs.get(MOVE_TARGET_POSITION_INDEX));

        chessGame = chessGame.move(currentPosition, targetPosition);

        outputView.printBoard(chessGame.getExistingPieces());
    }

    private void validateCommandSize(final List<String> commandInputs) {
        if (commandInputs.size() != VALID_COMMON_SIZE) {
            throw new IllegalArgumentException("유효하지 않은 이동 입력입니다.");
        }
    }

    private Position generatePositionBy(String fileRankInput) {
        final List<String> positionLetters = generatePositionLetters(fileRankInput);
        validatePositionLetterSize(positionLetters);
        final String fileValue = String.valueOf(positionLetters.get(FILE_INDEX));
        final String rankValue = String.valueOf(positionLetters.get(RANK_INDEX));
        return new Position(File.valueOfName(fileValue), Rank.valueOfName(rankValue));
    }

    private List<String> generatePositionLetters(final String fileRankInput) {
        return Arrays.stream(fileRankInput.split(DELIMITER)).collect(Collectors.toList());
    }

    private void validatePositionLetterSize(final List<String> positionLetters) {
        if (positionLetters.size() != 2) {
            throw new IllegalArgumentException("유효하지 않은 위치 입력입니다.");
        }
    }

}
