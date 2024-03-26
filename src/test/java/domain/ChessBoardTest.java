package domain;

import domain.chess.piece.Pawn;
import domain.chess.piece.PieceStatus;
import fixture.PieceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.chess.Color;
import domain.chess.piece.Piece;
import domain.chess.piece.Pieces;
import domain.chess.File;
import domain.chess.Point;
import domain.chess.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static domain.chess.piece.PieceStatus.*;
import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardTest {
    @Test
    @DisplayName("기물들을 통해 체스판을 생성한다")
    void create_with_pieces() {
        final var point1 = F1;
        final var color1 = Color.BLACK;

        final var point2 = F2;
        final var color2 = Color.BLACK;
        final List<Piece> pieceList = List.of(new PieceImpl(point1, color1), new PieceImpl(point2, color2));
        final var pieces = new Pieces(pieceList);

        final var sut = new ChessBoard(pieces);

        assertThat(sut).isInstanceOf(ChessBoard.class);
    }

    @Test
    @DisplayName("포인트에 기물이 없으면 예외를 발생한다.")
    void throw_exception_when_not_exist_point() {
        final var point = F1;
        final var color = Color.BLACK;
        final List<Piece> pieceList = List.of(new PieceImpl(point, color));
        final var pieces = new Pieces(pieceList);
        final var sut = new ChessBoard(pieces);

        assertThatThrownBy(() -> sut.move(D4, D5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("차례와 다른 색깔의 기물을 선택하면 예외를 발생한다.")
    void throw_exception_when_not_match_piece_in_turn() {
        final List<Piece> pieceList = List.of(new Pawn(C3, Color.BLACK));

        final var sut = new ChessBoard(new Pieces(pieceList), Color.WHITE);

        assertThatThrownBy(() -> sut.move(C3, C4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("차례입니다");
    }
}
