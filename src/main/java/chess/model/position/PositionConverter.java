package chess.model.position;

public final class PositionConverter {

    private static final int POSITION_INDEX = 1;

    private PositionConverter() {
    }

    public static Position convert(final String inputPosition) {
        final String inputFile = inputPosition.substring(0, POSITION_INDEX);
        final String inputRank = inputPosition.substring(POSITION_INDEX);
        final File file = File.findFile(inputFile);
        final int rankValue = mapToRankValue(inputRank);
        final Rank rank = Rank.findRank(rankValue);

        return Position.of(file, rank);
    }

    private static int mapToRankValue(final String inputRank) {
        try {
            return Integer.parseInt(inputRank);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 행을 입력해주세요.");
        }
    }
}
