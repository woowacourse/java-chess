package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Status;
import chess.domain.piece.GamePiece;
import chess.domain.player.User;
import chess.domain.result.ChessResult;
import chess.repository.*;

import java.sql.SQLException;
import java.util.ArrayList;
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
        saveUsers(whiteName, blackName);
        List<User> users = findUsers(whiteName, blackName);

        User whitePlayer = users.get(0);
        User blackPlayer = users.get(1);

        return loadOrCreateBoard(whitePlayer, blackPlayer);
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
        List<User> users = findUsers(whiteName, blackName);

        User whitePlayer = users.get(0);
        User blackPlayer = users.get(1);

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
        if (board.getStatus().isWhiteTurn()) {
            userDAO.updateRecord(whitePlayer, blackPlayer);
            return;
        }
        userDAO.updateRecord(blackPlayer, whitePlayer);
    }

    public ChessResult status(String whiteName, String blackName) throws SQLException {
        List<User> users = findUsers(whiteName, blackName);

        User whitePlayer = users.get(0);
        User blackPlayer = users.get(1);

        return loadBoard(whitePlayer, blackPlayer)
                .calculateResult();
    }

    void saveUsers(String... names) throws SQLException {
        for (String name : names) {
            userDAO.upsert(new User(name));
        }
    }

    List<User> findUsers(String... names) throws SQLException {
        List<User> users = new ArrayList<>();
        for (String name : names) {
            User user = userDAO.findUserByName(name);
            users.add(user);
        }

        return users;
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
