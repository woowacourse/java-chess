package chess.domain.board;

import static chess.domain.piece.type.PieceWithColorType.B_BP;
import static chess.domain.piece.type.PieceWithColorType.B_KG;
import static chess.domain.piece.type.PieceWithColorType.B_PN;
import static chess.domain.piece.type.PieceWithColorType.B_QN;
import static chess.domain.piece.type.PieceWithColorType.B_RK;
import static chess.domain.piece.type.PieceWithColorType.W_KG;
import static chess.domain.piece.type.PieceWithColorType.W_NT;
import static chess.domain.piece.type.PieceWithColorType.W_PN;
import static chess.domain.piece.type.PieceWithColorType.W_QN;
import static chess.domain.piece.type.PieceWithColorType.W_RK;
import static chess.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.setting.BoardCustomSetting;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.domain.piece.Pawn;
import chess.domain.player.score.Scores;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new BoardDefaultSetting());
    }

    @DisplayName("기물 이동")
    @Nested
    class MovePiece {
        @DisplayName("출발 위치에 자신의 기물이 없는 경우 - 빈 칸인 경우")
        @Test
        void cannotMovePieceAtStartPositionEmpty() {
            MoveRoute moveRoute = new MoveRoute("a3", "a4");

            assertThatThrownBy(() -> board.move(moveRoute, WHITE))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("출발 위치에 자신의 기물이 없는 경우 - 적의 기물이 있는 경우")
        @Test
        void cannotMovePieceAtStartPositionEnemyPiece() {
            MoveRoute moveRoute = new MoveRoute("a7", "a6");

            assertThatThrownBy(() -> board.move(moveRoute, WHITE))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("기물 이동")
        @Test
        void movePiece() {
            MoveRoute moveRoute = new MoveRoute("a2", "a4");
            board.move(moveRoute, WHITE);

            assertThat(board.find(Position.of("a2")).isEmpty()).isTrue();
            assertThat(board.find(Position.of("a4")).piece()).isEqualTo(new Pawn(WHITE));
        }

        @DisplayName("기물이 이동할 수 없는 도착위치")
        @Test
        void cannotMovePieceToDestination() {
            MoveRoute moveRoute = new MoveRoute("a2", "a5");

            assertThatThrownBy(() -> board.move(moveRoute, WHITE))
                .isInstanceOf(IllegalArgumentException.class);

            assertThat(board.find(Position.of("a2")).piece()).isEqualTo(new Pawn(WHITE));
            assertThat(board.find(Position.of("a5")).isEmpty()).isTrue();
        }
    }

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
            Board board = new Board(customBoardSetting);

            assertThat(board.isKingDead()).isTrue();
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
            Board board = new Board(customBoardSetting);

            assertThat(board.isKingDead()).isFalse();
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
        Board board = new Board(customBoardSetting);

        Scores scores = board.scores();

        assertThat(scores.blackPlayerScore().getScore()).isEqualTo(20);
        assertThat(scores.whitePlayerScore().getScore()).isEqualTo(19.5);
    }
}