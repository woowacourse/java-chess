package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 체스의 File
 */
public enum X {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private int x;
    private String expression;

    X(int x, String expression) {
        this.x = x;
        this.expression = expression;
    }

    public static X of(final String expression) {
        return Arrays.stream(values())
            .filter(x -> x.expression.equals(expression))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("체스의 X 축에 존재하지 않는 표현입니다."));
    }

    public static X of(final int x) {
        return Arrays.stream(values())
            .filter(value -> value.x == x)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("체스의 X 축에 존재하지 않는 표현입니다."));
    }

    public boolean canIncrease(final int x) {
        return (A.x <= (this.x + x)) && ((this.x + x) <= H.x);
    }

    public boolean canIncrease(final X x) {
        return canIncrease(x.x);
    }

    public X increase(final int x) {
        if (!canIncrease(x)) {
            throw new IllegalArgumentException("현재 X 위치에서 " + x + "만큼 increase 할 수 없습니다.");
        }
        return X.of(this.x + x);
    }

    public boolean canDecrease(int x) {
        return canIncrease(-x);
    }

    public boolean canDecrease(final X x) {
        return canDecrease(x.x);
    }

    public X decrease(final X x) {
        return decrease(x.x);
    }

    public X decrease(final int x) {
        if (!canDecrease(x)) {
            throw new IllegalArgumentException("현재 X 위치에서 " + x + "만큼 decrease 할 수 없습니다.");
        }
        return X.of(this.x - x);
    }

    public int calculateDistance(final X x) {
        int distance = this.x - x.x;
        if (distance > 0) {
            return distance;
        }
        return -distance;
    }

    public boolean isLargerThan(final X x) {
        return canDecrease(x);
    }

    public static List<X> getPathFromTo(final X from, final X to) {
        if (from.x < to.x) {
            return Collections.unmodifiableList(
                getPathFromSmallerToLarger(from, to)
            );
        }
        if (to.x < from.x) {
            List<X> path = getPathFromSmallerToLarger(to, from);
            return Collections.unmodifiableList(reverseOrder(path));
        }
        return Collections.singletonList(from);    /* from == to*/
    }

    private static List<X> getPathFromSmallerToLarger(final X smaller, final X larger) {
        List<X> path = new ArrayList<>();

        X xOnPath = smaller;
        while (xOnPath.x < larger.x) {
            path.add(xOnPath);
            xOnPath = xOnPath.increase(1);
        }
        path.add(xOnPath);
        return Collections.unmodifiableList(path);
    }

    private static List<X> reverseOrder(final List<X> path) {
        List<X> reversedPath = new ArrayList<>();

        for (int i = path.size() - 1; i >= 0; i--) {
            reversedPath.add(path.get(i));
        }
        return Collections.unmodifiableList(reversedPath);
    }


    int getInt() {
        return this.x;
    }

    @Override
    public String toString() {
        return "" + expression;
    }
}
