package chess.dto;

public record SquareRequest(String file, String rank) {
    private static final String SPLIT_REGEX = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";

    public static SquareRequest from(final String square) {
        String[] split = square.split(SPLIT_REGEX);
        return new SquareRequest(split[0], split[1]);
    }
}
