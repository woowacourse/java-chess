package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class PointFactory {

    private static Map<String, Point> bucket = new HashMap<>();
    private static Point invalidPoint = new Point(-1, -1);

    static {
        for (int i = 'a'; i <= 'h'; ++i) {
            for (int j = 1; j <= 8; ++j) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append((char) i).append(j);
                bucket.put(stringBuilder.toString(), new Point(i - 'a' + 1, j));
            }
        }
    }

    public static Point of(String scannedPoint) {
        return bucket.get(scannedPoint);
    }

    public static Point of(int x, int y) {
        return bucket.get(String.format("%c%d", x + 'a' - 1, y));
    }

    public static Point getInvalidPoint(){
        return invalidPoint;
    }
}
