package chess.domain.piece;

import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceFixture.WHITE_KNIGHT;
import static chess.domain.PositionFixture.WHITE_SOURCE;
import static chess.domain.PositionFixture.WHITE_TARGET;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KnightTest {

    @DisplayName("나이트는 상하좌우로 이동할 수 없다.")
    @Test
    void notTopBottomRightLeft() {
        Knight knight = WHITE_KNIGHT;

        assertThatThrownBy(() -> knight.canMove(WHITE_SOURCE, WHITE_TARGET))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나이트는 대각선으로 이동할 수 없다.")
    @Test
    void notDiagonal() {
        Knight knight = WHITE_KNIGHT;

        assertThatThrownBy(() -> knight.canMove(WHITE_SOURCE, new Position(File.C, Rank.FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나이트 대로 움직였을 때 테스트")
    @Test
    void correct1() {
        Knight knight = WHITE_KNIGHT;

        assertDoesNotThrow(() -> knight.canMove(WHITE_SOURCE, new Position(File.B, Rank.FOUR)));
    }

    @DisplayName("나이트 대로 움직였을 때 테스트")
    @Test
    void correct2() {
        Knight knight = WHITE_KNIGHT;

        assertDoesNotThrow(() -> knight.canMove(new Position(File.B, Rank.FOUR), new Position(File.C, Rank.TWO)));
    }
}
