package domain.board;

import static fixture.PositionFixture.A1;
import static fixture.PositionFixture.A2;
import static fixture.PositionFixture.A7;
import static fixture.PositionFixture.A8;
import static fixture.PositionFixture.B1;
import static fixture.PositionFixture.B2;
import static fixture.PositionFixture.B7;
import static fixture.PositionFixture.B8;
import static fixture.PositionFixture.C1;
import static fixture.PositionFixture.C2;
import static fixture.PositionFixture.C7;
import static fixture.PositionFixture.C8;
import static fixture.PositionFixture.D1;
import static fixture.PositionFixture.D2;
import static fixture.PositionFixture.D7;
import static fixture.PositionFixture.D8;
import static fixture.PositionFixture.E1;
import static fixture.PositionFixture.E2;
import static fixture.PositionFixture.E7;
import static fixture.PositionFixture.E8;
import static fixture.PositionFixture.F1;
import static fixture.PositionFixture.F2;
import static fixture.PositionFixture.F7;
import static fixture.PositionFixture.F8;
import static fixture.PositionFixture.G1;
import static fixture.PositionFixture.G2;
import static fixture.PositionFixture.G7;
import static fixture.PositionFixture.G8;
import static fixture.PositionFixture.H1;
import static fixture.PositionFixture.H2;
import static fixture.PositionFixture.H7;
import static fixture.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.InitialPosition;
import chess.domain.square.Square;
import chess.domain.piece.Side;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialSquareTest {

    @DisplayName("검은색 룩은 a8, h8에 위치한다.")
    @Test
    void blackRookPositions() {
        List<Square> squares = InitialPosition.ROOK.positions(Side.BLACK);

        assertThat(squares).containsOnlyOnce(A8, H8);
    }

    @DisplayName("흰색 룩은 a1, h1에 위치한다.")
    @Test
    void whiteRookPositions() {
        List<Square> squares = InitialPosition.ROOK.positions(Side.WHITE);

        assertThat(squares).containsOnlyOnce(A1, H1);
    }

    @DisplayName("검은색 나이트는 b8, g8에 위치한다.")
    @Test
    void blackKnightPosition() {
        List<Square> squares = InitialPosition.KNIGHT.positions(Side.BLACK);

        assertThat(squares).containsOnlyOnce(B8, G8);
    }

    @DisplayName("흰색 나이트는 b1, g1에 위치한다.")
    @Test
    void whiteKnightPosition() {
        List<Square> squares = InitialPosition.KNIGHT.positions(Side.WHITE);

        assertThat(squares).containsOnlyOnce(B1, G1);
    }

    @DisplayName("검은색 비숍은 c8, f8에 위치한다.")
    @Test
    void blackBishopPosition() {
        List<Square> squares = InitialPosition.BISHOP.positions(Side.BLACK);

        assertThat(squares).containsOnlyOnce(C8, F8);
    }

    @DisplayName("흰색 비숍은 c1, f1에 위치한다.")
    @Test
    void whiteBishopPosition() {
        List<Square> squares = InitialPosition.BISHOP.positions(Side.WHITE);

        assertThat(squares).containsOnlyOnce(C1, F1);
    }

    @DisplayName("검은색 퀸은 d8에 위치한다.")
    @Test
    void blackQueenPosition() {
        List<Square> squares = InitialPosition.QUEEN.positions(Side.BLACK);

        assertThat(squares).containsOnlyOnce(D8);
    }

    @DisplayName("흰색 퀸은 d1에 위치한다.")
    @Test
    void whiteQueenPosition() {
        List<Square> squares = InitialPosition.QUEEN.positions(Side.WHITE);

        assertThat(squares).containsOnlyOnce(D1);
    }

    @DisplayName("검은색 킹은 e8에 위치한다.")
    @Test
    void blackKingPosition() {
        List<Square> squares = InitialPosition.KING.positions(Side.BLACK);

        assertThat(squares).containsOnlyOnce(E8);
    }

    @DisplayName("흰색 킹은 e1에 위치한다.")
    @Test
    void whiteKingPosition() {
        List<Square> squares = InitialPosition.KING.positions(Side.WHITE);

        assertThat(squares).containsOnlyOnce(E1);
    }

    @DisplayName("검은색 폰은 a7~h7에 위치한다.")
    @Test
    void blackPawnPosition() {
        List<Square> squares = InitialPosition.PAWN.positions(Side.BLACK);

        assertThat(squares).containsOnlyOnce(A7, B7, C7, D7, E7, F7, G7, H7);
    }

    @DisplayName("흰색 폰은 a2~h2에 위치한다.")
    @Test
    void whitePawnPosition() {
        List<Square> squares = InitialPosition.PAWN.positions(Side.WHITE);

        assertThat(squares).containsOnlyOnce(A2, B2, C2, D2, E2, F2, G2, H2);
    }
}
