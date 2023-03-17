package chess.domain.piece.moveRule;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class TestFixture {
    public static final Position A1 = Position.of(File.FILE_A, Rank.RANK1);
    public static final Position A3 = Position.of(File.FILE_A, Rank.RANK3);
    public static final Position A5 = Position.of(File.FILE_A, Rank.RANK5);
    public static final Position A7 = Position.of(File.FILE_A, Rank.RANK7);

    public static final Position B1 = Position.of(File.FILE_B, Rank.RANK1);
    public static final Position B3 = Position.of(File.FILE_B, Rank.RANK3);
    public static final Position D1 = Position.of(File.FILE_D, Rank.RANK1);
    public static final Position D4 = Position.of(File.FILE_D, Rank.RANK4);
    public static final Position B2 = Position.of(File.FILE_B, Rank.RANK2);
}
