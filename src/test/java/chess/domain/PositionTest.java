package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

public class PositionTest {

    public static final Position POSITION_A1 = Position.of(File.FILE_A, Rank.RANK1);
    public static final Position POSITION_B2 = Position.of(File.FILE_B, Rank.RANK2);
    public static final Position POSITION_B1 = Position.of(File.FILE_B, Rank.RANK1);

    @Test
    void 위치_생성_성공() {
        assertDoesNotThrow(() -> Position.of(File.FILE_A, Rank.RANK1));
    }

    @Test
    void 위치_동등성_비교() {
        Position position = Position.of(File.FILE_H, Rank.RANK8);
        assertThat(position).isSameAs(Position.of(File.FILE_H, Rank.RANK8));
    }

    @Test
    void 두_위치가_직선상에서_동일한지_검사한다() {
        Position position1 = POSITION_A1;
        Position position2 = POSITION_B2;

        //when
        boolean isStraightSame = position1.isStraightEqual(position2);

        //then
        assertThat(isStraightSame).isFalse();
    }

    @Test
    void 두_위치가_대각선상에서_동일한지_검사한다() {
        Position position1 = POSITION_A1;
        Position position2 = POSITION_B1;

        //when
        boolean isDiagonalSame = position1.isDiagonalEqual(position2);

        //then
        assertThat(isDiagonalSame).isFalse();
    }
}
