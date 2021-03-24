package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.piece.Team;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {
    START("start", ChessGame::settingBoard),
    MOVE("move ([a-z][1-8]) ([a-z][1-8])", ChessGame::move),
    STATUS("status", Command::status),
    END("end", ChessGame::end);

    String command;
    BiConsumer<ChessGame, String> biConsumer;

    Command(String command, BiConsumer<ChessGame, String> biConsumer) {
        this.command = command;
        this.biConsumer = biConsumer;
    }

    public static void execute(ChessGame chessGame, String input) {
        findCommand(input).biConsumer.accept(chessGame, input);
    }

    private static Command findCommand(String input) {
        return Arrays.stream(Command.values())
                .filter(value -> input.matches(value.command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 명령어를 찾지 못했습니다."));
    }

    private static void status(ChessGame chessGame, String input) {
        OutputView.printStatus(chessGame.status(Team.BLACK), chessGame.status(Team.WHITE));
    }
}
