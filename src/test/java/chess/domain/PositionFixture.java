package chess.domain;

import static chess.domain.board.FileCoordinate.A;
import static chess.domain.board.FileCoordinate.B;
import static chess.domain.board.FileCoordinate.C;
import static chess.domain.board.FileCoordinate.D;
import static chess.domain.board.FileCoordinate.E;
import static chess.domain.board.FileCoordinate.F;
import static chess.domain.board.RankCoordinate.FIVE;
import static chess.domain.board.RankCoordinate.FOUR;
import static chess.domain.board.RankCoordinate.ONE;
import static chess.domain.board.RankCoordinate.SEVEN;
import static chess.domain.board.RankCoordinate.SIX;
import static chess.domain.board.RankCoordinate.THREE;
import static chess.domain.board.RankCoordinate.TWO;

import chess.domain.board.Position;

public class PositionFixture {
    public static final Position C_4 = new Position(C, FOUR);
    public static final Position A_1 = new Position(A, ONE);
    public static final Position A_2 = new Position(A, TWO);
    public static final Position A_3 = new Position(A, THREE);
    public static final Position D_4 = new Position(D, FOUR);
    public static final Position E_4 = new Position(E, FOUR);
    public static final Position F_4 = new Position(F, FOUR);
    public static final Position C_5 = new Position(C, FIVE);
    public static final Position C_6 = new Position(C, SIX);
    public static final Position C_7 = new Position(C, SEVEN);
    public static final Position D_5 = new Position(D, FIVE);
    public static final Position E_6 = new Position(E, SIX);
    public static final Position F_7 = new Position(F, SEVEN);
    public static final Position D_6 = new Position(D, SIX);
    public static final Position B_1 = new Position(B, ONE);
    public static final Position B_2 = new Position(B, TWO);
    public static final Position B_3 = new Position(B, THREE);
    public static final Position B_7 = new Position(B, SEVEN);
    public static final Position C_2 = new Position(C, TWO);
}
