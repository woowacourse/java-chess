package chess;

import chess.model.Play;
import chess.model.Side;
import chess.model.board.Board;
import chess.model.board.Column;
import chess.model.board.Row;
import chess.model.board.Square;
import chess.view.ConsoleInput;
import chess.view.ConsoleOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ConsoleChessApplication {
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final String EMPTY = "";
    private static final String UNDER_BAR = "_";
    private static final String INVALID_MOVE = "잘못된 말 이동입니다.";
    private final Map<String, Consumer<InputCommand>> commandMap = new HashMap<>();
    private Play play;
    private boolean isPlaying = true;

    private ConsoleChessApplication() {
        commandMap.put("start", this::start);
        commandMap.put("end", this::end);
        commandMap.put("move", this::move);
        commandMap.put("status", this::status);
    }

    public static void main(final String[] args) {
        final ConsoleChessApplication app = new ConsoleChessApplication();
        ConsoleOutput.startMessage();
        while (app.isPlaying) {
            app.commandDispatch(ConsoleInput.commandList());
        }
    }

    private void commandDispatch(InputCommand inputCommand) {
        final String command = inputCommand.getCommand().toLowerCase();
        final Consumer<InputCommand> function = commandMap.getOrDefault(command, this::retryInputCommand);
        function.accept(inputCommand);
    }

    private void retryInputCommand(final InputCommand placeholder) {
        commandDispatch(ConsoleInput.retryCommandList());
    }

    private void start(final InputCommand placeholder) {
        play = new Play(Board.makeInitialBoard());
        isPlaying = true;
        ConsoleOutput.board(play.getBoard());
        ConsoleOutput.side(play.getSide());
    }

    private void end(final InputCommand placeholder) {
        status(placeholder);
        isPlaying = false;
    }

    private void move(final InputCommand argument) {
        try {
            final String first = argument.getArguments().get(FIRST);
            final String second = argument.getArguments().get(SECOND);
            final Square target = parseSquare(first);
            final Square destination = parseSquare(second);
            play.movePieceAndTurnSide(target, destination);
            ConsoleOutput.board(play.getBoard());
            kingCheck(argument);
        } catch (Exception e) {
            System.err.println(INVALID_MOVE);
        }
    }

    private Square parseSquare(final String argument) {
        final String[] args = argument.trim().toUpperCase().split(EMPTY); // 11 미만 버전을 위한 변경
        final Column col = Column.valueOf(args[FIRST]);
        final Row row = Row.valueOf(UNDER_BAR + args[SECOND]);
        return new Square(col, row);
    }

    private void status(final InputCommand placeholder) {
        final double whiteScore = play.calcScore(Side.WHITE);
        final double blackScore = play.calcScore(Side.BLACK);
        ConsoleOutput.status(Side.WHITE, whiteScore);
        ConsoleOutput.status(Side.BLACK, blackScore);
    }

    private void kingCheck(final InputCommand placeholder) {
        if (play.isKingDead(Side.WHITE) || play.isKingDead(Side.BLACK)) {
            ConsoleOutput.endMessage();
            end(placeholder);
            return;
        }
        ConsoleOutput.side(play.getSide());
    }
}
