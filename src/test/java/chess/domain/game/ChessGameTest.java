package chess.domain.game;

import static chess.domain.piece.type.PieceWithColorType.B_BP;
import static chess.domain.piece.type.PieceWithColorType.B_KG;
import static chess.domain.piece.type.PieceWithColorType.B_NT;
import static chess.domain.piece.type.PieceWithColorType.B_PN;
import static chess.domain.piece.type.PieceWithColorType.B_QN;
import static chess.domain.piece.type.PieceWithColorType.B_RK;
import static chess.domain.piece.type.PieceWithColorType.W_BP;
import static chess.domain.piece.type.PieceWithColorType.W_KG;
import static chess.domain.piece.type.PieceWithColorType.W_NT;
import static chess.domain.piece.type.PieceWithColorType.W_PN;
import static chess.domain.piece.type.PieceWithColorType.W_QN;
import static chess.domain.piece.type.PieceWithColorType.W_RK;
import static chess.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.setting.BoardCustomSetting;
import chess.domain.board.setting.BoardSetting;
import chess.domain.player.score.Scores;
import chess.domain.position.MoveRoute;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    @DisplayName("King이 잡혔는지 확인")
    @Nested
    class KingDead {
        @DisplayName("King이 잡혔을 때")
        @Test
        void isKingDead() {
            BoardSetting customBoardSetting = new BoardCustomSetting(
                Arrays.asList(
                    null, B_KG, B_RK, null, null, null, null, null,
                    B_PN, null, B_PN, B_BP, null, null, null, null,
                    null, B_PN, null, null, B_QN, null, null, null,
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, W_NT, W_QN, null,
                    null, null, null, null, null, W_PN, null, W_PN,
                    null, null, null, null, null, W_PN, W_PN, null,
                    null, null, null, null, W_RK, null, null, null)
            );

            ChessGame chessGame = new ChessGame(customBoardSetting);

            assertThat(chessGame.isKingDead()).isTrue();
        }

        @DisplayName("King이 잡히지 않았을 때")
        @Test
        void isNotKingDead() {
            BoardSetting customBoardSetting = new BoardCustomSetting(
                Arrays.asList(
                    null, B_KG, B_RK, null, null, null, null, null,
                    B_PN, null, B_PN, B_BP, null, null, null, null,
                    null, B_PN, null, null, B_QN, null, null, null,
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, W_NT, W_QN, null,
                    null, null, null, null, null, W_PN, null, W_PN,
                    null, null, null, null, null, W_PN, W_PN, null,
                    null, null, null, null, W_RK, W_KG, null, null)
            );

            ChessGame chessGame = new ChessGame(customBoardSetting);

            assertThat(chessGame.isKingDead()).isFalse();
        }
    }

    @DisplayName("점수 계산")
    @Test
    void scores() {
        BoardSetting customBoardSetting = new BoardCustomSetting(
            Arrays.asList(
                null, B_KG, B_RK, null, null, null, null, null,
                B_PN, null, B_PN, B_BP, null, null, null, null,
                null, B_PN, null, null, B_QN, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, W_NT, W_QN, null,
                null, null, null, null, null, W_PN, null, W_PN,
                null, null, null, null, null, W_PN, W_PN, null,
                null, null, null, null, W_RK, W_KG, null, null)
        );

        ChessGame chessGame = new ChessGame(customBoardSetting);
        Scores scores = chessGame.scores();

        assertThat(scores.blackPlayerScore().getScore()).isEqualTo(20);
        assertThat(scores.whitePlayerScore().getScore()).isEqualTo(19.5);
    }

    @DisplayName("기물을 잡은 후 점수 계산")
    @Test
    void scoresAfterKillEnemyPieceByPawn() {
        BoardSetting customBoardSetting = new BoardCustomSetting(
            Arrays.asList(
                B_RK, B_NT, B_BP, B_QN, B_KG, B_BP, B_NT, B_RK,
                B_PN, null, B_PN, B_PN, B_PN, B_PN, B_PN, B_PN,
                null, null, null, null, null, null, null, null,
                null, B_PN, null, null, null, null, null, null,
                W_PN, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, W_PN, W_PN, W_PN, W_PN, W_PN, W_PN, W_PN,
                W_RK, W_NT, W_BP, W_QN, W_KG, W_BP, W_NT, W_RK)
        );

        ChessGame chessGame = new ChessGame(customBoardSetting);
        MoveRoute moveRoute = new MoveRoute("a4", "b5");
        MoveCommand moveCommand = new MoveCommand(WHITE, moveRoute);

        chessGame.move(moveCommand);
        Scores scores = chessGame.scores();

        assertThat(scores.blackPlayerScore().getScore()).isEqualTo(37);
        assertThat(scores.whitePlayerScore().getScore()).isEqualTo(37);
    }
}