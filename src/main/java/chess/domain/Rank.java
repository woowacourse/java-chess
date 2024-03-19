package chess.domain;

import java.util.regex.Pattern;

public record Rank(int rank) {

    static final String ERROR_NOT_NUMERIC = "랭크는 숫자여야 합니다.";
    static final String ERROR_NOT_EXIST_RANK = "존재하지 않는 랭크입니다.";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");
    private static final int MIN_RANK = 1;
    private static final int MAX_RANK = 8;
    
    public Rank {
        validateExist(rank);
    }

    public static Rank from(String rank) {
        validateNumeric(rank);
        return new Rank(Integer.parseInt(rank));
    }

    private static void validateNumeric(String rank) {
        if (!NUMBER_PATTERN.matcher(rank).matches()) {
            throw new IllegalArgumentException(ERROR_NOT_NUMERIC);
        }
    }

    private void validateExist(int rank) {
        if (rank < MIN_RANK || rank > MAX_RANK) {
            throw new IllegalArgumentException(ERROR_NOT_EXIST_RANK);
        }
    }
}
