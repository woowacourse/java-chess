package chess.domain.pieces;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.pieces.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("말의 종류에 Pawn이 있다.")
    void piece_createWith_blackPawn() {
        Piece piece = new Piece(BLACK, new Pawn());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("말의 종류에 knight가 있다.")
    void piece_createWith_whiteKnight() {
        Piece piece = new Piece(WHITE, new Knight());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("말의 종류에 bishop이 있다.")
    void piece_createWith_blackBishop() {
        Piece piece = new Piece(BLACK, new Bishop());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("말의 종류에 rook이 있다.")
    void piece_createWith_whiteRook() {
        Piece piece = new Piece(WHITE, new Rook());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("말의 종류에 queen이 있다.")
    void piece_createWith_blackQueen() {
        Piece piece = new Piece(BLACK, new Queen());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("말의 종류에 king이 있다.")
    void piece_createWith_whiteKing() {
        Piece piece = new Piece(WHITE, new King());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @ParameterizedTest
    @MethodSource("blackPawnMovement")
    @DisplayName("폰이 블랙이면 아래로만 이동한다")
    void blackPawn_moveBelow(Position source, Position target, boolean result) {
        Piece piece = new Piece(BLACK, new Pawn());
        assertThat(piece.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> blackPawnMovement() {
        return Stream.of(
                Arguments.of(Position.of("a3"), Position.of("a2"), true),
                Arguments.of(Position.of("a2"), Position.of("a3"), false),
                Arguments.of(Position.of("a3"), Position.of("a1"), false)
        );
    }

    @ParameterizedTest
    @MethodSource("whitePawnMovement")
    @DisplayName("폰이 흰색이면 아래로만 이동한다")
    void whitePawn_moveAbove(Position source, Position target, boolean result) {
        Piece piece = new Piece(WHITE, new Pawn());
        assertThat(piece.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> whitePawnMovement() {
        return Stream.of(
                Arguments.of(Position.of("a2"), Position.of("a3"), true),
                Arguments.of(Position.of("a3"), Position.of("a2"), false),
                Arguments.of(Position.of("a4"), Position.of("a2"), false)
        );
    }

    @ParameterizedTest
    @MethodSource("pawnFirstMovement")
    @DisplayName("폰은 초기에만 두 칸 움직일 수 있다")
    void pawn_firstMove_allowTwoSteps() {
        Piece piece = new Piece(WHITE, new Pawn());
        assertThat(piece.isMovable(Position.of("a3"), Position.of("a5"))).isFalse();
    }

    private static Stream<Arguments> pawnFirstMovement() {
        return Stream.of(
                Arguments.of(WHITE, Position.of("a2"), Position.of("a4"), true),
                Arguments.of(WHITE, Position.of("a7"), Position.of("a5"), false),
                Arguments.of(BLACK, Position.of("a7"), Position.of("a5"), true),
                Arguments.of(BLACK, Position.of("a2"), Position.of("a4"), false)
        );
    }

    @ParameterizedTest
    @MethodSource("blackPawnDiagonalMovement")
    @DisplayName("폰이 블랙이면 대각 아래로만 이동한다")
    void blackPawn_moveDiagonalBelow(Position source, Position target, boolean result) {
        Piece piece = new Piece(BLACK, new Pawn());
        assertThat(piece.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> blackPawnDiagonalMovement() {
        return Stream.of(
                Arguments.of(Position.of("c3"), Position.of("d2"), true),
                Arguments.of(Position.of("c3"), Position.of("d4"), false),
                Arguments.of(Position.of("c3"), Position.of("b2"), true),
                Arguments.of(Position.of("c3"), Position.of("b4"), false)
        );
    }

    @ParameterizedTest
    @MethodSource("whitePawnDiagonalMovement")
    @DisplayName("폰이 흰색이면 대각 위로만 이동한다")
    void whitePawn_moveDiagonalAbove(Position source, Position target, boolean result) {
        Piece piece = new Piece(WHITE, new Pawn());
        assertThat(piece.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> whitePawnDiagonalMovement() {
        return Stream.of(
                Arguments.of(Position.of("c3"), Position.of("d4"), true),
                Arguments.of(Position.of("c3"), Position.of("d2"), false),
                Arguments.of(Position.of("c3"), Position.of("b4"), true),
                Arguments.of(Position.of("c3"), Position.of("b2"), false)
        );
    }
}
