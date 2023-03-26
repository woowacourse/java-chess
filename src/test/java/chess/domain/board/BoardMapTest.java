package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.AbstractTestFixture;
import chess.domain.game.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class BoardMapTest {

    @DisplayName("두 위치의 기물이 같은 팀인지 알 수 있다")
    @Test
    void isSameTeam() {
        BoardMap boardMap = BoardMap.from(AbstractTestFixture.INITIAL_BOARD);

        Position position = new Position(File.A, Rank.ONE);
        Position other = new Position(File.B, Rank.ONE);
        Position another = new Position(File.B, Rank.SEVEN);

        assertThat(boardMap.isTeamSame(position, other)).isTrue();
        assertThat(boardMap.isTeamSame(position, another)).isFalse();
    }

    @DisplayName("어떤 위치에 기물이 존재하는지 알 수 있다")
    @Test
    void hasPieceAtPosition() {
        BoardMap boardMap = BoardMap.from(AbstractTestFixture.INITIAL_BOARD);

        Position whiteRookPosition = new Position(File.A, Rank.ONE);
        Position emptyPosition = new Position(File.B, Rank.FIVE);

        assertThat(boardMap.hasPieceAt(whiteRookPosition)).isTrue();
        assertThat(boardMap.hasPieceAt(emptyPosition)).isFalse();
    }

    @DisplayName("한 파일에 한 팀의 폰이 몇 개인지 센다")
    @Test
    void countPawns_inAFile() {
        BoardMap boardMap = BoardMap.from(AbstractTestFixture.INITIAL_BOARD);
        long count = boardMap.countFriendlyPawnsIn(File.B, Team.WHITE);

        assertThat(count).isEqualTo(1);
    }
}
