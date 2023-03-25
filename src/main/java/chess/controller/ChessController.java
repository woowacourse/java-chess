package chess.controller;

import chess.dao.ChessDao;
import chess.dao.DbChessGameDao;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameFactory;
import chess.view.IOViewResolver;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.INIT;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.SOURCE_INDEX;
import static chess.controller.GameCommand.START;
import static chess.controller.GameCommand.STATUS;
import static chess.controller.GameCommand.TARGET_INDEX;
import static chess.controller.GameCommand.from;
import static chess.controller.GameCommand.getPosition;

public final class ChessController {

    private final IOViewResolver viewResolver;
    private final Map<GameCommand, Function<List<String>, GameCommand>> gameStatusMap;
    private final ChessDao dao;
    private ChessGame chessGame;

    public ChessController(final IOViewResolver viewResolver) {
        this.viewResolver = viewResolver;
        this.gameStatusMap = new EnumMap<>(GameCommand.class);
        this.dao = new DbChessGameDao();
        initGameStatusMap();
    }

    private void initGameStatusMap() {
        gameStatusMap.put(START, this::start);
        gameStatusMap.put(MOVE, this::move);
        gameStatusMap.put(STATUS, this::status);
        gameStatusMap.put(END, this::end);
    }

    public void process() {
        viewResolver.printInitialMessage();
        GameCommand gameCommand = INIT;
        while (!gameCommand.isEnd()) {
            gameCommand = play(gameCommand);
        }
    }

    private GameCommand play(final GameCommand gameCommand) {
        try {
            final List<String> input = viewResolver.readCommand();
            final GameCommand newGameCommand = from(input);
            return gameStatusMap.get(newGameCommand).apply(input);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            viewResolver.printErrorMessage(exception.getMessage());
            return gameCommand;
        }
    }

    private GameCommand start(final List<String> input) {
        if (chessGame != null) {
            throw new IllegalArgumentException("체스 게임은 이미 진행되고 있습니다.");
        }
        if (dao.hasHistory()) {
            chessGame = dao.loadGame();
            viewResolver.printBoard(chessGame.getBoard());
            return MOVE;
        }
        chessGame = ChessGameFactory.generate();
        viewResolver.printBoard(chessGame.getBoard());
        return MOVE;
    }

    private GameCommand move(final List<String> input) {
        if (chessGame == null) {
            throw new IllegalArgumentException("체스 게임은 아직 시작하지 않았습니다.");
        }
        chessGame.move(getPosition(input, SOURCE_INDEX), getPosition(input, TARGET_INDEX));
        if (chessGame.isKingDead()) {
            dao.delete();
            viewResolver.printWinner(chessGame.getWinner());
            return END;
        }
        viewResolver.printBoard(chessGame.getBoard());
        return MOVE;
    }

    private GameCommand status(final List<String> strings) {
        dao.delete();
        viewResolver.printTotalScore(chessGame.calculateScore());
        viewResolver.printEndMessage();
        return END;
    }

    private GameCommand end(final List<String> input) {
        viewResolver.printEndMessage();
        dao.save(chessGame);
        return END;
    }
}
