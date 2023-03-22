package chess.controller;

import chess.domain.ChessGame;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ChessController {
    private final int COMMAND_INDEX = 0;
    private final int MOVE_CURRENT_POSITION_INDEX = 1;
    private final int MOVE_TARGET_POSITION_INDEX = 2;
    private final Map<GameState, Consumer<List<String>>> stateConsumerMap = Map.of(
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
            stateConsumerMap.get(gameState).accept(commandInputs);
            return gameState;
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            return GameState.EMPTY;
        }
    }

    private void start(final List<String> commandInputs) {
        if (chessGame.hasRun()) {
            throw new IllegalArgumentException("게임이 이미 실행되고 있습니다.");
        }
        chessGame = ChessGame.createWithSetBoard();
        outputView.printBoard(chessGame.getExistingPieces());
    }

    private void move(final List<String> commandInputs) {
        if (commandInputs.size() != 3) {
            throw new IllegalArgumentException("입력이 잘못 되었습니다.");
        }
        final Position currentPosition = generatePositionBy(commandInputs.get(MOVE_CURRENT_POSITION_INDEX));
        final Position targetPosition = generatePositionBy(commandInputs.get(MOVE_TARGET_POSITION_INDEX));

        chessGame.move(currentPosition, targetPosition);

        outputView.printBoard(chessGame.getExistingPieces());
    }

    private Position generatePositionBy(String fileRankInput) {
        final String fileValue = String.valueOf(fileRankInput.charAt(0));
        final String rankValue = String.valueOf(fileRankInput.charAt(1));

        return new Position(File.findByValue(fileValue), Rank.findByValue(rankValue));
    }

}
