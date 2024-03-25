package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import chess.domain.piece.Side;
import chess.fixture.PositionFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialPositionTest {

    @DisplayName("검은색 룩은 a8, h8에 위치한다.")
    @Test
    void blackRookPositions() {
        List<Position> positions = InitialPosition.ROOK.positions(Side.BLACK);

        assertThat(positions).containsOnlyOnce(PositionFixture.A8, PositionFixture.H8);
    }

    @DisplayName("흰색 룩은 a1, h1에 위치한다.")
    @Test
    void whiteRookPositions() {
        List<Position> positions = InitialPosition.ROOK.positions(Side.WHITE);

        assertThat(positions).containsOnlyOnce(PositionFixture.A1, PositionFixture.H1);
    }

    @DisplayName("검은색 나이트는 b8, g8에 위치한다.")
    @Test
    void blackKnightPosition() {
        List<Position> positions = InitialPosition.KNIGHT.positions(Side.BLACK);

        assertThat(positions).containsOnlyOnce(PositionFixture.B8, PositionFixture.G8);
    }

    @DisplayName("흰색 나이트는 b1, g1에 위치한다.")
    @Test
    void whiteKnightPosition() {
        List<Position> positions = InitialPosition.KNIGHT.positions(Side.WHITE);

        assertThat(positions).containsOnlyOnce(PositionFixture.B1, PositionFixture.G1);
    }

    @DisplayName("검은색 비숍은 c8, f8에 위치한다.")
    @Test
    void blackBishopPosition() {
        List<Position> positions = InitialPosition.BISHOP.positions(Side.BLACK);

        assertThat(positions).containsOnlyOnce(PositionFixture.C8, PositionFixture.F8);
    }

    @DisplayName("흰색 비숍은 c1, f1에 위치한다.")
    @Test
    void whiteBishopPosition() {
        List<Position> positions = InitialPosition.BISHOP.positions(Side.WHITE);

        assertThat(positions).containsOnlyOnce(PositionFixture.C1, PositionFixture.F1);
    }

    @DisplayName("검은색 퀸은 d8에 위치한다.")
    @Test
    void blackQueenPosition() {
        List<Position> positions = InitialPosition.QUEEN.positions(Side.BLACK);

        assertThat(positions).containsOnlyOnce(PositionFixture.D8);
    }

    @DisplayName("흰색 퀸은 d1에 위치한다.")
    @Test
    void whiteQueenPosition() {
        List<Position> positions = InitialPosition.QUEEN.positions(Side.WHITE);

        assertThat(positions).containsOnlyOnce(PositionFixture.D1);
    }

    @DisplayName("검은색 킹은 e8에 위치한다.")
    @Test
    void blackKingPosition() {
        List<Position> positions = InitialPosition.KING.positions(Side.BLACK);

        assertThat(positions).containsOnlyOnce(PositionFixture.E8);
    }

    @DisplayName("흰색 킹은 e1에 위치한다.")
    @Test
    void whiteKingPosition() {
        List<Position> positions = InitialPosition.KING.positions(Side.WHITE);

        assertThat(positions).containsOnlyOnce(PositionFixture.E1);
    }

    @DisplayName("검은색 폰은 a7~h7에 위치한다.")
    @Test
    void blackPawnPosition() {
        List<Position> positions = InitialPosition.PAWN.positions(Side.BLACK);

        assertThat(positions).containsOnlyOnce(
                PositionFixture.A7, PositionFixture.B7, PositionFixture.C7, PositionFixture.D7, PositionFixture.E7, PositionFixture.F7, PositionFixture.G7, PositionFixture.H7);
    }

    @DisplayName("흰색 폰은 a2~h2에 위치한다.")
    @Test
    void whitePawnPosition() {
        List<Position> positions = InitialPosition.PAWN.positions(Side.WHITE);

        assertThat(positions).containsOnlyOnce(
                PositionFixture.A2, PositionFixture.B2, PositionFixture.C2, PositionFixture.D2, PositionFixture.E2, PositionFixture.F2, PositionFixture.G2, PositionFixture.H2);
    }
}
