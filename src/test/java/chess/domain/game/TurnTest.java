package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TurnTest {

    @ParameterizedTest(name = "{displayName}")
    @MethodSource("generatePieceAndExpect")
    @DisplayName("White 팀 턴일 때 white팀의 말이 움직이려고 하면 true를 black팀의 말을 움직이려고 하면 false를 반환한다.")
    void c(Piece piece, boolean expect) {
        // given
        Turn whiteTeamTurn = Turn.WHITE_TEAM_TURN;

        // when
        boolean result = whiteTeamTurn.isCorrectTurn(piece);

        // then
        assertThat(result).isEqualTo(expect);
    }

    private static Stream<Arguments> generatePieceAndExpect() {
        return Stream.of(
            Arguments.of(new Bishop(Team.WHITE), true),
            Arguments.of(new King(Team.BLACK), false)
        );
    }
}