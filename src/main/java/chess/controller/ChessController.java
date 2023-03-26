package chess.controller;

import chess.dao.ChessGameDao;
import chess.domain.Team;
import chess.game.RandomTurnStrategy;
import chess.game.action.Action;
import chess.game.GameCommand;
import chess.domain.Position;
import chess.dto.PositionRequest;
import chess.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ChessController {
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";
    private static final String SAVE_COMMAND = "save";
    private static final String LOAD_COMMAND = "load";
    private static final String LEAVE_COMMAND = "leave";
    private static final String END_COMMAND = "end";
    private final Map<String, Action> actionMap = new HashMap<>();
    private final ChessGame chessGame;
    private final ChessGameDao chessGameDao;

    public ChessController(ChessGame chessGame, ChessGameDao chessGameDao) {
        this.chessGame = chessGame;
        this.chessGameDao = chessGameDao;
        actionMap.put(START_COMMAND, new Action(ignore -> startGame()));
        actionMap.put(MOVE_COMMAND, new Action(this::movePiece));
        actionMap.put(STATUS_COMMAND, new Action(ignore -> getGameStatus()));
        actionMap.put(SAVE_COMMAND, new Action(ignore -> saveGame()));
        actionMap.put(LOAD_COMMAND, new Action(ignore -> loadGame()));
        actionMap.put(LEAVE_COMMAND, new Action(ignore -> leaveGame()));
        actionMap.put(END_COMMAND, new Action(ignore -> endGame()));
    }

    private void startGame() {
        chessGame.start(new RandomTurnStrategy());
        OutputView.printTurn(chessGame.getTurn());
        OutputView.printBoard(chessGame.getBoard());
    }

    private void movePiece(GameCommand gameCommand) {
        PositionRequest source = PositionMapper.map(gameCommand.getParameter(0));
        PositionRequest target = PositionMapper.map(gameCommand.getParameter(1));
        chessGame.movePiece(Position.of(source.getX(), source.getY()), Position.of(target.getX(), target.getY()));
        chessGame.checkCheckmate();
        if (!chessGame.isEnd()) {
            chessGame.changeTurn();
            printCheckWarning();
            OutputView.printTurn(chessGame.getTurn());
        }
        OutputView.printBoard(chessGame.getBoard());
    }

    private void printCheckWarning() {
        if (chessGame.isChecked()) {
            OutputView.printCheckWarning(chessGame.getTurn());
        }
    }

    private void getGameStatus() {
        double blackTeamScore = chessGame.getTeamScore(Team.BLACK);
        double whiteTeamScore = chessGame.getTeamScore(Team.WHITE);
        OutputView.printGameStatus(blackTeamScore, whiteTeamScore);
    }

    private void saveGame() {
        chessGame.save(chessGameDao::saveChessGame);
        OutputView.printSaveMessage();
    }

    private void loadGame() {
        chessGame.load(chessGameDao::findBoard, chessGameDao::findGameState);
        OutputView.printLoadMessage();
        OutputView.printTurn(chessGame.getTurn());
        OutputView.printBoard(chessGame.getBoard());
    }

    private void leaveGame() {
        chessGame.leave();
        OutputView.printStartMessage();
    }

    private void endGame() {
        chessGame.end();
    }

    public void run() {
        OutputView.printStartMessage();
        while (!chessGame.isEnd()) {
            printError(this::gameLoop);
        }
        OutputView.printWinner(chessGame.getWinner());
    }

    private void printError(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void gameLoop() {
        GameCommand command = printErrorAndRetry(InputView::readCommand);
        actionMap.getOrDefault(command.getCommand(), Action.INVALID_ACTION)
                .execute(command);
    }

    private GameCommand printErrorAndRetry(Supplier<GameCommand> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
            return printErrorAndRetry(supplier);
        }
    }
}
