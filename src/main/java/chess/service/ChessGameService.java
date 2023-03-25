package chess.service;

import chess.domain.game.ChessGame;
import chess.domain.game.Position;
import chess.domain.game.command.BoardQuery;
import chess.domain.game.command.EndCommand;
import chess.domain.game.command.MoveCommand;
import chess.domain.game.command.StartCommand;
import chess.domain.game.command.StatusQuery;
import chess.domain.game.exception.ChessGameException;
import chess.domain.game.state.EndState;
import chess.domain.game.state.GameState;
import chess.domain.game.state.MovingState;
import chess.domain.game.state.StartState;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.repository.ChessGameDao;
import chess.repository.MoveDao;
import chess.repository.UserDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessGameService {

    private static final int ORIGIN_INDEX = 0;
    private static final int DESTINATION_INDEX = 1;
    private final ChessGameDao chessGameDao;
    private final MoveDao moveDao;
    private final UserDao userDao;

    public ChessGameService(ChessGameDao chessGameDao, MoveDao moveDao, UserDao userDao) {
        this.chessGameDao = chessGameDao;
        this.moveDao = moveDao;
        this.userDao = userDao;
    }

    public void login(String userId) {
        userDao.addUser(userId);
    }

    public void start(int boardId) {
        ChessGame chessGame = loadChessGame(boardId);
        new StartCommand().execute(chessGame);
        chessGameDao.update(boardId, StartState.getInstance().start());
    }

    public void move(int boardId, String origin, String destination) {
        ChessGame chessGame = loadChessGame(boardId);
        MoveCommand moveCommand = MoveCommand.of(origin, destination);
        moveCommand.execute(chessGame);
        moveDao.save(boardId, origin, destination, chessGame.getTurn().getValue());
    }

    public void end(int boardId) {
        ChessGame chessGame = loadChessGame(boardId);
        new EndCommand().execute(chessGame);
        chessGameDao.update(boardId, EndState.getInstance());
    }

    private List<List<Position>> convertToPosition(List<List<String>> moves) {
        List<List<Position>> movesWithPosition = new ArrayList<>();
        for (List<String> move : moves) {
            List<Position> moveWithPosition = new ArrayList<>();
            moveWithPosition.add(Position.from(move.get(ORIGIN_INDEX)));
            moveWithPosition.add(Position.from(move.get(DESTINATION_INDEX)));
            movesWithPosition.add(moveWithPosition);
        }
        return movesWithPosition;
    }

    private ChessGame loadChessGame(int boardId) {
        GameState gameState = loadGameState(boardId);
        List<List<String>> movesByBoardId = moveDao.findMovesByBoardId(boardId);
        List<List<Position>> movesWithPosition = convertToPosition(movesByBoardId);
        return new ChessGame(movesWithPosition, gameState);
    }

    private GameState loadGameState(int boardId) {
        String status = chessGameDao.findStatusByBoardId(boardId);
        switch (status) {
            case "start":
                return StartState.getInstance();
            case "moving":
                return MovingState.getInstance();
            case "end":
                return EndState.getInstance();
            default:
                throw new ChessGameException("존재하지 않는 상태입니다");
        }
    }

    public List<List<Piece>> getBoard(int boardId) {
        ChessGame chessGame = loadChessGame(boardId);
        return new BoardQuery().execute(chessGame);
    }

    public Map<Color, Double> getStatus(int boardId) {
        ChessGame chessGame = loadChessGame(boardId);
        return new StatusQuery().execute(chessGame);
    }

    public List<Integer> getGames(String userId) {
        return chessGameDao.findBoardIdsByUserId(userId);
    }

    public int createGame(String userId) {
        return chessGameDao.save(userId, StartState.getInstance());
    }

    public String joinGame(String userId, int boardId) {
        String status = chessGameDao.findStatusByBoardId(boardId);
        validateExistGame(status);
        String user = chessGameDao.findUserIdByBoardId(boardId);
        validateOwner(userId, user);
        return status;
    }

    private void validateExistGame(String status) {
        if (status == null) {
            throw new ChessGameException("존재하지 않는 게임입니다");
        }
    }

    private void validateOwner(String userId, String user) {
        if (!user.equals(userId)) {
            throw new ChessGameException("자신의 게임이 아닙니다");
        }
    }
}
