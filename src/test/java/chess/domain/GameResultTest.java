package chess.domain;

import static chess.domain.InitialPiece.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class GameResultTest {

    @DisplayName("체스말의 배치에 따른 팀별 점수를 계산한다.")
    @Test
    void 팀별_점수_계산() {
        GameResult gameResult = GameResult.from(
            Map.of(Position.from("e6"), BLACK_QUEEN.getPiece(),
                Position.from("e1"), WHITE_ROOK.getPiece(),
                Position.from("f4"), WHITE_KNIGHT.getPiece(),
                Position.from("f3"), WHITE_PAWN.getPiece(),
                Position.from("f2"), WHITE_PAWN.getPiece(),
                Position.from("f1"), WHITE_KING.getPiece(),
                Position.from("g4"), WHITE_QUEEN.getPiece(),
                Position.from("g2"), WHITE_PAWN.getPiece(),
                Position.from("h3"), WHITE_PAWN.getPiece()));

        assertThat(gameResult.calculateScoreOfTeam(TeamColor.WHITE)).isEqualTo(19.5);
    }

}