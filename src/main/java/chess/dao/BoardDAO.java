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

public class BoardDAO extends AbstractDAO {

    private static final BoardDAO boardDAO = new BoardDAO();

    private BoardDAO() {
    }

    public static BoardDAO instance() {
        return boardDAO;
    }

    // 방 생성 함수
    public int createBoard(WebBoardDTO webBoardDTO, WebPiecesDTO webPiecesDTO) throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = null;
        connection.setAutoCommit(false);
        try {
            String query = "INSERT INTO board(white_player, black_player, turn_is_white, is_finish) VALUES (?, ?, ?, ?)";
            pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, webBoardDTO.getWhitePlayer());
            pstmt.setString(2, webBoardDTO.getBlackPlayer());
            pstmt.setBoolean(3, webBoardDTO.getTurnIsWhite());
            pstmt.setBoolean(4, webBoardDTO.getIsFinish());
            pstmt.executeUpdate();
            ResultSet resultSet = pstmt.getGeneratedKeys();
            if (!resultSet.next()) {
                throw new SQLException("생성실패");
            }
            int boardID = resultSet.getInt(1);
            PiecesDAO piecesDAO = PiecesDAO.instance();
            piecesDAO.addPieces(boardID, webPiecesDTO.getPieces(), connection);
            connection.commit();
            return boardID;
        } catch (Exception e) {
            connection.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (!Objects.isNull(pstmt)) {
                pstmt.close();
            }
            closeConnection(connection);
        }
        throw new SQLException();
    }

    // 방 검색 함수
    public List<WebBoardDTO> searchBoard(String playerName) throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = null;
        try {
            String query = "SELECT * FROM board WHERE white_player = ? OR black_player = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, playerName);
            pstmt.setString(2, playerName);
            ResultSet resultSet = pstmt.executeQuery();

            List<WebBoardDTO> webBoardDTOS = new ArrayList<>();
            while (resultSet.next()) {
                WebBoardDTO webBoardDTO = new WebBoardDTO();
                webBoardDTO.setBoardId(resultSet.getInt("board_id"));
                webBoardDTO.setWhitePlayer(resultSet.getString("white_player"));
                webBoardDTO.setBlackPlayer(resultSet.getString("black_player"));
                webBoardDTO.setTurnIsWhite(resultSet.getBoolean("turn_is_white"));
                webBoardDTO.setFinish(resultSet.getBoolean("is_finish"));
                webBoardDTOS.add(webBoardDTO);
            }
            return webBoardDTOS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (!Objects.isNull(pstmt)) {
                pstmt.close();
            }
            closeConnection(connection);
        }
        throw new SQLException();
    }

    // 방 정보얻기 함수
    public Board joinBoard(int boardId) throws SQLException {
        Connection connection = connection();
        PreparedStatement pstmt = null;
        try {
            String query = "SELECT * FROM board WHERE board_id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, boardId);
            ResultSet resultSet = pstmt.executeQuery();

            if (!resultSet.next()){
                throw new IllegalArgumentException("없는 보드입니다.");
            }
            Players players = new Players(resultSet.getString("white_player"), resultSet.getString("black_player"));
            Color color = Color.BLACK;
            if (resultSet.getBoolean("turn_is_white")) {
                color = Color.WHITE;
            }
            PiecesDAO piecesDAO = PiecesDAO.instance();
            Pieces pieces = piecesDAO.joinPieces(boardId, connection);
            State state = new Play(color, pieces);
            if (resultSet.getBoolean("is_finish")) {
                state = new Finish(color, pieces);
            }
            return new Board(state, players);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (!Objects.isNull(pstmt)) {
                pstmt.close();
            }
            closeConnection(connection);
        }
        throw new SQLException();
    }


    // 방 수정 함수
    public boolean updateBoard(WebBoardDTO webBoardDTO, WebPiecesDTO webPiecesDTO) throws SQLException {
        String query = "UPDATE board SET turn_is_white = ?, is_finish = ? WHERE board_id = ?";
        Connection connection = connection();
        connection.setAutoCommit(false);
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, webBoardDTO.getTurnIsWhite());
            pstmt.setBoolean(2, webBoardDTO.getIsFinish());
            pstmt.setInt(3, webBoardDTO.getBoardId());
            pstmt.executeUpdate();
            PiecesDAO.instance().updatePiece(webBoardDTO.getBoardId(), webPiecesDTO, connection);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            System.out.println(e.getMessage());
            return false;
        } finally {
            connection.close();
        }
        return true;
    }
}
