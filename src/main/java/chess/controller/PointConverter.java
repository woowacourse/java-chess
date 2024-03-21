package chess.controller;

import java.awt.Point;

public class PointConverter {

    public static Point convert(final String position) {
        String[] fileAndRank = position.split("");
        int column = convertFile(fileAndRank[0]);
        int row = Integer.parseInt(fileAndRank[1]);

        return new Point(column, row);
    }

    private static int convertFile(final String rawFile) {
        return rawFile.charAt(0) - 'a' + 1;
    }
}
