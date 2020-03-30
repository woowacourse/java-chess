package chess.domain.piece.queen;

import chess.domain.UserInterface;
import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import chess.ui.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    private UserInterface userInterface = new Console();

    @ParameterizedTest
    @DisplayName("#move() : should return Bishop as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveSucceed"})
    void moveSucceed(Position from, Position to, Team team, Piece expected) {
        //todo: check convention
        Queen queen = (Queen) PieceFactory.createPiece(Queen.class, from, team);

        Board board = ChessBoard.initiaize(userInterface);
        Piece moved = queen.move(to, board);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#move() : should throw IllegalArgumentException as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMoveFail"})
    void moveFail(Position from, Position to, Team team) {
        //todo: check convention, 타입캐스팅 해도 될 지
        Queen queen = (Queen) PieceFactory.createPiece(Queen.class, from, team);

        Board board = ChessBoard.initiaize(userInterface);

        assertThatThrownBy(() -> queen.move(to, board))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getCasesForMoveSucceed() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(4, 2),
                        Position.of(4, 6),
                        team,
                        PieceFactory.createPiece(Queen.class, Position.of(4, 6), team)),
                Arguments.of(Position.of(4, 2),
                        Position.of(1, 5),
                        team,
                        PieceFactory.createPiece(Queen.class, Position.of(1, 5), team)),
                Arguments.of(Position.of(4, 2),
                        Position.of(8, 6),
                        team,
                        PieceFactory.createPiece(Queen.class, Position.of(8, 6), team)),
                Arguments.of(Position.of(4, 6),
                        Position.of(4, 3),
                        team,
                        PieceFactory.createPiece(Queen.class, Position.of(4, 3), team))
        );
    }

    private static Stream<Arguments> getCasesForMoveFail() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(4, 1), Position.of(4, 1), team),
                Arguments.of(Position.of(4, 1), Position.of(4, 3), team),
                Arguments.of(Position.of(4, 1), Position.of(4, 2), team),
                Arguments.of(Position.of(4, 2), Position.of(6, 3), team)

        );
    }
}