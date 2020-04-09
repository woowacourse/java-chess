package chess.board;

import chess.coordinate.Coordinate;
import chess.coordinate.File;
import chess.coordinate.Rank;
import chess.piece.MovedPawn;
import chess.piece.Pieces;
import chess.piece.Queen;
import chess.piece.Team;
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
        //given
        Score zero = Score.zero();

        //when
        double sum = zero.getSum();

        //then
        assertThat(sum).isEqualTo(0);
    }

    @DisplayName("단일 팀에 대한 계산만 진행한다.")
    @Test
    void calculateScore() {
        //given
        ChessBoard chessBoard = ChessBoard.empty();
        Tile tile1 = new Tile(Coordinate.of(File.F, Rank.ONE), Pieces.findBy(Queen.class, Team.BLACK));
        Tile tile2 = new Tile(Coordinate.of(File.H, Rank.ONE), Pieces.findBy(Queen.class, Team.WHITE));
        chessBoard.put(tile1);
        chessBoard.put(tile2);

        Map<Coordinate, Tile> board = chessBoard.getChessBoard();

        //when
        double sumOfBlack = Score.calculateScore(Team.BLACK, board::get).getSum();

        //then
        assertThat(sumOfBlack).isEqualTo(9);
    }

    @DisplayName("폰이 세로로 2개 이상 있는 경우 폰의 점수를 0.5점으로 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"F,1", "H,2"})
    void name(File file, double expect) {
        //given
        ChessBoard chessBoard = ChessBoard.empty();
        Tile tile1 = new Tile(Coordinate.of(File.F, Rank.ONE), Pieces.findBy(MovedPawn.class, Team.BLACK));
        Tile tile2 = new Tile(Coordinate.of(file, Rank.TWO), Pieces.findBy(MovedPawn.class, Team.BLACK));
        chessBoard.put(tile1);
        chessBoard.put(tile2);

        Map<Coordinate, Tile> board = chessBoard.getChessBoard();

        //when
        double sumOfBlack = Score.calculateScore(Team.BLACK, board::get).getSum();

        //then
        assertThat(sumOfBlack).isEqualTo(expect);
    }

}