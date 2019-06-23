package chess;

import chess.model.Play;
import chess.model.board.Board;
import chess.model.board.Column;
import chess.model.board.Row;
import chess.model.board.Square;
import chess.view.ConsoleInput;
import chess.view.ConsoleOutput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ConsoleChessApplication {
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final String EMPTY = "";
    private static final String UNDER_BAR = "_";
    private static final String INVALID_MOVE = "잘못된 말 이동입니다.";
    private final Map<String, Consumer<List<String>>> commandMap = new HashMap<>();
    private Play play;
    private boolean isPlaying = true;

    private ConsoleChessApplication() {
        commandMap.put("start", this::start);
        commandMap.put("end", this::end);
        commandMap.put("move", this::move);
    }

    public static void main(final String[] args) {
        final ConsoleChessApplication app = new ConsoleChessApplication();
        ConsoleOutput.startMessage();
        while (app.isPlaying) {
            app.commandDispatch(ConsoleInput.commandList());
        }
    }

    private void commandDispatch(List<String> inputCommands) {
        final String command = inputCommands.remove(FIRST).toLowerCase();
        final Consumer<List<String>> function = commandMap.getOrDefault(command, this::retryInputCommand);
        function.accept(inputCommands);
    }

    private void retryInputCommand(final List<String> placeholder) {
        commandDispatch(ConsoleInput.retryCommandList());
    }

    private void start(final List<String> placeholder) {
        play = new Play(Board.makeInitialBoard());
        isPlaying = true;
        ConsoleOutput.board(play.getBoard());
        ConsoleOutput.side(play.getSide());
    }

    private void end(final List<String> placeholder) {
        isPlaying = false;
    }

    private void move(final List<String> arguments) {
        try {
            final Square target = parseSquare(arguments.get(FIRST));
            final Square destination = parseSquare(arguments.get(SECOND));
            play.movePieceAndTurnSide(target, destination);
            ConsoleOutput.board(play.getBoard());
            ConsoleOutput.side(play.getSide());
        } catch (Exception e) {
            System.err.println(INVALID_MOVE);
        }
    }

    private Square parseSquare(final String argument) {
        final String[] args = argument.strip().toUpperCase().split(EMPTY);
        final Column col = Column.valueOf(args[FIRST]);
        final Row row = Row.valueOf(UNDER_BAR + args[SECOND]);
        return new Square(col, row);
    }
}
