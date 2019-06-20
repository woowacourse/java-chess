package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Spot {
    private static final int MIN_COLUMN = 0;
    private static final int MAX_COLUMN = 7;
    private static final int MIN_ROW = 0;
    private static final int MAX_ROW = 7;

    private final int x;
    private final int y;

    private Spot(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Spot valueOf(final int x, final int y) {
        validation(x, y);
        int index = x * 8 + y;
        return SpotCache.spotsCache.get(index);
    }

    private static void validation(int x, int y) {
        if (validationRow(x) || validationColumn(y)) {
            throw new IllegalArgumentException("범위 제대로 입력해 주세요");
        }
    }

    private static boolean validationRow(int x) {
        return x < MIN_COLUMN || x > MAX_COLUMN;
    }

    private static boolean validationColumn(int y) {
        return y < MIN_ROW || y > MAX_ROW;
    }

    public int getX(Spot spot) {
        return this.x - spot.x;
    }

    public int getY(Spot spot) {
        return this.y - spot.y;
    }

    //TODO 생각해보자...getter를 안쓰는 방법을..
    public Spot nextSpot(MovementUnit movementUnit) {
        int nextSpotX = this.x + movementUnit.getX();
        int nextSpotY = this.y + movementUnit.getY();

        return Spot.valueOf(nextSpotX, nextSpotY);
    }

    private static class SpotCache {
        private static List<Spot> spotsCache = new ArrayList<>();

        //TODO 이중 for 문 리팩토링하기
        static {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    spotsCache.add(new Spot(i, j));
                }
            }
        }

    }
}
