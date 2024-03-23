package domain.direction;

public interface Direction {

    boolean isSameDirection(int rowDifference, int columnDifference);

    int calculateDistance(int rowDifference, int columnDifference);

    int getRowOffset();

    int getColumnOffset();

    default int normalize(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }
}
