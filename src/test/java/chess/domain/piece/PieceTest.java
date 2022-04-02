package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Color;
import chess.domain.piece.role.Bishop;
import chess.domain.piece.role.King;
import chess.domain.piece.role.Knight;
import chess.domain.piece.role.Pawn;
import chess.domain.piece.role.Queen;
import chess.domain.piece.role.Rook;
import chess.domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTest {

    @Test
    @DisplayName("말의 종류에 Pawn이 있다.")
    void piece_createWith_blackPawn() {
        final var piece = new Piece(Color.BLACK, new Pawn());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("말의 종류에 knight가 있다.")
    void piece_createWith_whiteKnight() {
        final var piece = new Piece(Color.WHITE, new Knight());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("말의 종류에 bishop이 있다.")
    void piece_createWith_blackBishop() {
        final var piece = new Piece(Color.BLACK, new Bishop());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("말의 종류에 rook이 있다.")
    void piece_createWith_whiteRook() {
        final var piece = new Piece(Color.WHITE, new Rook());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("말의 종류에 queen이 있다.")
    void piece_createWith_blackQueen() {
        final var piece = new Piece(Color.BLACK, new Queen());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @Test
    @DisplayName("말의 종류에 king이 있다.")
    void piece_createWith_whiteKing() {
        final var piece = new Piece(Color.WHITE, new King());
        assertThat(piece).isInstanceOf(Piece.class);
    }

    @ParameterizedTest
    @MethodSource("blackPawnRightDirection")
    @DisplayName("검은 폰은 수직, 대각 아래 방향으로만 이동한다")
    void blackPawn_moveBelow(Position source, Position target) {
        Piece piece = new Piece(Color.BLACK, new Pawn());
        assertThatCode(() -> piece.checkMovable(source, target))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> blackPawnRightDirection() {
        return Stream.of(
                Arguments.of(Position.of("a3"), Position.of("a2")),
                Arguments.of(Position.of("h7"), Position.of("h5")),
                Arguments.of(Position.of("d6"), Position.of("c5")),
                Arguments.of(Position.of("d6"), Position.of("e5"))
        );
    }

    @ParameterizedTest
    @MethodSource("blackPawnWrongDirection")
    @DisplayName("검은 폰이 수직, 대각 위 방향 이동 시, 예외를 발생한다")
    void blackPawn_moveAbove_throwException(Position source, Position target) {
        Piece piece = new Piece(Color.BLACK, new Pawn());
        assertThatThrownBy(() -> piece.checkMovable(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰");
    }

    private static Stream<Arguments> blackPawnWrongDirection() {
        return Stream.of(
                Arguments.of(Position.of("a2"), Position.of("a3")),
                Arguments.of(Position.of("a2"), Position.of("a4")),
                Arguments.of(Position.of("d4"), Position.of("c5")),
                Arguments.of(Position.of("d4"), Position.of("f5"))
        );
    }

    @ParameterizedTest
    @MethodSource("whitePawnRightDirection")
    @DisplayName("흰 폰은 수직, 대각 아래 윗방향으로만 이동한다")
    void whitePawn_moveAbove(Position source, Position target) {
        Piece piece = new Piece(Color.WHITE, new Pawn());
        assertThatCode(() -> piece.checkMovable(source, target))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> whitePawnRightDirection() {
        return Stream.of(
                Arguments.of(Position.of("h2"), Position.of("h4")),
                Arguments.of(Position.of("e5"), Position.of("e6")),
                Arguments.of(Position.of("c3"), Position.of("d4")),
                Arguments.of(Position.of("c3"), Position.of("b4"))
        );
    }

    @ParameterizedTest
    @MethodSource("whitePawnWrongDirection")
    @DisplayName("흰 폰이 수직, 대각 아래 방향 이동 시, 예외를 발생한다")
    void whitePawn_moveBelow_throwException(Position source, Position target) {
        Piece piece = new Piece(Color.WHITE, new Pawn());
        assertThatThrownBy(() -> piece.checkMovable(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰");
    }

    private static Stream<Arguments> whitePawnWrongDirection() {
        return Stream.of(
                Arguments.of(Position.of("a6"), Position.of("a5")),
                Arguments.of(Position.of("f3"), Position.of("f2")),
                Arguments.of(Position.of("d4"), Position.of("c3")),
                Arguments.of(Position.of("d4"), Position.of("e3"))
        );
    }
}
