package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.piece.Team;
import chess.exception.NoSuchCommandException;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {
    START("start", (chessGame, input) -> chessGame.settingBoard()),
    MOVE("move ([a-z][1-8]) ([a-z][1-8])", ChessGame::move),
    STATUS("status", Command::status),
    END("end", (chessGame, input) -> chessGame.end());

    String command;
    BiConsumer<ChessGame, String> biConsumer;

    Command(String command, BiConsumer<ChessGame, String> biConsumer) {
        this.command = command;
        this.biConsumer = biConsumer;
    }

    public void execute(ChessGame chessGame, String input) {
        this.biConsumer.accept(chessGame, input);
    }

    public static Command findCommand(String input) {
        return Arrays.stream(Command.values())
                .filter(value -> input.matches(value.command))
                .findAny()
                .orElseThrow(NoSuchCommandException::new);
    }

    private static void status(ChessGame chessGame, String input) {
        OutputView.printStatus(chessGame.status(Team.BLACK), chessGame.status(Team.WHITE));
    }
}
