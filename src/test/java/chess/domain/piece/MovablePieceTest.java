package chess.domain.piece;

import chess.domain.side.Color;
import chess.domain.side.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    static Stream<Arguments> pieceDummy() {
        return Stream.of(
                Arguments.arguments(Role.PAWN, Pawn.class),
                Arguments.arguments(Role.ROOK, Rook.class),
                Arguments.arguments(Role.KNIGHT, Knight.class),
                Arguments.arguments(Role.BISHOP, Bishop.class),
                Arguments.arguments(Role.QUEEN, Queen.class),
                Arguments.arguments(Role.KING, King.class)
        );
    }

    @ParameterizedTest
    @MethodSource("pieceDummy")
    @DisplayName("역할에 따른 기물이 생성된다.")
    void create(Role role, Class<Piece> expectedPieceType) {
        //given
        Side side = Side.from(Color.BLACK);
        //when
        Piece piece = role.create(side);
        //expected
        assertThat(piece).isInstanceOf(expectedPieceType);
    }

    @Test
    @DisplayName("타겟 기물이 같은 편인지 확인한다.")
    void isSameSide() {
        Side sourceSide = Side.from(Color.WHITE);
        Side sameSide = Side.from(Color.WHITE);
        Side opponenetSide = Side.from(Color.BLACK);
        Piece sourcePiece = new Pawn(sourceSide, Role.PAWN);
        Piece targetPiece = new King(sameSide, Role.KING);
        Piece opponentPiece = new Queen(opponenetSide, Role.QUEEN);

        assertThat(sourcePiece.isOpponentSide(targetPiece)).isFalse();
        assertThat(sourcePiece.isOpponentSide(opponentPiece)).isTrue();
    }
}
