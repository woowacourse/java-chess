package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;
import chess.domain.player.User;

class PawnTest {

    private GamePiece gamePiece;

    @BeforeEach
    void setUp() {
        gamePiece = new Pawn(PlayerColor.WHITE);
    }

    @ParameterizedTest
    @DisplayName("이동 경로 찾기")
    @MethodSource("createSourceToTarget")
    void findMovePath(Position target, List<Position> expected) {
        Position source = Position.from("d2");
        Map<Position, GamePiece> boardMap = new TreeMap<>(
                BoardFactory.createEmptyBoard(User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER).getBoard());

        boardMap.put(source, gamePiece);
        boardMap.put(Position.from("c3"), new King(PlayerColor.BLACK));
        boardMap.put(Position.from("e3"), new King(PlayerColor.BLACK));

        Board board = BoardFactory.of(boardMap, 0, User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER);

        assertThatCode(() -> {
            gamePiece.validateMoveTo(board, source, target);
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
        Map<Position, GamePiece> boardMap = new TreeMap<>(
                BoardFactory.createEmptyBoard(User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER).getBoard());
        Position source = Position.from("d5");

        boardMap.put(source, gamePiece);

        Board board = BoardFactory.of(boardMap, 0, User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER);

        assertThatThrownBy(() -> {
            gamePiece.validateMoveTo(board, source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n이동할 수 없는 경로입니다.");
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
    void blackMove(GamePiece piece, Position source, Position target) {
        Map<Position, GamePiece> boardMap = new TreeMap<>(
                BoardFactory.createEmptyBoard(User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER).getBoard());
        boardMap.put(source, piece);

        Board board = BoardFactory.of(boardMap, 0, User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER);

        assertThatCode(() -> piece.validateMoveTo(board, source, target))
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> createPawnAndTarget() {
        return Stream.of(
                Arguments.of(new Pawn(PlayerColor.WHITE), Position.from("d5"), Position.from("d6")),
                Arguments.of(new Pawn(PlayerColor.WHITE), Position.from("e2"), Position.from("e3")),
                Arguments.of(new Pawn(PlayerColor.BLACK), Position.from("e7"), Position.from("e6")),
                Arguments.of(new Pawn(PlayerColor.BLACK), Position.from("d7"), Position.from("d6")),
                Arguments.of(new Pawn(PlayerColor.BLACK), Position.from("e4"), Position.from("e3"))
        );
    }

    @Test
    @DisplayName("original 위치가 아닌 곳에서 2칸 이동할 경우 예외 처리")
    void moveThrowException() {
        Map<Position, GamePiece> boardMap = new TreeMap<>(
                BoardFactory.createEmptyBoard(User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER).getBoard());
        GamePiece piece = new Pawn(PlayerColor.BLACK);
        Position source = Position.from("d5");
        boardMap.put(source, piece);

        Board board = BoardFactory.of(boardMap, 0, User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER);

        assertThatThrownBy(() -> piece.validateMoveTo(board, source, Position.from("d3")))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n이동할 수 없는 경로입니다.");
    }
}