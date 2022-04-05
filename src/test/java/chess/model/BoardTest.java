package chess.model;

import static chess.model.Board.*;
import static chess.model.File.*;
import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.model.boardinitializer.defaultInitializer;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;

class BoardTest {

    private static Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new defaultInitializer());
    }

    @Test
    @DisplayName("체스 보드 생성 테스트 : 개수")
    void init_count() {
        //given
        Map<Position, Piece> piecesByPositions = board.getValues();

        //when
        int actual = piecesByPositions.keySet().size();

        //then
        assertThat(actual).isEqualTo(32);
    }

    @Test
    @DisplayName("체스 보드 생성 테스트 : 폰이 있는 행")
    void init_pawns_only() {
        //given
        Map<Position, Piece> piecesByPositions = board.getValues();

        // when & then
        List<Piece> expected = Arrays.stream(File.values())
            .map(value -> Pawn.colorOf(PieceColor.WHITE))
            .collect(Collectors.toList());

        List<Piece> actual = Arrays.stream(File.values())
            .map(file -> piecesByPositions.get(Position.of(file, Rank.TWO)))
            .collect(Collectors.toList());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스 보드 생성 테스트 : 폰이 있는 행")
    void init_Except_Pawn() {
        //given
        Map<Position, Piece> piecesByPositions = board.getValues();

        //when
        List<Piece> expected = Arrays.stream(File.values())
            .map(value -> Pawn.colorOf(PieceColor.WHITE))
            .collect(Collectors.toList());

        List<Piece> actual = Arrays.stream(File.values())
            .map(file -> piecesByPositions.get(Position.of(file, Rank.TWO)))
            .collect(Collectors.toList());

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스 말이 없는 곳에서 이동 시키면 예외를 던진다.")
    void move_exception() {
        assertThatThrownBy(() -> board.move(Position.of(A, Rank.THREE), Position.of(B, Rank.THREE)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_SOURCE_PIECE_EMPTY);
    }

    @Test
    @DisplayName("체스 말이 입력한 target으로 정상 이동했는지 확인한다.")
    void move_test() {
        //when
        board.move(Position.of(A, Rank.TWO), Position.of(A, Rank.THREE));
        Map<Position, Piece> piecesByPositions = board.getValues();

        //then
        assertThat(piecesByPositions.get(Position.of(A, Rank.THREE))).isEqualTo(Pawn.colorOf(PieceColor.WHITE));
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE:A", "THREE:C"}, delimiter = ':')
    @DisplayName("퀸은 경로에 다른 기물 있으면 이동할 수 없다")
    void isBlocked(Rank rank, File file) {
        assertThatThrownBy(() ->
            board.move(Position.of(File.C, ONE), Position.of(file, rank))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"THREE:C", "THREE:A"}, delimiter = ':')
    @DisplayName("나이트는 경로에 다른 기물 있으면 이동할 수 있다")
    void isNonBlocked(Rank rank, File file) {
        assertDoesNotThrow(() ->
            board.move(Position.of(B, ONE), Position.of(file, rank))
        );
    }

    @DisplayName("기물이 다른 기물의 이동경로를 막고 있다면 이동이 불가하다")
    @Test
    void isBlockedAfterNightMoved() {
        board.move(Position.of(B, ONE), Position.of(C, THREE));
        assertThatThrownBy(() ->
            board.move(Position.of(C, TWO), Position.of(C, FOUR))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 하는 곳에 아군 기물이 있으면 이동이 불가능 하다")
    @Test
    void isMyTeam() {
        assertThatThrownBy(() ->
            board.move(Position.of(A, ONE), Position.of(A, TWO))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰을 A2 에서 A4로 이동시켰다면 A4에는 폰이 있다")
    @Test
    void move_pawn_and_now_pawn_is_at_target_pos() {
        board.move(Position.of(A, TWO), Position.of(A, FOUR));
        Piece findPiece = board.getValues().get(Position.of(A, FOUR));
        assertThat(findPiece).isInstanceOf(Pawn.class);
    }
}