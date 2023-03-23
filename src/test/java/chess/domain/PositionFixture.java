package chess.domain;

import chess.domain.board.Position;

import static chess.domain.board.FileCoordinate.*;
import static chess.domain.board.RankCoordinate.*;

public class PositionFixture {
    public static final Position C_4 = new Position(C, FOUR);
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
