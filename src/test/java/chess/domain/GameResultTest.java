package chess.domain;

import static chess.domain.InitialPiece.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class GameResultTest {

    @DisplayName("같은 세로줄에 같은 팀의 폰이 2개 있다면 0.5점으로 계산한다.")
    @Test
    void 같은_줄_2개_폰() {
        GameResult gameResult = GameResult.from(
            Map.of(Position.from("e6"), BLACK_QUEEN.getPiece(),
                Position.from("e1"), WHITE_ROOK.getPiece(),
                Position.from("f4"), WHITE_KNIGHT.getPiece(),
                Position.from("f3"), WHITE_PAWN.getPiece(),
                Position.from("f2"), WHITE_PAWN.getPiece(),
                Position.from("g4"), WHITE_QUEEN.getPiece()));

        assertThat(gameResult.calculateScoreOfTeam(TeamColor.WHITE)).isEqualTo(17.5);
    }

    @DisplayName("같은 팀의 폰이 다른 세로줄에 있을 때는 각각 1점으로 계산한다.")
    @Test
    void 다른_줄_2개_폰() {
        GameResult gameResult = GameResult.from(
            Map.of(Position.from("e6"), BLACK_QUEEN.getPiece(),
                Position.from("g4"), WHITE_QUEEN.getPiece(),
                Position.from("g2"), WHITE_PAWN.getPiece(),
                Position.from("h3"), WHITE_PAWN.getPiece()));

        assertThat(gameResult.calculateScoreOfTeam(TeamColor.WHITE)).isEqualTo(11);
    }


    @DisplayName("서로 다른 팀의 폰이 같은 줄에 각각 1개 있을 때는 1점으로 계산한다.")
    @Test
    void 같은_줄_상대_폰_2개() {
        GameResult gameResult = GameResult.from(
            Map.of(Position.from("e6"), BLACK_QUEEN.getPiece(),
                Position.from("g4"), WHITE_QUEEN.getPiece(),
                Position.from("h7"), BLACK_PAWN.getPiece(),
                Position.from("h3"), WHITE_PAWN.getPiece()));

        assertThat(gameResult.calculateScoreOfTeam(TeamColor.WHITE)).isEqualTo(10);
    }

    @DisplayName("King 이 살아있을 때, King 의 점수는 0점으로 계산한다.")
    @Test
    void King_0점() {
        GameResult gameResult = GameResult.from(
            Map.of(Position.from("e6"), BLACK_QUEEN.getPiece(),
                Position.from("g4"), WHITE_QUEEN.getPiece(),
                Position.from("e1"), WHITE_KING.getPiece(),
                Position.from("h3"), WHITE_PAWN.getPiece()));

        assertThat(gameResult.calculateScoreOfTeam(TeamColor.WHITE)).isEqualTo(10);
    }

    @DisplayName("체스판의 초기 점수는 38점이다.")
    @Test
    void 체스판_초기_점수() {
        GameResult gameResult = GameResult.from(InitialPiece.getPiecesWithPosition());

        Assertions.assertAll(
            () -> assertThat(gameResult.calculateScoreOfTeam(TeamColor.WHITE)).isEqualTo(38),
            () -> assertThat(gameResult.calculateScoreOfTeam(TeamColor.BLACK)).isEqualTo(38)
        );
    }

}