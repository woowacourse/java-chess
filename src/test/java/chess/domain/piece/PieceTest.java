package chess.domain.piece;

import chess.domain.piece.strategy.PieceDirection;
import chess.domain.piece.strategy.PawnMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("적이면 적으로 인식한다.")
    void is_enemy() {
        Piece whitePiece = new Pawn(Color.WHITE, new PawnMoveStrategy(PieceDirection.WHITE_PAWN));
        Piece blackPiece = new Pawn(Color.BLACK, new PawnMoveStrategy(PieceDirection.BLACK_PAWN));
        assertThat(whitePiece.isEnemy(blackPiece)).isTrue();
    }

    @Test
    @DisplayName("적이 아니면 적으로 인식하지 않는다.")
    void is_not_enemy() {
        Piece firstWhitePiece = new Pawn(Color.WHITE, new PawnMoveStrategy(PieceDirection.WHITE_PAWN));
        Piece secondWhitePiece = new Pawn(Color.WHITE, new PawnMoveStrategy(PieceDirection.WHITE_PAWN));
        assertThat(firstWhitePiece.isEnemy(secondWhitePiece)).isFalse();
    }
}