package chess.domain;

import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;

public class PositionFixture {

    public static final Position WHITE_SOURCE = new Position(File.A, Rank.TWO);
    public static final Position WHITE_TARGET = new Position(File.A, Rank.THREE);

    public static final Position BLACK_SOURCE = new Position(File.A, Rank.SEVEN);
    public static final Position BLACK_TARGET = new Position(File.A, Rank.SIX);
}
