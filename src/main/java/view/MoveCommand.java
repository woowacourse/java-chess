package view;

public record MoveCommand(String file, int rank) {

    public static final String COMMAND_REGEX = "[a-h][1-8]";
    public static final int FILE_INDEX = 0;
    public static final int RANK_INDEX = 1;

    public static MoveCommand fromInput(final String input) {
        if (!input.trim().matches(COMMAND_REGEX)) {
            throw new IllegalArgumentException("올바르지 않은 경로 입력입니다.");
        }
        return new MoveCommand(toString(input.charAt(FILE_INDEX)), toInt(input.substring(RANK_INDEX)));
    }

    private static String toString(final char input) {
        return String.valueOf(input);
    }

    private static int toInt(final String input) {
        return Integer.parseInt(input);
    }
}
