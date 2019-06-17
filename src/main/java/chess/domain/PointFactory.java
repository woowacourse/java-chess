package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class PointFactory {

    private static Map<String,Point> bucket=new HashMap<>();

    static {
        bucket.put("a1", new Point(1,1));
        bucket.put("a2", new Point(1,2));
        bucket.put("a3", new Point(1,3));
        bucket.put("a4", new Point(1,4));
        bucket.put("a5", new Point(1,5));
        bucket.put("a6", new Point(1,6));
        bucket.put("a7", new Point(1,7));
        bucket.put("a8", new Point(1,8));

        bucket.put("b1", new Point(2,1));
        bucket.put("b2", new Point(2,2));
        bucket.put("b3", new Point(2,3));
        bucket.put("b4", new Point(2,4));
        bucket.put("b5", new Point(2,5));
        bucket.put("b6", new Point(2,6));
        bucket.put("b7", new Point(2,7));
        bucket.put("b8", new Point(2,8));

        bucket.put("c1", new Point(3,1));
        bucket.put("c2", new Point(3,2));
        bucket.put("c3", new Point(3,3));
        bucket.put("c4", new Point(3,4));
        bucket.put("c5", new Point(3,5));
        bucket.put("c6", new Point(3,6));
        bucket.put("c7", new Point(3,7));
        bucket.put("c8", new Point(3,8));

        bucket.put("d1", new Point(4,1));
        bucket.put("d2", new Point(4,2));
        bucket.put("d3", new Point(4,3));
        bucket.put("d4", new Point(4,4));
        bucket.put("d5", new Point(4,5));
        bucket.put("d6", new Point(4,6));
        bucket.put("d7", new Point(4,7));
        bucket.put("d8", new Point(4,8));

        bucket.put("e1", new Point(5,1));
        bucket.put("e2", new Point(5,2));
        bucket.put("e3", new Point(5,3));
        bucket.put("e4", new Point(5,4));
        bucket.put("e5", new Point(5,5));
        bucket.put("e6", new Point(5,6));
        bucket.put("e7", new Point(5,7));
        bucket.put("e8", new Point(5,8));

        bucket.put("f1", new Point(6,1));
        bucket.put("f2", new Point(6,2));
        bucket.put("f3", new Point(6,3));
        bucket.put("f4", new Point(6,4));
        bucket.put("f5", new Point(6,5));
        bucket.put("f6", new Point(6,6));
        bucket.put("f7", new Point(6,7));
        bucket.put("f8", new Point(6,8));

        bucket.put("g1", new Point(7,1));
        bucket.put("g2", new Point(7,2));
        bucket.put("g3", new Point(7,3));
        bucket.put("g4", new Point(7,4));
        bucket.put("g5", new Point(7,5));
        bucket.put("g6", new Point(7,6));
        bucket.put("g7", new Point(7,7));
        bucket.put("g8", new Point(7,8));

        bucket.put("h1", new Point(8,1));
        bucket.put("h2", new Point(8,2));
        bucket.put("h3", new Point(8,3));
        bucket.put("h4", new Point(8,4));
        bucket.put("h5", new Point(8,5));
        bucket.put("h6", new Point(8,6));
        bucket.put("h7", new Point(8,7));
        bucket.put("h8", new Point(8,8));
    }

    public static Point of(String scannedPoint) {
        return bucket.get(scannedPoint);
    }
}
