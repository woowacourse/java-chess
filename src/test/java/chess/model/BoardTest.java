package chess.model;

import static chess.model.Board.*;
import static chess.model.File.*;
import static chess.model.PieceColor.*;
import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.model.boardinitializer.BoardInitializer;
import chess.model.boardinitializer.defaultInitializer;
import chess.model.piece.EmptyPiece;
import chess.model.piece.King;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Rook;

public class BoardTest {

    private static Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new TurnDecider(), new defaultInitializer());
    }

    @Test
    @DisplayName("체스 보드 생성 테스트 : 개수")
    void init_count() {
        //given
        Map<Position, Piece> piecesByPositions = board.getValues();

        //when
        int actual = piecesByPositions.keySet().size();

        //then
        assertThat(actual).isEqualTo(64);
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
            .map(file -> piecesByPositions.get(Position.of(Rank.TWO, file)))
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
            .map(file -> piecesByPositions.get(Position.of(Rank.TWO, file)))
            .collect(Collectors.toList());

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스 말이 없는 곳에서 이동 시키면 예외를 던진다.")
    void move_exception() {
        assertThatThrownBy(() -> board.move(Position.of(Rank.THREE, A), Position.of(Rank.THREE, B)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_SOURCE_PIECE_EMPTY);
    }

    @Test
    @DisplayName("체스 말이 입력한 target으로 정상 이동했는지 확인한다.")
    void move_test() {
        //when
        board.move(Position.of(Rank.TWO, A), Position.of(Rank.THREE, A));
        Map<Position, Piece> piecesByPositions = board.getValues();

        //then
        assertThat(piecesByPositions.get(Position.of(Rank.THREE, A))).isEqualTo(Pawn.colorOf(PieceColor.WHITE));
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE:A", "THREE:C"}, delimiter = ':')
    @DisplayName("퀸은 경로에 다른 기물 있으면 이동할 수 없다")
    void isBlocked(Rank rank, File file) {
        assertThatThrownBy(() ->
            board.move(Position.of(ONE, File.C), Position.of(rank, file))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"THREE:C", "THREE:A"}, delimiter = ':')
    @DisplayName("나이트는 경로에 다른 기물 있으면 이동할 수 있다")
    void isNonBlocked(Rank rank, File file) {
        assertDoesNotThrow(() ->
            board.move(Position.of(ONE, B), Position.of(rank, file))
        );
    }

    @DisplayName("기물이 다른 기물의 이동경로를 막고 있다면 이동이 불가하다")
    @Test
    void isBlockedAfterNightMoved() {
        board.move(Position.of(ONE, B), Position.of(THREE, C));
        assertThatThrownBy(() ->
            board.move(Position.of(TWO, C), Position.of(FOUR, C))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 하는 곳에 아군 기물이 있으면 이동이 불가능 하다")
    @Test
    void isMyTeam() {
        assertThatThrownBy(() ->
            board.move(Position.of(ONE, A), Position.of(TWO, A))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰을 A2 에서 A4로 이동시켰다면 A4에는 폰이 있다")
    @Test
    void move_pawn_and_now_pawn_is_at_target_pos() {
        board.move(Position.of(TWO, A), Position.of(FOUR, A));
        Piece findPiece = board.getValues().get(Position.of(FOUR, A));
        assertThat(findPiece).isInstanceOf(Pawn.class);
    }

    @DisplayName("킹이 잡힐 경우 isFinished는 true를 반환한다.")
    @Test
    void move_return_true_when_king_captured() {
        Board board = new Board(new TurnDecider(), new kingCaptureTestInitializer());

        board.move(Position.of(TWO, A), Position.of(THREE, A));
        assertThat(board.isFinished()).isTrue();
    }

    @DisplayName("킹이 잡힐 경우 룩 한 개 남은 승자의 점수 5점을 반환한다.")
    @Test
    void score_is_5_when_king_captured() {
        Board board = new Board(new TurnDecider(), new kingCaptureTestInitializer());
        board.move(Position.of(TWO, A), Position.of(THREE, A));
        double actual = board.calculateScore();
        assertThat(actual).isEqualTo(5);
    }

    @Test
    @DisplayName("첫판에 점수를 계산하면 38점이 나온다")
    void when_first_turn_cal_score_then_38() {
        Board board = new Board(new TurnDecider(), new defaultInitializer());
        double score = board.calculateScore();
        assertThat(score).isEqualTo(38.0);
    }

    public static class kingCaptureTestInitializer implements BoardInitializer {

        private static final Piece EMPTY_PIECE = EmptyPiece.of(EMPTY);

        @Override
        public Map<Position, Piece> apply() {
            Map<Position, Piece> result = new HashMap<>();
            putAllEmptyPieces(result);
            result.put(Position.of(TWO, A), Rook.colorOf(PieceColor.WHITE));
            result.put(Position.of(THREE, A), King.colorOf(PieceColor.BLACK));
            return result;
        }

        private void putAllEmptyPieces(Map<Position, Piece> result) {
            for (Rank rank : Rank.reverseValues()) {
                putEmptyPiecesInOneRank(result, rank);
            }
        }

        private void putEmptyPiecesInOneRank(Map<Position, Piece> result, Rank rank) {
            for (File file : File.values()) {
                result.put(Position.of(rank, file), EMPTY_PIECE);
            }
        }
    }
}