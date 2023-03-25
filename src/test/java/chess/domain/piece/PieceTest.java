package chess.domain.piece;

import chess.domain.piece.movestrategy.KingMovementStrategy;
import chess.domain.piece.movestrategy.RookMovementStrategy;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Piece 은")
class PieceTest {

    private final Color myColor = Color.WHITE;
    private final Color enemyColor = Color.BLACK;

    @Test
    void 경유지탐색_시_같은_위치면_예외() {
        // given
        Piece myPiece = new Piece(myColor, PiecePosition.of(1, 'a'), new RookMovementStrategy());

        // when & then
        assertThatThrownBy(() -> myPiece.waypoints(PiecePosition.of(1, 'a'), null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 경유지탐색_시_도달불가능하면_오류() {
        // given
        Piece myPiece = new Piece(myColor, PiecePosition.of(1, 'a'), new RookMovementStrategy());

        // when & then
        assertThatThrownBy(() -> myPiece.waypoints(PiecePosition.of(2, 'c'), null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 단순_이동할_수_있다() {
        // given
        final Piece pawn = new Piece(myColor, PiecePosition.of("b6"), new RookMovementStrategy());

        // when
        final Piece next = pawn.move(PiecePosition.of("b5"), null);

        // then
        assertThat(next.piecePosition()).isEqualTo(PiecePosition.of("b5"));
    }

    @Test
    void 이동할_수_없는_경로로_이동하면_오류() {
        // given
        final Piece pawn = new Piece(myColor, PiecePosition.of("b6"), new RookMovementStrategy());

        // when & then
        assertThatThrownBy(() -> pawn.move(PiecePosition.of("c5"), null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 죽이기_위해_이동할_수_있따() {
        // given
        final Piece piece = new Piece(myColor, PiecePosition.of("b6"), new RookMovementStrategy());
        final Piece enemy = new Piece(enemyColor, PiecePosition.of("b7"), new RookMovementStrategy());

        // when
        final Piece next = piece.move(enemy.piecePosition(), enemy);

        // then
        assertThat(next.piecePosition()).isEqualTo(PiecePosition.of("b7"));
        assertThat(next.pieceMovementStrategy()).isInstanceOf(RookMovementStrategy.class);
    }

    @Test
    void 같은_타입인지_여부를_판단한다() {
        // given
        final Piece king = new Piece(myColor, PiecePosition.of("b6"), new KingMovementStrategy());
        final Piece rook = new Piece(enemyColor, PiecePosition.of("b7"), new RookMovementStrategy());

        // when & then
        assertThat(king.isSameType(MovementType.KING)).isTrue();
        assertThat(rook.isSameType(MovementType.KING)).isFalse();
    }
}
