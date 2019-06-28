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

class KnightTest {
    Position position;
    Piece knight;

    @BeforeEach
    void setUp() {
        position = Position.of(4, 4);
        knight = PieceFactory.create(Knight.class, PieceColor.BLACK, position);
    }

    @Test
    @DisplayName("나이트는 앞으로 한칸 대각선으로 한칸 움직일 수 있다.")
    void getMovablePositionsTest() {
        Board board = new BoardBuilder()
                .piece(knight)
                .build();

        assertThat(knight.getMovablePositions(board.getBoardView())).contains(
                Position.of(6, 5));
    }

    @Test
    @DisplayName("우리 팀이 있는 경우 해당 자리로는 갈 수 없다.")
    void getMovablePositionsWhenObstacleIsMyTeam() {
        Piece myPiece = PieceFactory.create(Pawn.class, PieceColor.BLACK,
                position.of(6, 5));

        Board board = new BoardBuilder()
                .piece(knight)
                .piece(myPiece)
                .build();

        assertThat(knight.getMovablePositions(board.getBoardView())).doesNotContain(
                Position.of(6, 5));
    }

    @Test
    @DisplayName("적팀이 있는 경우 해당 자리는로는 갈 수 있다.")
    void getMovablePositionsWhenObstacleIsEnemy() {
        Piece enemy = PieceFactory.create(Pawn.class, PieceColor.WHITE,
                position.of(6, 5));

        Board board = new BoardBuilder()
                .piece(knight)
                .piece(enemy)
                .build();

        assertThat(knight.getMovablePositions(board.getBoardView())).contains(
                Position.of(6, 5));
    }

    @Test
    @DisplayName("경로에 우리 팀 장애물이 있어도 넘어갈 수 있다.")
    void getMovablePositionsWhenMyTeamAmongRoute() {
        Piece myPiece = PieceFactory.create(Pawn.class, PieceColor.BLACK,
                position.of(5, 5));
        Piece myPiece2 = PieceFactory.create(Pawn.class, PieceColor.BLACK,
                position.of(4, 5));

        Board board = new BoardBuilder()
                .piece(knight)
                .piece(myPiece)
                .piece(myPiece2)
                .build();

        assertThat(knight.getMovablePositions(board.getBoardView())).contains(
                Position.of(6, 5));
    }

    @Test
    @DisplayName("경로에 적팀 장애물이 있어도 넘어갈 수 있다.")
    void getMovablePositionsWhenEnemiesAmongRoute() {
        Piece enemy = PieceFactory.create(Pawn.class, PieceColor.WHITE,
                position.of(5, 5));
        Piece enemy2 = PieceFactory.create(Pawn.class, PieceColor.WHITE,
                position.of(4, 5));

        Board board = new BoardBuilder()
                .piece(knight)
                .piece(enemy)
                .piece(enemy2)
                .build();

        assertThat(knight.getMovablePositions(board.getBoardView())).contains(
                Position.of(6, 5));
    }
}