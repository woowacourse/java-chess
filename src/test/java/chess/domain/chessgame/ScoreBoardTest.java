package chess.domain.chessgame;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreBoardTest {

    private Board board;
    private ScoreBoard scoreBoard;

    @BeforeEach
    @DisplayName("보드의 초기 설정")
    void setUp() {
        board = new Board();
        scoreBoard = new ScoreBoard(board);
    }

    @Test
    @DisplayName("세로 줄에 같은 팀의 폰이 있는 경우 0.5점으로 계산")
    void testScoreWhenSameTeamPawnInSameColumn() {
        board.putSymmetrically(Piece.PAWN, Point.of("c4"));
        board.putSymmetrically(Piece.PAWN, Point.of("c3"));

        assertThat(scoreBoard.score(Team.WHITE)).isCloseTo(1.0d, Assertions.offset(0.01d));
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

        assertThat(scoreBoard.score(Team.WHITE)).isCloseTo(19.5d, Assertions.offset(0.01d));
    }
}
