package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.Piece;
import chess.domain.piece.type.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("같은 포지션인지 여부를 반환한다.")
    void isSamePosition() {
        // given
        final Piece piece = new Rook(new Position(File.A, Rank.ONE), Side.WHITE);
        final Position positionToCompare = new Position(File.A, Rank.ONE);

        // when, then
        assertThat(piece.isSamePosition(positionToCompare)).isTrue();
    }

    @Test
    @DisplayName("같은 진영인지 여부를 반환한다.")
    void isSameSide() {
        // given
        final Piece piece = new Rook(new Position(File.A, Rank.ONE), Side.WHITE);
        final Piece sameSidePiece = new Bishop(new Position(File.C, Rank.ONE), Side.WHITE);
        final Piece differentSidePiece = new Bishop(new Position(File.C, Rank.EIGHT), Side.BLACK);

        // when, then
        assertThat(piece.isSameSide(sameSidePiece)).isTrue();
        assertThat(piece.isSameSide(differentSidePiece)).isFalse();
    }

    @Test
    @DisplayName("받은 위치로 이동한다.")
    void move() {
        // given
        final Piece piece = new Rook(new Position(File.A, Rank.ONE), Side.WHITE);
        final Position positionToMove = new Position(File.F, Rank.ONE);

        // when
        final Piece movedPiece = piece.move(positionToMove);

        // then
        assertThat(movedPiece).isInstanceOf(Rook.class);
        assertThat(movedPiece.isSamePosition(positionToMove)).isTrue();
    }
}
