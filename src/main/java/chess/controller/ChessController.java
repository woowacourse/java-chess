package chess.controller;

import chess.dao.ChessGameDao;
import chess.domain.Team;
import chess.game.Command;
import chess.game.RandomTurnStrategy;
import chess.game.action.Action;
import chess.game.GameCommand;
import chess.domain.Position;
import chess.dto.PositionRequest;
import chess.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionMapper;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class ChessController {
    private final Map<Command, Action> actionMap = new EnumMap<>(Command.class);
    private final ChessGame chessGame;
    private final ChessGameDao chessGameDao;

    public ChessController(ChessGame chessGame, ChessGameDao chessGameDao) {
        this.chessGame = chessGame;
        this.chessGameDao = chessGameDao;
        initializeActionMap();
    }

    private void initializeActionMap() {
        actionMap.put(Command.START, new Action(ignore -> startGame()));
        actionMap.put(Command.MOVE, new Action(this::movePiece));
        actionMap.put(Command.STATUS, new Action(ignore -> getGameStatus()));
        actionMap.put(Command.SAVE, new Action(ignore -> saveGame()));
        actionMap.put(Command.LOAD, new Action(ignore -> loadGame()));
        actionMap.put(Command.LEAVE, new Action(ignore -> leaveGame()));
        actionMap.put(Command.END, new Action(ignore -> endGame()));
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
        changeTurn();
        OutputView.printBoard(chessGame.getBoard());
    }

    private void changeTurn() {
        if (!chessGame.isEnd()) {
            chessGame.changeTurn();
            printCheckWarning();
            OutputView.printTurn(chessGame.getTurn());
        }
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
