package model.piece.impl;

import model.Position;
import model.board.Board;
import model.board.BoardBuilder;
import model.board.BoardView;
import model.piece.Piece;
import model.piece.PieceColor;
import model.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    Position position;
    Piece king;

    @BeforeEach
    void setUp() {
        position = Position.of(4, 4);
        king = PieceFactory.create(King.class, PieceColor.BLACK, position);
    }

    @Test
    @DisplayName("킹의 움직일 수 있는 좌표를 모드 되돌려준다.")
    void getMovablePositionsTest() {
        Piece myPiece = PieceFactory.create(Pawn.class, PieceColor.BLACK,
                position.of(WEST));
        Piece enemyPiece = PieceFactory.create(Pawn.class, PieceColor.WHITE,
                position.of(NORTH));

        Board board = new BoardBuilder()
                .piece(king)
                .piece(myPiece)
                .piece(enemyPiece)
                .build();

        BoardView boardView = board.getBoardView();

        assertThat(king.getMovablePositions(boardView)).contains(
                position.of(NORTH),
                position.of(EAST),
                position.of(SOUTH_EAST),
                position.of(SOUTH),
                position.of(SOUTH_WEST),
                position.of(NORTH_WEST)
        );
    }

    @Test
    @DisplayName("우리 팀이 있는 경우 해당 자리로는 갈 수 없다.")
    void getMovablePositionsWhenObstacleIsMyTeam() {
        Piece myPiece = PieceFactory.create(Pawn.class, PieceColor.BLACK,
                position.of(4, 5));

        Board board = new BoardBuilder()
                .piece(king)
                .piece(myPiece)
                .build();

        assertThat(king.getMovablePositions(board.getBoardView())).doesNotContain(
                Position.of(4, 5));
    }

    @Test
    @DisplayName("적팀이 있는 경우 해당 자리로는 갈 수 있다.")
    void getMovablePositionsWhenObstacleIsEnemy() {
        Piece enemy = PieceFactory.create(Pawn.class, PieceColor.WHITE,
                position.of(4, 5));

        Board board = new BoardBuilder()
                .piece(king)
                .piece(enemy)
                .build();

        assertThat(king.getMovablePositions(board.getBoardView())).contains(
                Position.of(4, 5));
    }
}