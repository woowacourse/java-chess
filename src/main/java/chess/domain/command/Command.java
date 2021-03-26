package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.piece.Team;
import chess.exception.NoSuchCommandException;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;

import static chess.domain.ChessGame.*;

public enum Command {
    START("start", (chessGame, input) -> chessGame.settingBoard()),
    MOVE("move", ChessGame::move),
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
                .filter(value -> Objects.equals(input.split(DELIMITER)[0], value.command))
                .findAny()
                .orElseThrow(NoSuchCommandException::new);
    }

    private static void status(ChessGame chessGame, String input) {
        OutputView.printStatus(chessGame.status(Team.BLACK), chessGame.status(Team.WHITE));
    }
}
