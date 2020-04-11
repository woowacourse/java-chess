package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum File {
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

    File(int x, String expression) {
        this.x = x;
        this.expression = expression;
    }

    public static File of(final String expression) {
        return Arrays.stream(values())
            .filter(x -> x.expression.equals(expression))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("체스의 X 축에 존재하지 않는 표현입니다."));
    }

    public static File of(final int x) {
        return Arrays.stream(values())
            .filter(value -> value.x == x)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("체스의 X 축에 존재하지 않는 표현입니다."));
    }

    public boolean canIncrease(final int x) {
        return (A.x <= (this.x + x)) && ((this.x + x) <= H.x);
    }

    public boolean canIncrease(final File file) {
        return canIncrease(file.x);
    }

    public File increase(final int x) {
        if (!canIncrease(x)) {
            throw new IllegalArgumentException("현재 X 위치에서 " + x + "만큼 increase 할 수 없습니다.");
        }
        return File.of(this.x + x);
    }

    public boolean canDecrease(int x) {
        return canIncrease(-x);
    }

    public boolean canDecrease(final File file) {
        return canDecrease(file.x);
    }

    public File decrease(final int x) {
        if (!canDecrease(x)) {
            throw new IllegalArgumentException("현재 X 위치에서 " + x + "만큼 decrease 할 수 없습니다.");
        }
        return File.of(this.x - x);
    }

    public int calculateDistance(final File file) {
        int distance = this.x - file.x;
        if (distance > 0) {
            return distance;
        }
        return -distance;
    }

    public boolean isLargerThan(final File file) {
        return canDecrease(file);
    }

    public static List<File> getPathFromTo(final File from, final File to) {
        if (from.x < to.x) {
            return Collections.unmodifiableList(
                getPathFromSmallerToLarger(from, to)
            );
        }
        if (to.x < from.x) {
            List<File> path = getPathFromSmallerToLarger(to, from);
            return Collections.unmodifiableList(reverseOrder(path));
        }
        return Collections.singletonList(from);    /* from == to*/
    }

    private static List<File> getPathFromSmallerToLarger(final File smaller, final File larger) {
        List<File> path = new ArrayList<>();

        File fileOnPath = smaller;
        while (fileOnPath.x < larger.x) {
            path.add(fileOnPath);
            fileOnPath = fileOnPath.increase(1);
        }
        path.add(fileOnPath);
        return Collections.unmodifiableList(path);
    }

    private static List<File> reverseOrder(final List<File> path) {
        List<File> reversedPath = new ArrayList<>();

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
