package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class QueenTest {

    @ParameterizedTest
    @DisplayName("이동 경로 찾기")
    @MethodSource("createSourceToTarget")
    void findMovePath(Position source, Position target, List<Position> expected) {
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        GamePiece gamePiece = ChessPiece.WHITE_QUEEN.getGamePiece();
        board.put(source, gamePiece);

        assertThatCode(() -> {
            gamePiece.canMoveTo(Board.from(board, Status.initialStatus()), source, target);
        }).doesNotThrowAnyException();
    }

    static Stream<Arguments> createSourceToTarget() {
        return Stream.of(
                Arguments.of(Position.from("a1"), Position.from("d4"),
                        Arrays.asList(Position.from("b2"), Position.from("c3"))),
                Arguments.of(Position.from("d4"), Position.from("a1"),
                        Arrays.asList(Position.from("c3"), Position.from("b2"))),
                Arguments.of(Position.from("b2"), Position.from("f6"),
                        Arrays.asList(Position.from("c3"), Position.from("d4"), Position.from("e5"))),
                Arguments.of(Position.from("f6"), Position.from("b2"),
                        Arrays.asList(Position.from("e5"), Position.from("d4"), Position.from("c3"))),
                Arguments.of(Position.from("g5"), Position.from("f4"), Collections.emptyList()),
                Arguments.of(Position.from("a1"), Position.from("a4"),
                        Arrays.asList(Position.from("a2"), Position.from("a3"))),
                Arguments.of(Position.from("a4"), Position.from("a1"),
                        Arrays.asList(Position.from("a3"), Position.from("a2"))),
                Arguments.of(Position.from("b2"), Position.from("f2"),
                        Arrays.asList(Position.from("c2"), Position.from("d2"), Position.from("e2"))),
                Arguments.of(Position.from("f2"), Position.from("b2"),
                        Arrays.asList(Position.from("e2"), Position.from("d2"), Position.from("c2"))),
                Arguments.of(Position.from("g5"), Position.from("g4"), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @DisplayName("이동할 수 없는 source, target")
    @MethodSource("createInvalidTarget")
    void invalidMovementException(Position target) {
        Position source = Position.from("d5");
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        GamePiece gamePiece = ChessPiece.WHITE_QUEEN.getGamePiece();
        board.put(source, gamePiece);

        assertThat(gamePiece.canMoveTo(Board.from(board, Status.initialStatus()), source, target)).isFalse();
    }

    static Stream<Arguments> createInvalidTarget() {
        return Stream.of(
                Arguments.of(Position.from("e7")),
                Arguments.of(Position.from("f6")),
                Arguments.of(Position.from("f4")),
                Arguments.of(Position.from("e3")),
                Arguments.of(Position.from("c3")),
                Arguments.of(Position.from("b4")),
                Arguments.of(Position.from("b6")),
                Arguments.of(Position.from("c7"))
        );
    }


    @Test
    @DisplayName("장애물이 있을 경우")
    void obstacle() {
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        Position source = Position.from("d5");
        Position target = Position.from("f7");
        GamePiece piece = ChessPiece.WHITE_QUEEN.getGamePiece();

        Position obstacle = Position.from("e6");

        board.put(source, piece);
        board.put(obstacle, ChessPiece.BLACK_PAWN.getGamePiece());

        assertThat(piece.canMoveTo(Board.from(board, Status.initialStatus()), source, target)).isFalse();

    }
}