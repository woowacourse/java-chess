package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Pawn;
import chess.domain.piece.attribute.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnMoveStrategyTest {

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
    void movePossibilityOfTrue(File fileA, Rank rankA, File fileB, Rank rankB) {
        assertDoesNotThrow(() -> new PawnMoveStrategy()
                .isValidateCanMove(Team.WHITE,
                        new Pawn(Team.BLACK),
                        new Position(fileA, rankA),
                        new Position(fileB, rankB)
                )
        );
    }

    @Test
    @DisplayName("폰의 이동방향이 TOP, DOWN 일 경우에는 추가 검증을 해야한다.")
    void canMoveAdditionalValidation() {
        assertThat(new PawnMoveStrategy().isValidateCanMove(Team.WHITE,
                new Pawn(Team.WHITE),
                new Position(File.A, Rank.TWO),
                new Position(File.A, Rank.THREE)))
                .isFalse();
    }

    @Test
    @DisplayName("폰의 이동방향이 TOPLEFT, TOPRIGHT, DOWNLEFT, DOWNRIGHT 일 경우에는 검증이 필요없다.")
    void canMoveAdditionalInValidation() {
        assertThat(new PawnMoveStrategy().isValidateCanMove(Team.WHITE,
                new Pawn(Team.BLACK),
                new Position(File.A, Rank.ONE),
                new Position(File.B, Rank.TWO)))
                .isTrue();
    }
}
