package chess.service;

import chess.dao.ChessDataSource;
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
import java.util.List;
import java.util.stream.Collectors;

public class ChessService {

    private static final ChessDataSource CHESS_DATA_SOURCE = new ChessDataSource();
    private static final RoomDao ROOM_DAO = new RoomDao(CHESS_DATA_SOURCE);
    private static final UserDao USER_DAO = new UserDao(CHESS_DATA_SOURCE);
    private static final PlayLogDao PLAY_LOG_DAO = new PlayLogDao(CHESS_DATA_SOURCE);

    public List<RoomDto> openedRooms() {
        return ROOM_DAO.openedRooms();
    }

    public String create(RoomDto newRoom) {
        USER_DAO.insert(newRoom.getWhite());
        USER_DAO.insert(newRoom.getBlack());
        return ROOM_DAO.insert(newRoom);
    }

    public BoardDto latestBoard(String id) {
        return PLAY_LOG_DAO.latestBoard(id);
    }

    public UsersInRoomDto usersInRoom(String id) {
        return USER_DAO.usersInRoom(id);
    }

    public BoardDto start(String id) {
        Board board = PLAY_LOG_DAO.latestBoard(id).toEntity();
        ChessGame chessGame = chessGameFromDb(board, id);
        chessGame.start();
        BoardDto boardDto = new BoardDto(board);
        GameStatusDto gameStatusDto = new GameStatusDto(chessGame);
        PLAY_LOG_DAO.insert(boardDto, gameStatusDto, id);
        return new BoardDto(board);
    }

    public List<PointDto> movablePoints(String id, String point) {
        Board board = boardFromDb(id);
        ChessGame chessGame = chessGameFromDb(board, id);
        List<Point> movablePoints = chessGame.movablePoints(Point.of(point));
        return movablePoints.stream()
            .map(PointDto::new)
            .collect(Collectors.toList());
    }

    public BoardDto move(String id, String sourceName, String destinationName) {
        Board board = boardFromDb(id);
        ChessGame chessGame = chessGameFromDb(board, id);

        chessGame.move(Point.of(sourceName), Point.of(destinationName));
        PLAY_LOG_DAO.insert(new BoardDto(board), new GameStatusDto(chessGame), id);
        if (!chessGame.isOngoing() && chessGame.winner() != Team.NONE) {
            USER_DAO.updateStatistics(id, chessGame.winner());
        }
        return new BoardDto(board);
    }

    public GameStatusDto gameStatus(String id) {
        Board board = boardFromDb(id);
        ChessGame chessGame = chessGameFromDb(board, id);

        return new GameStatusDto(chessGame);
    }

    public void exit(String id) {
        Board board = boardFromDb(id);
        ChessGame chessGame = chessGameFromDb(board, id);
        chessGame.end();
        BoardDto boardDto = new BoardDto(board);
        GameStatusDto gameStatusDto = new GameStatusDto(chessGame);
        PLAY_LOG_DAO.insert(boardDto, gameStatusDto, id);
    }

    public void close(String id) {
        ROOM_DAO.close(id);
    }

    private Board boardFromDb(String roomId) {
        return PLAY_LOG_DAO.latestBoard(roomId).toEntity();
    }

    private ChessGame chessGameFromDb(Board board, String roomId) {
        GameStatusDto gameStatusDto = PLAY_LOG_DAO.latestGameStatus(roomId);
        Turn turn = gameStatusDto.toTurnEntity();
        GameState gameState = gameStatusDto.toGameStateEntity(board);
        return new ChessGame(turn, new ScoreBoard(board), gameState);
    }
}
