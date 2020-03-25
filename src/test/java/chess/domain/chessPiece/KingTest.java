package chess.domain.chessPiece;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.chessPiece.team.BlackTeam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @Test
    @DisplayName("이동 성공 테스트")
    void movable() {
        Position position = Position.of(File.D, Rank.TWO);
        Position target = Position.of(File.C, Rank.TWO);
        Piece king = new King(position, new BlackTeam());

        assertThat(king.isMovable(target, null)).isTrue();
    }

    @Test
    @DisplayName("이동 실패 테스트")
    void isNotMovable() {
        Position position = Position.of(File.D, Rank.TWO);
        Position target = Position.of(File.D, Rank.FIVE);
        Piece king = new King(position, new BlackTeam());

        assertThat(king.isMovable(target, null)).isFalse();
    }

}