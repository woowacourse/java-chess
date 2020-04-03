package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PawnTest {

    @ParameterizedTest
    @DisplayName("이동 경로 찾기")
    @MethodSource("createSourceToTarget")
    void findMovePath(Position target, List<Position> expected) {
        Position source = Position.from("d2");
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        GamePiece gamePiece = ChessPiece.WHITE_PAWN.getGamePiece();
        board.put(source, gamePiece);
        board.put(Position.from("c3"), ChessPiece.BLACK_KING.getGamePiece());
        board.put(Position.from("e3"), ChessPiece.BLACK_KING.getGamePiece());

        assertThatCode(() -> {
            gamePiece.canMoveTo(Board.from(board, Status.initialStatus()), source, target);
        }).doesNotThrowAnyException();

    }

    static Stream<Arguments> createSourceToTarget() {
        return Stream.of(
                Arguments.of(Position.from("d3"), Collections.emptyList()),
                Arguments.of(Position.from("d4"), Collections.singletonList(Position.from("d3"))),
                Arguments.of(Position.from("c3"), Collections.emptyList()),
                Arguments.of(Position.from("e3"), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @DisplayName("이동할 수 없는 source, target")
    @MethodSource("createInvalidTarget")
    void invalidMovementException(Position target) {
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        Position source = Position.from("d5");
        GamePiece piece = ChessPiece.WHITE_PAWN.getGamePiece();

        board.put(source, piece);

        assertThat(piece.canMoveTo(Board.from(board, Status.initialStatus()), source, target)).isFalse();
    }

    static Stream<Arguments> createInvalidTarget() {
        return Stream.of(
                Arguments.of(Position.from("c6")),
                Arguments.of(Position.from("c5")),
                Arguments.of(Position.from("c4")),
                Arguments.of(Position.from("d4")),
                Arguments.of(Position.from("e4")),
                Arguments.of(Position.from("e5")),
                Arguments.of(Position.from("e6"))
        );
    }

    @ParameterizedTest
    @DisplayName("기물 플레이어 색에 따른 이동")
    @MethodSource("createPawnAndTarget")
    void blackMove(GamePiece gamePiece, Position source, Position target) {
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        board.put(source, gamePiece);

        assertThatCode(() -> gamePiece.canMoveTo(Board.from(board, Status.initialStatus()), source, target))
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> createPawnAndTarget() {
        return Stream.of(
                Arguments.of(ChessPiece.WHITE_PAWN.getGamePiece(), Position.from("d5"), Position.from("d6")),
                Arguments.of(ChessPiece.WHITE_PAWN.getGamePiece(), Position.from("e2"), Position.from("e3")),
                Arguments.of(ChessPiece.BLACK_PAWN.getGamePiece(), Position.from("e7"), Position.from("e6")),
                Arguments.of(ChessPiece.BLACK_PAWN.getGamePiece(), Position.from("d7"), Position.from("d6")),
                Arguments.of(ChessPiece.BLACK_PAWN.getGamePiece(), Position.from("e4"), Position.from("e3"))
        );
    }

    @Test
    @DisplayName("original 위치가 아닌 곳에서 2칸 이동할 경우 예외 처리")
    void moveThrowException() {
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        GamePiece gamePiece = ChessPiece.BLACK_PAWN.getGamePiece();
        Position source = Position.from("d5");
        board.put(source, gamePiece);

        assertThat(gamePiece.canMoveTo(Board.from(board, Status.initialStatus()), source, Position.from("d3"))).isFalse();

    }
}