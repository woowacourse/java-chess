package chess.location;

public class LocationSubStringUtil {
    private static final String UNDER_BAR = "_";

    public static Location substring(String str) {
        String[] colAndRow = str.split(UNDER_BAR);
        char col = colAndRow[0].charAt(0);
        int row = colAndRow[1].charAt(0) - '0';
        return new Location(row, col);
    }
}
