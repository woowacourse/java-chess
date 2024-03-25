package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.chessboard.attribute.Square;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Position;

class ChessboardTest {

    static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(Position.from("a2"), Position.from("a3"), Pawn.class),
                Arguments.of(Position.from("e2"), Position.from("e4"), Pawn.class),
                Arguments.of(Position.from("b1"), Position.from("c3"), Knight.class),
                Arguments.of(Position.from("g8"), Position.from("f6"), Knight.class)
        );
    }

    @DisplayName("기물을 이동한다.")
    @MethodSource
    @ParameterizedTest
    <P extends Piece> void move(Position source, Position target, Class<P> pieceClass) {
        Chessboard sut = ChessboardFactory.create();
        sut.move(source, target);
        Square square = sut.squareIn(target);
        Piece piece = square.piece();
        assertAll(
                () -> assertThat(piece).isInstanceOf(pieceClass),
                () -> assertThat(piece.position()).isEqualTo(target)
        );
    }
}
