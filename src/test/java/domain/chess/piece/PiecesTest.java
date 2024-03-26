package domain.chess.piece;

import fixture.PieceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.chess.File;
import domain.chess.Point;
import domain.chess.Rank;

import java.util.List;

import static domain.chess.Color.BLACK;
import static domain.chess.Color.WHITE;
import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {
    @Test
    @DisplayName("기물 목록을 포함한 일급컬렉션을 생성한다.")
    void create_with_PieceList() {
        final var point1 = new Point(File.F, Rank.ONE);
        final var color1 = BLACK;

        final var point2 = new Point(File.F, Rank.ONE);
        final var color2 = BLACK;
        final List<Piece> pieceList = List.of(new PieceImpl(point1, color1), new PieceImpl(point2, color2));

        final var sut = new Pieces(pieceList);

        assertThat(sut).isInstanceOf(Pieces.class);
    }

    @Test
    @DisplayName("기물을 해당 좌표로 이동시킨다.")
    void move_piece_to_point() {
        final var sut = new Pieces(List.of(
                new Knight(A1, BLACK),
                new Queen(C2, BLACK)));

        final var piece = sut.findPieceWithPoint(new Point(File.A, Rank.ONE))
                             .get();

        sut.move(piece, B3);

        final var findPiece = sut.findPieceWithPoint(B3)
                                 .get();
        assertThat(piece).isEqualTo(findPiece);
    }

    @Test
    @DisplayName("기물이 이동할 때 해당 좌표에 적 기물이 있으면 제거한다.")
    void move_piece_with_remove_if_exist_enemy_piece() {
        final var sut = new Pieces(List.of(
                new Knight(A1, BLACK),
                new Queen(C2, WHITE)));

        final var piece = sut.findPieceWithPoint(A1)
                             .get();

        sut.move(piece, C2);

        assertThat(sut.size()).isEqualTo(1);
    }
}
