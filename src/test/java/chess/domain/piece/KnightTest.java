package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.move.PassingMove;
import chess.domain.piece.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("나이트는 L자 모양으로 움직일 수 있다.")
    void isMovable() {
        Piece knight = new Knight(Camp.WHITE);

        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.C, Rank.TWO);

        PieceMove result = knight.getMovement(from, to);

        assertThat(result).isInstanceOf(PassingMove.class);
    }
}
