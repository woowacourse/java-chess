package chess;

import chess.domain.result.Result;
import chess.domain.result.Score;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum Command {

    START("start", (chessGame, commands) -> start(chessGame)),
    END("end", ((chessGame, commands) -> end(chessGame))),
    MOVE("move", Command::move),
    STATUS("status", (chessGame, commands) -> status(chessGame)),
    ;

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final String command;
    private final BiConsumer<ChessGame, List<String>> execute;

    Command(final String command, final BiConsumer<ChessGame, List<String>> execute) {
        this.command = command;
        this.execute = execute;
    }

    public static Command of(final String command) {
        return Arrays.stream(Command.values())
                .filter(value -> value.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다."));
    }

    public void execute(final ChessGame chessGame, final List<String> commands) {
        execute.accept(chessGame, commands);
    }

    private static void start(final ChessGame chessGame) {
        OutputView.printBoard(chessGame.getBoard());
        chessGame.start();
    }

    private static void end(final ChessGame chessGame) {
        chessGame.end();
    }

    private static void move(final ChessGame chessGame, final List<String> commands) {
        final Position from = Position.create(toPosition(commands, SOURCE_INDEX));
        final Position to = Position.create(toPosition(commands, TARGET_INDEX));
        chessGame.move(from, to);
        OutputView.printBoard(chessGame.getBoard());
    }

    private static String toPosition(final List<String> commands, final int index) {
        try {
            return commands.get(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("[ERROR] source 위치와 target 위치를 입력해주세요.");
        }
    }

    private static void status(final ChessGame chessGame) {
        chessGame.status();

        final Score myScore = chessGame.calculateMyScore();
        final Score opponentScore = chessGame.calculateOpponentScore();

        OutputView.printNewLine();
        OutputView.printScore(chessGame.getTurnName(), myScore.getValue());
        OutputView.printScore(chessGame.getOppositeTurnName(), opponentScore.getValue());
        OutputView.printResult(chessGame.getTurnName(), myScore.decideResult(opponentScore).getName());
    }
}
