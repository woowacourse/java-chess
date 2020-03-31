package chess.location;

public class LocationSubStringUtil {

    private static final String SPACE = " ";

    public static Location substring(String str, int index) {
        String location = str.split(SPACE)[index];
        int row = location.charAt(1) - '0';
        char col = location.charAt(0);
        return new Location(row, col);
    }
}
