package chess.repository;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Status;
import chess.domain.piece.GamePiece;
import chess.domain.player.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessDAO {

    private final UserDAO userDAO;
    private final BoardDAO boardDAO;
    private final PositionDAO positionDAO;
    private final GamePieceDAO gamePieceDAO;

    public ChessDAO() {
        RepositoryUtil repositoryUtil = new RepositoryUtil();
        this.userDAO = new UserDAO(repositoryUtil);
        this.boardDAO = new BoardDAO(repositoryUtil);
        this.positionDAO = new PositionDAO(repositoryUtil);
        this.gamePieceDAO = new GamePieceDAO(repositoryUtil);
    }

    public void saveUsers(String... names) throws SQLException {
        for (String name : names) {
            userDAO.insertIfNotExist(new User(name));
        }
    }

    public List<User> findUsers(String... names) throws SQLException {
        List<User> users = new ArrayList<>();
        for (String name : names) {
            User user = userDAO.findUserByName(name);
            users.add(user);
        }

        return users;
    }

    public void saveBoard(Board board, User firstUser, User secondUser) throws SQLException {
        int boardId = boardDAO.insertOrUpdateIfExist(firstUser, secondUser, board);

        for (Map.Entry<Position, GamePiece> entry : board.getBoard().entrySet()) {
            int positionId = positionDAO.insertIfNotExist(boardId, entry.getKey());
            gamePieceDAO.insertOrUpdateIfExist(positionId, entry.getValue());
        }
    }

    public Board loadBoard(User firstUser, User secondUser) throws SQLException {
        int boardId = boardDAO.findIdByUsers(firstUser, secondUser);
        Status status = boardDAO.findStatusByUsers(firstUser, secondUser);

        Map<Position, GamePiece> board = positionDAO.findBoardContentByBoardId(boardId);

        return Board.from(board, status);
    }

    public Board createOrLoadBoard(User firstUser, User secondUser) throws SQLException {
        if (boardDAO.isBoardExist(firstUser, secondUser)) {
            return loadBoard(firstUser, secondUser);
        }
        Board board = Board.createEmpty().placeInitialPieces();
        saveBoard(board, firstUser, secondUser);
        return board;
    }
}
