package chess.domain.position;

import chess.domain.Direction;

import java.util.Arrays;

public enum Rank {
    RANK1(1),
    RANK2(2),
    RANK3(3),
    RANK4(4),
    RANK5(5),
    RANK6(6),
    RANK7(7),
    RANK8(8);

    private final int number;

    Rank(final int number) {
        this.number = number;
    }

    public static Rank of(int number){
        return Arrays.stream(values())
                .filter(rank -> rank.number == number).findFirst().get();
    }

    public int distance(Rank other) {
        return Math.abs(this.number - other.number);
    }

    public Direction getDirection(Rank other){
        if(other.number - this.number > 0){
            return Direction.PLUS;
        }
        return Direction.MINUS;
    }

    public Rank moveToDirection(Direction direction){
        if(direction.equals(Direction.PLUS)){
            return next();
        }
        return prev();
    }

    public Rank next() {
        return Rank.of(this.number + 1);
    }

    public Rank prev(){
        return Rank.of(this.number - 1);
    }
}
