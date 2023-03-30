package chess.domain;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PointCalculatorTest {

    @Test
    void 기물의_전체합을_계산한다() {
        assertThat(PointCalculator.sumPoints(
                List.of(
                        Double.valueOf(5),
                        Double.valueOf(4),
                        Double.valueOf(3),
                        Double.valueOf(2),
                        Double.valueOf(1)
                ))).isEqualTo(15);
    }

    @Test
    void 체스판의_특정_팀의_폰_제외점수를_계산한다() {
        ChessBoard chessBoard = ChessBoard.create();
        chessBoard.move(Coordinate.createCoordinate("2","a"),Coordinate.createCoordinate("4","a"));
        chessBoard.move(Coordinate.createCoordinate("4","a"),Coordinate.createCoordinate("5","a"));
        chessBoard.move(Coordinate.createCoordinate("5","a"),Coordinate.createCoordinate("6","a"));
        chessBoard.move(Coordinate.createCoordinate("6","a"),Coordinate.createCoordinate("7","b"));
        assertThat(PointCalculator.totalExcludingPointsOfPawn(chessBoard.chessBoard(), Team.BLACK)).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"2,1,BLACK","1,2,WHITE","2,2,EMPTY"})
    void 양팀의_점수를_보고_승자를_찾는다(double blackTeamPoints,double whiteTeamPoints, Team expected) {
        assertThat(PointCalculator.winnerOf(blackTeamPoints,whiteTeamPoints)).isSameAs(expected);
    }
}