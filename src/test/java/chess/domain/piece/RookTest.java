package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.board.Board;
import chess.domain.board.Cell;
import chess.domain.board.Coordinate;
import chess.domain.player.TeamType;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("생성 테스트")
    @Test
    void makeRook() {
        assertThatCode(() -> new Rook(TeamType.BLACK))
            .doesNotThrowAnyException();
    }

    @DisplayName("빈 체스판 - 룩 이동 - 이동 가능")
    @Test
    void rookMoveOnEmptyBoard() {
        Board board = Board.getInstance();
        Map<Coordinate, Cell> cells = board.getCells();
        Rook rook = new Rook(TeamType.BLACK);
        Coordinate currentCoordinate = Coordinate.from("d4");
        Coordinate targetCoordinate = Coordinate.from("d5");

        cells.get(currentCoordinate).put(rook);
        boolean isMovable = rook.isMovableTo(board, currentCoordinate, targetCoordinate);

        assertThat(isMovable).isTrue();
    }

    @DisplayName("목적지에 적 기물 - 룩 이동 - 이동 가능")
    @Test
    void rookMoveEnemyPieceOnTargetCoordinate() {
        Board board = Board.getInstance();
        Map<Coordinate, Cell> cells = board.getCells();
        Rook rook = new Rook(TeamType.BLACK);

        Coordinate currentCoordinate = Coordinate.from("d4");
        Coordinate routeCoordinate = Coordinate.from("d7");
        Coordinate targetCoordinate = Coordinate.from("d7");

        cells.get(currentCoordinate).put(rook);
        Piece enemyPiece = new Queen(TeamType.WHITE);
        cells.get(routeCoordinate).put(enemyPiece);
        boolean isMovable = rook.isMovableTo(board, currentCoordinate, targetCoordinate);

        assertThat(isMovable).isTrue();
    }

    @DisplayName("빈 체스판 - 룩 이동 - 이동 불가 - 이동 경로 중간에 다른 기물 존재")
    @Test
    void rookCannotMoveOtherPieceOnRoute() {
        Board board = Board.getInstance();
        Map<Coordinate, Cell> cells = board.getCells();
        Rook rook = new Rook(TeamType.BLACK);
        Coordinate currentCoordinate = Coordinate.from("d4");
        Coordinate routeCoordinate = Coordinate.from("d6");
        Coordinate targetCoordinate = Coordinate.from("d7");

        cells.get(currentCoordinate).put(rook);
        cells.get(routeCoordinate).put(new Rook(TeamType.WHITE));

        boolean isMovable = rook.isMovableTo(board, currentCoordinate, targetCoordinate);
        assertThat(isMovable).isFalse();
    }

    @DisplayName("빈 체스판 - 룩 이동 - 이동 불가 - 도착 위치에 자신의 기물 존재")
    @Test
    void rookCannotMoveOwnPieceOnTargetCoordinate() {
        Board board = Board.getInstance();
        Map<Coordinate, Cell> cells = board.getCells();
        Rook rook = new Rook(TeamType.BLACK);

        Coordinate currentCoordinate = Coordinate.from("d4");
        Coordinate routeCoordinate = Coordinate.from("d7");
        Coordinate targetCoordinate = Coordinate.from("d7");

        cells.get(currentCoordinate).put(rook);
        Piece ownPiece = new Pawn(TeamType.BLACK);
        cells.get(routeCoordinate).put(ownPiece);

        boolean isMovable = rook.isMovableTo(board, currentCoordinate, targetCoordinate);

        assertThat(isMovable).isFalse();
    }

    @DisplayName("빈 체스판 - 룩 이동 - 이동 불가 - 이동할 수 없는 도착 위치")
    @Test
    void rookCannotInvalidTargetCoordinate() {
        Board board = Board.getInstance();
        Map<Coordinate, Cell> cells = board.getCells();
        Rook rook = new Rook(TeamType.BLACK);

        Coordinate currentCoordinate = Coordinate.from("d4");
        Coordinate targetCoordinate = Coordinate.from("c5");

        cells.get(currentCoordinate).put(rook);

        boolean isMovable = rook.isMovableTo(board, currentCoordinate, targetCoordinate);

        assertThat(isMovable).isFalse();
    }
}