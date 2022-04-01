package chess.model.piece;

import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;

public class Fixtures {

    //center
    public static final Square D4 = Square.of(File.D, Rank.FOUR);

    // bishop
    public static final Square B6 = Square.of(File.B, Rank.SIX);
    public static final Square B2 = Square.of(File.B, Rank.TWO);
    public static final Square F6 = Square.of(File.F, Rank.SIX);
    public static final Square F2 = Square.of(File.F, Rank.TWO);

    // rook
    public static final Square H4 = Square.of(File.H, Rank.FOUR);
    public static final Square A4 = Square.of(File.A, Rank.FOUR);
    public static final Square D1 = Square.of(File.D, Rank.ONE);
    public static final Square D8 = Square.of(File.D, Rank.EIGHT);

    // knight
    public static final Square E6 = Square.of(File.E, Rank.SIX);
    public static final Square C6 = Square.of(File.C, Rank.SIX);
    public static final Square F5 = Square.of(File.F, Rank.FIVE);
    public static final Square B5 = Square.of(File.B, Rank.FIVE);
    public static final Square F3 = Square.of(File.F, Rank.THREE);
    public static final Square B3 = Square.of(File.B, Rank.THREE);
    public static final Square E2 = Square.of(File.E, Rank.TWO);
    public static final Square C2 = Square.of(File.C, Rank.TWO);

    // king
    public static final Square C3 = Square.of(File.C, Rank.THREE);
    public static final Square C4 = Square.of(File.C, Rank.FOUR);
    public static final Square C5 = Square.of(File.C, Rank.FIVE);
    public static final Square D5 = Square.of(File.D, Rank.FIVE);
    public static final Square E5 = Square.of(File.E, Rank.FIVE);
    public static final Square E4 = Square.of(File.E, Rank.FOUR);
    public static final Square E3 = Square.of(File.E, Rank.THREE);
    public static final Square D3 = Square.of(File.D, Rank.THREE);

    public static final Square A7 = Square.of(File.A, Rank.SEVEN);
    public static final Square E1 = Square.of(File.E, Rank.ONE);
}
