package chess.domain;

import chess.domain.gamecommand.End;
import chess.domain.gamecommand.GameCommand;
import chess.domain.gamecommand.Move;
import chess.domain.gamecommand.Start;
import chess.domain.gamecommand.Status;
import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Pattern;

public enum Command {

    START("start", Start::new),
    MOVE("move [a-h][1-9] [a-h][1-9]", Move::new),
    END("end", End::new),
    STATUS("status", Status::new),
    ;

    private static final String WRONG_COMMAND_MESSAGE = "잘못된 명령어를 입력하였습니다.";

    private final String command;
    private final Function<ChessGame, GameCommand> gameCommandGenerator;

    Command(String command, Function<ChessGame, GameCommand> gameCommandGenerator) {
        this.command = command;
        this.gameCommandGenerator = gameCommandGenerator;
    }

    public static GameCommand findByInput(final String command, ChessGame chessGame) {
        return Arrays.stream(Command.values())
            .filter(it -> Pattern.matches(it.command, command))
            .map(it -> it.gameCommandGenerator.apply(chessGame))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_MESSAGE));
    }
}
