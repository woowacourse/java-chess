package chess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;

public class Command {
    private static final Map<Pattern, BiFunction<ChessGame, List<String>, Map<Position, Piece>>> commands = new HashMap<>();

    static {
        commands.put(Pattern.compile("^(start)$"), (chessGame, ignored) -> chessGame.start());
        commands.put(Pattern.compile("^(end)$"), (chessGame, ignored) -> chessGame.end());
        commands.put(Pattern.compile("^((move) ([a-h][1-8]) ([a-h][1-8]))$"),
            (chessGame, inputs) -> chessGame.move(getPosition(inputs.get(1)), getPosition(inputs.get(2))));
    }

    public static Map<Position, Piece> act(String input, ChessGame chessGame) {
        Pattern command = commands.keySet().stream()
            .filter(pattern -> pattern.matcher(input).find())
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("명령어를 올바르게 입력해 주세요."));

        return commands.get(command).apply(chessGame, Arrays.asList(input.split(" ")));
    }

    private static Position getPosition(String input) {
        return Position.of(getFile(input.substring(0, 1)), getRank(input.substring(1, 2)));
    }

    private static File getFile(String file) {
        return File.of(file);
    }

    private static Rank getRank(String rank) {
        return Rank.of(Integer.parseInt(rank));
    }
}
