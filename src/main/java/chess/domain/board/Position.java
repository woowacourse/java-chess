package chess.domain.board;


import java.util.List;
import java.util.Objects;

public class Position {

    private static final int FROM_FOR_ROW = 0;
    private static final int TO_FOR_ROW = 1;
    private static final int START_INDEX_FOR_COLUMN = 1;

    private final Rank rank;
    private final File file;

    private Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(String input) {
        return new Position(
                Rank.of(input.substring(START_INDEX_FOR_COLUMN)),
                File.of(input.substring(FROM_FOR_ROW, TO_FOR_ROW)));
    }

    public static Position of(Rank rank, File file) {
        return new Position(rank, file);
    }

    public List<Integer> calculateDistance(Position another) {
        int columnDistance = this.file.calculate(another.file);
        int rowDistance = this.rank.calculate(another.rank);

        return List.of(columnDistance, rowDistance);
    }

    public List<Position> getPositionBetween(Position target) {
        // Position이 다른 Position을 받아서, 중간 좌표를 List로 묶기 좋음
        // 이때 대각선, 세로, 가로방식은 distance 구해서 확인
        // List<Position>을 board가 모두 찾아서 있는지 여부 확인 좋음

        List<File> columnsBetween = File.getBetween(this.file, target.file);

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
