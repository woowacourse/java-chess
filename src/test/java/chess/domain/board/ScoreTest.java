package chess.domain.board;

import chess.domain.coordinate.Coordinate;
import chess.domain.coordinate.File;
import chess.domain.coordinate.Rank;
import chess.domain.piece.MovedPawn;
import chess.domain.piece.Pieces;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @DisplayName("zero는 0을 가지고 있다.")
    @Test
    void zero() {
        double sum = Score.zero().getSum();

        assertThat(sum).isEqualTo(0);
    }

    @DisplayName("Piece별 점수 계산 확인")
    @ParameterizedTest
    @CsvSource(value = {"BLACK_BISHOP,3",
            "BLACK_QUEEN,9",
            "BLACK_ROOK,5",
            "BLACK_KNIGHT, 2.5",
            "BLACK_KING, 0",
            "BLACK_MOVED_PAWN,1"
    })
    void calculateScore(Pieces pieces, double score) {
        ChessBoard chessBoard = ChessBoard.empty();
        Tile tile = new Tile(Coordinate.of(File.A, Rank.ONE), pieces.getPiece());
        chessBoard.put(tile);
        Map<Coordinate, Tile> board = chessBoard.getChessBoard();

        //when
        double sumOfBlack = Score.calculateScore(Team.BLACK, board::get);

        //then
        assertThat(sumOfBlack).isEqualTo(score);
    }

    @DisplayName("Pawn 같은 file에 있을 경우 점수 테스트")
    @Test
    void calculateScore2() {
        ChessBoard chessBoard = ChessBoard.empty();
        Tile tile = new Tile(Coordinate.of(File.F, Rank.ONE), Pieces.findBy(MovedPawn.class, Team.BLACK));
        chessBoard.put(tile);
        tile = new Tile(Coordinate.of(File.F, Rank.TWO), Pieces.findBy(MovedPawn.class, Team.BLACK));
        chessBoard.put(tile);

        Map<Coordinate, Tile> board = chessBoard.getChessBoard();

        //when
        double sumOfBlack = Score.calculateScore(Team.BLACK, board::get);

        //then
        assertThat(sumOfBlack).isEqualTo(1);
    }

}