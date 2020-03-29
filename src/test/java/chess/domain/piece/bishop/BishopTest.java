package chess.domain.piece.bishop;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @ParameterizedTest
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        Bishop bishop = (Bishop) PieceFactory.createPiece(Bishop.class, from, team);
        Board board = ChessBoard.initiaize();
        boolean hasHindrance = bishop.hasHindrance(to, board);
        assertThat(hasHindrance).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForHasHindrance() {
        return Stream.of(
                Arguments.of(Position.of(3, 2), Position.of(4, 4), Team.WHITE, true),
                Arguments.of(Position.of(3, 1), Position.of(5, 3), Team.WHITE, true),
                Arguments.of(Position.of(1, 3), Position.of(3, 1), Team.WHITE, true),
                Arguments.of(Position.of(5, 3), Position.of(3, 1), Team.WHITE, true),
                Arguments.of(Position.of(3, 1), Position.of(1, 3), Team.WHITE, true),
                Arguments.of(Position.of(2, 2), Position.of(6, 6), Team.WHITE, false)
        );
    }
}