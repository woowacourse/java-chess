package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.dto.BoardDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
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
        BoardDto actualBoard = board.boardDto();
        List<List<String>> expectedBoard = new ArrayList<>();
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));

        assertThat(expectedBoard).isEqualTo(actualBoard.board());
    }

    @Test
    @DisplayName("흑과 백이 대칭적으로 말을 놓는 기능")
    void putSymmetrically() {
        List<List<String>> expectedBoard = new ArrayList<>();
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", "R", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", "r", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        board.putSymmetrically(Piece.ROOK, Point.of("c3"));
        BoardDto actualBoard = board.boardDto();

        assertThat(expectedBoard).isEqualTo(actualBoard.board());
    }

    @Test
    @DisplayName("세로 줄에 같은 팀의 폰이 있는 경우 0.5점으로 계산")
    void testScoreWhenSameTeamPawnInSameColumn() {
        Board board = new Board();

        board.putSymmetrically(Piece.PAWN, Point.of("c4"));
        board.putSymmetrically(Piece.PAWN, Point.of("c3"));

        assertThat(board.score(Team.WHITE)).isCloseTo(1.0d, Assertions.offset(0.01d));
    }

    @Test
    @DisplayName("현재 체스판 위의 말들의 점수를 계산")
    void testScoreWhenSomePiecesNotExist() {
        board.putSymmetrically(Piece.KNIGHT, Point.of("f4"));
        board.putSymmetrically(Piece.QUEEN, Point.of("g4"));
        board.putSymmetrically(Piece.ROOK, Point.of("e1"));
        board.putSymmetrically(Piece.KING, Point.of("f1"));
        board.putSymmetrically(Piece.PAWN, Point.of("f2"));
        board.putSymmetrically(Piece.PAWN, Point.of("g2"));
        board.putSymmetrically(Piece.PAWN, Point.of("f3"));
        board.putSymmetrically(Piece.PAWN, Point.of("h3"));

        assertThat(board.score(Team.WHITE)).isCloseTo(19.5d, Assertions.offset(0.01d));
    }
}
