package chess.domain.square;

import chess.domain.square.dto.SquareCreateCommand;
import chess.domain.square.dto.SquareDifferent;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Square {

    private static final Pattern INTEGER_FORMAT_REGEX = Pattern.compile("^[1-9][0-9]*$");
    private static final String INVALID_RANK_ERROR = "랭크는 자연수로 입력해야 합니다.";
    private static final Map<String, Square> POOL = createSquarePool();

    private final File file;
    private final Rank rank;

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(File file, Rank rank) {
        return POOL.get(toKey(file, rank));
    }

    public static Square from(SquareCreateCommand command) {
        String fileCommand = String.valueOf(command.getFileCommand());
        String rankValue = String.valueOf(command.getRankValue());
        validateRank(rankValue);

        File file = File.findFileByCommand(fileCommand);
        Rank rank = Rank.findRankByValue(Integer.parseInt(rankValue));

        return POOL.get(toKey(file, rank));
    }

    private static void validateRank(String rankValue) {
        if (!INTEGER_FORMAT_REGEX.matcher(rankValue).matches()) {
            throw new IllegalArgumentException(INVALID_RANK_ERROR);
        }
    }

    private static Map<String, Square> createSquarePool() {
        return Arrays.stream(Rank.values())
                .flatMap(createSquare())
                .collect(Collectors.toMap(it -> toKey(it.file, it.rank), Function.identity()));
    }

    private static Function<Rank, Stream<Square>> createSquare() {
        return rank -> Arrays.stream(File.values())
                .map(file -> new Square(file, rank));
    }

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.name();
    }

    public Square moveVertical(int index) {
        return Square.of(file, rank.moveVertical(index));
    }

    public Square moveHorizontal(int index) {
        return Square.of(file.moveHorizontal(index), rank);
    }

    public Square moveDiagonal(int horizontalIndex, int verticalIndex) {
        return Square.of(file.moveHorizontal(horizontalIndex), rank.moveVertical(verticalIndex));
    }

    public SquareDifferent calculateDiff(Square another) {
        return new SquareDifferent(rank.calculateDiff(another.rank), file.calculateDiff(another.file));
    }

    public boolean isSameRank(Rank rank) {
        return this.rank.equals(rank);
    }
}
