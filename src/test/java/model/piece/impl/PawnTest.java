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

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @Test
    @DisplayName("적이 없을 때 움직일 수 있는 지점들을 모두 되돌려준다")
    void isMovableToTest() {
        Position position = Position.of(1, 1);
        Piece pawn = PieceFactory.create(Pawn.class, PieceColor.WHITE, position);

        Board board = new BoardBuilder()
                .piece(pawn)
                .build();

        BoardView boardView = board.getBoardView();

        assertThat(pawn.getMovablePositions(boardView)).contains(
                position.of(Direction.NORTH),
                position.of(Direction.NORTH).of(Direction.NORTH)
        );
    }

    @Test
    @DisplayName("적이 있을 때 움직일 수 있는 지점들을 모두 되돌려준다")
    void isAttackableTest() {
        Position position = Position.of(1, 1);
        Piece pawn = PieceFactory.create(Pawn.class, PieceColor.WHITE, position);
        Piece enemy = PieceFactory.create(Pawn.class, PieceColor.BLACK, position.of(Direction.NORTH_EAST));

        Board board = new BoardBuilder()
                .piece(pawn)
                .piece(enemy)
                .build();

        BoardView boardView = board.getBoardView();

        assertThat(pawn.getMovablePositions(boardView)).contains(
                position.of(Direction.NORTH),
                position.of(Direction.NORTH).of(Direction.NORTH),
                position.of(Direction.NORTH_EAST)
        );
    }

    @Test
    @DisplayName("앞이 적으로 막혀있을 때 움직일 수 있는 지점들을 모두 되돌려준다")
    void cannotMoveForwardBecauseOfEnemy() {
        Position position = Position.of(1, 1);
        Piece myPawn = PieceFactory.create(Pawn.class, PieceColor.WHITE, position);
        Piece enemy = PieceFactory.create(Pawn.class, PieceColor.BLACK, position.of(Direction.NORTH));

        Board board = new BoardBuilder()
                .piece(myPawn)
                .piece(enemy)
                .build();
        BoardView boardView = board.getBoardView();

        assertThat(myPawn.getMovablePositions(boardView)).isEmpty();
    }

    @Test
    @DisplayName("앞이 아군으로 막혀있을 때 움직일 수 있는 지점들을 모두 되돌려준다")
    void cannotMoveForwardBecauseOfColleague() {
        Position position = Position.of(1, 1);
        Piece myPawn = PieceFactory.create(Pawn.class, PieceColor.WHITE, position);
        Piece friend = PieceFactory.create(Pawn.class, PieceColor.WHITE, position.of(Direction.NORTH));

        Board board = new BoardBuilder()
                .piece(myPawn)
                .piece(friend)
                .build();
        BoardView boardView = board.getBoardView();

        assertThat(myPawn.getMovablePositions(boardView)).isEmpty();
    }

    @Test
    @DisplayName("한 번이라도 움직인 적이 있으면 앞으로는 한칸만 전진 가능하다.")
    void hasMoved() {
        Position position = Position.of(1, 1);
        Piece myPawn = PieceFactory.create(Pawn.class, PieceColor.WHITE, position);

        Board board = new BoardBuilder()
                .piece(myPawn)
                .build();

        myPawn.moveTo(position.of(Direction.NORTH));

        BoardView boardView = board.getBoardView();
        Position movedPosition = position.of(Direction.NORTH);

        assertThat(myPawn.getMovablePositions(boardView)).contains(
                movedPosition.of(Direction.NORTH)
        );
    }

    @Test
    @DisplayName("체스판 끝에 서있는 폰은 앞으로 갈 수 없다.")
    void onEdgeOfChessBoard() {
        Position position = Position.of(1, 7);
        Piece myPawn = PieceFactory.create(Pawn.class, PieceColor.WHITE, position);

        Board board = new BoardBuilder()
                .piece(myPawn)
                .build();

        BoardView boardView = board.getBoardView();

        assertThat(myPawn.getMovablePositions(boardView)).isEmpty();
    }
}