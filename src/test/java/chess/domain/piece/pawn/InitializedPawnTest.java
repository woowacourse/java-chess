package chess.domain.piece.pawn;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InitializedPawnTest {

    @ParameterizedTest
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        InitializedPawn initializedPawn = new InitializedPawn("testInitializedPawn", from, team);
        Board board = ChessBoard.initiaize();
        boolean hasHindrance = initializedPawn.hasHindrance(to, board);
        assertThat(hasHindrance).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(1,2), Position.of(1,3), Team.WHITE, false),
                Arguments.of(Position.of(1,2), Position.of(1,4), Team.WHITE, false),
                Arguments.of(Position.of(1,2), Position.of(2,3), Team.WHITE, false),
                Arguments.of(Position.of(1,5), Position.of(1,7), Team.WHITE, true),
                Arguments.of(Position.of(1,6), Position.of(1,7), Team.WHITE, true)
        );
    }
}