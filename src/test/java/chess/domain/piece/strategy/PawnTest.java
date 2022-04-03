package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Pawn;
import chess.domain.piece.attribute.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {
            "A,TWO,A,THREE",
            "A,TWO,A,FOUR",
            "A,TWO,B,THREE",
            "B,TWO,A,THREE",
            "B,THREE,B,FOUR",
            "A,THREE,B,FOUR",
            "B,THREE,A,FOUR",
    })
    @DisplayName("폰이 갈 수 있는 위치 중 하나여야 한다.")
    void movePossibilityOfTrue(Column columnA, Rank rankA, Column columnB, Rank rankB) {
        assertDoesNotThrow(() -> new Pawn(Team.WHITE)
                .canMove(new Pawn(Team.BLACK),
                        new Position(columnA, rankA),
                        new Position(columnB, rankB)
                )
        );
    }

    @Test
    @DisplayName("폰의 이동방향이 TOP, DOWN 일 경우에는 추가 검증을 해야한다.")
    void canMoveAdditionalValidation() {
        assertThat(new Pawn(Team.WHITE)
                .canMove(
                        new Pawn(Team.WHITE),
                        new Position(Column.A, Rank.TWO),
                        new Position(Column.A, Rank.THREE)))
                .isFalse();
    }

    @Test
    @DisplayName("폰의 이동방향이 TOPLEFT, TOPRIGHT, DOWNLEFT, DOWNRIGHT 일 경우에는 검증이 필요없다.")
    void canMoveAdditionalInValidation() {
        assertThat(new Pawn(Team.WHITE)
                .canMove(
                        new Pawn(Team.BLACK),
                        new Position(Column.A, Rank.ONE),
                        new Position(Column.B, Rank.TWO)))
                .isTrue();
    }
}
