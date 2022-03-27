package chess.domain.board;

import static chess.domain.board.Board.*;
import static chess.domain.board.File.*;
import static chess.domain.board.Rank.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import chess.turndecider.FixedTurnDecider;
import chess.domain.piece.constant.PieceColor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.turndecider.AlternatingTurnDecider;

public class BoardTest {

    private static Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new FixedTurnDecider());
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
            .map(value -> new Pawn(PieceColor.WHITE))
            .collect(Collectors.toList());

        List<Piece> actual = Arrays.stream(File.values())
            .map(file -> piecesByPositions.get(new Position(Rank.TWO, file)))
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
            .map(value -> new Pawn(PieceColor.WHITE))
            .collect(Collectors.toList());

        List<Piece> actual = Arrays.stream(File.values())
            .map(file -> piecesByPositions.get(new Position(Rank.TWO, file)))
            .collect(Collectors.toList());

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스 말이 없는 곳에서 이동 시키면 예외를 던진다.")
    void move_exception() {
        assertThatThrownBy(() -> board.move(new Position(Rank.THREE, A), new Position(Rank.THREE, B)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE);
    }

    @Test
    @DisplayName("체스 말이 입력한 target으로 정상 이동했는지 확인한다.")
    void move_test() {
        //when
        board.move(new Position(Rank.TWO, A), new Position(Rank.THREE, A));
        Map<Position, Piece> piecesByPositions = board.getValues();

        //then
        assertThat(piecesByPositions.get(new Position(Rank.THREE, A))).isEqualTo(new Pawn(PieceColor.WHITE));
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE:A", "THREE:C"}, delimiter = ':')
    @DisplayName("퀸은 경로에 다른 기물 있으면 이동할 수 없다")
    void isBlocked(Rank rank, File file) {
        assertThatThrownBy(() ->
            board.move(new Position(ONE, File.C), new Position(rank, file))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"THREE:C", "THREE:A"}, delimiter = ':')
    @DisplayName("나이트는 경로에 다른 기물 있으면 이동할 수 있다")
    void isNonBlocked(Rank rank, File file) {
        assertDoesNotThrow(() ->
            board.move(new Position(ONE, B), new Position(rank, file))
        );
    }

    @DisplayName("기물이 다른 기물의 이동경로를 막고 있다면 이동이 불가하다")
    @Test
    void isBlockedAfterNightMoved() {
        board.move(new Position(ONE, B), new Position(THREE, C));
        assertThatThrownBy(() ->
            board.move(new Position(TWO, C), new Position(FOUR, C))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 하는 곳에 아군 기물이 있으면 이동이 불가능 하다")
    @Test
    void isMyTeam() {
        assertThatThrownBy(() ->
            board.move(new Position(ONE, A), new Position(TWO, A))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰을 A2 에서 A4로 이동시켰다면 A4에는 폰이 있다")
    @Test
    void move_pawn_and_now_pawn_is_at_target_pos() {
        board.move(new Position(TWO, A), new Position(FOUR, A));
        Piece findPiece = board.getValues().get(new Position(FOUR, A));
        assertThat(findPiece).isInstanceOf(Pawn.class);
    }

    @DisplayName("킹이 잡힐 경우 move는 true를 반환한다.")
    @Test
    void move_return_true_when_king_captured() {
        board.move(new Position(ONE, B), new Position(THREE, C));
        board.move(new Position(TWO, F), new Position(THREE, F));

        board.move(new Position(THREE, C), new Position(FOUR, E));
        board.move(new Position(TWO, G), new Position(THREE, G));

        board.move(new Position(FOUR, E), new Position(SIX, D));
        board.move(new Position(TWO, H), new Position(THREE, H));

        boolean isFinished = board.move(new Position(SIX, D), new Position(EIGHT, E));

        assertThat(isFinished).isTrue();
    }

    @Test
    @DisplayName("첫판에 점수를 계산하면 38점이 나온다")
    void when_first_turn_cal_score_then_38() {
        Board board = new Board(new AlternatingTurnDecider());
        double score = board.calculateScore();
        assertThat(score).isEqualTo(38.0);
    }

    @Test
    @DisplayName("폰이 같은 File에 두 개 이상 있을 경우 각 0.5점으로 계산한다.")
    void when_pawns_in_same_file() {
        Board board = new Board(new AlternatingTurnDecider());

        board.move(new Position(TWO, A), new Position(FOUR, A));
        board.move(new Position(SEVEN, B), new Position(FIVE, B));
        board.move(new Position(FOUR, A), new Position(FIVE, B));

        //then
        double actual = board.calculateScore();
        assertThat(actual).isEqualTo(37.0);
    }
}
