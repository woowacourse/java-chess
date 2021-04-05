package chess.service;

import chess.dao.PlayLogDao;
import chess.dao.RoomDao;
import chess.dao.UserDao;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.ScoreBoard;
import chess.domain.chessgame.Turn;
import chess.domain.gamestate.GameState;
import chess.dto.web.BoardDto;
import chess.dto.web.GameStatusDto;
import chess.dto.web.PointDto;
import chess.dto.web.RoomDto;
import chess.dto.web.UsersInRoomDto;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ChessService {

    private static final RoomDao ROOM_DAO = new RoomDao();
    private static final UserDao USER_DAO = new UserDao();
    private static final PlayLogDao PLAY_LOG_DAO = new PlayLogDao();

    private Board boardFromDb(String roomId) throws SQLException {
        return PLAY_LOG_DAO.latestBoard(roomId).toEntity();
    }

    private ChessGame chessGameFromDb(Board board, String roomId)
        throws SQLException {
        GameStatusDto gameStatusDto = PLAY_LOG_DAO.latestGameStatus(roomId);
        Turn turn = gameStatusDto.toTurnEntity();
        GameState gameState = gameStatusDto.toGameStateEntity(board);
        return new ChessGame(turn, new ScoreBoard(board), gameState);
    }

    public List<RoomDto> openedRooms() throws SQLException {
        return ROOM_DAO.openedRooms();
    }

    public String create(RoomDto newRoom) throws SQLException {
        USER_DAO.insert(newRoom.getWhite());
        USER_DAO.insert(newRoom.getBlack());
        return ROOM_DAO.insert(newRoom);
    }

    public BoardDto latestBoard(String id) throws SQLException {
        return PLAY_LOG_DAO.latestBoard(id);
    }

    public UsersInRoomDto usersInRoom(String id) throws SQLException {
        return USER_DAO.usersInRoom(id);
    }

    public BoardDto start(String id) throws SQLException {
        Board board = PLAY_LOG_DAO.latestBoard(id).toEntity();
        ChessGame chessGame = chessGameFromDb(board, id);
        chessGame.start();
        BoardDto boardDto = new BoardDto(board);
        GameStatusDto gameStatusDto = new GameStatusDto(chessGame);
        PLAY_LOG_DAO.insert(boardDto, gameStatusDto, id);
        return new BoardDto(board);
    }

    public List<PointDto> movablePoints(String id, String point) throws SQLException {
        Board board = boardFromDb(id);
        ChessGame chessGame = chessGameFromDb(board, id);
        List<Point> movablePoints = chessGame.movablePoints(Point.of(point));
        return movablePoints.stream()
            .map(PointDto::new)
            .collect(Collectors.toList());
    }

    public BoardDto move(String id, String sourceName, String destinationName) throws SQLException {
        Board board = boardFromDb(id);
        ChessGame chessGame = chessGameFromDb(board, id);

        chessGame.move(Point.of(sourceName), Point.of(destinationName));
        PLAY_LOG_DAO.insert(new BoardDto(board), new GameStatusDto(chessGame), id);
        if (!chessGame.isOngoing() && chessGame.winner() != Team.NONE) {
            USER_DAO.updateStatistics(id, chessGame.winner());
        }
        return new BoardDto(board);
    }

    public GameStatusDto gameStatus(String id) throws SQLException {
        Board board = boardFromDb(id);
        ChessGame chessGame = chessGameFromDb(board, id);

        return new GameStatusDto(chessGame);
    }

    public void exit(String id) throws SQLException {
        Board board = boardFromDb(id);
        ChessGame chessGame = chessGameFromDb(board, id);
        chessGame.end();
        BoardDto boardDto = new BoardDto(board);
        GameStatusDto gameStatusDto = new GameStatusDto(chessGame);
        PLAY_LOG_DAO.insert(boardDto, gameStatusDto, id);
    }

    public void close(String id) throws SQLException {
        ROOM_DAO.close(id);
    }
}
