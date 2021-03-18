package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
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
        Map<Coordinate, Piece> cells = board.getCells();
        Rook rook = new Rook(TeamType.BLACK);
        Coordinate currentCoordinate = Coordinate.from("d4");
        Coordinate targetCoordinate = Coordinate.from("d5");

        cells.put(currentCoordinate, rook);
        rook.move(board, currentCoordinate, targetCoordinate);

        assertThat(cells.get(currentCoordinate)).isNull();
        assertThat(cells.get(targetCoordinate)).isSameAs(rook);
    }

    @DisplayName("목적지에 적 기물 - 룩 이동 - 이동 가능")
    @Test
    void rookMoveEnemyPieceOnTargetCoordinate() {
        Board board = Board.getInstance();
        Map<Coordinate, Piece> cells = board.getCells();
        Rook rook = new Rook(TeamType.BLACK);

        Coordinate currentCoordinate = Coordinate.from("d4");
        Coordinate routeCoordinate = Coordinate.from("d7");
        Coordinate targetCoordinate = Coordinate.from("d7");

        cells.put(currentCoordinate, rook);
        Piece enemyPiece = new Queen(TeamType.WHITE);
        cells.put(routeCoordinate, enemyPiece);
        rook.move(board, currentCoordinate, targetCoordinate);

        assertThat(cells.get(currentCoordinate)).isNull();
        assertThat(cells.get(targetCoordinate)).isSameAs(rook);
    }

    @DisplayName("빈 체스판 - 룩 이동 - 이동 불가 - 이동 경로 중간에 다른 기물 존재")
    @Test
    void rookCannotMoveOtherPieceOnRoute() {
        Board board = Board.getInstance();
        Map<Coordinate, Piece> cells = board.getCells();
        Rook rook = new Rook(TeamType.BLACK);
        Coordinate currentCoordinate = Coordinate.from("d4");
        Coordinate routeCoordinate = Coordinate.from("d6");
        Coordinate targetCoordinate = Coordinate.from("d7");

        cells.put(currentCoordinate, rook);
        cells.put(routeCoordinate, new Rook(TeamType.WHITE));
        assertThatThrownBy(() -> rook.move(board, currentCoordinate, targetCoordinate))
            .isInstanceOf(IllegalArgumentException.class);

        assertThat(cells.get(currentCoordinate)).isSameAs(rook);
        assertThat(cells.get(targetCoordinate)).isNull();
    }

    @DisplayName("빈 체스판 - 룩 이동 - 이동 불가 - 도착 위치에 자신의 기물 존재")
    @Test
    void rookCannotMoveOwnPieceOnTargetCoordinate() {
        Board board = Board.getInstance();
        Map<Coordinate, Piece> cells = board.getCells();
        Rook rook = new Rook(TeamType.BLACK);

        Coordinate currentCoordinate = Coordinate.from("d4");
        Coordinate routeCoordinate = Coordinate.from("d7");
        Coordinate targetCoordinate = Coordinate.from("d7");

        cells.put(currentCoordinate, rook);
        Piece ownPiece = new Pawn(TeamType.BLACK);
        cells.put(routeCoordinate, ownPiece);
        assertThatThrownBy(() -> rook.move(board, currentCoordinate, targetCoordinate))
            .isInstanceOf(IllegalArgumentException.class);

        assertThat(cells.get(currentCoordinate)).isSameAs(rook);
        assertThat(cells.get(targetCoordinate)).isSameAs(ownPiece);
    }

    @DisplayName("빈 체스판 - 룩 이동 - 이동 불가 - 이동할 수 없는 도착 위치")
    @Test
    void rookCannotInvalidTargetCoordinate() {
        Board board = Board.getInstance();
        Map<Coordinate, Piece> cells = board.getCells();
        Rook rook = new Rook(TeamType.BLACK);

        Coordinate currentCoordinate = Coordinate.from("d4");
        Coordinate targetCoordinate = Coordinate.from("c5");

        cells.put(currentCoordinate, rook);
        assertThatThrownBy(() -> rook.move(board, currentCoordinate, targetCoordinate))
            .isInstanceOf(IllegalArgumentException.class);

        assertThat(cells.get(currentCoordinate)).isSameAs(rook);
    }
}