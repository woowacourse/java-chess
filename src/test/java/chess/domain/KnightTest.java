package chess.domain;

import chess.domain.piece.Knight;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.PieceFixture.whiteKnight;
import static chess.domain.PositionFixture.WHITE_SOURCE;
import static chess.domain.PositionFixture.WHITE_TARGET;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KnightTest {

    @DisplayName("나이트는 상하좌우로 이동할 수 없다.")
    @Test
    void notTopBottomRightLeft() {
        Knight knight = whiteKnight;

        assertThatThrownBy(() -> knight.canMove(WHITE_SOURCE, WHITE_TARGET))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나이트는 대각선으로 이동할 수 없다.")
    @Test
    void notDiagonal() {
        Knight knight = whiteKnight;

        assertThatThrownBy(() -> knight.canMove(WHITE_SOURCE, new Position(File.C, Rank.FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나이트 대로 움직였을 때 테스트")
    @Test
    void correct1() {
        Knight knight = whiteKnight;

        assertDoesNotThrow(() -> knight.canMove(WHITE_SOURCE, new Position(File.B, Rank.FOUR)));
    }

    @DisplayName("나이트 대로 움직였을 때 테스트")
    @Test
    void correct2() {
        Knight knight = whiteKnight;

        assertDoesNotThrow(() -> knight.canMove(new Position(File.B, Rank.FOUR), new Position(File.C, Rank.TWO)));
    }
}
