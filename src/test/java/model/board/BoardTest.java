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

    @Test
    @DisplayName("체스 피스가 움직였을 때 보드에 업데이트가 일어나야 한다.")
    void updateTest() {
        Position destination = Position.of(1, 0);
        Piece piece = board.getPieceAt(Position.of(0, 0));
        Position lastPosition = piece.getPosition();
        piece.moveTo(destination);
        assertThat(board.getPieceAt(lastPosition).getPieceColor()).isEqualByComparingTo(PieceColor.EMPTY);
        assertThat(board.getPieceAt(destination).getPieceColor()).isNotEqualByComparingTo(PieceColor.EMPTY);
    }
}