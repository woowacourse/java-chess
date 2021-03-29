package chess.domain.player;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.manager.ChessGame;
import chess.domain.piece.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    private ChessGame chessGame;
    private Board board;
    private Players players;

    @BeforeEach
    void initPlayersAndBoard() {
        chessGame = new ChessGame();
        board = chessGame.board();
        players = new Players();
    }

    @DisplayName("해당 위치의 기물이 어떤 진영의 것인지 확인한다.")
    @Test
    void ownerOf() {
        assertThat(players.ownerOf(new Position("a2"))).isEqualTo(Owner.WHITE);
        assertThat(players.ownerOf(new Position("a7"))).isEqualTo(Owner.BLACK);
    }

    @DisplayName("플레이어 중 한 사람이라도 King을 잃게되면 true를 반환한다.")
    @Test
    void anyKingDead() {
        assertThat(players.anyKingDead(board)).isFalse();
        chessGame.move(new Position("c7"), new Position("c6"));
        chessGame.move(new Position("d8"), new Position("a5"));
        chessGame.move(new Position("d2"), new Position("d3"));
        chessGame.move(new Position("a5"), new Position("e1"));
        assertThat(players.anyKingDead(board)).isTrue();
    }
}