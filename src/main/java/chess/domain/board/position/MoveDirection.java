package chess.domain.board.position;

import static java.lang.Math.abs;

import java.util.Arrays;

public enum MoveDirection {

    UP(0, +1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(+1, 0),

    UP_LEFT(-1, +1),
    UP_RIGHT(+1, +1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(+1, -1),

    TWO_UP_ONE_LEFT(-1, +2),
    TWO_UP_ONE_RIGHT(+1, +2),
    TWO_DOWN_ONE_LEFT(-1, -2),
    TWO_DOWN_ONE_RIGHT(+1, -2),
    TWO_LEFT_ONE_UP(-2, +1),
    TWO_LEFT_ONE_DOWN(-2, -1),
    TWO_RIGHT_ONE_UP(+2, 1),
    TWO_RIGHT_ONE_DOWN(+2, -1),

    NOTHING(0, 0);

    private final int differentialFile;
    private final int differentialRank;

    MoveDirection(int differentialFile, int differentialRank) {
        this.differentialFile = differentialFile;
        this.differentialRank = differentialRank;
    }

    public static MoveDirection of(Position from, Position to) {
        double fileDisplacement = to.fileDisplacement(from);
        double rankDisplacement = to.rankDisplacement(from);

        final double unitDifferentialFile = getUnitDifferential(fileDisplacement, rankDisplacement);
        final double unitDifferentialRank = getUnitDifferential(rankDisplacement, fileDisplacement);

        return Arrays.stream(values())
                .filter(moveDirection -> findMoveDirection(moveDirection, unitDifferentialFile, unitDifferentialRank))
                .findAny()
                .orElse(NOTHING);
    }

    public boolean isNothing() {
        return this == NOTHING;
    }

    private static double getUnitDifferential(double base, double divider) {
        if (base == 0) {
            return 0;
        }
        if (divider == 0) {
            return unitSign(base);
        }
        if (abs(base) > abs(divider)) {
            return base / divider;
        }
        return unitSign(base);
    }

    private static double unitSign(double base) {
        return base / Math.abs(base);
    }

    private static boolean findMoveDirection(MoveDirection moveDirection,
                                             double differentialFile,
                                             double differentialRank) {

        return (double) moveDirection.differentialFile == differentialFile &&
                (double) moveDirection.differentialRank == differentialRank;
    }
}
