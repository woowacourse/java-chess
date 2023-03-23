package domain.game;

import domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RefereeTest {
    private Map<Position, Piece> chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoardGenerator().generate();
    }

    /**
     * RNBQKBNR
     * PPPPPPPP
     * ........
     * ........
     * ........
     * ........
     * .ppppppp
     * rnbqkbnr
     */
    @DisplayName("체스 게임판의 말에 따라 white, black각각의 점수를 계산한다.")
    @Test
    void calculateSideScoresTest() {
        //given
        Referee referee = new Referee();
        chessBoard.put(Position.of("a", "2"), new EmptyPiece());

        //when
        Map<Side, Double> scores = referee.calculateScore(chessBoard);

        //then
        assertAll(
                () -> assertThat(scores.get(Side.WHITE)).isEqualTo(37),
                () -> assertThat(scores.get(Side.BLACK)).isEqualTo(38)
        );
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
     * 흰색은 5(rook) + 2.5(knight) + 9(queen) + 3(pawn, 4개의 pawn이 있지만 세로로 있어 각 0.5이 된다.) + 0(king) = 19.5점
     * 검은색은 5(rook) + 3(bishop) + 9(queen) + 3(pawn) + 0(king) = 20점
     */
    @DisplayName("동일한 rank에 같은 진영의 폰이 존재하면 0.5점으로 계산한다.")
    @Test
    void calculateScoresWhenSameSidePawnOnSameRank() {
        //given
        chessBoard.put(Position.of("c", "5"), Pawn.createOfWhite());
        chessBoard.put(Position.of("b", "2"), new EmptyPiece());
        chessBoard.put(Position.of("c", "7"), new EmptyPiece());

        //when
        Referee referee = new Referee();
        Map<Side, Double> scores = referee.calculateScore(chessBoard);

        //then
        assertThat(scores.get(Side.BLACK)).isEqualTo(37);
        assertThat(scores.get(Side.WHITE)).isEqualTo(37);
    }

    /**
     * .KR.....
     * P.PB....
     * .P..Q...
     * ........
     * .....nq.
     * .....p.p
     * .....pp.
     * ....rk..
     */
    @DisplayName("체스보드에 맞는 게임 점수를 계산한다.")
    @Test
    void calculateScoresTest() {
        //given
        setRank1();
        setRank2();
        setRank3();
        setRank4();
        setRank6();
        setRank7();
        setRank8();

        Referee referee = new Referee();
        //when
        Map<Side, Double> scores = referee.calculateScore(chessBoard);
        //then
        assertThat(scores.get(Side.WHITE)).isEqualTo(19.5);
        assertThat(scores.get(Side.BLACK)).isEqualTo(20);
    }

    private void setRank8() {
        chessBoard.put(Position.of("a", "8"), new EmptyPiece());
        chessBoard.put(Position.of("b", "8"), King.createOfBlack());
        chessBoard.put(Position.of("c", "8"), Rook.createOfBlack());
        chessBoard.put(Position.of("d", "8"), new EmptyPiece());
        chessBoard.put(Position.of("e", "8"), new EmptyPiece());
        chessBoard.put(Position.of("f", "8"), new EmptyPiece());
        chessBoard.put(Position.of("g", "8"), new EmptyPiece());
        chessBoard.put(Position.of("h", "8"), new EmptyPiece());
    }

    private void setRank7() {
        chessBoard.put(Position.of("b", "7"), new EmptyPiece());
        chessBoard.put(Position.of("d", "7"), Bishop.createOfBlack());
        chessBoard.put(Position.of("e", "7"), new EmptyPiece());
        chessBoard.put(Position.of("f", "7"), new EmptyPiece());
        chessBoard.put(Position.of("g", "7"), new EmptyPiece());
        chessBoard.put(Position.of("h", "7"), new EmptyPiece());
    }

    private void setRank6() {
        chessBoard.put(Position.of("b", "6"), Pawn.createOfBlack());
        chessBoard.put(Position.of("e", "6"), Queen.createOfBlack());
    }

    private void setRank4() {
        chessBoard.put(Position.of("f", "4"), Knight.createOfWhite());
        chessBoard.put(Position.of("g", "4"), Queen.createOfWhite());
    }

    private void setRank3() {
        chessBoard.put(Position.of("f", "3"), Pawn.createOfWhite());
        chessBoard.put(Position.of("h", "3"), Pawn.createOfWhite());
    }

    private void setRank2() {
        chessBoard.put(Position.of("a", "2"), new EmptyPiece());
        chessBoard.put(Position.of("b", "2"), new EmptyPiece());
        chessBoard.put(Position.of("c", "2"), new EmptyPiece());
        chessBoard.put(Position.of("d", "2"), new EmptyPiece());
        chessBoard.put(Position.of("e", "2"), new EmptyPiece());
        chessBoard.put(Position.of("h", "2"), new EmptyPiece());
    }

    private void setRank1() {
        chessBoard.put(Position.of("a", "1"), new EmptyPiece());
        chessBoard.put(Position.of("b", "1"), new EmptyPiece());
        chessBoard.put(Position.of("c", "1"), new EmptyPiece());
        chessBoard.put(Position.of("d", "1"), new EmptyPiece());
        chessBoard.put(Position.of("g", "1"), new EmptyPiece());
        chessBoard.put(Position.of("h", "1"), new EmptyPiece());
        chessBoard.put(Position.of("e", "1"), Rook.createOfWhite());
        chessBoard.put(Position.of("f", "1"), King.createOfWhite());
    }


}