package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.C2;
import static chess.domain.fixture.CoordinateFixture.C3;
import static chess.domain.fixture.CoordinateFixture.C4;
import static chess.domain.fixture.CoordinateFixture.C5;
import static chess.domain.fixture.CoordinateFixture.C6;
import static chess.domain.fixture.CoordinateFixture.C7;
import static chess.domain.fixture.PieceFixture.BLACK_PAWN;
import static chess.domain.fixture.PieceFixture.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.board.Coordinate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Pawn(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("흰색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void findMovablePathWhite() {
        List<Coordinate> result = WHITE_PAWN.findMovablePath(C2, C3);

        List<Coordinate> expected = List.of(C3, C4);
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("검은색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void findMovablePathBlack() {
        List<Coordinate> result = BLACK_PAWN.findMovablePath(C7, C3);

        List<Coordinate> expected = List.of(C6, C5);
        assertThat(result).containsExactlyElementsOf(expected);
    }
}
