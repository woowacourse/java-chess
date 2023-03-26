package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.AbstractTestFixture;
import chess.domain.board.Board;
import chess.domain.exception.DifferentTeamException;
import chess.domain.exception.NotPlayableException;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class GameTest extends AbstractTestFixture {

    @DisplayName("기물을 움직이게 한다")
    @Test
    void movePiece() {
        Game game = new Game();

        game.movePiece(createPosition("B,TWO"), createPosition("B,THREE"));

        Map<Position, Piece> pieces = game.getPieces();
        assertThat(pieces.get(createPosition("B,THREE")))
                .isNotNull()
                .isInstanceOf(Pawn.class);
    }

    @DisplayName("자신의 턴에는 자신의 기물만 움직일 수 있다.")
    @Test
    void moveBlackFirst_throws() {
        Game game = new Game();

        assertThatThrownBy(() -> game.movePiece(createPosition("B,SEVEN"), createPosition("B,SIX")))
                .isInstanceOf(DifferentTeamException.class)
                .hasMessage("자신의 기물만 움직일 수 있습니다");
    }

    @DisplayName("한 수마다 턴을 바꾼다")
    @Test
    void moveBlackSecond() {
        Game game = new Game();

        game.movePiece(createPosition("B,TWO"), createPosition("B,THREE"));
        game.movePiece(createPosition("B,SEVEN"), createPosition("B,SIX"));

        Map<Position, Piece> pieces = game.getPieces();
        assertThat(pieces.get(createPosition("B,SIX")))
                .isNotNull()
                .isInstanceOf(Pawn.class);
    }

    @DisplayName("한 팀이라도 왕이 없으면 게임을 종료한다")
    @Test
    void isFinished() {
        var board = new Board(Map.ofEntries(
                Map.entry(new Position(File.A, Rank.ONE), new King(Team.WHITE))
        ));
        var game = new Game(Team.WHITE, board);

        assertThat(game.isFinished()).isTrue();
    }

    @DisplayName("한 팀이라도 왕이 없으면 게임을 진행할 수 없다")
    @Test
    void playingWithoutKing_throws() {
        var board = new Board(Map.ofEntries(
                Map.entry(new Position(File.A, Rank.ONE), new King(Team.WHITE))
        ));
        var game = new Game(Team.WHITE, board);

        assertThatThrownBy(() -> game.movePiece(new Position(File.A, Rank.ONE), new Position(File.A, Rank.TWO)))
                .isInstanceOf(NotPlayableException.class)
                .hasMessage("왕 없이 플레이할 수 없습니다");
    }

    @DisplayName("각 팀의 점수를 가져올 수 있다")
    @Test
    void getScoreOfTeam() {
        Game game = new Game();

        assertThat(game.getScoreOf(Team.BLACK)).isEqualTo(38);
    }

    @DisplayName("이긴 팀을 알 수 있다")
    @Test
    void getWinner_noKing() {
        var board = new Board(Map.ofEntries(
                Map.entry(new Position(File.A, Rank.ONE), new King(Team.WHITE))
        ));
        var game = new Game(Team.WHITE, board);

        assertThat(game.getWinner()).isEqualTo(Team.WHITE);
    }

    @DisplayName("이긴 팀을 알 수 있다")
    @Test
    void getWinner_hasKing() {
        var board = new Board(Map.ofEntries(
                Map.entry(new Position(File.A, Rank.ONE), new King(Team.WHITE)),
                Map.entry(new Position(File.B, Rank.ONE), new King(Team.BLACK)),
                Map.entry(new Position(File.C, Rank.ONE), new Pawn(Team.BLACK))
        ));
        var game = new Game(Team.WHITE, board);

        assertThat(game.getWinner()).isEqualTo(Team.BLACK);
    }

    @DisplayName("이긴 팀을 알 수 있다")
    @Test
    void getWinner_draw() {
        var board = new Board(Map.ofEntries(
                Map.entry(new Position(File.A, Rank.ONE), new King(Team.WHITE)),
                Map.entry(new Position(File.B, Rank.ONE), new King(Team.BLACK))
        ));
        var game = new Game(Team.WHITE, board);

        assertThat(game.getWinner()).isEqualTo(Team.NEUTRAL);
    }
}
