package chess.board;

import chess.coordinate.Coordinate;
import chess.coordinate.File;
import chess.coordinate.Rank;
import chess.piece.Pieces;
import chess.piece.Queen;
import chess.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @DisplayName("zero는 0을 가지고 있다.")
    @Test
    void zero() {
        //given
        Score zero = Score.zero();

        //when
        double sum = zero.getSum();

        //then
        assertThat(sum).isEqualTo(0);
    }

    @Test
    void calculateScore() {
        //given
        Score score = Score.zero();

        ChessBoard chessBoard = ChessBoard.empty();
        Tile tile = new Tile(Coordinate.of(File.F, Rank.ONE), Pieces.findBy(Queen.class, Team.BLACK));
        chessBoard.put(tile);

        Map<Coordinate, Tile> board = chessBoard.getChessBoard();

        //when
        double sumOfBlack = Score.calculateScore(Team.BLACK, board::get);

        //then
        assertThat(sumOfBlack).isEqualTo(9);
    }

}