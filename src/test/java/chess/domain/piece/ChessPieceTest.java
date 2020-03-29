package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ChessPieceTest {

    @Test
    @DisplayName("플레이어별 game piece 생성")
    void list() {
        assertThat(ChessPiece.list()).hasSize(12);
    }

    @ParameterizedTest
    @DisplayName("gamepiece가 pawn인지 확인")
    @MethodSource("createPieces")
    void isPawn(GamePiece gamePiece, boolean expected) {
        assertThat(ChessPiece.isPawn(gamePiece)).isEqualTo(expected);
    }

    static Stream<Arguments> createPieces() {
        return Stream.of(
                Arguments.of(ChessPiece.BLACK_ROOK.getGamePiece(), false),
                Arguments.of(ChessPiece.WHITE_PAWN.getGamePiece(), true)
        );
    }

    @ParameterizedTest
    @DisplayName("gamepiece가 king인지 확인")
    @MethodSource("createKingPieces")
    void isKing(GamePiece gamePiece, boolean expected) {
        assertThat(ChessPiece.isKing(gamePiece)).isEqualTo(expected);
    }

    static Stream<Arguments> createKingPieces() {
        return Stream.of(
                Arguments.of(ChessPiece.BLACK_KING.getGamePiece(), true),
                Arguments.of(ChessPiece.WHITE_PAWN.getGamePiece(), false)
        );
    }
}