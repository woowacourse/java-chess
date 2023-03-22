package chess.domain;

import static chess.domain.InitialPiece.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Pieces;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class GameResultTest {

    @DisplayName("체스말의 배치에 따른 팀별 점수를 계산한다.")
    @Test
    void 팀별_점수_계산() {
        GameResult gameResult = new GameResult(Set.of(
            new Pieces(List.of(BLACK_ROOK.getPiece(), WHITE_QUEEN.getPiece())),
            new Pieces(List.of(BLACK_PAWN.getPiece(), BLACK_PAWN.getPiece(),
                BLACK_KNIGHT.getPiece(), BLACK_KING.getPiece())),
            new Pieces(List.of(BLACK_QUEEN.getPiece(), BLACK_PAWN.getPiece())),
            new Pieces(List.of(BLACK_PAWN.getPiece()))));

        assertThat(gameResult.calculateScoreOfTeam(TeamColor.BLACK)).isEqualTo(19.5);
    }

}