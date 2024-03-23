package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("특정 위치에 어떤 말이 있는지 알려준다.")
    void findTest_whenPieceExist() {
        Position position = new Position(File.D, Rank.TWO);
        King king = new King(Team.WHITE);
        Map<Position, Piece> map = Map.of(position, king);
        Board board = new Board(map);

        assertThat(board.find(position)).isEqualTo(Optional.of(king));
    }

    @Test
    @DisplayName("특정 위치에 어떤 말이 있는지 알려준다.")
    void findTest_whenPieceNotExist() {
        Position position = new Position(File.E, Rank.TWO);
        King king = new King(Team.WHITE);
        Map<Position, Piece> map = Map.of(position, king);
        Position notExistPosition = new Position(File.D, Rank.TWO);
        Board board = new Board(map);

        assertThat(board.find(notExistPosition)).isEqualTo(Optional.empty());
    }

    /*
     * .R......
     * ........
     * ........
     * .q.k....
     * ........
     * ........
     * ........
     * */
    @Nested
    @DisplayName("말 이동 테스트")
    class MovingTest {

        private static final Position START_KING = new Position(File.E, Rank.FOUR);
        private static final King KING = new King(Team.WHITE);
        private static final Position START_QUEEN = new Position(File.E, Rank.TWO);
        private static final Queen QUEEN = new Queen(Team.WHITE);
        private static final Rook ENEMY_ROOK = new Rook(Team.BLACK);
        private static final Position START_ENEMY_ROOK = new Position(File.H, Rank.TWO);
        private static final Map<Position, Piece> MAP = Map.of(
                START_KING, KING, START_QUEEN, QUEEN, START_ENEMY_ROOK, ENEMY_ROOK);
        private Board board;

        @BeforeEach
        void beforeEach() {
            board = new Board(MAP);
        }

        @Test
        @DisplayName("시작 위치에 있는 말을 도착 위치로 움직인다.")
        void moveTest() {
            Position possibleEnd = new Position(File.E, Rank.THREE);

            board.move(START_KING, possibleEnd);

            assertAll(
                    () -> assertThat(board.find(possibleEnd)).isEqualTo(Optional.of(KING)),
                    () -> assertThat(board.find(START_KING)).isEqualTo(Optional.empty())
            );
        }

        @Test
        @DisplayName("시작 위치에 말이 없을 경우, 예외가 발생한다.")
        void moveTest_whenPieceNotExist_throwException() {
            Position emptyPosition = new Position(File.F, Rank.EIGHT);
            Position end = new Position(File.F, Rank.TWO);

            assertThatThrownBy(() -> board.move(emptyPosition, end))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치에 말이 없습니다.");
        }

        @Test
        @DisplayName("말의 이동 반경을 벗어날 경우, 예외가 발생한다.")
        void moveTest_whenOutOfMovement_throwException() {
            Position outOfMovement = new Position(File.F, Rank.EIGHT);

            assertThatThrownBy(() -> board.move(START_KING, outOfMovement))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("불가능한 경로입니다.");
        }

        @Test
        @DisplayName("이동 경로 위에 다른 말이 존재할 경우, 예외가 발생한다.")
        void moveTest_whenBlocked_throwException() {
            Position blockedPosition = new Position(File.E, Rank.FIVE);

            assertThatThrownBy(() -> board.move(START_QUEEN, blockedPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("다른 말이 있어 이동 불가능합니다.");
        }

        @Test
        @DisplayName("도착할 곳에 같은 팀이 있을 경우, 예외가 발생한다.")
        void moveTest_whenExistSameTeamAtTheEnd_throwException() {
            assertThatThrownBy(() -> board.move(START_QUEEN, START_KING))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 지점에 같은 팀 말이 있어 이동이 불가능합니다.");
        }

        @Test
        @DisplayName("도착할 곳에 다른 팀이 있을 경우, 해당 말을 잡아먹는다.")
        void moveTest_whenExistSameTeamAtTheEnd() {
            board.move(START_QUEEN, START_ENEMY_ROOK);

            assertAll(
                    () -> assertThat(board.find(START_QUEEN)).isEqualTo(Optional.empty()),
                    () -> assertThat(board.find(START_ENEMY_ROOK)).isEqualTo(Optional.of(QUEEN))
            );
        }

        @Test
        @DisplayName("도착할 곳에 다른 팀이 있을 경우, 해당 말을 잡아먹는다.")
        void moveTest_whenNotInTurn_throwException() {
            Position legalPosition = new Position(File.D, Rank.EIGHT);

            assertThatThrownBy(() -> board.move(START_ENEMY_ROOK, legalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 팀의 차례가 아닙니다.");
        }
    }
}
