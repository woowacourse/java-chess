package chess.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.domain.piece.Color;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private ChessGameDao chessGameDao;

    @BeforeEach
    public void setup() {
        chessGameDao = ChessGameDao.getInstance();
    }

    @Test
    public void crud() {
        Color gameTurn = Color.BLACK;
        Map<Color, String> userNames = new HashMap<>();
        userNames.put(Color.BLACK, "USER_BLACK");
        userNames.put(Color.WHITE, "USER_WHITE");

        // insert
        int gameId = chessGameDao.insert(1, gameTurn, userNames);
        assertThat(chessGameDao.getGameTurn(gameId).get()).isEqualTo(gameTurn);
        assertThat(chessGameDao.getUserNames(gameId)).isEqualTo(userNames);
        assertThat(chessGameDao.isProceeding(gameId).get()).isTrue();

        // update - turn
        chessGameDao.updateTurn(gameId, gameTurn.nextTurnIfEmptyMySelf());
        assertThat(chessGameDao.getGameTurn(gameId).get())
            .isEqualTo(gameTurn.nextTurnIfEmptyMySelf());
        assertThat(chessGameDao.getUserNames(gameId)).isEqualTo(userNames);
        assertThat(chessGameDao.isProceeding(gameId).get()).isTrue();

        // update - proceed
        chessGameDao.updateProceedN(gameId);
        assertThat(chessGameDao.getGameTurn(gameId).get())
            .isEqualTo(gameTurn.nextTurnIfEmptyMySelf());
        assertThat(chessGameDao.getUserNames(gameId)).isEqualTo(userNames);
        assertThat(chessGameDao.isProceeding(gameId).get()).isFalse();

        // delete
        chessGameDao.delete(gameId);

        assertThat(chessGameDao.getGameTurn(gameId).isPresent()).isFalse();
        assertThat(chessGameDao.getUserNames(gameId)).isEmpty();
        assertThat(chessGameDao.isProceeding(gameId).isPresent()).isFalse();

    }


}