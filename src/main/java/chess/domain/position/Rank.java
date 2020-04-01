package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {
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

    Rank(int y) {
        this.y = y;
    }

    public static Rank of(int y) {
        return Arrays.stream(values())
            .filter(x -> x.y == y)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("체스의 Y 축에 존재하지 않는 Rank입니다."));
    }

    public boolean canIncrease(Rank rank) {
        return canIncrease(rank.y);
    }

    public boolean canIncrease(int y) {
        return (ONE.y <= (this.y + y)) && ((this.y + y) <= EIGHT.y);
    }

    public Rank increase(int y) {
        if (!canIncrease(y)) {
            throw new IllegalArgumentException("현재 Y 위치에서 " + y + "만큼 increase 할 수 없습니다.");
        }
        return Rank.of(this.y + y);
    }

    public boolean canDecrease(Rank rank) {
        return canDecrease(rank.y);
    }

    public boolean canDecrease(int y) {
        return canIncrease(-y);
    }

    public Rank decrease(Rank rank) {
        return decrease(rank.y);
    }

    public Rank decrease(int y) {
        if (!canDecrease(y)) {
            throw new IllegalArgumentException("현재 Y 위치에서 " + y + "만큼 decrease 할 수 없습니다.");
        }
        return Rank.of(this.y - y);
    }

    public int calculateDistance(Rank rank) {
        int distance = this.y - rank.y;
        if (distance > 0) {
            return distance;
        }
        return -distance;
    }

    public boolean isLargerThan(Rank rank) {
        return canDecrease(rank);
    }

    public static List<Rank> getPathFromTo(final Rank from, final Rank to) {
        List<Rank> path;

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

    private static List<Rank> getPathFromSmallerToLarger(final Rank smaller, final Rank larger) {
        List<Rank> path = new ArrayList<>();

        if (smaller.y >= larger.y) {
            throw new IllegalArgumentException("smaller Y 가 lager Y 보다 작아야합니다.");
        }

        Rank rankOnPath = smaller;
        while (rankOnPath.y < larger.y) {
            path.add(rankOnPath);
            rankOnPath = rankOnPath.increase(1);
        }
        path.add(rankOnPath);
        return Collections.unmodifiableList(path);
    }

    private static List<Rank> reverseOrder(final List<Rank> path) {
        List<Rank> reversedPath = new ArrayList<>();

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
