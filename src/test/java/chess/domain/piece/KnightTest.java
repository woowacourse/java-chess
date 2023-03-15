package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static chess.domain.File.B;
import static chess.domain.File.C;
import static chess.domain.Rank.EIGHT;
import static chess.domain.Rank.FIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    @ParameterizedTest
    @CsvSource({"C, SIX", "D, SEVEN"})
    @DisplayName("지나갈 경로를 얻는다.")
    void getPassingPathTest(final File file, final Rank rank) {
        final Piece knight = new Knight(B, EIGHT, Color.BLACK);

        final List<Position> path = knight.getPassingPositions(new Position(file, rank));

        assertThat(path).isEmpty();
    }

    @Test
    @DisplayName("이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
    void getPassingPathFailTest() {
        final Piece knight = new Knight(C, EIGHT, Color.BLACK);

        assertThatThrownBy(() -> knight.getPassingPositions(new Position(B, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }
}
