package chess.domain.piece.bishop;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    @ParameterizedTest
    @DisplayName("#move() : should return Bishop as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Position from, Position to, Team team, Piece expected) {
        //todo: check convention
        Bishop bishop = (Bishop) PieceFactory.createPiece(Bishop.class, from, team);

        Board board = ChessBoard.initiaize();
        Piece moved = bishop.move(to, board);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Position from, Position to, Team team) {
        //todo: check convention, 타입캐스팅 해도 될 지
        Bishop bishop = (Bishop) PieceFactory.createPiece(Bishop.class, from, team);

        Board board = ChessBoard.initiaize();

        assertThatThrownBy(() -> bishop.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
    @MethodSource({"getCasesForHasHindrance"})
    void hasHindrance(Position from, Position to, Team team, boolean expected) {
        Bishop bishop = (Bishop) PieceFactory.createPiece(Bishop.class, from, team);
        Board board = ChessBoard.initiaize();
        boolean hasHindrance = bishop.hasHindrance(to, board);
        assertThat(hasHindrance).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(2, 2),
                        Position.of(6, 6),
                        team,
                        PieceFactory.createPiece(Bishop.class, Position.of(6, 6), team)),
                Arguments.of(Position.of(2, 2),
                        Position.of(7, 7),
                        team,
                        PieceFactory.createPiece(Bishop.class, Position.of(7, 7), team)),
                Arguments.of(Position.of(2, 2),
                        Position.of(1, 3),
                        team,
                        PieceFactory.createPiece(Bishop.class, Position.of(1, 3), team))
        );
    }

    private static Stream<Arguments> getCasesForMoveFail() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(3, 1), Position.of(3, 1), team),
                Arguments.of(Position.of(3, 1), Position.of(1, 3), team),
                Arguments.of(Position.of(3, 1), Position.of(2, 2), team),
                Arguments.of(Position.of(2, 2), Position.of(3, 4), team)

        );
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