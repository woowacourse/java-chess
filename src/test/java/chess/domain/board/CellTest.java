package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CellTest {

    private Cell cell;

    @BeforeEach
    void setup() {
        cell = new Cell();
    }

    @DisplayName("Cell은 초기에 항상 비어있는 상태다.")
    @Test
    void isInitializedAsEmpty() {
        assertThat(cell.isEmpty()).isTrue();
    }

    @DisplayName("Cell에 기물을 놓는다.")
    @Test
    void put() {
        cell.put(new Pawn(TeamType.BLACK));

        assertThat(cell.isEmpty()).isFalse();
    }

    @DisplayName("Cell에 기물이 없는데 찾으려는 경우 예외가 발생한다.")
    @Test
    void cannotFindPiece() {
        assertThatCode(() -> cell.getPiece())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 위치에 기물이 없습니다.");
    }

    @DisplayName("Cell에 기물이 있다면 정상적으로 찾는다.")
    @Test
    void findPiece() {
        Piece piece = new Pawn(TeamType.BLACK);
        cell.put(piece);

        Piece returnedPiece = cell.getPiece();

        assertThat(piece).isEqualTo(returnedPiece);
    }

    @DisplayName("Cell이 비어있거나 적이 있는 지를 확인한다.")
    @Test
    void isEmptyOrHasEnemy() {
        boolean isEmpty = cell.isEmptyOrHasEnemy(TeamType.WHITE);
        Piece piece = new Pawn(TeamType.BLACK);
        cell.put(piece);
        boolean hasEnemy = cell.isEmptyOrHasEnemy(TeamType.WHITE);

        assertThat(isEmpty).isTrue();
        assertThat(hasEnemy).isTrue();
    }

    @DisplayName("Cell에 적이 있는지 확인한다.")
    @Test
    void hasEnemy() {
        boolean hasEnemyFirst = cell.hasEnemy(TeamType.WHITE);
        cell.put(new Pawn(TeamType.BLACK));
        boolean hasEnemyTwo = cell.hasEnemy(TeamType.WHITE);

        assertThat(hasEnemyFirst).isFalse();
        assertThat(hasEnemyTwo).isTrue();
    }
}
