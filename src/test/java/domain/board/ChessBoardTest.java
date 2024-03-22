package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Map;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class ChessBoardTest {
    @Test
    void 기물을_움직일_때_중간에_다른_기물이_있으면_예외가_발생한다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.EIGHT);
        Map<Position, Piece> pieceMap = Map.of(source, new Queen(Color.WHITE),
                new Position(File.F, Rank.FIVE), new Pawn(Color.BLACK));
        ChessBoard board = new ChessBoard(pieceMap);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중간에 말이 있어서 이동할 수 없습니다.");
    }

    @Test
    void 기물을_움직일_때_중간에_다른_기물이_없으면_이동한다() {
        Position source = new Position(File.F, Rank.FOUR);
        Piece piece = new Queen(Color.WHITE);
        Position target = new Position(File.F, Rank.EIGHT);
        Map<Position, Piece> pieceMap = Map.of(source, piece);
        ChessBoard board = new ChessBoard(pieceMap);

        board.move(source, target);
        assertThat(board).extracting("board")
                .asInstanceOf(InstanceOfAssertFactories.map(Position.class, Piece.class))
                .containsEntry(target, piece)
                .doesNotContainKey(source);
    }

    @Test
    void 기물을_잡는다() {
        Position source = new Position(File.F, Rank.FOUR);
        Piece piece = new Pawn(Color.WHITE);
        Position target = new Position(File.G, Rank.FIVE);
        Map<Position, Piece> pieceMap = Map.of(source, piece, target, new Pawn(Color.BLACK));
        ChessBoard board = new ChessBoard(pieceMap);

        board.move(source, target);
        assertThat(board).extracting("board")
                .asInstanceOf(InstanceOfAssertFactories.map(Position.class, Piece.class))
                .containsEntry(target, piece)
                .doesNotContainKey(source);
    }
}
