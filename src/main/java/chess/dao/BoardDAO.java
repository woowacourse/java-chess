package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Pieces;
import chess.domain.board.Players;
import chess.domain.piece.Color;
import chess.domain.state.Finish;
import chess.domain.state.Play;
import chess.domain.state.State;
import chess.dto.MovePieceDTO;
import chess.dto.WebSimpleBoardDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class BoardDAO extends AbstractDAO {

    private static final BoardDAO boardDAO = new BoardDAO();

    private BoardDAO() {
    }

    public static BoardDAO instance() {
        return boardDAO;
    }

    public int addBoard(WebSimpleBoardDTO webSimpleBoardDTO, Connection connection) throws SQLException {
        String query = "INSERT INTO board(white_player, black_player, turn_is_white, is_finish) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            createBoardDataInit(webSimpleBoardDTO, preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            validateCreate(resultSet);
            return resultSet.getInt(1);
        } finally {
            disconnect(preparedStatement, resultSet);
        }
    }

    private void createBoardDataInit(WebSimpleBoardDTO webSimpleBoardDTO, PreparedStatement preparedStatement)
        throws SQLException {
        preparedStatement.setString(1, webSimpleBoardDTO.getWhitePlayer());
        preparedStatement.setString(2, webSimpleBoardDTO.getBlackPlayer());
        preparedStatement.setBoolean(3, webSimpleBoardDTO.isWhiteTurn());
        preparedStatement.setBoolean(4, webSimpleBoardDTO.isFinish());
    }

    private void validateCreate(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new SQLException("생성실패");
        }
    }

    public List<WebSimpleBoardDTO> findBoardsByPlayerName(String playerName) throws SQLException {
        Connection connection = connection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM board WHERE white_player = ? OR black_player = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerName);
            preparedStatement.setString(2, playerName);
            resultSet = preparedStatement.executeQuery();

            return WebBoardDTOs(resultSet);
        } finally {
            closeConnection(connection);
            disconnect(preparedStatement, resultSet);
        }
    }

    private List<WebSimpleBoardDTO> WebBoardDTOs(ResultSet resultSet)
        throws SQLException {
        List<WebSimpleBoardDTO> webSimpleBoardDTOS = new ArrayList<>();
        while (resultSet.next()) {
            WebSimpleBoardDTO webSimpleBoardDTO = new WebSimpleBoardDTO();
            webSimpleBoardDTO.setBoardId(resultSet.getInt("board_id"));
            webSimpleBoardDTO.setWhitePlayer(resultSet.getString("white_player"));
            webSimpleBoardDTO.setBlackPlayer(resultSet.getString("black_player"));
            webSimpleBoardDTO.setWhiteTurn(resultSet.getBoolean("turn_is_white"));
            webSimpleBoardDTO.setFinish(resultSet.getBoolean("is_finish"));
            webSimpleBoardDTOS.add(webSimpleBoardDTO);
        }
        return webSimpleBoardDTOS;
    }

    public Board findBoardByBoardId(int boardId, Pieces pieces) throws SQLException {
        Connection connection = connection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM board WHERE board_id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardId);
            resultSet = preparedStatement.executeQuery();
            validateFindBoard(resultSet);
            return createBoardObject(resultSet, pieces);
        } finally {
            closeConnection(connection);
            disconnect(preparedStatement, resultSet);
        }
    }

    private Board createBoardObject(ResultSet resultSet, Pieces pieces)
        throws SQLException {
        Players players = new Players(resultSet.getString("white_player"),
            resultSet.getString("black_player"));
        Color color = getColor(resultSet);
        State state = getState(resultSet, color, pieces);
        return new Board(state, players);
    }

    private State getState(ResultSet resultSet, Color color, Pieces pieces) throws SQLException {
        if (resultSet.getBoolean("is_finish")) {
            return new Finish(color, pieces);
        }
        return new Play(color, pieces);
    }

    private Color getColor(ResultSet resultSet) throws SQLException {
        if (resultSet.getBoolean("turn_is_white")) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    private void validateFindBoard(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new IllegalArgumentException("없는 보드입니다.");
        }
    }

    public void updateBoard(Board board, MovePieceDTO movePieceDTO, Connection connection)
        throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE board SET turn_is_white = ?, is_finish = ? WHERE board_id = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            updateBoardDataInit(board, movePieceDTO.getBoardId(), preparedStatement);
            preparedStatement.executeUpdate();
        } finally {
            disconnect(preparedStatement, null);
        }
    }

    private void updateBoardDataInit(Board board, int boardId, PreparedStatement preparedStatement)
        throws SQLException {
        preparedStatement.setBoolean(1, board.turn().isWhite());
        preparedStatement.setBoolean(2, board.isFinish());
        preparedStatement.setInt(3, boardId);
    }
}
