package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {

    START("start", (chessGame, ignored) -> chessGame.start()),
    END("end", (chessGame, ignored) -> chessGame.end()),
    MOVE("move", movePiece());

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final String name;
    private final BiConsumer<ChessGame, String[]> consumer;

    Command(final String name, final BiConsumer<ChessGame, String[]> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    private static BiConsumer<ChessGame, String[]> movePiece() {
        return (chessGame, splitCommand) -> {
            final String[] source = splitPosition(splitCommand, SOURCE_INDEX);
            final String[] target = splitPosition(splitCommand, TARGET_INDEX);

            final Position sourcePosition = Position.of(File.findBySymbol(source[FILE_INDEX]),
                    Rank.findByIndex(Integer.parseInt(source[RANK_INDEX])));
            final Position targetPosition = Position.of(File.findBySymbol(target[FILE_INDEX]),
                    Rank.findByIndex(Integer.parseInt(target[RANK_INDEX])));

            chessGame.movePiece(sourcePosition, targetPosition);
        };
    }

    private static String[] splitPosition(final String[] splitCommand, final int index) {
        return splitCommand[index].split("");
    }

    public static Command findByString(final String name) {
        return Arrays.stream(values())
                .filter(command -> command.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드입니다."));
    }

    public void execute(final ChessGame chessGame, final String[] splitCommand) {
        this.consumer.accept(chessGame, splitCommand);
    }
}
