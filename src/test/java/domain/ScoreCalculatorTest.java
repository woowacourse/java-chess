package domain;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.TeamColor;

class ScoreCalculatorTest {

//    .KR.....  8
//    P.PB....  7
//    .P..Q...  6
//    ........  5
//    .....nq.  4
//    .....p.p  3
//    .....pp.  2
//    ....rk..  1

//    abcdefgh


    private Map<Square, Piece> pieceLocations = Map.ofEntries(
            Map.entry(Square.of("e1"), new Rook(TeamColor.WHITE)),
            Map.entry(Square.of("f1"), new King(TeamColor.WHITE)),
            Map.entry(Square.of("f2"), new Pawn(TeamColor.WHITE)),
            Map.entry(Square.of("g2"), new Pawn(TeamColor.WHITE)),
            Map.entry(Square.of("f3"), new Pawn(TeamColor.WHITE)),
            Map.entry(Square.of("h3"), new Pawn(TeamColor.WHITE)),
            Map.entry(Square.of("f4"), new Knight(TeamColor.WHITE)),
            Map.entry(Square.of("g4"), new Queen(TeamColor.WHITE)),
            Map.entry(Square.of("b6"), new Pawn(TeamColor.BLACK)),
            Map.entry(Square.of("e6"), new Queen(TeamColor.BLACK)),
            Map.entry(Square.of("a7"), new Pawn(TeamColor.BLACK)),
            Map.entry(Square.of("c7"), new Pawn(TeamColor.BLACK)),
            Map.entry(Square.of("d7"), new Bishop(TeamColor.BLACK)),
            Map.entry(Square.of("b8"), new King(TeamColor.BLACK)),
            Map.entry(Square.of("c8"), new Rook(TeamColor.BLACK))
    );


    @Test
    @DisplayName("블랙팀 기물 점수 구하기")
    void sumBlackTeam() {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        double score = scoreCalculator.sumBlackScore(pieceLocations);

        Assertions.assertThat(score).isEqualTo(20);
    }

    @Test
    @DisplayName("화이트팀 기물 점수 구하기")
    void sumWhiteTeam() {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        double score = scoreCalculator.sumWhiteScore(pieceLocations);

        Assertions.assertThat(score).isEqualTo(19.5);
    }

}
