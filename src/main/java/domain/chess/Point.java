package domain.chess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Point(File file, Rank rank) {

    private static final Pattern pattern = Pattern.compile("[a-h][1-8]");

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

    public int getFileIndex() {
        return this.file.ordinal();
    }

    public int getRankIndex() {
        return this.rank.ordinal();
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
        return canMoveRight(1);
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

    public boolean canMoveUpUpLeft() {
        return canMoveLeft(1) && canMoveUp(2);
    }

    public Point moveUpUpLeft() {
        return new Point(this.file.moveLeft(1), this.rank.moveUp(2));
    }

    public boolean canMoveUpUpRight() {
        return canMoveRight(1) && canMoveUp(2);
    }

    public Point moveUpUpRight() {
        return new Point(this.file.moveRight(1), this.rank.moveUp(2));
    }

    public Point moveRightUpRight() {
        return new Point(this.file.moveRight(2), this.rank.moveUp(1));
    }

    public boolean canMoveRightUpRight() {
        return canMoveRight(2) && canMoveUp(1);
    }

    public Point moveRightDownRight() {
        return new Point(this.file.moveRight(2), this.rank.moveDown(1));
    }

    public boolean canMoveRightDownRight() {
        return canMoveRight(2) && canMoveDown(1);
    }

    public Point moveLeftDownLeft() {
        return new Point(this.file.moveLeft(2), this.rank.moveDown(1));
    }

    public boolean canMoveLeftDownLeft() {
        return canMoveLeft(2) && canMoveDown(1);
    }

    public Point moveLeftUpLeft() {
        return new Point(this.file.moveLeft(2), this.rank.moveUp(1));
    }

    public boolean canMoveLeftUpLeft() {
        return canMoveLeft(2) && canMoveUp(1);
    }

    public Point moveDownDownRight() {
        return new Point(this.file.moveRight(1), this.rank.moveDown(2));
    }

    public boolean canMoveDownDownRight() {
        return canMoveRight(1) && canMoveDown(2);
    }

    public Point moveDownDownLeft() {
        return new Point(this.file.moveLeft(1), this.rank.moveDown(2));
    }

    public boolean canMoveDownDownLeft() {
        return canMoveLeft(1) && canMoveDown(2);
    }


    public boolean notEquals(final Point point) {
        return !this.equals(point);
    }


}
