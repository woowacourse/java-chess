package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.INIT;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.SOURCE_INDEX;
import static chess.controller.GameCommand.START;
import static chess.controller.GameCommand.TARGET_INDEX;
import static chess.controller.GameCommand.from;
import static chess.controller.GameCommand.getPosition;

public final class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<GameCommand, Function<List<String>, GameCommand>> gameStatusMap;
    private ChessGame chessGame;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameStatusMap = new EnumMap<>(GameCommand.class);
        initGameStatusMap();
    }

    private void initGameStatusMap() {
        gameStatusMap.put(START, this::start);
        gameStatusMap.put(MOVE, this::move);
        gameStatusMap.put(END, this::end);
    }

    public void process() {
        outputView.printInitialMessage();
        GameCommand gameCommand = INIT;
        while (!gameCommand.isEnd()) {
            gameCommand = play(gameCommand);
        }
    }

    private GameCommand play(final GameCommand gameCommand) {
        try {
            final List<String> input = inputView.readCommand();
            final GameCommand newGameCommand = from(input);
            return gameStatusMap.get(newGameCommand).apply(input);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return gameCommand;
        }
    }

    private GameCommand start(final List<String> input) {
        if (chessGame != null) {
            throw new IllegalArgumentException("체스 게임은 이미 진행되고 있습니다.");
        }
        chessGame = ChessGameFactory.generate();
        outputView.printBoard(chessGame.getBoard());
        return MOVE;
    }

    private GameCommand move(final List<String> input) {
        if (chessGame == null) {
            throw new IllegalArgumentException("체스 게임은 아직 시작하지 않았습니다.");
        }
        chessGame.move(getPosition(input, SOURCE_INDEX), getPosition(input, TARGET_INDEX));
        if (chessGame.isKingDead()) {
            outputView.printWinner(chessGame.getWinner());
            return END;
        }
        outputView.printBoard(chessGame.getBoard());
        return MOVE;
    }

    private GameCommand end(final List<String> input) {
        outputView.printEndMessage();
        return END;
    }
}
