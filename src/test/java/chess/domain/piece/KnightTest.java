package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Paths;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    // todo : 여기 테스트들은 path에 있어도 될 것 같고?
    @DisplayName("나이트가 이동가능한 전체 위치를 구한다. 상황 : 흰나이트-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.ofName("e4");
        Piece knight = new Knight(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(knight, current);
        assertThat(paths.pathsToPosition()).isEqualTo(knightE4WithoutObstacles());
    }

    @DisplayName("나이트가 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 흰나이트-e4 흰피스-d2,f2 검은피스-d6,f6")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.ofName("e4");
        Piece knight = new Knight(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(knight, current);
        Board board = new Board();
        Piece firstBlackPiece = new Bishop(PieceColor.BLACK);
        Piece secondBlackPiece = new Bishop(PieceColor.BLACK);
        Piece firstWhitePiece = new Bishop(PieceColor.WHITE);
        Piece secondWhitePiece = new Bishop(PieceColor.WHITE);
        board.putPiece(firstBlackPiece, Position.ofName("d6"));
        board.putPiece(secondBlackPiece, Position.ofName("f6"));
        board.putPiece(firstWhitePiece, Position.ofName("d2"));
        board.putPiece(secondWhitePiece, Position.ofName("f2"));
        assertThat(paths.removeObstacles(knight, board).positions()).isEqualTo(
                knightE4WithObstacles());
    }

    List<Position> knightE4WithoutObstacles() {
        return Arrays.asList(
                Position.ofName("d6"),
                Position.ofName("f6"),
                Position.ofName("g5"),
                Position.ofName("g3"),
                Position.ofName("f2"),
                Position.ofName("d2"),
                Position.ofName("c5"),
                Position.ofName("c3")
        );
    }

    List<Position> knightE4WithObstacles() {
        return Arrays.asList(
                Position.ofName("d6"),
                Position.ofName("f6"),
                Position.ofName("g5"),
                Position.ofName("g3"),
                Position.ofName("c5"),
                Position.ofName("c3")
        );
    }
}