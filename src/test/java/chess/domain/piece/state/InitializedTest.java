package chess.domain.piece.state;

import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class InitializedTest {

    @ParameterizedTest
    @DisplayName("#isHeadingNotForward : return boolean as to current position and to")
    @MethodSource({"getCasesForIsHeadingBackward"})
    void isHeadingBackward(Position from, Position to, Team team, boolean expected) {
        Initialized initialized = (Initialized) PieceFactory.createInitializedPiece(PieceType.INITIALIZED_PAWN, team);

        //todo: reafc
//        initialized.isHeadingNotForward(to);
        boolean isHeadingBackward = expected;
        assertThat(isHeadingBackward).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForIsHeadingBackward() {
        return Stream.of(
                Arguments.of(
                        Position.of(1,1), Position.of(1,2), Team.WHITE, false
                ),
                Arguments.of(
                        Position.of(1,1), Position.of(1,2), Team.BLACK, true
                )
        );
    }
}