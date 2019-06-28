package model.piece.impl;

import model.Position;
import model.board.Board;
import model.board.BoardBuilder;
import model.piece.Piece;
import model.piece.PieceColor;
import model.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    Position position;
    Piece bishop;

    @BeforeEach
    void setUp() {
        position = Position.of(4, 4);
        bishop = PieceFactory.create(Bishop.class, PieceColor.BLACK, position);
    }

    @Test
    @DisplayName("아무것도 없는 경우 끝까지 움직일 수 있다.")
    void getMovablePositionsBoundTest() {
        Board board = new BoardBuilder()
                .piece(bishop)
                .build();

        assertThat(bishop.getMovablePositions(board.getBoardView())).contains(
                Position.of(7, 7));
    }

    @Test
    @DisplayName("우리 팀이 있는 경우 해당 자리 포함하여 뒤로는 갈 수 없다.")
    void getMovablePositionsWhenObstacleIsMyTeam() {
        Piece myPiece = PieceFactory.create(Pawn.class, PieceColor.BLACK,
                position.of(6, 6));

        Board board = new BoardBuilder()
                .piece(bishop)
                .piece(myPiece)
                .build();

        assertThat(bishop.getMovablePositions(board.getBoardView())).contains(
                Position.of(5, 5));
        assertThat(bishop.getMovablePositions(board.getBoardView())).doesNotContain(
                Position.of(6, 6));
        assertThat(bishop.getMovablePositions(board.getBoardView())).doesNotContain(
                Position.of(7, 7));
    }

    @Test
    @DisplayName("적팀이 있는 경우 해당 자리는 움직이고 그 뒤로는 갈 수 없다.")
    void getMovablePositionsWhenObstacleIsEnemy() {
        Piece enemy = PieceFactory.create(Pawn.class, PieceColor.WHITE,
                position.of(6, 6));

        Board board = new BoardBuilder()
                .piece(bishop)
                .piece(enemy)
                .build();

        assertThat(bishop.getMovablePositions(board.getBoardView())).contains(
                Position.of(6, 6));
        assertThat(bishop.getMovablePositions(board.getBoardView())).doesNotContain(
                Position.of(7, 7));
    }
}