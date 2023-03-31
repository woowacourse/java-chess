package domain.game;

import domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ScoreBoardTest {
    private Map<Position, Piece> chessBoard;

    @BeforeEach
    void generateGame() {
        this.chessBoard = new ChessBoardGenerator().generate();
    }

    /**
     * RNBQKBNR
     * .PPPPPPP
     * ........
     * ........
     * ........
     * ........
     * pppppppp
     * rnbqkbnr
     */
    @DisplayName("체스 게임판의 말에 따라 white, black각각의 점수를 계산한다.")
    @Test
    void calculateSideScoresTest() {
        //given
        chessBoard.put(Position.of("a", "7"), new EmptyPiece());
        ScoreBoard scoreBoard = new ScoreBoard(chessBoard, GameState.RUN);

        //when
        Map<Side, Double> scores = scoreBoard.calculateScore();

        //then
        assertAll(
                () -> assertThat(scores.get(Side.WHITE)).isEqualTo(38),
                () -> assertThat(scores.get(Side.BLACK)).isEqualTo(37)
        );
    }

    @DisplayName("white진영의 점수가 높을 경우 white진영이 승자가 된다.")
    @Test
    void whiteWinTest() {
        //given
        chessBoard.put(Position.of("a", "7"), new EmptyPiece());
        ScoreBoard scoreBoard = new ScoreBoard(chessBoard, GameState.RUN);
        //when
        assertThat(scoreBoard.calculateWinner(scoreBoard.calculateScore())).isEqualTo(Side.WHITE);
    }

    /**
     * RNBQKBNR
     * PP.PPPPP
     * ........
     * ..p.....
     * ........
     * ........
     * p.pppppp
     * rnbqkbnr
     */
    @DisplayName("동일한 rank에 같은 진영의 폰이 존재하면 0.5점으로 계산한다.")
    @Test
    void calculateScoresWhenSameSidePawnOnSameRank() {
        //given
        chessBoard.put(Position.of("c", "5"), new Pawn(Side.WHITE));
        chessBoard.put(Position.of("b", "2"), new EmptyPiece());
        chessBoard.put(Position.of("c", "7"), new EmptyPiece());
        ScoreBoard scoreBoard = new ScoreBoard(chessBoard, GameState.RUN);
        //when
        Map<Side, Double> scores = scoreBoard.calculateScore();

        //then
        assertThat(scores.get(Side.BLACK)).isEqualTo(37);
        assertThat(scores.get(Side.WHITE)).isEqualTo(37);
    }

    @DisplayName("black, white진영의 점수가 같으면 중립(NEUTRAL)진영이 반환된다.")
    @Test
    void drawWinTest() {
        //given
        chessBoard.put(Position.of("c", "5"), new Pawn(Side.WHITE));
        chessBoard.put(Position.of("b", "2"), new EmptyPiece());
        chessBoard.put(Position.of("c", "7"), new EmptyPiece());
        ScoreBoard scoreBoard = new ScoreBoard(chessBoard, GameState.RUN);
        //when
        assertThat(scoreBoard.calculateWinner(scoreBoard.calculateScore())).isEqualTo(Side.NEUTRAL);
    }

    @DisplayName("왕이 죽은 경우 왕이 살아있는 진영이 승리한다.")
    @Test
    void calculateWinnerWhenKingDeadTest() {
        //given
        chessBoard.put(Position.of("e", "1"), new Pawn(Side.BLACK));
        ScoreBoard scoreBoard = new ScoreBoard(chessBoard, GameState.KING_DEAD);
        //when
        Side winSide = scoreBoard.calculateWinner(scoreBoard.calculateScore());
        //then
        assertThat(winSide).isEqualTo(Side.BLACK);
    }

}
