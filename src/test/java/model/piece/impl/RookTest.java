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

class RookTest {
    Position position;
    Piece rook;

    @BeforeEach
    void setUp() {
        position = Position.of(4, 4);
        rook = PieceFactory.create(Rook.class, PieceColor.BLACK, position);
    }

    @Test
    @DisplayName("아무것도 없는 경우 끝까지 움직일 수 있다.")
    void getMovablePositionsBoundTest() {
        Board board = new BoardBuilder()
                .piece(rook)
                .build();

        assertThat(rook.getMovablePositions(board.getBoardView())).contains(
                Position.of(4, 7));
    }

    @Test
    @DisplayName("우리 팀이 있는 경우 해당 자리 포함하여 뒤로는 갈 수 없다.")
    void getMovablePositionsWhenObstacleIsMyTeam() {
        Piece myPiece = PieceFactory.create(Pawn.class, PieceColor.BLACK,
                position.of(4, 6));

        Board board = new BoardBuilder()
                .piece(rook)
                .piece(myPiece)
                .build();

        assertThat(rook.getMovablePositions(board.getBoardView())).doesNotContain(
                Position.of(4, 6));
        assertThat(rook.getMovablePositions(board.getBoardView())).doesNotContain(
                Position.of(4, 7));
    }

    @Test
    @DisplayName("적팀이 있는 경우 해당 자리는 움직이고 그 뒤로는 갈 수 없다.")
    void getMovablePositionsWhenObstacleIsEnemy() {
        Piece enemy = PieceFactory.create(Pawn.class, PieceColor.WHITE,
                position.of(4, 6));

        Board board = new BoardBuilder()
                .piece(rook)
                .piece(enemy)
                .build();

        assertThat(rook.getMovablePositions(board.getBoardView())).contains(
                Position.of(4, 6));
        assertThat(rook.getMovablePositions(board.getBoardView())).doesNotContain(
                Position.of(4, 7));
    }
}