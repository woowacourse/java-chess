package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Status;
import chess.domain.piece.GamePiece;
import chess.domain.player.User;
import chess.domain.result.ChessResult;
import chess.repository.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private final UserDAO userDAO;
    private final BoardDAO boardDAO;
    private final PositionDAO positionDAO;
    private final GamePieceDAO gamePieceDAO;

    public ChessGame() {
        DBConnector dbConnector = new DBConnector();
        userDAO = new UserDAO(dbConnector);
        boardDAO = new BoardDAO(dbConnector);
        positionDAO = new PositionDAO(dbConnector);
        gamePieceDAO = new GamePieceDAO(dbConnector);
    }

    public Board start(String whiteName, String blackName) throws SQLException {
        User whitePlayer = loadOrCreateUser(whiteName);
        User blackPlayer = loadOrCreateUser(blackName);

        return loadOrCreateBoard(whitePlayer, blackPlayer);
    }

    private User loadOrCreateUser(String name) throws SQLException {
        User user = userDAO.findUserByName(name).orElseGet(() -> new User(name));
        if (user.isNeverPlayingGame()) {
            userDAO.addUser(user);
        }
        return user;
    }

    private Board loadOrCreateBoard(User whitePlayer, User blackPlayer) throws SQLException {
        try {
            return loadBoard(whitePlayer, blackPlayer);
        } catch (IllegalArgumentException e) {
            Board board = Board.createEmpty().placeInitialPieces();
            saveBoard(board, whitePlayer, blackPlayer);
            return board;
        }
    }

    public Board move(String whiteName, String blackName, String sourceName, String targetName) throws SQLException {
        User whitePlayer = loadOrCreateUser(whiteName);
        User blackPlayer = loadOrCreateUser(blackName);

        Position source = Position.from(sourceName);
        Position target = Position.from(targetName);

        Board board = loadBoard(whitePlayer, blackPlayer).move(source, target);
        saveBoard(board.toSave(), whitePlayer, blackPlayer);

        if (board.isFinished()) {
            updateRecord(whitePlayer, blackPlayer, board);
        }
        return board;
    }

    private void updateRecord(User whitePlayer, User blackPlayer, Board board) throws SQLException {
        if (board.isWhiteTurn()) {
            userDAO.updateByName(whitePlayer.getName(), whitePlayer.win());
            userDAO.updateByName(blackPlayer.getName(), blackPlayer.lose());
            return;
        }
        userDAO.updateByName(blackPlayer.getName(), blackPlayer.win());
        userDAO.updateByName(whitePlayer.getName(), whitePlayer.lose());
    }

    public ChessResult status(String whiteName, String blackName) throws SQLException {
        User whitePlayer = loadOrCreateUser(whiteName);
        User blackPlayer = loadOrCreateUser(blackName);

        return loadBoard(whitePlayer, blackPlayer)
                .calculateResult();
    }

    void saveBoard(Board board, User firstUser, User secondUser) throws SQLException {
        int boardId = boardDAO.upsert(firstUser, secondUser, board);

        for (Map.Entry<Position, GamePiece> entry : board.getBoard().entrySet()) {
            int positionId = positionDAO.upsert(boardId, entry.getKey());
            gamePieceDAO.upsert(positionId, entry.getValue());
        }
    }

    Board loadBoard(User firstUser, User secondUser) throws SQLException {
        int boardId = boardDAO.findIdByUsers(firstUser, secondUser);
        Status status = boardDAO.findStatusByUsers(firstUser, secondUser);

        Map<Position, GamePiece> board = positionDAO.findBoardContentByBoardId(boardId);

        return Board.from(board, status);
    }

    public List<User> findRankers() throws SQLException {
        return userDAO.findRankers();
    }
}
