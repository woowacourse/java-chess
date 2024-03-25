package chess.view;

import java.awt.Point;

public class PointConverter {

    private static final int ASCII_DIFFERENCE_FROM_A = 'a' - 1;
    private static final int FIRST_CHARACTER_INDEX = 0;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public static Point convert(final String position) {
        String[] fileAndRank = position.split("");
        int file = convertFile(fileAndRank[FILE_INDEX]);
        int rank = Integer.parseInt(fileAndRank[RANK_INDEX]);

        return new Point(file, rank);
    }

    private static int convertFile(final String rawFile) {
        return rawFile.charAt(FIRST_CHARACTER_INDEX) - ASCII_DIFFERENCE_FROM_A;
    }
}
