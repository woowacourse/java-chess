package domain;

public record Vector(int x, int y) {

    public static Vector of(final Square source, final Square target) {
        final File sourceFile = source.getFile();
        final File targetFile = target.getFile();

        final Rank sourceRank = source.getRank();
        final Rank targetRank = target.getRank();

        return new Vector(targetFile.subtract(sourceFile), targetRank.subtrack(sourceRank));
    }

    public Vector normalizedVector() {
        final int unitX = x == 0 ? 0 : x / Math.abs(x);
        final int unitY = y == 0 ? 0 : y / Math.abs(y);

        return new Vector(unitX, unitY);
    }

    public boolean isDiagonal() {
        return Math.abs(x) == Math.abs(y);
    }

    public boolean isHorizontalOrVertical() {
        return x == 0 || y == 0;
    }

    public int maxAxiosSize() {
        return Math.max(Math.abs(x), Math.abs(y));
    }
}
