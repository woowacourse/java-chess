package chess.domain.player;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    Players players;

    @BeforeEach
    void initPlayersAndBoard(){
        players = new Players();
    }

    @DisplayName("첫 시작으로 흑팀이 움직이면 예외를 발생한다.")
    @Test
    void validateTurn(){
        assertThrows(IllegalArgumentException.class, ()-> players.validateTurn(new Position("a7")));
    }

    @DisplayName("반대편으로 턴이 넘어간다.")
    @Test
    void validateTurnChange(){
        assertThrows(IllegalArgumentException.class, ()->{
            players.changeTurn();
            players.validateTurn(new Position("a2"));
        });
    }

    @DisplayName("왕이 죽었는지 확인한다.")
    @Test
    void testIsKingDead(){
        players.move(new Position("a2"), new Position("a4"));
        players.move(new Position("a4"), new Position("a5"));
        players.move(new Position("a5"), new Position("a6"));
        players.move(new Position("c7"), new Position("c6"));
        players.move(new Position("d8"), new Position("a5"));
        players.move(new Position("d2"), new Position("d3"));
        players.move(new Position("a5"), new Position("e1"));
        assertThat(players.isEnd()).isTrue();
    }
}