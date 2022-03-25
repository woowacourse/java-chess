package chess;

import static chess.Board.SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE;
import static chess.File.A;
import static chess.File.B;
import static chess.File.C;
import static chess.Rank.FOUR;
import static chess.Rank.ONE;
import static chess.Rank.THREE;
import static chess.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.piece.Pawn;
import chess.piece.Piece;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
}
