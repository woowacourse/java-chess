package chess.domain.piece.knight;

import chess.domain.UserInterface;
import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import chess.ui.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {
    private UserInterface userInterface = new Console();

    @ParameterizedTest
    @DisplayName("#move() : should return Bishop as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Position from, Position to, Team team, Piece expected) {
        //todo: check convention
        Knight knight = (Knight) PieceFactory.createPiece(Knight.class, from, team);

        Board board = ChessBoard.initiaize(userInterface);
        Piece moved = knight.move(to, board);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Position from, Position to, Team team) {
        //todo: check convention, 타입캐스팅 해도 될 지
        Knight knight = (Knight) PieceFactory.createPiece(Knight.class, from, team);

        Board board = ChessBoard.initiaize(userInterface);

        assertThatThrownBy(() -> knight.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(4, 4),
                        Position.of(5, 6),
                        team,
                        PieceFactory.createPiece(Knight.class, Position.of(5, 6), team)),
                Arguments.of(Position.of(4, 4),
                        Position.of(6, 5),
                        team,
                        PieceFactory.createPiece(Knight.class, Position.of(6, 5), team)),
                Arguments.of(Position.of(4, 4),
                        Position.of(6, 3),
                        team,
                        PieceFactory.createPiece(Knight.class, Position.of(6, 3), team)),
                Arguments.of(Position.of(4, 4),
                        Position.of(5, 2),
                        team,
                        PieceFactory.createPiece(Knight.class, Position.of(5, 2), team)),
                Arguments.of(Position.of(4, 4),
                        Position.of(3, 2),
                        team,
                        PieceFactory.createPiece(Knight.class, Position.of(3, 2), team)),
                Arguments.of(Position.of(4, 4),
                        Position.of(2, 3),
                        team,
                        PieceFactory.createPiece(Knight.class, Position.of(2, 3), team)),
                Arguments.of(Position.of(4, 4),
                        Position.of(2, 5),
                        team,
                        PieceFactory.createPiece(Knight.class, Position.of(2, 5), team)),

                Arguments.of(Position.of(4, 4),
                        Position.of(3, 6),
                        team,
                        PieceFactory.createPiece(Knight.class, Position.of(3, 6), team))
        );
    }

    private static Stream<Arguments> getCasesForMoveFail() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(4, 4), Position.of(4, 4), team),
                Arguments.of(Position.of(4, 4), Position.of(5, 7), team),
                Arguments.of(Position.of(4, 4), Position.of(4, 5), team),
                Arguments.of(Position.of(4, 4), Position.of(5, 4), team),
                Arguments.of(Position.of(4, 4), Position.of(5, 5), team)

        );
    }
}