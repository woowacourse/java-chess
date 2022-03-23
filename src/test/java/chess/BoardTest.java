package chess;

import static chess.Board.*;
import static chess.PieceType.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private static Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
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
            .map(value -> new Piece(PieceType.PAWN, PieceColor.WHITE))
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
        ListIterator<PieceType> pieceTypeListIterator = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT,
            ROOK).listIterator();

        //when
        List<Piece> expected = Arrays.stream(File.values())
            .map(value -> new Piece(pieceTypeListIterator.next(), PieceColor.WHITE))
            .collect(Collectors.toList());

        List<Piece> actual = Arrays.stream(File.values())
            .map(file -> piecesByPositions.get(new Position(Rank.ONE, file)))
            .collect(Collectors.toList());

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스 말이 없는 곳에서 이동 시키면 예외를 던진다.")
    void move_exception() {
        assertThatThrownBy(() -> board.move(new Position(Rank.THREE, File.A), new Position(Rank.THREE, File.B)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE);
    }

    @Test
    @DisplayName("체스 말이 입력한 target으로 정상 이동했는지 확인한다.")
    void move_test() {
        //when
        board.move(new Position(Rank.TWO, File.A), new Position(Rank.THREE, File.A));
        Map<Position, Piece> piecesByPositions = board.getValues();

        //then
        assertThat(piecesByPositions.get(new Position(Rank.THREE, File.A))).isEqualTo(new Piece(PAWN, PieceColor.WHITE));
    }
}
