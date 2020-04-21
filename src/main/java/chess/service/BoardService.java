package chess.service;

import chess.ChessGame;
import chess.dao.GamesDao;
import chess.dao.MoveDao;
import chess.domain.position.Position;
import chess.dto.ScoreDto;
import chess.dto.TurnDto;
import chess.dto.UnitsDto;

import java.sql.SQLException;
import java.util.Map;

public class BoardService {
    private GamesDao gamesDao = new GamesDao();
    private MoveDao moveDao = new MoveDao();

    public Map<String, String> everyGames() throws SQLException, ClassNotFoundException {
        return gamesDao.everyGames();
    }

    public TurnDto turn(int id) throws SQLException, ClassNotFoundException {
        ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
        return new TurnDto(game.turn());
    }

    public UnitsDto board(int id) throws SQLException, ClassNotFoundException {
        ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
        return new UnitsDto(game.board().getBoard());
    }

    public ScoreDto score(int id) throws SQLException, ClassNotFoundException {
        ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
        return new ScoreDto(game.status());
    }

    public UnitsDto move(int id, Position source, Position target) throws SQLException, ClassNotFoundException {
        ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
        game.move(source, target);
        moveDao.save(source, target, id);
        return new UnitsDto(game.board().getBoard());
    }
}
