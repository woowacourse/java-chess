package chess.domain.board;

import chess.domain.piece.GamePiece;
import chess.domain.piece.GamePieceDAO;
import chess.domain.player.User;
import chess.repository.RepositoryUtil;

import java.sql.*;
import java.util.Map;

public class BoardDAO {

    private static final int NOT_FOUND = 0;

    private final Connection connection;
    private final PositionDAO positionDAO;
    private final GamePieceDAO gamePieceDAO;

    public BoardDAO() {
        this.connection = RepositoryUtil.getConnection();
        this.positionDAO = new PositionDAO();
        this.gamePieceDAO = new GamePieceDAO();
    }

    public int addBoard(User user1, User user2, Board board) throws SQLException {
        String query = "INSERT INTO board (user1, user2, turn) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, user1.getName());
        pstmt.setString(2, user2.getName());
        pstmt.setString(3, Integer.toString(board.getStatus().getTurn()));
        pstmt.executeUpdate();

        ResultSet generatedKeys = pstmt.getGeneratedKeys();

        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        return NOT_FOUND;
    }

    public int findIdByUserNames(String name1, String name2) throws SQLException {
        ResultSet rs = selectAllByUserNames(name1, name2);

        if (!rs.next()) return NOT_FOUND;

        return rs.getInt("id");
    }

    public int findTurnByUserNames(String name1, String name2) throws SQLException {
        ResultSet rs = selectAllByUserNames(name1, name2);

        if (!rs.next()) return NOT_FOUND;

        return rs.getInt("turn");
    }

    private ResultSet selectAllByUserNames(String name1, String name2) throws SQLException {
        String query = "SELECT * FROM board WHERE user1 = ? AND user2 = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, name1);
        pstmt.setString(2, name2);

        return pstmt.executeQuery();
    }

    // Board - Position - Piece는 CASCADE

    public boolean deleteByUserNames(String name1, String name2) throws SQLException {
        String query = "DELETE FROM board WHERE user1 = ? AND user2 = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, name1);
        pstmt.setString(2, name2);
        int affectedRowCount = pstmt.executeUpdate();

        return affectedRowCount > NOT_FOUND;
    }

    public void saveBoard(Board board, User user1, User user2) throws SQLException {
        int boardId = addBoard(user1, user2, board);

        for (Map.Entry<Position, GamePiece> entry : board.getBoard().entrySet()) {
            int positionId = positionDAO.addPosition(boardId, entry.getKey());
            gamePieceDAO.addGamePiece(positionId, entry.getValue());
        }
    }

    public void updateBoard(Board board, User user1, User user2) throws SQLException {
        int boardId = findIdByUserNames(user1.getName(), user2.getName());

        updateTurnByBoardId(boardId, board.getStatus().getTurn());
        for (Map.Entry<Position, GamePiece> entry : board.getBoard().entrySet()) {
            int positionId = positionDAO.findIdByPositionName(boardId, entry.getKey().getName());
            gamePieceDAO.updatePieceNameByPositionId(positionId, entry.getValue().getName());
        }
    }

    private void updateTurnByBoardId(int boardId, int turn) throws SQLException {
        String query = "UPDATE board SET turn = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, Integer.toString(turn));
        pstmt.setString(2, Integer.toString(boardId));
        pstmt.executeUpdate();
    }

    public Board findByUsers(User user1, User user2) throws SQLException {
        int boardId = findIdByUserNames(user1.getName(), user2.getName());
        Status status = new Status(findTurnByUserNames(user1.getName(), user2.getName()), StatusType.PROCESSING);

        Map<Position, GamePiece> board = positionDAO.findBoardContentByBoardId(boardId);

        return Board.from(board, status);
    }

    public boolean isBoardExist(User user1, User user2) throws SQLException {
        return findIdByUserNames(user1.getName(), user2.getName()) != NOT_FOUND;
    }
}
