package chess.domain.position;

public class Position {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int POSITION_LENGTH = 2;

    private final File file;
    private final Rank rank;

    public Position(String position) {
        validateLength(position);
        this.file = File.of(position.substring(FILE_INDEX, RANK_INDEX));
        this.rank = Rank.of(position.substring(RANK_INDEX));
    }

    private void validateLength(String position) {
        if (position.length() != POSITION_LENGTH) {
            throw new IllegalArgumentException("위치는 두자리여야 합니다.");
        }
    }
}