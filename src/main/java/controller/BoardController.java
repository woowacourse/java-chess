package controller;

import controller.command.Command;
import controller.command.CommandType;
import dao.BoardDao;
import dao.Movement;
import domain.Turn;
import domain.piece.Piece;
import domain.point.Point;
import dto.ChessGame;
import exception.CheckMateException;
import util.ScoreCalculator;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class BoardController {
    private final ChessGame chessGame;
    private final BoardDao boardDao;
    private final OutputView outputView;
    private final InputView inputView;

    public BoardController(ChessGame chessGame, BoardDao boardDao, OutputView outputView, InputView inputView) {
        this.chessGame = chessGame;
        this.boardDao = boardDao;
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public final Map<CommandType, Runnable> initializingActions = Map.of(
            CommandType.START, this::start,
            CommandType.END, this::end,
            CommandType.MOVE, () -> {throw new IllegalArgumentException("아직 게임이 시작하지 않았습니다!");},
            CommandType.STATUS, this::status
    );

    public final Map<CommandType, BiConsumer<Command, Turn>> commandActions = Map.of(
            CommandType.START, (command, turn) -> start(),
            CommandType.END, (command, turn) -> end(),
            CommandType.MOVE, this::move,
            CommandType.STATUS, (command, turn) -> status()
    );

    public void initializeBoard() {
        try {
            outputView.printAskingBootingCommandMessage();
            Command command = inputView.getGameCommand();
            initializingActions.get(command.getType()).run();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            initializeBoard();
        }
    }

    public void executeByCommand(Turn turn) {
        try {
            Command command = inputView.getGameCommand();
            commandActions.get(command.getType()).accept(command, turn);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            executeByCommand(turn);
        } catch (CheckMateException e) {
            checkmate(turn);
        }
    }

    private void start() {
        outputView.printStatus(chessGame.findCurrentStatus());
    }

    private void end() {
        outputView.printGameEndMessage();
    }

    private void move(Command command, Turn turn) {
        String[] split = command.getValue().split(" ");
        Movement movement = new Movement(Point.fromSymbol(split[1]), Point.fromSymbol(split[2]));
        chessGame.move(movement, turn);
        boardDao.updateMovement(chessGame.getId(), movement);
        start();
    }

    private void status() {
        List<List<Piece>> currentStatus = chessGame.findCurrentStatus();
        float blackScore = ScoreCalculator.calculate(currentStatus, Turn.BLACK);
        float whiteScore = ScoreCalculator.calculate(currentStatus, Turn.WHITE);
        outputView.printScoreStatus(blackScore, whiteScore);
    }

    private void checkmate(Turn turn) {
        outputView.printWinner(turn);
    }
}
