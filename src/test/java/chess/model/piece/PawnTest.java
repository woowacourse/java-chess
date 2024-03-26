package chess.model.piece;

import static chess.model.Fixtures.B4;
import static chess.model.Fixtures.C2;
import static chess.model.Fixtures.C3;
import static chess.model.Fixtures.C4;
import static chess.model.Fixtures.C5;
import static chess.model.Fixtures.D2;
import static chess.model.Fixtures.D3;
import static chess.model.Fixtures.D4;
import static chess.model.Fixtures.F6;
import static chess.model.Fixtures.F7;
import static chess.model.Fixtures.G4;
import static chess.model.Fixtures.G5;
import static chess.model.Fixtures.G6;
import static chess.model.Fixtures.G7;
import static chess.model.Fixtures.G8;
import static chess.model.Fixtures.H6;
import static chess.model.material.Color.BLACK;
import static chess.model.material.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.material.Color;
import chess.model.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    /*
        ........
        ......P.
        ......*.
        ........
        ..*.....
        ..p.....
        ........
        ........
         */
    @DisplayName("전진 1칸 이동이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideSingleMoveWithColor")
    void pawnCanSingleMove(Position source, Position target, Color color) {
        Piece piece = new Pawn(color);
        assertThatCode(() -> piece.findRoute(source, target))
            .doesNotThrowAnyException();
    }

    public static Stream<Arguments> provideSingleMoveWithColor() {
        return Stream.of(
            Arguments.of(C3, C4, WHITE),
            Arguments.of(G7, G6, BLACK)
        );
    }

    /*
    ........
    ......P.
    ........
    ......*.
    ..*.....
    ........
    ..p.....
    ........
    */
    @DisplayName("최초 2칸 전진 이동이면 이동할 수 있다")
    @ParameterizedTest
    @MethodSource("provideDoubleMoveWithColor")
    void pawnCanDoubleMove(Position source, Position target, Color color) {
        Piece piece = new Pawn(color);
        assertThatCode(() -> piece.findRoute(source, target))
            .doesNotThrowAnyException();
    }

    public static Stream<Arguments> provideDoubleMoveWithColor() {
        return Stream.of(
            Arguments.of(C2, C4, WHITE),
            Arguments.of(G7, G5, BLACK)
        );
    }

    /*
    ......*.
    .....*P.
    .......*
    ..*.....
    .*....*.
    ..p*....
    ..*.....
    ........
    */
    @DisplayName("전진 1칸 이동 혹은 최초 2칸 이동이 아니면 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("provideInvalidMoveWithColor")
    void kingCanNotMove(Position source, Position target, Color color) {
        Piece piece = new Pawn(color);
        assertThatThrownBy(() -> piece.findRoute(source, target))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Pawn은 1칸 전진 이동 혹은 최초 2칸 전진 이동만 가능합니다.");
    }

    public static Stream<Arguments> provideInvalidMoveWithColor() {
        return Stream.of(
            Arguments.of(C3, B4, WHITE),
            Arguments.of(C3, C5, WHITE),
            Arguments.of(C3, C2, WHITE),
            Arguments.of(C3, D3, WHITE),
            Arguments.of(G7, F7, BLACK),
            Arguments.of(G7, G8, BLACK),
            Arguments.of(G7, G4, BLACK),
            Arguments.of(G7, H6, BLACK)
        );
    }

    /*
    ........
    ......P.
    .....n.n
    ........
    .N.N....
    ..p.....
    ........
    ........
    */
    @DisplayName("전방 대각선 1칸 공격이면 이동할 수 있다")
    @ParameterizedTest
    @MethodSource("provideAttackMoveWithColor")
    void pawnCanAttackMove(Position source, Position target, Color color) {
        Piece piece = new Pawn(color);
        assertThatCode(() -> piece.canAttack(source, target))
            .doesNotThrowAnyException();
    }

    public static Stream<Arguments> provideAttackMoveWithColor() {
        return Stream.of(
            Arguments.of(C3, B4, WHITE),
            Arguments.of(C3, D4, WHITE),
            Arguments.of(G7, F6, BLACK),
            Arguments.of(G7, H6, BLACK)
        );
    }

    /*
    ........
    ......P.
    ......n.
    ........
    ........
    ..p.....
    ...N....
    ........
    */
    @DisplayName("전방 대각선 1칸 공격이 아니면 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("provideInvalidAttackMoveWithColor")
    void pawnCanNotAttackMove(Position source, Position target, Color color) {
        Piece piece = new Pawn(color);
        assertThatThrownBy(() -> piece.canAttack(source, target))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Pawn은 공격 시 전방 대각선 1칸 이동만 가능합니다.");
    }

    public static Stream<Arguments> provideInvalidAttackMoveWithColor() {
        return Stream.of(
            Arguments.of(C3, D2, WHITE),
            Arguments.of(G7, G6, BLACK)
        );
    }
}
