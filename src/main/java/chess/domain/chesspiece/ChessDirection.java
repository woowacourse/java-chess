package chess.domain.chesspiece;

import chess.domain.chesspoint.RelativeChessPoint;

import java.util.Arrays;
import java.util.List;

public enum ChessDirection {
    N(RelativeChessPoint.of(1, 0)),
    E(RelativeChessPoint.of(0, 1)),
    S(RelativeChessPoint.of(-1, 0)),
    W(RelativeChessPoint.of(0, -1)),

    NE(RelativeChessPoint.of(1, 1)),
    SE(RelativeChessPoint.of(-1, 1)),
    SW(RelativeChessPoint.of(-1, -1)),
    NW(RelativeChessPoint.of(1, -1)),

    NNE(RelativeChessPoint.of(2, 1)),
    EEN(RelativeChessPoint.of(1, 2)),
    EES(RelativeChessPoint.of(-1, 2)),
    SSE(RelativeChessPoint.of(-2, 1)),
    SSW(RelativeChessPoint.of(-2, -1)),
    WWS(RelativeChessPoint.of(-1, -2)),
    WWN(RelativeChessPoint.of(1, -2)),
    NNW(RelativeChessPoint.of(2, -1));

    private RelativeChessPoint direction;

    ChessDirection(RelativeChessPoint direction) {
        this.direction = direction;
    }

    public static List<ChessDirection> linearDirection() {
        return Arrays.asList(N, E, S, W);
    }

    public static List<ChessDirection> diagonalDirection() {
        return Arrays.asList(NE, SE, SW, NW);
    }

    public static List<ChessDirection> everyDirection() {
        return Arrays.asList(N, E, S, W, NE, SE, SW, NW);
    }

    public static List<ChessDirection> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<ChessDirection> whitePawnDirectionOnEmptyTarget() {
        return Arrays.asList(N);
    }

    public static List<ChessDirection> whitePawnDirectionOnExistTarget() {
        return Arrays.asList(NE, NW);
    }

    public static List<ChessDirection> blackPawnDirectionOnEmptyTarget() {
        return Arrays.asList(S);
    }

    public static List<ChessDirection> blackPawnDirectionOnExistTarget() {
        return Arrays.asList(SE, SW);
    }

    public boolean match(RelativeChessPoint direction) {
        return this.direction.equals(direction);
    }
}
