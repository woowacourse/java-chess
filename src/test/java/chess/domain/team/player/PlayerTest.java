package chess.domain.team.player;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.team.Team.BLACK;
import static chess.domain.team.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    @Test
    @DisplayName("플레이어를 생성할 때, 문제없이 생성하는지 확인하다")
    void ofPlayer_NoException() {
        assertThatNoException().isThrownBy(() -> Player.of(WHITE, new WhitePieceGenerator()));
    }

    @ParameterizedTest
    @MethodSource("createWhiteGenerator")
    @DisplayName("흰팀의 플레이어가 모든 흰색 체스 기물을 가지고 있는지 확인한다")
    void containPiece_whitePiece(Piece piece) {
        // given
        final Player player = Player.of(WHITE, new WhitePieceGenerator());

        // when
        final boolean actual = player.containPiece(piece);

        // then
        assertTrue(actual);
    }

    private static Stream<Arguments> createWhiteGenerator() {
        return Stream.of(
                Arguments.of(new King(WHITE)),
                Arguments.of(new Queen(WHITE)),
                Arguments.of(new Bishop(WHITE)),
                Arguments.of(new Knight(WHITE)),
                Arguments.of(new Rook(WHITE)),
                Arguments.of(new Pawn(WHITE))
        );
    }

    @ParameterizedTest
    @MethodSource("createBlackGenerator")
    @DisplayName("검은팀의 플레이어가 모든 검은색 체스 기물을 가지고 있는지 확인한다")
    void containPiece_blackPiece(Piece piece) {
        // given
        final Player player = Player.of(BLACK, new BlackPieceGenerator());

        // when
        final boolean actual = player.containPiece(piece);

        // then
        assertTrue(actual);
    }

    private static Stream<Arguments> createBlackGenerator() {
        return Stream.of(
                Arguments.of(new King(BLACK)),
                Arguments.of(new Queen(BLACK)),
                Arguments.of(new Bishop(BLACK)),
                Arguments.of(new Knight(BLACK)),
                Arguments.of(new Rook(BLACK)),
                Arguments.of(new Pawn(BLACK))
        );
    }
}
