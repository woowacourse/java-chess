package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static chess.domain.piece.ChessPiece.BLACK_ROOK;
import static chess.domain.piece.ChessPiece.WHITE_ROOK;
import static chess.domain.player.PlayerColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class GamePieceTest {

    @ParameterizedTest
    @DisplayName("GamePiece별 초기 위치")
    @MethodSource("createPositions")
    void getInitialPositions(GamePiece piece, List<Position> expected) {
        assertThat(piece.getOriginalPositions()).isEqualTo(expected);
    }

    static Stream<Arguments> createPositions() {
        return Stream.of(
                Arguments.of(BLACK_ROOK.getGamePiece(), Arrays.asList(Position.from("a8"), Position.from("h8"))),
                Arguments.of(WHITE_ROOK.getGamePiece(), Arrays.asList(Position.from("a1"), Position.from("h1")))
        );
    }

    @ParameterizedTest
    @DisplayName("gamepiece player가 white인지 확인")
    @MethodSource("createWhitePieces")
    void isWhite(GamePiece gamePiece, boolean expected) {
        assertThat(gamePiece.is(WHITE)).isEqualTo(expected);
    }

    static Stream<Arguments> createWhitePieces() {
        return Stream.of(
                Arguments.of(BLACK_ROOK.getGamePiece(), false),
                Arguments.of(WHITE_ROOK.getGamePiece(), true)
        );
    }
}