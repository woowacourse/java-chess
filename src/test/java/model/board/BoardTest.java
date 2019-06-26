package model.board;

import model.Position;
import model.piece.Piece;
import model.piece.PieceColor;
import model.piece.PieceFactory;
import model.piece.impl.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new BoardBuilder()
                .piece(PieceFactory.create(Pawn.class, PieceColor.BLACK, Position.of(0, 0)))
                .build();
    }

    @Test
    @DisplayName("특정 포지션에 있는 말을 반환받는다.")
    void getPeiceAtNotEmptyPosition() {
        //when
        Piece piece = board.getPieceAt(Position.of(0, 0));

        //then
        assertThat(piece.getPieceColor()).isNotEqualByComparingTo(PieceColor.EMPTY);
        assertThat(piece.getPosition()).isEqualTo(Position.of(0, 0));
    }

    @Test
    @DisplayName("특정 포지션에 말이 없을 때는 빈 말을 반환받는다.")
    void getPeiceAtEmptyPosition() {
        //when
        Piece piece = board.getPieceAt(Position.of(7, 7));

        //then
        assertThat(piece.getPieceColor()).isEqualByComparingTo(PieceColor.EMPTY);
        assertThat(piece.getPosition()).isEqualTo(Position.of(7, 7));
    }
}