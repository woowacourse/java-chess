package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.dto.console.BoardDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    @DisplayName("보드의 초기 설정")
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("빈 보드 생성")
    void createBoard() {
        BoardDto actualBoard = new BoardDto(board);

        List<List<String>> expectedBoard = new ArrayList<>();
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));

        assertThat(actualBoard.board()).isEqualTo(expectedBoard);
    }

    @Test
    @DisplayName("흑과 백이 대칭적으로 말을 놓는 기능")
    void putSymmetrically() {
        board.putSymmetrically(Piece.ROOK, Point.of("c3"));
        BoardDto actualBoard = new BoardDto(board);

        List<List<String>> expectedBoard = new ArrayList<>();
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", "R", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", "r", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));

        assertThat(actualBoard.board()).isEqualTo(expectedBoard);
    }

    @Test
    @DisplayName("특정 열에 있는 해당 팀의 폰 개수")
    void pawnCountInColumn() {
        board.putSymmetrically(Piece.PAWN, Point.of("a1"));
        board.putSymmetrically(Piece.ROOK, Point.of("a2"));
        board.putSymmetrically(Piece.KNIGHT, Point.of("a3"));
        board.putSymmetrically(Piece.PAWN, Point.of("b1"));
        board.putSymmetrically(Piece.PAWN, Point.of("b2"));
        board.putSymmetrically(Piece.KING, Point.of("b3"));
        board.putSymmetrically(Piece.PAWN, Point.of("c1"));
        board.putSymmetrically(Piece.PAWN, Point.of("c2"));
        board.putSymmetrically(Piece.PAWN, Point.of("c3"));

        assertThat(board.pawnCountInColumn(Team.WHITE, Column.A)).isEqualTo(1);
        assertThat(board.pawnCountInColumn(Team.WHITE, Column.B)).isEqualTo(2);
        assertThat(board.pawnCountInColumn(Team.WHITE, Column.C)).isEqualTo(3);
    }
}
