package chess.domain.position;

import chess.domain.Direction;

import java.util.Arrays;

public enum Rank {
    RANK1(0,'1'),
    RANK2(1,'2'),
    RANK3(2,'3'),
    RANK4(3,'4'),
    RANK5(4,'5'),
    RANK6(5,'6'),
    RANK7(6,'7'),
    RANK8(7,'8');

    private final int index;
    private final char symbol;

    Rank(final int index, char symbol) {
        this.index = index;
        this.symbol = symbol;
    }

    public static Rank of(int number) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == number).findFirst().get();
    }

    public static Rank of(char symbol) {
        return Arrays.stream(values())
                .filter(rank -> rank.symbol == symbol).findFirst().get();
    }

    public int distance(Rank other) {
        return Math.abs(this.index - other.index);
    }

    public Direction getDirection(Rank other) {
        if (other.index > this.index) {
            return Direction.PLUS;
        }
        if (other.index < this.index) {
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
        return Rank.of(this.index + 1);
    }

    public Rank move(int distance) {
        if (this.index + distance < 0) {
            return Rank.of(0);
        }
        if (this.index + distance > 7) {
            return Rank.of(7);
        }
        return Rank.of(this.index + distance);
    }

    public Rank prev() {
        return Rank.of(this.index - 1);
    }

//    public Rank moveForward(Direction direction){
//        return Rank.of(direction.goToDirection(number));
//    }

    public int getRankIndex(){
        return 7- index;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "number=" + index +
                "}";
    }
}
