package chess.domain.pieces;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.pieces.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTest {

    @ParameterizedTest
    @MethodSource("createPieces")
    @DisplayName("말의 종류에 Pawn이 있다.")
    void piece_createWith_blackPawn(Piece piece) {
        assertThat(piece).isInstanceOf(Piece.class);
    }

    private static Stream<Piece> createPieces() {
        return Stream.of(
                new Piece(BLACK, new Pawn()),
                new Piece(WHITE, new Knight()),
                new Piece(BLACK, new Bishop()),
                new Piece(WHITE, new Rook()),
                new Piece(BLACK, new Queen()),
                new Piece(WHITE, new King())
        );
    }

    @ParameterizedTest
    @MethodSource("blackPawnMovement")
    @DisplayName("폰이 블랙이면 아래로만 이동한다")
    void blackPawn_moveBelow(Position source, Position target) {
        Piece piece = new Piece(BLACK, new Pawn());
        assertThatThrownBy(() -> piece.validateMovement(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> blackPawnMovement() {
        return Stream.of(
                Arguments.of(Position.of("a2"), Position.of("a3")),
                Arguments.of(Position.of("a3"), Position.of("a1"))
        );
    }

    @ParameterizedTest
    @MethodSource("whitePawnMovement")
    @DisplayName("폰이 흰색이면 아래로만 이동한다")
    void whitePawn_moveAbove(Position source, Position target) {
        Piece piece = new Piece(WHITE, new Pawn());
        assertThatThrownBy(() -> piece.validateMovement(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> whitePawnMovement() {
        return Stream.of(
                Arguments.of(Position.of("a3"), Position.of("a2")),
                Arguments.of(Position.of("a4"), Position.of("a2"))
        );
    }

    @ParameterizedTest
    @MethodSource("pawnFirstMovement")
    @DisplayName("폰은 초기에만 두 칸 움직일 수 있다")
    void pawn_firstMove_allowTwoSteps(Color color, Position source, Position target) {
        Piece piece = new Piece(color, new Pawn());
        assertThatThrownBy(() -> piece.validateMovement(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> pawnFirstMovement() {
        return Stream.of(
                Arguments.of(WHITE, Position.of("a7"), Position.of("a5")),
                Arguments.of(BLACK, Position.of("a2"), Position.of("a4"))
        );
    }

    @ParameterizedTest
    @MethodSource("blackPawnDiagonalMovement")
    @DisplayName("폰이 블랙이면 대각 아래로만 이동한다")
    void blackPawn_moveDiagonalBelow(Position source, Position target) {
        Piece piece = new Piece(BLACK, new Pawn());
        assertThatThrownBy(() -> piece.validateMovement(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> blackPawnDiagonalMovement() {
        return Stream.of(
                Arguments.of(Position.of("c3"), Position.of("d4")),
                Arguments.of(Position.of("c3"), Position.of("b4"))
        );
    }

    @ParameterizedTest
    @MethodSource("whitePawnDiagonalMovement")
    @DisplayName("폰이 흰색이면 대각 위로만 이동한다")
    void whitePawn_moveDiagonalAbove(Position source, Position target) {
        Piece piece = new Piece(WHITE, new Pawn());
        assertThatThrownBy(() -> piece.validateMovement(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> whitePawnDiagonalMovement() {
        return Stream.of(
                Arguments.of(Position.of("c3"), Position.of("d2")),
                Arguments.of(Position.of("c3"), Position.of("b2"))
        );
    }
}
