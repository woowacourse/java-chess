package chess.controller;

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
    private static final String END_COMMAND = "end";
    private final Map<String, Action> actionMap = new HashMap<>();
    private final ChessGame chessGame;

    public ChessController(ChessGame chessGame) {
        this.chessGame = chessGame;
        actionMap.put(START_COMMAND, new Action(ignore -> startGame()));
        actionMap.put(MOVE_COMMAND, new Action(this::movePiece));
        actionMap.put(STATUS_COMMAND, new Action(ignore -> getGameStatus()));
        actionMap.put(END_COMMAND, new Action(ignore -> endGame()));
    }

    private void startGame() {
        chessGame.start(new RandomTurnStrategy());
        OutputView.printBoard(chessGame.getBoard());
        OutputView.printTurn(chessGame.getTurn());
    }

    private void movePiece(GameCommand gameCommand) {
        PositionRequest source = PositionMapper.map(gameCommand.getParameter(0));
        PositionRequest target = PositionMapper.map(gameCommand.getParameter(1));
        chessGame.movePiece(Position.of(source.getX(), source.getY()), Position.of(target.getX(), target.getY()));
        OutputView.printBoard(chessGame.getBoard());
        Team turn = chessGame.getTurn();
        OutputView.printTurn(turn);
        if (chessGame.isChecked(turn)) {
            OutputView.printChecked(turn);
        }
    }

    private void getGameStatus() {
        double blackTeamScore = chessGame.getTeamScore(Team.BLACK);
        double whiteTeamScore = chessGame.getTeamScore(Team.WHITE);
        OutputView.printGameStatus(blackTeamScore, whiteTeamScore);
    }

    private void endGame() {
        chessGame.end();
    }

    public void run() {
        OutputView.printStartMessage();
        while (!chessGame.isEnd()) {
            printError(this::gameLoop);
        }
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
