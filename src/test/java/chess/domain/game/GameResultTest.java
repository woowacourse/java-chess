package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import chess.domain.pieces.pawn.Pawn;
import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.score.Score;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임 결과")
class GameResultTest {

    @DisplayName("기본 점수의 합을 반환한다")
    @Test
    void gameResultScore() {
        //given
        Color black = Color.BLACK;

        Map<Square, Piece> pieces = Map.of(
                Square.of(File.C, Rank.EIGHT), new Rook(black),
                Square.of(File.D, Rank.SEVEN), new Bishop(black),
                Square.of(File.E, Rank.SIX), new Queen(black),
                Square.of(File.A, Rank.SEVEN), Pawn.of(black),
                Square.of(File.B, Rank.SIX), Pawn.of(black),
                Square.of(File.C, Rank.SEVEN), Pawn.of(black),
                Square.of(File.B, Rank.EIGHT), new King(black)
        );

        //when
        GameResult gameResult = new GameResult(pieces);

        //then
        assertThat(gameResult.calculateScore(black)).isEqualTo(Score.of(20));
    }

    @DisplayName("세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점으로 계산한다")
    @Test
    void gameResultScoreWithVertical() {
        //given
        Color white = Color.WHITE;

        Map<Square, Piece> pieces = Map.of(
                Square.of(File.E, Rank.ONE), new Rook(white),
                Square.of(File.F, Rank.FOUR), new Knight(white),
                Square.of(File.G, Rank.FOUR), new Queen(white),
                Square.of(File.F, Rank.TWO), Pawn.of(white),
                Square.of(File.F, Rank.THREE), Pawn.of(white),
                Square.of(File.G, Rank.TWO), Pawn.of(white),
                Square.of(File.H, Rank.THREE), Pawn.of(white),
                Square.of(File.F, Rank.ONE), new King(white)
        );

        //when
        GameResult gameResult = new GameResult(pieces);

        //then
        assertThat(gameResult.calculateScore(white)).isEqualTo(Score.of(19.5));
    }

    @DisplayName("게임이 끝났는지 검증한다")
    @Test
    void finishGame() {
        //given
        Color white = Color.WHITE;

        Map<Square, Piece> pieces = Map.of(
                Square.of(File.E, Rank.ONE), new Rook(white),
                Square.of(File.F, Rank.FOUR), new Knight(white),
                Square.of(File.G, Rank.FOUR), new Queen(white),
                Square.of(File.F, Rank.TWO), Pawn.of(white),
                Square.of(File.F, Rank.THREE), Pawn.of(white),
                Square.of(File.G, Rank.TWO), Pawn.of(white),
                Square.of(File.H, Rank.THREE), Pawn.of(white),
                Square.of(File.F, Rank.ONE), new King(white)
        );

        //when
        GameResult gameResult = new GameResult(pieces);

        //then
        assertThat(gameResult.isGameOver()).isTrue();
    }
}
