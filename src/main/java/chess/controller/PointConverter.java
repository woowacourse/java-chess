package chess.controller;

import java.awt.Point;

public class PointConverter {

    public static Point convert(final String position) {
        String[] fileAndRank = position.split("");
        int file = convertFile(fileAndRank[0]);
        int rank = Integer.parseInt(fileAndRank[1]);

        return new Point(file, rank);
    }

    private static int convertFile(final String rawFile) {
        return rawFile.charAt(0) - 'a' + 1;
    }
}
