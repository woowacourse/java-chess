package domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.TeamColor;
import chess.domain.result.Score;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PiecesTest {

    @Test
    @DisplayName("0일때 점수계산 확인")
    void score() {
        List<Piece> piecesValue = Collections.emptyList();
        Pieces pieces = new Pieces(piecesValue);

        assertThat(pieces.calculateScore()).isEqualTo(Score.ZERO);
    }


    @Test
    @DisplayName("각 1개씩 있을 때 점수계산 확인")
    void score_1() {
        // rook : 5, b: 3, q: 9, : p1 , n: 2.5
        List<Piece> piecesValue = Arrays.asList(
            new Rook(TeamColor.WHITE, Position.valueOf("b1")),
            new Bishop(TeamColor.WHITE, Position.valueOf("b1")),
            new Queen(TeamColor.WHITE, Position.valueOf("b1")),
            new Pawn(TeamColor.WHITE, Position.valueOf("b1")),
            new Knight(TeamColor.WHITE, Position.valueOf("b1")),
            new King(TeamColor.WHITE, Position.valueOf("b1"))
        );
        Pieces pieces = new Pieces(piecesValue);

        assertThat(pieces.calculateScore()).isEqualTo(new Score(20.5));
    }

    @Test
    @DisplayName("폰이 하나만 있을 경우 1점이여야 한다")
    void score_one_pawn() {
        // rook : 5, b: 3, q: 9, : p1 , n: 2.5
        List<Piece> piecesValue = Arrays.asList(
            new Pawn(TeamColor.WHITE, Position.valueOf("b1"))
        );
        Pieces pieces = new Pieces(piecesValue);

        assertThat(pieces.calculateScore()).isEqualTo(new Score(1));
    }

    @Test
    @DisplayName("동일한 세로축에 폰이 있을 경우")
    void score_column_three_pawn() {
        // rook : 5, b: 3, q: 9, : p1 , n: 2.5
        List<Piece> piecesValue = Arrays.asList(
            new Pawn(TeamColor.WHITE, Position.valueOf("b1")),
            new Pawn(TeamColor.WHITE, Position.valueOf("b2")),
            new Pawn(TeamColor.WHITE, Position.valueOf("b3"))
        );
        Pieces pieces = new Pieces(piecesValue);

        assertThat(pieces.calculateScore()).isEqualTo(new Score(1.5));
    }
}
