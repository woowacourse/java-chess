package chessgame.domain.piece;

import chessgame.domain.piece.kind.jumping.King;
import chessgame.domain.piece.kind.jumping.Knight;
import chessgame.domain.piece.kind.sliding.Bishop;
import chessgame.domain.piece.kind.sliding.Queen;
import chessgame.domain.piece.kind.sliding.Rook;
import chessgame.fixture.PieceImpl;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chessgame.domain.piece.attribute.Color;
import chessgame.domain.piece.attribute.point.File;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.domain.piece.attribute.point.Rank;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {

    @Test
    @DisplayName("기물 목록을 포함한 일급컬렉션을 생성한다.")
    void create_with_PieceList() {
        Set<Piece> pieceList = Set.of(new PieceImpl(new Point(File.F, Rank.ONE), Color.BLACK),
                new PieceImpl(new Point(File.B, Rank.ONE), Color.WHITE));

        final var sut = new Pieces(pieceList);

        assertThat(sut).isInstanceOf(Pieces.class);
    }

    @Test
    @DisplayName("해당 기물이 특정 기물들 사이에서 이동할 수 없으면 거짓을 반환한다.")
    void false_if_bishop_piece_can_move() {
        final var sut = new Pieces(Set.of(
                new Bishop(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.C, Rank.THREE), Color.WHITE),
                new Queen(new Point(File.E, Rank.FIVE), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.canReplace(piece, new Point(File.E, Rank.FIVE));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("해당 기물이 특정 기물들 사이에서 이동할 수 없으면 거짓을 반환한다.")
    void false_if_rook_piece_can_move() {
        final var sut = new Pieces(Set.of(
                new Rook(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.C, Rank.ONE), Color.WHITE),
                new Queen(new Point(File.E, Rank.FIVE), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.canReplace(piece, new Point(File.E, Rank.ONE));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("해당 기물이 특정 기물들 사이에서 이동할 수 없으면 거짓을 반환한다.")
    void false_if_queen_piece_can_move() {
        final var sut = new Pieces(Set.of(
                new Queen(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.C, Rank.THREE), Color.WHITE),
                new Queen(new Point(File.E, Rank.FIVE), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.canReplace(piece, new Point(File.E, Rank.FIVE));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("해당 퀸이 특정 기물들 사이에서 이동할 수 있으면 참을 반환한다.")
    void true_if_queen_piece_can_move() {

        final var sut = new Pieces(Set.of(
                new Queen(new Point(File.A, Rank.ONE), Color.BLACK),
                new Bishop(new Point(File.C, Rank.ONE), Color.WHITE),
                new Queen(new Point(File.E, Rank.ONE), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.canReplace(piece, new Point(File.A, Rank.THREE));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("나이트는 도착하는 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_knight_piece_move_friend_point() {
        final var sut = new Pieces(Set.of(
                new Knight(new Point(File.A, Rank.ONE), Color.BLACK),
                new Queen(new Point(File.C, Rank.TWO), Color.BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.canReplace(piece, new Point(File.C, Rank.TWO));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("킹은 도착하는 위치에 아군 기물이 있으면 거짓을 반환한다.")
    void false_if_king_piece_move_friend_point() {
        final var sut = new Pieces(Set.of(
                new King(new Point(File.A, Rank.ONE), Color.BLACK),
                new Queen(new Point(File.B, Rank.TWO), Color.BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        final var result = sut.canReplace(piece, new Point(File.B, Rank.TWO));
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("킹은 도착하는 위치에 아군 기물이 아니면 참을 반환한다.")
    void true_if_king_piece_move_friend_point() {
        final var sut = new Pieces(Set.of(
                new King(new Point(File.A, Rank.ONE), Color.BLACK),
                new Queen(new Point(File.B, Rank.TWO), Color.WHITE)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .orElseThrow();

        final var result = sut.canReplace(piece, new Point(File.B, Rank.TWO));
        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("기물을 해당 좌표로 이동시킨다.")
    void move_piece_to_point() {
        final var sut = new Pieces(Set.of(
                new Knight(new Point(File.A, Rank.ONE), Color.BLACK),
                new Queen(new Point(File.C, Rank.TWO), Color.BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE)).orElseThrow();

        sut.replace(piece, new Point(File.B, Rank.THREE));

        final var findPiece = sut.findPieceWithPoint(new Point(File.B, Rank.THREE))
                                 .orElseThrow();
        assertThat(findPiece).isEqualTo(new Knight(new Point(File.B, Rank.THREE), Color.BLACK));
    }

}
