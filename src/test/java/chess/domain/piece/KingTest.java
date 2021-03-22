package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Paths;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    // todo : 여기 테스트들은 path에 있어도 될 것 같고?
    @DisplayName("킹이 이동가능한 전체 위치를 구한다. 상황 : 흰킹-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.ofName("e4");
        Piece king = new King(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(king, current);

        assertThat(paths.pathsToPosition()).isEqualTo(kingE4WithoutObstacles());
    }

    @DisplayName("킹이 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 흰킹-e4 흰피스-e3,f3 검은피스-e5,f5")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.ofName("e4");
        Piece king = new King(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(king, current);
        Board board = new Board();
        Piece firstBlackPiece = new Knight(PieceColor.BLACK);
        Piece secondBlackPiece = new Knight(PieceColor.BLACK);
        Piece firstWhitePiece = new Knight(PieceColor.WHITE);
        Piece secondWhitePiece = new Knight(PieceColor.WHITE);
        board.putPiece(firstBlackPiece, Position.ofName("e5"));
        board.putPiece(secondBlackPiece, Position.ofName("f5"));
        board.putPiece(firstWhitePiece, Position.ofName("e3"));
        board.putPiece(secondWhitePiece, Position.ofName("f3"));
        assertThat(paths.removeObstacles(king, board).positions()).isEqualTo(
                kingE4WithObstacles());
    }

    List<Position> kingE4WithoutObstacles() {
        return Arrays.asList(
                Position.ofName("e5"),
                Position.ofName("f4"),
                Position.ofName("e3"),
                Position.ofName("d4"),
                Position.ofName("f5"),
                Position.ofName("f3"),
                Position.ofName("d3"),
                Position.ofName("d5")
        );
    }

    List<Position> kingE4WithObstacles() {
        return Arrays.asList(
                Position.ofName("e5"),
                Position.ofName("f4"),
                Position.ofName("d4"),
                Position.ofName("f5"),
                Position.ofName("d3"),
                Position.ofName("d5")
        );
    }
}