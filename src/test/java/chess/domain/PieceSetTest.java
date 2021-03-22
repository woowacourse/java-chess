package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.player.BlackSet;
import chess.domain.player.PieceSet;
import chess.domain.player.Score;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceSetTest {
    @Test
    @DisplayName("초기화될 당시 점수계산 확인")
    void score() {
        PieceSet pieces = new BlackSet();

        assertThat(pieces.calculateScore()).isEqualTo(new Score(38));
    }

    @Test
    @DisplayName("각 1개씩 있을 때 점수계산 확인")
    void score_1() {
        List<Piece> piecesValue = Arrays.asList(
            new Pawn(TeamColor.WHITE, Position.valueOf("b1")),
            new Knight(TeamColor.WHITE, Position.valueOf("b1")),
            new Queen(TeamColor.WHITE, Position.valueOf("b1")),
            new Bishop(TeamColor.WHITE, Position.valueOf("b1")),
            new King(TeamColor.WHITE, Position.valueOf("b1")),
            new Rook(TeamColor.WHITE, Position.valueOf("b1"))
        );

        PieceSet pieces = new BlackSet(piecesValue);

        assertThat(pieces.calculateScore()).isEqualTo(new Score(20.5));
    }

    @Test
    @DisplayName("폰이 하나만 있을 경우 1점이여야 한다")
    void score_one_pawn() {
        List<Piece> piecesValue = Arrays.asList(
            new Pawn(TeamColor.WHITE, Position.valueOf("b1"))
        );

        PieceSet pieces = new BlackSet(piecesValue);

        assertThat(pieces.calculateScore()).isEqualTo(new Score(1));
    }

    @Test
    @DisplayName("동일한 세로축에 폰이 있을 경우")
    void score_column_three_pawn() {
        List<Piece> piecesValue = Arrays.asList(
            new Pawn(TeamColor.WHITE, Position.valueOf("b1")),
            new Pawn(TeamColor.WHITE, Position.valueOf("b2")),
            new Pawn(TeamColor.WHITE, Position.valueOf("b3"))
        );
        PieceSet pieces = new BlackSet(piecesValue);

        assertThat(pieces.calculateScore()).isEqualTo(new Score(1.5));
    }

}
