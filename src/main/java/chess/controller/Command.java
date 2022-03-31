package chess.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

import chess.domain.game.ChessGame;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.dto.GameResult;

public class Command {
    private static final Map<Pattern, BiFunction<ChessGame, List<String>, GameResult>> commands = new HashMap<>();

    static {
        commands.put(Pattern.compile("^(status)$"), (chessGame, ignored) -> GameResult.ofScore(chessGame.status()));

        commands.put(Pattern.compile("^(start)$"), (chessGame, ignored) -> GameResult.ofBoard(chessGame.start()));
        commands.put(Pattern.compile("^(end)$"), (chessGame, ignored) -> GameResult.ofBoard(chessGame.end()));
        commands.put(Pattern.compile("^((move) ([a-h][1-8]) ([a-h][1-8]))$"),
            (chessGame, inputs) -> GameResult.ofBoard(chessGame.move(toPosition(inputs.get(1)), toPosition(inputs.get(2)))));
    }

    public static GameResult act(String input, ChessGame chessGame) {
        Pattern command = commands.keySet().stream()
            .filter(pattern -> pattern.matcher(input).find())
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("명령어를 올바르게 입력해 주세요."));

        return commands.get(command).apply(chessGame, Arrays.asList(input.split(" ")));
    }

    private static Position toPosition(String input) {
        return Position.of(toFile(input.substring(0, 1)), toRank(input.substring(1, 2)));
    }

    private static File toFile(String file) {
        return File.of(file);
    }

    private static Rank toRank(String rank) {
        return Rank.of(Integer.parseInt(rank));
    }
}
