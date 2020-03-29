package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 체스의 Rank 에 해당
 */
public enum Y {
    /* 순서를 오름차순으로 바꾸면
     * 콘솔에 출력되는 체스판의 아래-위가 역전됨 */
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private int y;

    Y(int y) {
        this.y = y;
    }

    public static Y of(int y) {
        return Arrays.stream(values())
            .filter(x -> x.y == y)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("체스의 Y 축에 존재하지 않는 Rank입니다."));
    }

    public boolean canIncrease(Y y) {
        return canIncrease(y.y);
    }

    public boolean canIncrease(int y) {
        return (ONE.y <= (this.y + y)) && ((this.y + y) <= EIGHT.y);
    }

    public Y increase(int y) {
        if (!canIncrease(y)) {
            throw new IllegalArgumentException("현재 Y 위치에서 " + y + "만큼 increase 할 수 없습니다.");
        }
        return Y.of(this.y + y);
    }

    public boolean canDecrease(Y y) {
        return canDecrease(y.y);
    }

    public boolean canDecrease(int y) {
        return canIncrease(-y);
    }

    public Y decrease(Y y) {
        return decrease(y.y);
    }

    public Y decrease(int y) {
        if (!canDecrease(y)) {
            throw new IllegalArgumentException("현재 Y 위치에서 " + y + "만큼 decrease 할 수 없습니다.");
        }
        return Y.of(this.y - y);
    }

    public int calculateDistance(Y y) {
        int distance = this.y - y.y;
        if (distance > 0) {
            return distance;
        }
        return -distance;
    }

    public boolean isLargerThan(Y y) {
        return canDecrease(y);
    }

    public static List<Y> getPathFromTo(final Y from, final Y to) {
        List<Y> path;

        if (from.y < to.y) {
            path = getPathFromSmallerToLarger(from, to);
            return Collections.unmodifiableList(path);
        }
        if (to.y < from.y) {
            path = getPathFromSmallerToLarger(to, from);
            return Collections.unmodifiableList(reverseOrder(path));
        }
        return Collections.singletonList(from);    /* from == to*/
    }

    private static List<Y> getPathFromSmallerToLarger(final Y smaller, final Y larger) {
        List<Y> path = new ArrayList<>();

        if (smaller.y >= larger.y) {
            throw new IllegalArgumentException("smaller Y 가 lager Y 보다 작아야합니다.");
        }

        Y yOnPath = smaller;
        while (yOnPath.y < larger.y) {
            path.add(yOnPath);
            yOnPath = yOnPath.increase(1);
        }
        path.add(yOnPath);
        return Collections.unmodifiableList(path);
    }

    private static List<Y> reverseOrder(final List<Y> path) {
        List<Y> reversedPath = new ArrayList<>();

        for (int i = path.size() - 1; i >= 0; i--) {
            reversedPath.add(path.get(i));
        }
        return Collections.unmodifiableList(reversedPath);
    }

    int getInt() {
        return y;
    }

    @Override
    public String toString() {
        return "" + y;
    }
}
