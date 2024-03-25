package model.piece;

import static model.Fixtures.A2;
import static model.Fixtures.A4;
import static model.Fixtures.A5;
import static model.Fixtures.A6;
import static model.Fixtures.A7;
import static model.Fixtures.A8;
import static model.Fixtures.B2;
import static model.Fixtures.B3;
import static model.Fixtures.B4;
import static model.Fixtures.B5;
import static model.Fixtures.B7;
import static model.Fixtures.C2;
import static model.Fixtures.C3;
import static model.Fixtures.C4;
import static model.Fixtures.D5;
import static model.Fixtures.D6;
import static model.Fixtures.D7;
import static model.Fixtures.G8;
import static model.Fixtures.H2;
import static model.Fixtures.H3;
import static model.Fixtures.H4;
import static model.Fixtures.H5;
import static model.Fixtures.H6;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import exception.InvalidMovingException;
import java.util.Set;
import java.util.stream.Stream;
import model.Camp;
import model.ChessGame;
import model.position.Moving;
import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @DisplayName("이동할 수 없는 경로면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidMovingParameterProvider")
    void invalidMoving(final Camp camp, final Moving moving) {
        final Pawn pawn = Pawn.create(camp);

        assertAll(
                () -> assertThat(pawn.canMovable(moving)).isFalse(),
                () -> assertThatThrownBy(() -> pawn.getMoveRoute(moving))
                        .isInstanceOf(InvalidMovingException.class)
        );
    }

    static Stream<Arguments> invalidMovingParameterProvider() {
        return Stream.of(
                Arguments.of(Camp.BLACK, new Moving(A6, A4)),
                Arguments.of(Camp.BLACK, new Moving(A7, A4)),
                Arguments.of(Camp.WHITE, new Moving(A8, A7))
        );
    }

    @DisplayName("이동 경로를 반환한다. 출발지와 도착지는 포함하지 않는다.")
    @ParameterizedTest
    @MethodSource("checkRouteParameterProvider")
    void checkRoute(final Camp camp, final Moving moving, final Set<Position> expected) {
        final Pawn pawn = Pawn.create(camp);

        assertAll(
                () -> assertThat(pawn.canMovable(moving)).isTrue(),
                () -> assertThat(pawn.getMoveRoute(moving)).isEqualTo(expected)
        );

    }

    static Stream<Arguments> checkRouteParameterProvider() {
        return Stream.of(
                Arguments.of(Camp.BLACK, new Moving(A7, A5), Set.of(A6)),
                Arguments.of(Camp.BLACK, new Moving(A6, A5), Set.of()),
                Arguments.of(Camp.WHITE, new Moving(B2, B4), Set.of(B3)),
                Arguments.of(Camp.WHITE, new Moving(C2, C3), Set.of())
        );
    }

    @Test
    @DisplayName("앞에 기물이 있을때 전진이 불가하다.")
    void whenPieceInFrontCanNotMoveForward() {
        //given
        final ChessGame chessGame = ChessGame.setupStartingPosition();

        /*
        RNBQKBNR  8
        .PPPPPPP  7
        ........  6
        P.......  5
        p.......  4
        ........  3
        .ppppppp  2
        rnbqkbnr  1

        abcdefgh
         */

        //when
        chessGame.move(new Moving(A2, A4));
        chessGame.move(new Moving(A7, A5));

        final Moving forwadMoving = new Moving(A4, A5);

        //then
        assertThatThrownBy(() -> chessGame.move(forwadMoving))
                .isInstanceOf(InvalidMovingException.class);
    }

    @Test
    @DisplayName("대각선에 기물이 있을 때 대각선 이동이 가능하다. WHITE (위 오른쪽)")
    void whenPieceInDiagonalCanMove1() {
        //given
        final ChessGame chessGame = ChessGame.setupStartingPosition();

        /*
        RNBQKBNR  8
        P.PPPPPP  7
        ........  6
        .P......  5
        p.......  4
        ........  3
        .ppppppp  2
        rnbqkbnr  1

        abcdefgh
         */

        //when
        chessGame.move(new Moving(A2, A4));
        chessGame.move(new Moving(B7, B5));

        //then
        assertThatCode(() -> chessGame.move(new Moving(A4, B5)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("대각선에 기물이 있다면 이동이 가능하다. BLACK (아래 오른쪽)")
    void whenPieceInDiagonalCanMove2() {
        //given
        final ChessGame chessGame = ChessGame.setupStartingPosition();

        /*
        RNBQKBNR  8
        P.PPPPPP  7
        ........  6
        .P......  5
        ..p.....  4
        .p......  3
        p..ppppp  2
        rnbqkbnr  1

        abcdefgh
         */

        //when
        chessGame.move(new Moving(C2, C4));
        chessGame.move(new Moving(B7, B5));
        chessGame.move(new Moving(B2, B3));

        //then
        assertThatCode(() -> chessGame.move(new Moving(B5, C4)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("대각선에 기물이 없다면 대각선 이동이 불가하다.")
    void whenPieceNotInDiagonalCanNotMove() {
        //given
        final ChessGame chessGame = ChessGame.setupStartingPosition();

        /*
        RNBQKB.R  8
        PPPPPPPP  7
        .......N  6
        ........  5
        p.......  4
        ........  3
        .ppppppp  2
        rnbqkbnr  1

        abcdefgh
         */

        //when
        chessGame.move(new Moving(A2, A4));
        chessGame.move(new Moving(G8, H6));

        final Moving diagonalMoving = new Moving(A4, B5);

        //then
        assertThatThrownBy(() -> chessGame.move(diagonalMoving))
                .isInstanceOf(InvalidMovingException.class);
    }

    @Test
    @DisplayName("폰은 후진할 수 없다. WHITE")
    void failToMoveBackWhitePawn() {
        //given
        final ChessGame chessGame = ChessGame.setupStartingPosition();

        //when
        chessGame.move(new Moving(H2, H4));
        chessGame.move(new Moving(D7, D5));

        final Moving whiteBackMoving = new Moving(H4, H3);

        //then
        assertThatThrownBy(() -> chessGame.move(whiteBackMoving))
                .isInstanceOf(InvalidMovingException.class);
    }

    @Test
    @DisplayName("폰은 후진할 수 없다. BLACK")
    void failToMoveBackBlackPawn() {
        //given
        final ChessGame chessGame = ChessGame.setupStartingPosition();

        //when
        chessGame.move(new Moving(H2, H4));
        chessGame.move(new Moving(D7, D5));
        chessGame.move(new Moving(H4, H5));

        final Moving blackBackMoving = new Moving(D5, D6);

        //then
        assertThatThrownBy(() -> chessGame.move(blackBackMoving))
                .isInstanceOf(InvalidMovingException.class);
    }
}
