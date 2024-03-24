package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.nonpawn.Queen;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardTest {
    @Test
    void 출발_위치에_기물_존재하지_않으면_예외가_발생한다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.EIGHT);
        ChessBoard board = new ChessBoard(Map.of());

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("피스가 없습니다.");
    }

    @Test
    void 기물을_움직일_때_중간에_다른_기물이_있으면_예외가_발생한다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.EIGHT);
        Position between = new Position(File.F, Rank.FIVE);
        ChessBoard board = new ChessBoard(Map.of(
                source, new Queen(Color.WHITE),
                between, new BlackPawn()
        ));

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중간에 말이 있어서 이동할 수 없습니다.");
    }

    @Test
    void 기물을_움직일_때_중간에_다른_기물이_없으면_이동한다() {
        Piece piece = new Queen(Color.WHITE);
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.EIGHT);
        ChessBoard board = new ChessBoard(Map.of(source, piece));

        board.move(source, target);
        assertThat(board.getBoard())
                .containsEntry(target, piece)
                .doesNotContainKey(source);
    }

    @Test
    void 기물을_잡는다() {
        Piece piece = new WhitePawn();
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.G, Rank.FIVE);
        ChessBoard board = new ChessBoard(Map.of(
                source, piece,
                target, new BlackPawn())
        );

        board.move(source, target);
        assertThat(board.getBoard())
                .containsEntry(target, piece)
                .doesNotContainKey(source);
    }

    @Test
    void 게임을_시작하고_피스를_한_번_움직이면_다음은_BLACK_턴이다() {
        Position resource = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.EIGHT);
        ChessBoard board = new ChessBoard(Map.of(resource, new Queen(Color.WHITE)));
        board.move(resource, target);

        assertThatThrownBy(() -> board.move(target, resource))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대 턴입니다.");
    }

    @Test
    void 피스가_움직이지_않으면_턴이_바뀌지_않는다() {
        Position resource = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.EIGHT);
        Position between = new Position(File.F, Rank.FIVE);
        ChessBoard board = new ChessBoard(Map.of(
                resource, new Queen(Color.WHITE),
                between, new BlackPawn()
        ));

        assertThatThrownBy(() -> board.move(resource, target))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatCode(() -> board.move(resource, between))
                .doesNotThrowAnyException();
    }

    @Test
    void 피스를_두_번_움직이면_턴이_되돌아온다() {
        Position whiteResource = new Position(File.F, Rank.FOUR);
        Position whiteTarget = new Position(File.F, Rank.EIGHT);
        Position blackResource = new Position(File.A, Rank.FOUR);
        Position blackTarget = new Position(File.A, Rank.EIGHT);
        ChessBoard board = new ChessBoard(Map.of(
                whiteResource, new Queen(Color.WHITE),
                blackResource, new Queen(Color.BLACK)
        ));

        board.move(whiteResource, whiteTarget);
        board.move(blackResource, blackTarget);

        assertThatCode(() -> board.move(whiteTarget, whiteResource))
                .doesNotThrowAnyException();
    }
}
