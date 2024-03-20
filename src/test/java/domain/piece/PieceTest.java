package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.piecerole.Bishop;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    @DisplayName("같은 PieceType인 Piece 객체는 Position이 달라도 동등성을 유지한다.")
    @Test
    void generatePiece() {
        Piece piece = new Piece(new PieceType(new Bishop(), Color.BLACK), new Position(new File('b'), new Rank(1)));
        assertThat(piece).isEqualTo(
                new Piece(new PieceType(new Bishop(), Color.BLACK), new Position(new File('c'), new Rank(2)))
        );
    }

    @DisplayName("현재 위치에서 target 위치로 이동한다.")
    @Test
    void moveToTarget() {
        Piece piece = new Piece(new PieceType(new Bishop(), Color.BLACK), new Position(new File('b'), new Rank(1)));
        piece.move(new Position(new File('c'), new Rank(2)));
        Assertions.assertThat(piece.isEqualPosition(new Position(new File('c'), new Rank(2)))).isTrue();
    }
}
