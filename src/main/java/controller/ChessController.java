package controller;

import common.ExecuteContext;
import domain.Board;
import domain.ChessGame;
import domain.Location;
import domain.type.Color;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final ChessGame chessGame;
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Command, CommandAction> commandMapper = new EnumMap<>(Command.class);

    public ChessController(final ChessGame chessGame, final InputView inputView, final OutputView outputView) {
        this.chessGame = chessGame;
        this.inputView = inputView;
        this.outputView = outputView;
        putCommandActions();
    }

    private void putCommandActions() {
        commandMapper.put(Command.START, ignore -> start());
        commandMapper.put(Command.END, ignore -> end());
        commandMapper.put(Command.STATUS, ignore -> status());
        commandMapper.put(Command.ENTER, ignore -> enter());
        commandMapper.put(Command.MOVE, this::move);
    }

    public void play() {
        inputView.printStartInformation();
        ExecuteContext.repeatableExecute(this::printBoard, () -> command(inputView.getCommand()));
    }

    private Void printBoard() {
        final Board board = chessGame.getBoard();
        outputView.printBoard(board);
        return null;
    }

    private boolean command(final List<String> commands) {
        final Command command = Command.find(commands.get(0));
        final CommandAction commandAction = commandMapper.get(command);
        return commandAction.execute(commands);
    }

    private boolean start() {
        chessGame.initialize(inputView.getBoardId());
        return true;
    }

    private boolean end() {
        chessGame.save();
        return false;
    }

    private boolean enter() {
        chessGame.findPreviousGame(inputView.getBoardId());
        return true;
    }

    private boolean status() {
        calculateScore();
        return true;
    }

    private void calculateScore() {
        final double whiteScore = chessGame.calculateWhiteScore();
        final double blackScore = chessGame.calculateBlackScore();
        final Color color = chessGame.judgeResult();
        outputView.printScore(whiteScore, blackScore);
        outputView.printResult(color);
    }

    private boolean move(final List<String> commands) {
        final Location start = mapToLocation(commands.get(1));
        final Location end = mapToLocation(commands.get(2));
        final Color result = chessGame.move(start, end);
        if (result.equals(Color.NONE)) {
            return true;
        }
        outputView.printResult(result);
        return false;
    }

    private Location mapToLocation(final String location) {
        final String columnInput = location.substring(0, 1);
        final String rowInput = location.substring(1);
        final int col = ColumnConverter.findColumn(columnInput);
        final int row = Integer.parseInt(rowInput);
        return Location.of(col, row);
    }
}
