package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashMap;
import java.util.Map;
import model.piece.Bishop;
import model.piece.BlackPawn;
import model.piece.King;
import model.piece.Knight;
import model.piece.Piece;
import model.piece.Queen;
import model.piece.Rook;
import model.piece.WhitePawn;
import model.position.Column;
import model.position.Position;
import model.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

    /*
            .KR.....  8
            P.PB....  7
            .P..Q...  6
            ........  5
            .....nq.  4
            .....p.p  3
            .....pp.  2
            ....rk..  1

            abcdefgh
    */
    @DisplayName("두 팀다 King이 살아있을 때 각 팀의 점수를 비교하여 점수가 높은 사람이 winner다.")
    @Test
    void testCompareScoresAndDeclareWinnerWithBothTeamsAlive() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(new Position(Column.B, Row.EIGHT), new King(Camp.BLACK));
        board.put(new Position(Column.C, Row.EIGHT), new Rook(Camp.BLACK));
        board.put(new Position(Column.A, Row.SEVEN), new BlackPawn());
        board.put(new Position(Column.C, Row.SEVEN), new BlackPawn());
        board.put(new Position(Column.D, Row.SEVEN), new Bishop(Camp.BLACK));
        board.put(new Position(Column.B, Row.SIX), new BlackPawn());
        board.put(new Position(Column.E, Row.SIX), new Queen(Camp.BLACK));
        board.put(new Position(Column.F, Row.FOUR), new Knight(Camp.WHITE));
        board.put(new Position(Column.G, Row.FOUR), new Queen(Camp.WHITE));
        board.put(new Position(Column.F, Row.THREE), new WhitePawn());
        board.put(new Position(Column.H, Row.THREE), new WhitePawn());
        board.put(new Position(Column.F, Row.TWO), new WhitePawn());
        board.put(new Position(Column.G, Row.TWO), new WhitePawn());
        board.put(new Position(Column.E, Row.ONE), new Rook(Camp.WHITE));
        board.put(new Position(Column.F, Row.ONE), new King(Camp.WHITE));

        ScoreCalculator scoreCalculator = new ScoreCalculator(board);
        Map<Camp, Double> gameResult = scoreCalculator.getResult();

        double blackScore = gameResult.get(Camp.BLACK);
        double whiteScore = gameResult.get(Camp.WHITE);
        Result result = scoreCalculator.getWinner();

        assertAll(
                () -> assertThat(blackScore).isEqualTo(20),
                () -> assertThat(whiteScore).isEqualTo(19.5),
                () -> assertThat(result).isEqualTo(Result.BLACK_WIN)
        );
    }

    /*
            .KR.....  8
            ..PB...P  7
            ..P.Q...  6
            ..P.....  5
            .....nq.  4
            .....p.p  3
            .....pp.  2
            ....rk..  1

            abcdefgh
    */
    @DisplayName("두 팀다 King이 살아있을 때 각 팀의 점수를 비교하여 점수가 같으면 DRAW다.")
    @Test
    void testDeclareLoserWhenKingIsDeadAndTeamScoreIsZero() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(new Position(Column.C, Row.EIGHT), new Rook(Camp.BLACK));
        board.put(new Position(Column.B, Row.SEVEN), new BlackPawn());
        board.put(new Position(Column.B, Row.SIX), new BlackPawn());
        board.put(new Position(Column.D, Row.SEVEN), new Bishop(Camp.BLACK));
        board.put(new Position(Column.D, Row.EIGHT), new King(Camp.BLACK));
        board.put(new Position(Column.B, Row.FIVE), new BlackPawn());
        board.put(new Position(Column.H, Row.SEVEN), new BlackPawn());
        board.put(new Position(Column.E, Row.SIX), new Queen(Camp.BLACK));
        board.put(new Position(Column.F, Row.FOUR), new Knight(Camp.WHITE));
        board.put(new Position(Column.G, Row.FOUR), new Queen(Camp.WHITE));
        board.put(new Position(Column.F, Row.THREE), new WhitePawn());
        board.put(new Position(Column.H, Row.THREE), new WhitePawn());
        board.put(new Position(Column.F, Row.TWO), new WhitePawn());
        board.put(new Position(Column.G, Row.TWO), new WhitePawn());
        board.put(new Position(Column.E, Row.ONE), new Rook(Camp.WHITE));
        board.put(new Position(Column.F, Row.ONE), new King(Camp.WHITE));

        ScoreCalculator scoreCalculator = new ScoreCalculator(board);
        Map<Camp, Double> gameResult = scoreCalculator.getResult();

        double blackScore = gameResult.get(Camp.BLACK);
        double whiteScore = gameResult.get(Camp.WHITE);
        Result result = scoreCalculator.getWinner();

        assertAll(
                () -> assertThat(blackScore).isEqualTo(19.5),
                () -> assertThat(whiteScore).isEqualTo(19.5),
                () -> assertThat(result).isEqualTo(Result.DRAW)
        );
    }

    /*
            ..R.....  8
            P.PB....  7
            .P..Q...  6
            ........  5
            .....nq.  4
            .....p.p  3
            .....pp.  2
            ....rk..  1

            abcdefgh
    */
    @DisplayName("King이 죽었을 때 King이 죽은 팀의 점수는 0점으로 계산하고 그 팀은 패배한다.")
    @Test
    void testDeclareLoserWhenKigIsDeadAndTeamScoreIsZero() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(new Position(Column.C, Row.EIGHT), new Rook(Camp.BLACK));
        board.put(new Position(Column.A, Row.SEVEN), new BlackPawn());
        board.put(new Position(Column.C, Row.SEVEN), new BlackPawn());
        board.put(new Position(Column.D, Row.SEVEN), new Bishop(Camp.BLACK));
        board.put(new Position(Column.B, Row.SIX), new BlackPawn());
        board.put(new Position(Column.E, Row.SIX), new Queen(Camp.BLACK));
        board.put(new Position(Column.F, Row.FOUR), new Knight(Camp.WHITE));
        board.put(new Position(Column.G, Row.FOUR), new Queen(Camp.WHITE));
        board.put(new Position(Column.F, Row.THREE), new WhitePawn());
        board.put(new Position(Column.H, Row.THREE), new WhitePawn());
        board.put(new Position(Column.F, Row.TWO), new WhitePawn());
        board.put(new Position(Column.G, Row.TWO), new WhitePawn());
        board.put(new Position(Column.E, Row.ONE), new Rook(Camp.WHITE));
        board.put(new Position(Column.F, Row.ONE), new King(Camp.WHITE));

        ScoreCalculator scoreCalculator = new ScoreCalculator(board);
        Map<Camp, Double> gameResult = scoreCalculator.getResult();

        double blackScore = gameResult.get(Camp.BLACK);
        double whiteScore = gameResult.get(Camp.WHITE);
        Result result = scoreCalculator.getWinner();

        assertAll(
                () -> assertThat(blackScore).isZero(),
                () -> assertThat(whiteScore).isEqualTo(19.5),
                () -> assertThat(result).isEqualTo(Result.WHITE_WIN)
        );
    }
}
