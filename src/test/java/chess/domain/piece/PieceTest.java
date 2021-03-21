package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    private static Stream<Arguments> getTeams() {
        return Stream.of(Arguments.of(TeamType.BLACK, TeamType.WHITE, false),
                Arguments.of(TeamType.WHITE, TeamType.WHITE, true));
    }

    @DisplayName("기물이 킹인지 확인한다.")
    @Test
    void isKing() {
        Piece king = new King(TeamType.WHITE);
        Piece notKing = new Pawn(TeamType.WHITE);

        assertThat(king.isKing()).isTrue();
        assertThat(notKing.isKing()).isFalse();
    }

    @DisplayName("기물이 폰인지 확인한다.")
    @Test
    void isPawn() {
        Piece pawn = new Pawn(TeamType.WHITE);
        Piece notPawn = new Rook(TeamType.WHITE);

        assertThat(pawn.isPawn()).isTrue();
        assertThat(notPawn.isPawn()).isFalse();
    }

    @DisplayName("기물이 주어진 팀과 같은 팀인지 확인한다.")
    @ParameterizedTest
    @MethodSource("getTeams")
    void isTeamOf(TeamType pieceTeam, TeamType comparingTeam, boolean expected) {
        Piece piece = new Rook(pieceTeam);

        boolean isSameTeam = piece.isTeamOf(comparingTeam);

        assertThat(isSameTeam).isEqualTo(expected);
    }
}
