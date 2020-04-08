package chess.location;

public class LocationSubStringUtil {
    private static final String UNDER_BAR = "_";

    public static Location substring(String str) {
        String[] colAndRow = str.split(UNDER_BAR);
        char col = parseColAndRow(0, colAndRow);
        int row = parseColAndRow(1, colAndRow) - '0';
        return new Location(row, col);
    }

    static char parseColAndRow(int i, String[] colAndRow) {
        return colAndRow[i].charAt(0);
    }
}
