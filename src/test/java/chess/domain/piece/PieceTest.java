package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    private static Stream<Arguments> makePosition() {
        return Stream.of(
                Arguments.of(Position.of(1, 1), Position.of(7, 7))
                , Arguments.of(Position.of(1, 1), Position.of(7, 1))
                , Arguments.of(Position.of(1, 1), Position.of(2, 1))
                , Arguments.of(Position.of(1, 1), Position.of(0, 0))
                , Arguments.of(Position.of(1, 1), Position.of(1, 0))
        );
    }

    @ParameterizedTest(name = "체스말이 입력받은 체스말과 동일한 진영인지 판단한다.")
    @CsvSource(value = {"WHITE:true", "BLACK:false"}, delimiter = ':')
    void isSameTeamColor(final TeamColor teamColor, final boolean expected) {
        // given
        final Piece piece = new Knight(PieceType.KNIGHT, TeamColor.WHITE);

        // when, then
        assertThat(piece.isSameTeam(teamColor))
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "특정 체스말이 시작 위치에서 타겟 위치로 이동 가능한지 판단한다.")
    @MethodSource(value = "makePosition")
    void canMove(final Position source, final Position target) {
        // given
        final Piece piece = new Queen(PieceType.QUEEN, TeamColor.WHITE);

        // when, then
        assertThat(piece.canMove(source, target, null))
                .isTrue();
    }
}
