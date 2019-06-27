package model.piece.impl;

import model.Position;
import model.board.Board;
import model.board.BoardBuilder;
import model.board.BoardView;
import model.piece.Piece;
import model.piece.PieceColor;
import model.piece.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    @DisplayName("비숍의 움직일 수 있는 좌표를 모드 되돌려준다.")
    void getMovablePositionsTest() {
        Position position = Position.of(4, 4);
        Piece bishop = PieceFactory.create(Bishop.class, PieceColor.BLACK, position);
        Piece myPiece = PieceFactory.create(Pawn.class, PieceColor.BLACK,
                position.of(SOUTH_WEST).of(SOUTH_WEST));
        Piece enemyPiece = PieceFactory.create(Pawn.class, PieceColor.WHITE,
                position.of(NORTH_WEST).of(NORTH_WEST));

        Board board = new BoardBuilder()
                .piece(bishop)
                .piece(myPiece)
                .piece(enemyPiece)
                .build();

        BoardView boardView = board.getBoardView();

        assertThat(bishop.getMovablePositions(boardView)).contains(
                position.of(NORTH_WEST),
                position.of(NORTH_WEST).of(NORTH_WEST),
                position.of(NORTH_EAST),
                position.of(NORTH_EAST).of(NORTH_EAST),
                position.of(NORTH_EAST).of(NORTH_EAST).of(NORTH_EAST),
                position.of(SOUTH_EAST),
                position.of(SOUTH_EAST).of(SOUTH_EAST),
                position.of(SOUTH_EAST).of(SOUTH_EAST).of(SOUTH_EAST),
                position.of(SOUTH_WEST)
        );
    }
}