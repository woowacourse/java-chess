package chess.model.dao;

import chess.ConnectionFactory;
import chess.model.Square;
import chess.model.board.BasicBoardInitializer;
import chess.model.board.Board;
import chess.model.dto.BoardInfo;
import chess.model.dto.GameInfo;
import chess.model.unit.Piece;
import chess.model.unit.Side;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessDaoImp implements ChessDao {
    private static final String JDBC_ERROR_MSG = "데이터 베이스 연동 간에 에러가 발생했습니다.";
    private static ChessDaoImp chessDaoImp = new ChessDaoImp();

    private ChessDaoImp() {
    }

    static ChessDao getInstance() {
        return chessDaoImp;
    }

    public void deleteAllData() {
        String deleteGameInfoQuery = "DELETE FROM chessgame";
        String deletePiecesQuery = "DELETE FROM piece";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmtGameInfo = con.prepareStatement(deleteGameInfoQuery);
             PreparedStatement pstmtPiece = con.prepareStatement(deletePiecesQuery)) {
            pstmtGameInfo.executeUpdate();
            pstmtPiece.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCErrorException(JDBC_ERROR_MSG);
        }
    }

    public void initializeBoard(BoardInfo boardInfo, GameInfo gameInfo) {
        deleteAllData();
        insertBoardInfo(boardInfo);
        insertGameInfo(gameInfo);
    }

    private void insertBoardInfo(BoardInfo boardInfo) {
        String insertPieceQuery = "INSERT INTO piece (unitType, side, position) values (?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertPieceQuery)) {
            for (Map.Entry<String, String> squarePieceEntry : boardInfo.getPieceMap().entrySet()) {
                pstmt.setString(1, squarePieceEntry.getValue().substring(1, 2));
                pstmt.setString(2, squarePieceEntry.getValue().substring(0, 1));
                pstmt.setString(3, squarePieceEntry.getKey());
                pstmt.executeUpdate();
                pstmt.clearParameters();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCErrorException(JDBC_ERROR_MSG);
        }
    }

    private void insertGameInfo(GameInfo gameInfo) {
        String insertQuery = "INSERT INTO chessgame (side, endflag) values (?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertQuery)) {
            pstmt.setString(1, gameInfo.getTurn());
            pstmt.setBoolean(2, gameInfo.isFinished());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCErrorException(JDBC_ERROR_MSG);
        }
    }

    public Side loadTurn() {
        String findSideQuery = "SELECT side FROM chessgame";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(findSideQuery);
             ResultSet rs = pstmt.executeQuery()) {
            if (!rs.next()) {
                return Side.WHITE;
            }
            return Side.findSide(rs.getString("side"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCErrorException(JDBC_ERROR_MSG);
        }
    }

    public Board loadBoard() {
        String findPiecesQuery = "SELECT unitType, side, position FROM piece";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(findPiecesQuery);
             ResultSet rs = pstmt.executeQuery()) {
            Board board = new Board();

            if (!rs.next()) {
                board.initialize(new BasicBoardInitializer());
                return board;
            }
            Map<Square, Piece> map = new HashMap<>();
            do {
                map.put(Square.of(rs.getString("position"))
                        , Piece.createPiece(rs.getString("side") + rs.getString("unitType")));
            } while (rs.next());
            board.initialize(() -> map);

            return board;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCErrorException(JDBC_ERROR_MSG);
        }
    }


    public void updateMove(Square beginSquare, Square endSquare) {
        String deleteQuery = "DELETE FROM piece WHERE position = ?";
        String updateQuery = "UPDATE piece SET position = ? WHERE position = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmtDelete = con.prepareStatement(deleteQuery);
             PreparedStatement pstmtUpdate = con.prepareStatement(updateQuery)) {
            pstmtDelete.setString(1, endSquare.toString());
            pstmtDelete.executeUpdate();

            pstmtUpdate.setString(1, endSquare.toString());
            pstmtUpdate.setString(2, beginSquare.toString());
            pstmtUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCErrorException(JDBC_ERROR_MSG);
        }
    }

    public void updateGameInfo(GameInfo gameInfo) {
        String updateGameInfoQuery = "UPDATE chessgame SET endflag = ?, side = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(updateGameInfoQuery)) {
            pstmt.setBoolean(1, gameInfo.isFinished());
            pstmt.setString(2, gameInfo.getTurn());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCErrorException(JDBC_ERROR_MSG);
        }
    }

    public boolean checkEmpty() {
        String updateGameInfoQuery = "SELECT * FROM chessgame";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(updateGameInfoQuery)) {
            ResultSet rs = pstmt.executeQuery();
            return rs == null || !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JDBCErrorException(JDBC_ERROR_MSG);
        }
    }
}