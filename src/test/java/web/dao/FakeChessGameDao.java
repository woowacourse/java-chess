package web.dao;

import chess.domain.command.Command;
import chess.domain.game.ChessGame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import web.dto.ChessGameResponse;

public class FakeChessGameDao implements ChessGameDao {

    private Map<Long, ChessGame> store = new HashMap<>();
    private Long idx = 0L;

    @Override
    public Long createGame(ChessGame chessGame) {
        store.put(++idx, chessGame);
        return idx;
    }

    @Override
    public List<ChessGameResponse> findRunningGames() {
        List<ChessGameResponse> chessGameResponses = new ArrayList<>();
        for (ChessGame chessGame : store.values()) {
            makeChessGameResponses(chessGameResponses, chessGame);
        }
        return chessGameResponses;
    }

    private void makeChessGameResponses(List<ChessGameResponse> chessGameResponses,
        ChessGame chessGame) {
        if (!chessGame.isFinished()) {
            chessGameResponses.add(new ChessGameResponse(
                idx,
                chessGame.getName(),
                chessGame.currentTurn().name()
            ));
        }
    }

    @Override
    public ChessGameResponse findByGameId(String gameId) {
        ChessGame chessGame = store.get(Long.parseLong(gameId));
        return new ChessGameResponse(idx, chessGame.getName(), chessGame.currentTurn().name());
    }

    @Override
    public void updateTurn(Long gameId, String turn) throws SQLException {
        ChessGame chessGame = store.get(gameId);
    }

    @Override
    public String findTurn(String gameId) {
        ChessGame chessGame = store.get(Long.parseLong(gameId));
        return chessGame.currentTurn().name();
    }

    @Override
    public void updateGameEnd(Long gameId) {
        ChessGame chessGame = store.get(gameId);
        chessGame.execute(Command.from("end"));
        System.out.println(chessGame.isFinished());
        store.put(gameId, chessGame);
    }
}
