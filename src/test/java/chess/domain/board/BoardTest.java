package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.Pieces;
import chess.domain.position.Col;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.EnemyTurnException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private final static double BASE_SCORE = 38.0;
    private Board board;

    @BeforeEach
    void setUp() {
        BoardFactory boardFactory = new BoardFactory();
        board = boardFactory.board();
    }

    @Test
    @DisplayName("다른 팀 말을 움직이려고 하면, 예외가 발생한다.")
    void anotherTeamPieceMoveCheck() {
        Position startPoint = new Position(6, 0);
        Position endPoint = new Position(4, 0);

        assertThatThrownBy(
                () -> board.move(startPoint, endPoint, Team.BLACK)
        ).isInstanceOf(EnemyTurnException.class);
        board.move(startPoint, endPoint, Team.WHITE);
    }

    @Test
    @DisplayName("보드 크기를 벗어나는 좌표로 이동하려고 하면, false를 리턴한다.")
    void validateRangeCheck() {
        assertAll(
                () -> assertFalse(board.isWithinBoardRange(new Position(8, 0))),
                () -> assertFalse(board.isWithinBoardRange(new Position(-1, 0))),
                () -> assertFalse(board.isWithinBoardRange(new Position(0, 8))),
                () -> assertFalse(board.isWithinBoardRange(new Position(0, -1)))
        );
    }

    @Test
    @DisplayName("적팀의 왕이 죽으면, true를 리턴한다.")
    void enemyKingDieCheck() {
        Team team = Team.BLACK;
        Pieces pieces = board.toMap().get(Team.enemyTeam(team));
        Position WhiteTeamKingPosition = new Position(Row.location("1"), Col.location("e"));

        assertThat(board.isEnemyKingDead(team)).isFalse();
        pieces.removePieceByPosition(WhiteTeamKingPosition);
        assertThat(board.isEnemyKingDead(team)).isTrue();
    }

    @Test
    @DisplayName("팀을 받으면, 팀에 따른 현재 점수를 계산한다.")
    void scoreByTeamCheck() {
        assertThat(board.scoreByTeam(Team.BLACK)).isEqualTo(BASE_SCORE);
    }

    @Test
    @DisplayName("팀과 위치를 받아 piece가 해당 위치에 있는지 확인")
    void existPieceByTeam() {
        assertTrue(board.existsPieceByTeam(Team.WHITE, new Position(6, 0)));
        assertTrue(board.existsPieceByTeam(Team.BLACK, new Position(1, 0)));
        assertFalse(board.existsPieceByTeam(Team.WHITE, new Position(1, 0)));
        assertFalse(board.existsPieceByTeam(Team.BLACK, new Position(6, 0)));
    }
}