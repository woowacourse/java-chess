package chess.domain;

import chess.domain.piece.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessGameTest {

    @Nested
    @DisplayName("게임이 끝났는지 판단하는 isGameEnd 메서드 테스트")
    class isGameEndTest {

        @Test
        @DisplayName("두 팀의 왕이 모두 죽지 않았으면 false 를 반환한다.")
        void isKingDeadTest1() {
            ChessGame game = ChessGame.create();
            boolean isGameEnd = game.isGameEnd();

            assertThat(isGameEnd).isFalse();
        }

        @Test
        @DisplayName("백 팀의 왕이 죽었으면 true 를 반환한다.")
        void isGameEndTest2() {
            ChessGame game = ChessGame.create();

            game.movePiece(new Position(4, 6), new Position(4, 4));
            game.movePiece(new Position(4, 1), new Position(4, 3));
            game.movePiece(new Position(4, 4), new Position(4, 3));
            game.movePiece(new Position(4, 3), new Position(4, 2));
            game.movePiece(new Position(4, 2), new Position(4, 1));
            game.movePiece(new Position(4, 1), new Position(4, 0));
            boolean isGameEnd = game.isGameEnd();

            assertThat(isGameEnd).isTrue();
        }

        @Test
        @DisplayName("흑 팀의 왕이 죽었으면 true 를 반환한다.")
        void isGameEndTest3() {
            ChessGame game = ChessGame.create();

            game.movePiece(new Position(4, 1), new Position(4, 3));
            game.movePiece(new Position(4, 6), new Position(4, 4));
            game.movePiece(new Position(4, 3), new Position(4, 4));
            game.movePiece(new Position(4, 4), new Position(4, 5));
            game.movePiece(new Position(4, 5), new Position(4, 6));
            game.movePiece(new Position(4, 6), new Position(4, 7));
            boolean isGameEnd = game.isGameEnd();

            assertThat(isGameEnd).isTrue();
        }
    }

    @Nested
    @DisplayName("한 팀의 점수를 계산하는 getTotalScore 메서드 테스트")
    class getTotalScoreTest {

        @Test
        @DisplayName("한 팀의 초기 점수의 합을 구한다.")
        void getTotalScoreTest1() {
            ChessGame game = ChessGame.create();
            double score = game.getTotalScore(Team.WHITE);

            assertThat(score).isEqualTo(38.0);
        }
    }
}
