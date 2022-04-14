package chess.console.command;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

import chess.domain.game.ChessGame;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.web.dto.GameResult;

public enum Command {
    STATUS(Pattern.compile("^(status)$"), (chessGame, ignored) -> GameResult.ofScore(chessGame.status())),
    START(Pattern.compile("^(start)$"), (chessGame, ignored) -> GameResult.ofBoard(chessGame.start())),
    END(Pattern.compile("^(end)$"), (chessGame, ignored) -> GameResult.ofBoard(chessGame.end())),
    MOVE(Pattern.compile("^((move) ([a-h][1-8]) ([a-h][1-8]))$"),
            (chessGame, inputs) -> GameResult.ofBoard(chessGame.move(toPosition(inputs.get(1)), toPosition(inputs.get(2)))));

    private Pattern pattern;
    private BiFunction<ChessGame, List<String>, GameResult> operator;

    Command(Pattern pattern, BiFunction<ChessGame, List<String>, GameResult> operator) {
        this.pattern = pattern;
        this.operator = operator;
    }

    public static GameResult act(String input, ChessGame chessGame) {
        return Arrays.stream(Command.values())
                .filter(pattern -> pattern.pattern.matcher(input).find())
                .findFirst()
                .map(command -> command.operator)
                .map(operator -> operator.apply(chessGame, Arrays.asList(input.split(" "))))
                .orElseThrow(() -> new NoSuchElementException("명령어를 올바르게 입력해 주세요."));
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
