package chess.player;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.game.ChessSet;
import chess.location.Location;
import chess.piece.type.Piece;
import chess.score.Score;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("팀이 같은지 : 참")
    void isSame1() {
        Player player = new Player(new ChessSet(Collections.EMPTY_MAP), Team.WHITE);

        assertThat(player.is(Team.WHITE)).isTrue();
    }

    @Test
    @DisplayName("팀이 같은지 : 거짓")
    void isSame2() {
        Player player = new Player(new ChessSet(Collections.EMPTY_MAP), Team.WHITE);

        assertThat(player.is(Team.BLACK)).isFalse();
    }

    @Test
    @DisplayName("팀이 같지 않은지 : 참")
    void isNotSame() {
        Player player = new Player(new ChessSet(Collections.EMPTY_MAP), Team.WHITE);

        assertThat(player.isNotSame(Team.BLACK)).isTrue();
    }

    @Test
    @DisplayName("팀이 같지 않은지 : 거짓")
    void isNotSame2() {
        Player player = new Player(new ChessSet(Collections.EMPTY_MAP), Team.WHITE);

        assertThat(player.isNotSame(Team.WHITE)).isFalse();
    }

    @Test
    void calculateScoreExceptPawnReduce() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Map<Location, Piece> locationPieceMap = chessBoard.giveMyPiece(Team.WHITE);
        Player player = new Player(new ChessSet(locationPieceMap), Team.WHITE);

        assertThat(player.calculateScoreExceptPawnReduce()).isEqualTo(new Score(38));
    }

}