package domain;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PositionTest {

    public static final Position POSITION_A1 = Position.of(File.FILE_A, Rank.RANK1);
    public static final Position POSITION_B2 = Position.of(File.FILE_B, Rank.RANK2);

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
}