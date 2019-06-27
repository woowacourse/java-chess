package model.piece.impl;

import model.Direction;
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

class KnightTest {
    @Test
    @DisplayName("나이트의 움직일 수 있는 좌표를 모드 되돌려준다.")
    void getMovablePositionsTest() {
        Position position = Position.of(4, 4);
        Piece knight = PieceFactory.create(Knight.class, PieceColor.BLACK, position);
        Piece myPiece = PieceFactory.create(Pawn.class, PieceColor.BLACK,
                position.of(EAST).of(Direction.NORTH_EAST));
        Piece enemyPiece = PieceFactory.create(Pawn.class, PieceColor.WHITE,
                position.of(EAST).of(SOUTH_EAST));

        Board board = new BoardBuilder()
                .piece(knight)
                .piece(myPiece)
                .piece(enemyPiece)
                .build();

        BoardView boardView = board.getBoardView();

        assertThat(knight.getMovablePositions(boardView)).contains(
                position.of(NORTH).of(NORTH_WEST),
                position.of(NORTH).of(NORTH_EAST),
                position.of(SOUTH).of(SOUTH_EAST),
                position.of(SOUTH).of(SOUTH_WEST),
                position.of(WEST).of(SOUTH_WEST),
                position.of(WEST).of(NORTH_WEST)
        );
    }
}