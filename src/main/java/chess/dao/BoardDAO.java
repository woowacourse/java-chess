package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Pieces;
import chess.domain.board.Players;
import chess.domain.piece.Color;
import chess.domain.state.Finish;
import chess.domain.state.Play;
import chess.domain.state.State;
import chess.dto.WebBoardDTO;
import chess.dto.WebPiecesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class BoardDAO extends AbstractDAO {

    private static final BoardDAO boardDAO = new BoardDAO();

    private BoardDAO() {
    }

    public static BoardDAO instance() {
        return boardDAO;
    }

    public int addBoard(WebBoardDTO webBoardDTO, WebPiecesDTO webPiecesDTO) throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = null;
        connection.setAutoCommit(false);
        try {
            String query = "INSERT INTO board(white_player, black_player, turn_is_white, is_finish) VALUES (?, ?, ?, ?)";
            pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            createBoardDataInit(webBoardDTO, pstmt);
            pstmt.executeUpdate();
            ResultSet resultSet = pstmt.getGeneratedKeys();
            validateCreate(resultSet);
            int boardID = resultSet.getInt(1);
            addPieces(webPiecesDTO, connection, boardID);
            connection.commit();
            return boardID;
        } catch (Exception e) {
            connection.rollback();
            System.out.println(e.getMessage());
        } finally {
            disconnect(connection, pstmt);
        }
        throw new SQLException();
    }

    private void createBoardDataInit(WebBoardDTO webBoardDTO, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, webBoardDTO.getWhitePlayer());
        pstmt.setString(2, webBoardDTO.getBlackPlayer());
        pstmt.setBoolean(3, webBoardDTO.getTurnIsWhite());
        pstmt.setBoolean(4, webBoardDTO.getIsFinish());
    }

    private void validateCreate(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new SQLException("생성실패");
        }
    }

    private void addPieces(WebPiecesDTO webPiecesDTO, Connection connection, int boardID)
        throws SQLException {
        PiecesDAO piecesDAO = PiecesDAO.instance();
        piecesDAO.addPieces(boardID, webPiecesDTO.getPieces(), connection);
    }

    public List<WebBoardDTO> findBoardsByPlayerName(String playerName) throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = null;
        try {
            String query = "SELECT * FROM board WHERE white_player = ? OR black_player = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, playerName);
            pstmt.setString(2, playerName);
            ResultSet resultSet = pstmt.executeQuery();

            return WebBoardDTOs(resultSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect(connection, pstmt);
        }
        throw new SQLException();
    }

    private List<WebBoardDTO> WebBoardDTOs(ResultSet resultSet)
        throws SQLException {
        List<WebBoardDTO> webBoardDTOs = new ArrayList<>();
        while (resultSet.next()) {
            WebBoardDTO webBoardDTO = new WebBoardDTO();
            webBoardDTO.setBoardId(resultSet.getInt("board_id"));
            webBoardDTO.setWhitePlayer(resultSet.getString("white_player"));
            webBoardDTO.setBlackPlayer(resultSet.getString("black_player"));
            webBoardDTO.setTurnIsWhite(resultSet.getBoolean("turn_is_white"));
            webBoardDTO.setFinish(resultSet.getBoolean("is_finish"));
            webBoardDTOs.add(webBoardDTO);
        }
        return webBoardDTOs;
    }

    public Board findBoardByBoardId(int boardId) throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = null;
        try {
            String query = "SELECT * FROM board WHERE board_id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, boardId);
            ResultSet resultSet = pstmt.executeQuery();
            validateFindBoard(resultSet);
            return dataToBoardObject(resultSet, boardId, connection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect(connection, pstmt);
        }
        throw new SQLException();
    }

    private Board dataToBoardObject(ResultSet resultSet, int boardId, Connection connection) throws SQLException {
        Players players = new Players(resultSet.getString("white_player"), resultSet.getString("black_player"));
        Color color = getColor(resultSet);
        PiecesDAO piecesDAO = PiecesDAO.instance();
        Pieces pieces = piecesDAO.joinPieces(boardId, connection);
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
        if (!resultSet.next()){
            throw new IllegalArgumentException("없는 보드입니다.");
        }
    }

    public boolean updateBoard(WebBoardDTO webBoardDTO, WebPiecesDTO webPiecesDTO) throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = null;
        connection.setAutoCommit(false);
        try {
            String query = "UPDATE board SET turn_is_white = ?, is_finish = ? WHERE board_id = ?";
            pstmt = connection.prepareStatement(query);
            updateBoardDataInit(webBoardDTO, pstmt);
            pstmt.executeUpdate();
            PiecesDAO.instance().updatePiece(webBoardDTO.getBoardId(), webPiecesDTO, connection);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            System.out.println(e.getMessage());
            return false;
        } finally {
            disconnect(connection, pstmt);
        }
        return true;
    }

    private void updateBoardDataInit(WebBoardDTO webBoardDTO, PreparedStatement pstmt) throws SQLException {
        pstmt.setBoolean(1, webBoardDTO.getTurnIsWhite());
        pstmt.setBoolean(2, webBoardDTO.getIsFinish());
        pstmt.setInt(3, webBoardDTO.getBoardId());
    }

    private void disconnect(Connection connection, PreparedStatement pstmt) throws SQLException {
        if (!Objects.isNull(pstmt)) {
            pstmt.close();
        }
        closeConnection(connection);
    }
}
