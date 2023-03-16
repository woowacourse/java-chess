package chess.domain.position;

import chess.domain.Direction;

import java.util.Arrays;

public enum Rank {
    RANK1(0),
    RANK2(1),
    RANK3(2),
    RANK4(3),
    RANK5(4),
    RANK6(5),
    RANK7(6),
    RANK8(7);

    private final int number;

    Rank(final int number) {
        this.number = number;
    }

    public static Rank of(int number) {
        return Arrays.stream(values())
                .filter(rank -> rank.number == number).findFirst().get();
    }

    public int distance(Rank other) {
        return Math.abs(this.number - other.number);
    }

    public Direction getDirection(Rank other) {
        if (other.number > this.number) {
            return Direction.PLUS;
        }
        if (other.number < this.number) {
            return Direction.MINUS;
        }
        return Direction.ZERO;
    }

    public Rank moveToDirection(Direction direction) {
        if (direction.equals(Direction.PLUS)) {
            return next();
        }
        if (direction.equals(Direction.MINUS)) {
            return prev();
        }
        return this;
    }

    public Rank next() {
        return Rank.of(this.number + 1);
    }

    public Rank move(int distance) {
        if (this.number + distance < 0) {
            return Rank.of(0);
        }
        if (this.number + distance > 7) {
            return Rank.of(7);
        }
        return Rank.of(this.number + distance);
    }

    public Rank prev() {
        return Rank.of(this.number - 1);
    }

//    public Rank moveForward(Direction direction){
//        return Rank.of(direction.goToDirection(number));
//    }

    public int getRankIndex(){
        return 7- number;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "number=" + number +
                '}';
    }
}
