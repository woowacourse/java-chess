package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("킹이 이동가능한 전체 위치를 구한다. 상황 : 흰킹-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.of("e4");
        Piece king = new King(PieceColor.WHITE);
        Path path = king.generatePaths(current, new Board());

        assertThat(path.positions()).isEqualTo(kingE4WithoutObstacles());
    }

    @DisplayName("킹이 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 흰킹-e4 흰피스-e3,f3 검은피스-e5,f5")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.of("e4");
        Piece king = new King(PieceColor.WHITE);
        Board board = new Board();
        Piece firstBlackPiece = new Knight(PieceColor.BLACK);
        Piece secondBlackPiece = new Knight(PieceColor.BLACK);
        Piece firstWhitePiece = new Knight(PieceColor.WHITE);
        Piece secondWhitePiece = new Knight(PieceColor.WHITE);
        board.putPiece(firstBlackPiece, Position.of("e5"));
        board.putPiece(secondBlackPiece, Position.of("f5"));
        board.putPiece(firstWhitePiece, Position.of("e3"));
        board.putPiece(secondWhitePiece, Position.of("f3"));
        Path path = king.generatePaths(current, board);
        assertThat(path.positions()).isEqualTo(
            kingE4WithObstacles());
    }

    List<Position> kingE4WithoutObstacles() {
        return Arrays.asList(
            Position.of("e5"),
            Position.of("f4"),
            Position.of("e3"),
            Position.of("d4"),
            Position.of("f5"),
            Position.of("f3"),
            Position.of("d3"),
            Position.of("d5")
        );
    }

    List<Position> kingE4WithObstacles() {
        return Arrays.asList(
            Position.of("e5"),
            Position.of("f4"),
            Position.of("d4"),
            Position.of("f5"),
            Position.of("d3"),
            Position.of("d5")
        );
    }
}