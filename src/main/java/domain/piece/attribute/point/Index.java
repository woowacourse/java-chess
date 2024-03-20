package domain.piece.attribute.point;

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
}
