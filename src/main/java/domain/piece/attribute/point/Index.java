package domain.piece.attribute.point;

import java.util.ArrayList;
import java.util.List;

public record Index(int vertical, int horizontal) {
    public boolean isInBoundary() {
        return Rank.isInBoundary(vertical) && File.isInBoundary(horizontal);
    }

    public int prevVertical() {
        return this.vertical - 1;
    }

    public int nextVertical() {
        return this.vertical + 1;
    }

    public int prevHorizontal() {
        return this.horizontal() - 1;
    }

    public int nextHorizontal() {
        return this.horizontal() + 1;
    }

    public Index move(Direction direction) {
        return direction.move(this);
    }

    public Index move(Direction direction, int count) {
        Index index = this;
        for (int i = 0; i < count; i++) {
            index = index.move(direction);
        }
        return index;
    }

    public List<Index> findMovePath(Direction direction, Index other) {
        List<Index> list = new ArrayList<>();
        Index current = direction.move(this);
        while (!current.equals(other)) {
            list.add(current);
            current = direction.move(current);
        }
        return list;
    }
}
