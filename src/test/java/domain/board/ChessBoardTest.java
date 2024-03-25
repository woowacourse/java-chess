package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.nonpawn.Queen;
import domain.piece.pawn.BlackPawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ChessBoardTest {
    @Test
    void 기물을_움직일_때_중간에_다른_기물이_있으면_예외가_발생한다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.EIGHT);
        ChessBoard board = new ChessBoard(Map.of(
                source, new Queen(Color.WHITE),
                new Position(File.F, Rank.FIVE), new Queen(Color.BLACK)));

        assertThatThrownBy(() -> board.movePiece(source, target))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 다른 기물이 존재합니다.");
    }

    @Test
    void 기물을_움직일_때_중간에_다른_기물이_없으면_이동한다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.EIGHT);
        Piece piece = new Queen(Color.WHITE);
        ChessBoard board = new ChessBoard(Map.of(source, piece));

        board.movePiece(source, target);

        assertThat(board.getPositionAndPieces()).containsEntry(target, piece)
                .doesNotContainKey(source);
    }

    @Test
    void 기물을_잡는다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.G, Rank.FIVE);
        Piece sourcePiece = new Queen(Color.WHITE);
        BlackPawn targetPiece = new BlackPawn();
        ChessBoard board = new ChessBoard(Map.of(
                source, sourcePiece,
                target, targetPiece));

        board.movePiece(source, target);

        assertThat(board.getPositionAndPieces()).containsEntry(target, sourcePiece)
                .doesNotContainValue(targetPiece);
    }
}
