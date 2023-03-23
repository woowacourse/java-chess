package chess.controller;

import chess.application.ChessGameService;
import chess.controller.command.RunningCommand;
import chess.controller.command.RunningCommandType;
import chess.controller.command.StartCommand;
import chess.controller.command.StartCommandType;
import chess.domain.board.ChessBoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static chess.controller.command.RunningCommand.FROM_POSITION_INDEX;
import static chess.controller.command.RunningCommand.TO_POSITION_INDEX;

public class ChessGameController {

    interface StartAction {
        Long findGameId(final StartCommand command);
    }

    interface RunningAction {
        void execute(final Long chessGameId, final RunningCommand command);
    }

    private final Map<StartCommandType, StartAction> startActionMap = new EnumMap<>(StartCommandType.class);
    private final Map<RunningCommandType, RunningAction> runningActionMap = new EnumMap<>(RunningCommandType.class);

    private final ChessGameService chessGameService;
    private final ChessBoardFactory chessBoardFactory;

    public ChessGameController(final ChessGameService chessGameService,
                               final ChessBoardFactory chessBoardFactory) {
        this.chessGameService = chessGameService;
        this.chessBoardFactory = chessBoardFactory;

        startActionMap.put(StartCommandType.START, ignored -> createGame());
        startActionMap.put(StartCommandType.RESTART, this::restartGame);
        runningActionMap.put(RunningCommandType.MOVE, this::move);
        runningActionMap.put(RunningCommandType.STATUS, (chessGameId, ignored) -> this.status(chessGameId));
    }

    public void start() {
        try {
            OutputView.printStartMessage();
            final StartCommand startCommand = readStartCommand();
            final StartAction startAction = startActionMap.get(startCommand.type());
            final Long gameId = startAction.findGameId(startCommand);
            playGame(gameId);
        } catch (Exception e) {
            OutputView.error(e.getMessage());
            start();
        }
    }

    private StartCommand readStartCommand() {
        final List<String> commands = InputView.readCommand();
        return StartCommand.parse(commands);
    }

    private Long createGame() {
        return chessGameService.create(chessBoardFactory);
    }

    private Long restartGame(final StartCommand startCommand) {
        return startCommand.restartParameter();
    }

    private void playGame(final Long chessGameId) {
        ChessGame chessGame = chessGameService.findById(chessGameId);
        validateAlreadyEnd(chessGame);
        OutputView.startGame(chessGameId);
        runToEnd(chessGame);
    }

    private void validateAlreadyEnd(final ChessGame chessGame) {
        if (!chessGame.playable()) {
            throw new IllegalArgumentException("이미 끝난 게임입니다.");
        }
    }

    private void runToEnd(ChessGame chessGame) {
        do {
            OutputView.showBoard(chessGame.pieces(), chessGame.turnColor());
            final RunningCommand command = readRunningCommand();
            if (command.type() == RunningCommandType.END) {
                OutputView.saveAndEnd();
                return;
            }
            run(chessGame.id(), command);
            chessGame = chessGameService.findById(chessGame.id());
        } while (chessGame.playable());
        OutputView.printWinColor(chessGame.winColor());
    }

    private RunningCommand readRunningCommand() {
        try {
            final List<String> commands = InputView.readCommand();
            return RunningCommand.parse(commands);
        } catch (Exception e) {
            OutputView.error(e.getMessage());
            return readRunningCommand();
        }
    }

    private void run(final Long chessGameId, final RunningCommand command) {
        try {
            final RunningAction runningAction = runningActionMap.get(command.type());
            runningAction.execute(chessGameId, command);
        } catch (Exception e) {
            OutputView.error(e.getMessage());
        }
    }

    private void move(final Long chessGameId, final RunningCommand command) {
        final List<PiecePosition> piecePositions = command.moveParameters();
        final PiecePosition from = piecePositions.get(FROM_POSITION_INDEX);
        final PiecePosition to = piecePositions.get(TO_POSITION_INDEX);
        chessGameService.movePiece(chessGameId, from, to);
    }

    private void status(final Long chessGameId) {
        ChessGame chessGame = chessGameService.findById(chessGameId);
        final Map<Color, Double> colorDoubleMap = chessGame.calculateScore();
        OutputView.printScore(colorDoubleMap);
    }
}
