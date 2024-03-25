package domain;

import domain.piece.*;
import domain.square.File;
import domain.square.Rank;
import domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameResultTest {

    @DisplayName("남아있는 기물의 점수 합을 계산한다.")
    @Test
    void calculateScore() {
        // given
        final Team team = Team.BLACK;

        final Map<Square, Piece> pieceSquares = Map.of(
                new Square(File.A, Rank.ONE), new Rook(team),
                new Square(File.A, Rank.TWO), new Bishop(team),
                new Square(File.A, Rank.THREE), new Queen(team),
                new Square(File.B, Rank.TWO), new Pawn(team),
                new Square(File.C, Rank.TWO), new Pawn(team),
                new Square(File.D, Rank.TWO), new Pawn(team),
                new Square(File.A, Rank.FOUR), new King(team),
                new Square(File.D, Rank.THREE), new Pawn(Team.WHITE),
                new Square(File.D, Rank.FOUR), new Pawn(Team.WHITE)
        );

        final ChessGameResult chessGameResult = ChessGameResult.from(pieceSquares);

        // when
        final double blackScore = chessGameResult.getBlackScore();

        // then
        assertThat(blackScore).isEqualTo(20);
    }

    @DisplayName("File이 같은 Pawn이 있으면 0.5점으로 계산한다.")
    @Test
    void calculatePawnScore() {
        // given
        final Team team = Team.BLACK;

        final Map<Square, Piece> pieceSquares = Map.of(
                new Square(File.A, Rank.ONE), new Rook(team),
                new Square(File.A, Rank.TWO), new Knight(team),
                new Square(File.A, Rank.THREE), new Queen(team),
                new Square(File.B, Rank.TWO), new Pawn(team),
                new Square(File.B, Rank.THREE), new Pawn(team),
                new Square(File.C, Rank.TWO), new Pawn(team),
                new Square(File.D, Rank.TWO), new Pawn(team),
                new Square(File.A, Rank.FOUR), new King(team),
                new Square(File.D, Rank.THREE), new Pawn(Team.WHITE),
                new Square(File.D, Rank.FOUR), new Pawn(Team.WHITE)
        );

        final ChessGameResult chessGameResult = ChessGameResult.from(pieceSquares);

        // when
        final double blackScore = chessGameResult.getBlackScore();

        // then
        assertThat(blackScore).isEqualTo(19.5);
    }

    @DisplayName("King이 죽으면 0점이다.")
    @Test
    void dieKingScore() {
        // given
        final Team team = Team.BLACK;

        final Map<Square, Piece> pieceSquares = Map.of(
                new Square(File.A, Rank.ONE), new Rook(team),
                new Square(File.A, Rank.TWO), new Knight(team),
                new Square(File.A, Rank.THREE), new Queen(team),
                new Square(File.B, Rank.TWO), new Pawn(team),
                new Square(File.B, Rank.THREE), new Pawn(team),
                new Square(File.C, Rank.TWO), new Pawn(team),
                new Square(File.D, Rank.TWO), new Pawn(team),
                new Square(File.D, Rank.THREE), new Pawn(Team.WHITE),
                new Square(File.D, Rank.FOUR), new Pawn(Team.WHITE)
        );

        final ChessGameResult chessGameResult = ChessGameResult.from(pieceSquares);

        // when
        final double blackScore = chessGameResult.getBlackScore();

        // then
        assertThat(blackScore).isEqualTo(0);
    }
}
