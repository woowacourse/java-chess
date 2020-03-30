package chess.view;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chess.domain.ChessContext;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.judge.Judge;
import chess.exceptions.InvalidInputException;

public enum Command {
    START("start", Command::initialize),
    END("end", Command::quit),
    STATUS("status", Command::showStatus),
    MOVE("move ([a-h][1-8]) ([a-h][1-8])", Command::move);

    public static final int START_GROUP = 1;
    public static final int END_GROUP = 2;

    private final String value;
    private BiConsumer<String, ChessContext> consumer;

    Command(final String value, BiConsumer<String, ChessContext> consumer) {
        this.value = value;
        this.consumer = consumer;
    }

    public static Command of(String value) {
        return Arrays.stream(Command.values())
            .filter(command -> command.is(value))
            .findAny()
            .orElseThrow(InvalidInputException::new);
    }

    private static void initialize(final String input, final ChessContext context) {
        Board board = context.getBoard();
        OutputView.showBoard(board);
    }

    private static void quit(final String input, final ChessContext context) {
        OutputView.quit();
        System.exit(0);
    }

    private static void showStatus(final String input, final ChessContext context) {
        Judge judge = context.getJudge();
        OutputView.showStatus(judge);
    }

    private static void move(final String input, final ChessContext context) {
        Board board = context.getBoard();
        if (Objects.isNull(input) || input.trim().isEmpty()) {
            throw new InvalidInputException();
        }
        tryToMove(board, positionOf(input, START_GROUP), positionOf(input, END_GROUP));
        OutputView.showBoard(board);
    }

    private static void tryToMove(final Board board, final Position start, final Position end) {
        try {
            board.move(start, end);
        } catch (InvalidInputException e) {
            OutputView.showError(e);
        }
    }

    private static Position positionOf(String input, int groupNumber) {
        Pattern pattern = Pattern.compile(MOVE.value);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Position.of(matcher.group(groupNumber));
        }
        throw new InvalidInputException();
    }

    public boolean is(String value) {
        return value.matches(this.value);
    }

    public void execute(String input, ChessContext context) {
        consumer.accept(input, context);
    }
}
