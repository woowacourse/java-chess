package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("폰이 수직으로 존재할 경우 총점을 계산한다.")
    void getWhiteScoreWhenPawnVertical() {
        Board board = new Board(BoardFactory.initialize());
        board.movePiece(Position.valueOf("a2"), Position.valueOf("b3"));
        board.movePiece(Position.valueOf("c2"), Position.valueOf("b4"));

        Score score = new Score(board.getBoard());
        assertThat(score.calculateScore(Team.WHITE)).isEqualTo(36.5);
    }

    @Test
    @DisplayName("queen이 없고 폰이 수직으로 존재하는 경우 총점을 계산한다.")
    void getWhiteScoreWhenNoQueenPawnVertical() {
        Board board = new Board(BoardFactory.initialize());
        board.movePiece(Position.valueOf("a2"), Position.valueOf("d1"));

        Score score = new Score(board.getBoard());
        assertThat(score.calculateScore(Team.WHITE)).isEqualTo(28);
    }

    @Test
    @DisplayName("게임에서 이긴 플레이어의 Team을 반환")
    void getWinTeamWhenBlackWin() {
        Board board = new Board(BoardFactory.initialize());
        board.movePiece(Position.valueOf("a2"), Position.valueOf("d1"));

        Score score = new Score(board.getBoard());
        assertThat(score.calculateWinningTeam(score.calculateScore(Team.WHITE),
                score.calculateScore(Team.BLACK))).isEqualTo(Team.BLACK);
    }

    @Test
    @DisplayName("게임에서 이긴 플레이어의 Team을 반환")
    void getWinTeamWhenWhiteWin() {
        Board board = new Board(BoardFactory.initialize());
        board.movePiece(Position.valueOf("a7"), Position.valueOf("b6"));

        Score score = new Score(board.getBoard());
        assertThat(score.calculateWinningTeam(score.calculateScore(Team.WHITE),
                score.calculateScore(Team.BLACK))).isEqualTo(Team.WHITE);
    }
}