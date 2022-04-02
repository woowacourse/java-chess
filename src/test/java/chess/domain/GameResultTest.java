package chess.domain;

import static chess.domain.TestPieces.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Square;

public class GameResultTest {

    @Test
    @DisplayName("룩1, 비숍1, 퀸1, 폰1 가 있으면 18점을 반환한다")
    void getStatus_rook1_bishop1_queen1_pawn1() {
        Board chessBoard = new Board(() ->
            Map.of(
                    new Square("c3"), WHITE_QUEEN,
                    new Square("c4"), WHITE_ROOK,
                    new Square("c5"), WHITE_BISHOP,
                    new Square("c6"), WHITE_PAWN
            ));
        GameResult gameResult = new GameResult(chessBoard);

        assertThat(gameResult.calculateScore(Color.WHITE)).isEqualTo(18);
    }

    @Test
    @DisplayName("같은 세로줄에 폰이 2개가 있을 때 1점을 반환한다")
    void getStatus_pawn2_sameFile() {
        Board chessBoard = new Board(() ->
            Map.of(
                new Square("c5"), WHITE_PAWN,
                new Square("c6"), WHITE_PAWN
            ));
        GameResult gameResult = new GameResult(chessBoard);

        assertThat(gameResult.calculateScore(Color.WHITE)).isEqualTo(1);
    }

    @Test
    @DisplayName("룩1, 비숍1, 퀸1, 폰3 가 있으면 18.5점을 반환한다(폰 3개가 같은 세로줄에 있다)")
    void getStatus_rook1_bishop1_queen1_pawn3() {
        Board chessBoard = new Board(() ->
            Map.of(
                new Square("c3"), WHITE_QUEEN,
                new Square("b4"), WHITE_ROOK,
                new Square("a5"), WHITE_BISHOP,
                new Square("c6"), WHITE_PAWN,
                new Square("c5"), WHITE_PAWN,
                new Square("c4"), WHITE_PAWN
            ));
        GameResult gameResult = new GameResult(chessBoard);

        assertThat(gameResult.calculateScore(Color.WHITE)).isEqualTo(18.5);
    }
}
