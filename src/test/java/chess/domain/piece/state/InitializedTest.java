package chess.domain.piece.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class InitializedTest {

    @ParameterizedTest
    @DisplayName("#isHeadingBackward : return boolean as to current position and to")
    @MethodSource({"getCasesForIsHeadingBackward"})
    void isHeadingBackward(Position from, Position to, Team team, boolean expected) {
        Initialized initialized = new Initialized("testInitiaiizedPiece", from, team) {
            @Override
            protected boolean canNotMove(Position to, Board board) {
                return false;
            }

            @Override
            public Piece move(Position to, Board board) {
                return null;
            }
        };

        boolean isHeadingBackward = initialized.isHeadingBackward(to);
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