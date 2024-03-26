package model.piece;

import static model.Fixtures.G7;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidMovingException;
import java.util.stream.Stream;
import model.Camp;
import model.position.Moving;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTest {

    @ParameterizedTest
    @DisplayName("위치가 동일하다면 예외가 발생한다.")
    @MethodSource("invalidMovingParameterProvider")
    void invalidMoving(Piece piece) {
        final Moving notMovedMoving = new Moving(G7, G7);
        assertThatThrownBy(() -> piece.getMoveRoute(notMovedMoving))
                .isInstanceOf(InvalidMovingException.class);
    }

    static Stream<Arguments> invalidMovingParameterProvider() {
        return Stream.of(
                Arguments.of(new Bishop(Camp.BLACK)),
                Arguments.of(new King(Camp.BLACK)),
                Arguments.of(new Knight(Camp.WHITE)),
                Arguments.of(new WhitePawn()),
                Arguments.of(new Queen(Camp.WHITE)),
                Arguments.of(new Rook(Camp.WHITE))
        );
    }
}
