package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.move.Coordinate;
import domain.piece.nonsliding.King;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BoardTest {

    @Test
    @DisplayName("보드 좌측 바깥으로는 이동이 불가능하다")
    void moveLeftOutside() {
        Board board = new Board(BoardInitialImage.getCachedBoard());

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, -1);

        assertThatThrownBy(() -> board.isMovable(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보드 우측 바깥으로는 이동이 불가능하다")
    void moveRightOutside() {
        Board board = new Board(BoardInitialImage.getCachedBoard());

        Coordinate start = new Coordinate(0, 7);
        Coordinate end = new Coordinate(0, 8);

        assertThatThrownBy(() -> board.isMovable(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보드 위쪽 바깥으로는 이동이 불가능하다")
    void moveDownOutside() {
        Board board = new Board(BoardInitialImage.getCachedBoard());

        Coordinate start = new Coordinate(7, 0);
        Coordinate end = new Coordinate(8, 0);

        assertThatThrownBy(() -> board.isMovable(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보드 아래쪽 바깥으로는 이동이 불가능하다")
    void moveUpOutside() {
        Board board = new Board(BoardInitialImage.getCachedBoard());

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(-1, 0);

        assertThatThrownBy(() -> board.isMovable(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "({0}, {1})에 기물은 존재하지 않는다")
    @CsvSource(value =  {"2:0", "5:0", "2:7", "5:7"}, delimiter = ':')
    void isEmptyPiece(int row, int col) {
        Board board = new Board(BoardInitialImage.getCachedBoard());
        Coordinate coordinate = new Coordinate(row, col);

        assertThat(board.isPieceEmptyAt(coordinate)).isTrue();
    }


    @Test
    @DisplayName("화이트 팀의 기본 점수를 계산할 수 있다")
    void collectPointWhite() {
        Board board = new Board(BoardInitialImage.getCachedBoard());

        assertThat(board.collectPointFor(Color.WHITE))
                .isEqualTo(38);
    }

    @Test
    @DisplayName("블랙 팀의 기본 점수를 계산할 수 있다")
    void collectPointBlack() {
        Board board = new Board(BoardInitialImage.getCachedBoard());

        assertThat(board.collectPointFor(Color.BLACK))
                .isEqualTo(38);
    }

    @ParameterizedTest(name = "화이트 폰이 일렬로 {0}개 놓이는 경우 점수가 0.5씩 부여된다")
    @ValueSource(ints = {2, 3, 4, 5, 6, 7, 8})
    void whitePawnTestWhenStraightExist(final int pawnCount) {
        Map<Coordinate, Piece> mockPieceLocations = new HashMap<>();
        for (int i = 0; i < pawnCount; i++) {
            mockPieceLocations.put(
                    new Coordinate(i, 0),
                    new WhitePawn(Color.WHITE)
            );
        }

        Board board = new Board(mockPieceLocations);

        assertThat(board.collectPointFor(Color.WHITE)).isEqualTo(pawnCount * 0.5);
    }

    @ParameterizedTest(name = "블랙 폰이 일렬로 {0}개 놓이는 경우 점수가 0.5씩 부여된다")
    @ValueSource(ints = {2, 3, 4, 5, 6, 7, 8})
    void blackPawnTestWhenStraightExist(final int pawnCount) {
        Map<Coordinate, Piece> mockPieceLocations = new HashMap<>();
        for (int i = 0; i < pawnCount; i++) {
            mockPieceLocations.put(
                    new Coordinate(i, 0),
                    new BlackPawn(Color.BLACK)
            );
        }

        Board board = new Board(mockPieceLocations);

        assertThat(board.collectPointFor(Color.BLACK)).isEqualTo(pawnCount * 0.5);
    }

    @Test
    @DisplayName("킹이 두 개 있는지를 판단할 수 있다")
    void judgeKingAlive() {
        Map<Coordinate, Piece> mockPieceLocations = new HashMap<>();
        mockPieceLocations.put(new Coordinate(0, 0), new King(Color.WHITE));
        mockPieceLocations.put(new Coordinate(1, 1), new King(Color.BLACK));

        Board board = new Board(mockPieceLocations);

        assertThat(board.allKingAlive()).isTrue();
    }

    @Test
    @DisplayName("킹이 두개가 아니라면 판단할 수 있다")
    void judgeKingDead() {
        Map<Coordinate, Piece> mockPieceLocations = new HashMap<>();
        mockPieceLocations.put(new Coordinate(0, 0), new King(Color.WHITE));

        Board board = new Board(mockPieceLocations);

        assertThat(board.allKingAlive()).isFalse();
    }
}
