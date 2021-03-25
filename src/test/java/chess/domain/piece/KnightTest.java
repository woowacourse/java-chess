package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @DisplayName("나이트가 이동가능한 전체 위치를 구한다. 상황 : 흰나이트-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.of("e4");
        Piece knight = new Knight(PieceColor.WHITE);
        Path path = knight.generatePaths(current, new Board());
        assertThat(path.positions()).isEqualTo(knightE4WithoutObstacles());
    }

    @DisplayName("나이트가 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 흰나이트-e4 흰피스-d2,f2 검은피스-d6,f6")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.of("e4");
        Piece knight = new Knight(PieceColor.WHITE);

        Board board = new Board();
        Piece firstBlackPiece = new Bishop(PieceColor.BLACK);
        Piece secondBlackPiece = new Bishop(PieceColor.BLACK);
        Piece firstWhitePiece = new Bishop(PieceColor.WHITE);
        Piece secondWhitePiece = new Bishop(PieceColor.WHITE);
        board.putPiece(firstBlackPiece, Position.of("d6"));
        board.putPiece(secondBlackPiece, Position.of("f6"));
        board.putPiece(firstWhitePiece, Position.of("d2"));
        board.putPiece(secondWhitePiece, Position.of("f2"));
        Path path = knight.generatePaths(current, board);
        assertThat(path.positions()).isEqualTo(
            knightE4WithObstacles());
    }

    List<Position> knightE4WithoutObstacles() {
        return Arrays.asList(
            Position.of("d6"),
            Position.of("f6"),
            Position.of("g5"),
            Position.of("g3"),
            Position.of("f2"),
            Position.of("d2"),
            Position.of("c5"),
            Position.of("c3")
        );
    }

    List<Position> knightE4WithObstacles() {
        return Arrays.asList(
            Position.of("d6"),
            Position.of("f6"),
            Position.of("g5"),
            Position.of("g3"),
            Position.of("c5"),
            Position.of("c3")
        );
    }
}