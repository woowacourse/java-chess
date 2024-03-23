package domain.piece.attribute.point;

import util.DirectionUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Point(File file, Rank rank) {

    private static final Pattern pattern = Pattern.compile("[a-h][1-8]");

    public int getFileIndex() {
        return this.file.ordinal();
    }

    public int getRankIndex() {
        return this.rank.ordinal();
    }

    public static Point fromIndex(final Index index) {
        if (!index.isInBoundary()) {
            throw new IllegalArgumentException("파일과 랭크의 범위를 벗어났습니다.");
        }
        return new Point(File.findByIndex(index.horizontal()), Rank.findByIndex(index.vertical()));
    }

    public Direction calculate(final Point point) {
        return DirectionUtil.determineDirection(this, point);
    }


    public static Point from(final String value) {
        validate(value);
        final File file = File.from(value.charAt(0));
        final Rank rank = Rank.from(Integer.parseInt(value.substring(1)));
        return new Point(file, rank);
    }

    private static void validate(final String value) {
        final Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("파일은 a~h이고, 랭크는 아래부터 위로 1~8까지입니다.");
        }
    }


    public boolean canMoveLeft() {
        return canMoveLeft(1);
    }

    public boolean canMoveLeft(final int count) {
        return this.file.canMoveLeft(count);
    }

    public Point moveLeft() {
        return moveLeft(1);
    }

    public Point moveLeft(final int count) {
        return new Point(this.file.moveLeft(count), this.rank);
    }

    public boolean canMoveRight() {
        return canMoveLeft(1);
    }

    public boolean canMoveRight(final int count) {
        return this.file.canMoveRight(count);
    }

    public Point moveRight() {
        return moveRight(1);
    }

    public Point moveRight(final int count) {
        return new Point(this.file.moveRight(count), this.rank);
    }

    public boolean canMoveUp() {
        return canMoveUp(1);
    }

    public boolean canMoveUp(final int count) {
        return this.rank.canMoveUp(count);
    }

    public Point moveUp() {
        return moveUp(1);
    }

    public Point moveUp(final int count) {
        return new Point(this.file, this.rank.moveUp(count));
    }

    public boolean canMoveDown() {
        return canMoveDown(1);
    }

    public boolean canMoveDown(final int count) {
        return this.rank.canMoveDown(count);
    }

    public Point moveDown() {
        return moveDown(1);
    }

    public Point moveDown(final int count) {
        return new Point(this.file, this.rank.moveDown(count));
    }

    public boolean canMoveUpLeft() {
        return canMoveUpLeft(1);
    }

    public boolean canMoveUpLeft(final int count) {
        return this.rank.canMoveUp(count) && this.file.canMoveLeft(count);
    }

    public Point moveUpLeft() {
        return moveUpLeft(1);
    }

    public Point moveUpLeft(final int count) {
        return new Point(this.file.moveLeft(count), this.rank.moveUp(count));
    }

    public boolean canMoveUpRight() {
        return canMoveUpRight(1);
    }

    public boolean canMoveUpRight(final int count) {
        return this.rank.canMoveUp(count) && this.file.canMoveRight(count);
    }

    public Point moveUpRight() {
        return moveUpRight(1);
    }

    public Point moveUpRight(final int count) {
        return new Point(this.file.moveRight(count), this.rank.moveUp(count));
    }

    public boolean canMoveDownLeft() {
        return canMoveDownLeft(1);
    }

    public boolean canMoveDownLeft(final int count) {
        return this.rank.canMoveUp(count) && this.file.canMoveLeft(count);
    }

    public Point moveDownLeft() {
        return moveDownLeft(1);
    }

    public Point moveDownLeft(final int count) {
        return new Point(this.file.moveLeft(count), this.rank.moveDown(count));
    }

    public boolean canMoveDownRight() {
        return canMoveDownRight(1);
    }

    public boolean canMoveDownRight(final int count) {
        return this.rank.canMoveDown(count) && this.file.canMoveRight(count);
    }

    public Point moveDownRight() {
        return moveDownRight(1);
    }

    public Point moveDownRight(final int count) {
        return new Point(this.file.moveRight(count), this.rank.moveDown(count));
    }

    public Index toIndex() {
        return new Index(rank.ordinal(), file.ordinal());
    }

}
