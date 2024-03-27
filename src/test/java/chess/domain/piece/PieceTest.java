package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("두 기물이 같은 색상이면 True를 리턴한다.")
    @Test
    void returnTrueWhenPiecesAreSameColor() {
        final Piece piece = new Piece(PieceType.ROOK, PieceColor.WHITE);
        final PieceColor other = PieceColor.WHITE;

        final boolean actual = piece.isSameColor(other);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지에서 목적지로 이동할 수 있는지 확인한다.")
    @Test
    void checkCanMoveFromSourceToTarget() {
        final Piece piece = new Piece(PieceType.ROOK, PieceColor.WHITE);
        final Square source = new Square(File.b, Rank.FOUR);
        final Square target = new Square(File.b, Rank.FIVE);

        final boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();
    }
}

