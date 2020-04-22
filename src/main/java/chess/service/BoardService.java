package chess.service;

import chess.ChessGame;
import chess.dao.*;
import chess.domain.Board;
import chess.domain.Turn;
import chess.domain.position.Position;
import chess.domain.state.Playing;
import chess.dto.TurnDto;
import chess.dto.GameDto;

import java.sql.SQLException;

public class BoardService {
    private GamesDao gamesDao = new GamesDao();
    private MoveDao moveDao = new MoveDao();
    private final BoardDao boardDao = new BoardDao();
    private final RoomDao roomDao = new RoomDao();
    private final PlayerDao playerDao = new PlayerDao();

//    public Map<String, String> everyGames() throws SQLException, ClassNotFoundException {
//        return gamesDao.everyGames();
//    }

//    public TurnDto turn(int id) throws SQLException, ClassNotFoundException {
//        ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
//        return new TurnDto(game.turn());
//    }

    public GameDto load(int roomId) throws SQLException, ClassNotFoundException {
        Board board = boardDao.findByRoomId(roomId);
        int turnPlayerId = roomDao.findTurnPlayerId(roomId);
        Turn turn = playerDao.findTurn(turnPlayerId);
        ChessGame game = new ChessGame(new Playing(board, turn));
        return new GameDto(board.getBoard(), turn, game.status());
    }

//    public ScoreDto score(int id) throws SQLException, ClassNotFoundException {
//        ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
//        return new ScoreDto(game.status());
//    }

    public GameDto move(int roomId, Position source, Position target) throws SQLException, ClassNotFoundException {
        Board board = boardDao.findByRoomId(roomId);
        int turnPlayerId = roomDao.findTurnPlayerId(roomId);
        Turn turn = playerDao.findTurn(turnPlayerId);
        ChessGame game = new ChessGame(new Playing(board, turn));
        game.move(source, target);
        boardDao.updateBoard(roomId, source.getName(), target.getName());
        return new GameDto(game.board().getBoard(), turn, game.status());
    }
}
