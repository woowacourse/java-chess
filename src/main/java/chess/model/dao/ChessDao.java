package chess.model.dao;

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

public class ChessDao {
    private Connection con;

    public ChessDao(Connection con) {
        this.con = con;
    }

    void deleteAllData() throws SQLException {
        String deleteGameInfoQuery = "DELETE FROM chessgame";
        PreparedStatement pstmt = con.prepareStatement(deleteGameInfoQuery);
        pstmt.executeUpdate();

        String deletePiecesQuery = "DELETE FROM piece";
        pstmt = con.prepareStatement(deletePiecesQuery);
        pstmt.executeUpdate();
    }

    public void initializeBoard(BoardInfo boardInfo, GameInfo gameInfo) throws SQLException {
        deleteAllData();
        insertBoardInfo(boardInfo);
        insertGameInfo(gameInfo);
    }

    private void insertBoardInfo(BoardInfo boardInfo) throws SQLException {
        String insertPieceQuery = "INSERT INTO piece (unitType, side, position) values (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(insertPieceQuery);
        for (Map.Entry<String, String> squarePieceEntry : boardInfo.getPieceMap().entrySet()) {
            pstmt.setString(1, squarePieceEntry.getValue().substring(1, 2));
            pstmt.setString(2, squarePieceEntry.getValue().substring(0, 1));
            pstmt.setString(3, squarePieceEntry.getKey());
            pstmt.executeUpdate();
            pstmt.clearParameters();
        }
    }

    private void insertGameInfo(GameInfo gameInfo) throws SQLException {
        String insertQuery = "INSERT INTO chessgame (side, endflag) values (?, ?)";
        PreparedStatement pstmt = con.prepareStatement(insertQuery);
        pstmt.setString(1, gameInfo.getTurn());
        pstmt.setBoolean(2, gameInfo.isFinished());
        pstmt.executeUpdate();
    }

    public Side loadTurn() throws SQLException {
        String findSideQuery = "SELECT side FROM chessgame";

        PreparedStatement pstmt = con.prepareStatement(findSideQuery);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return Side.WHITE;
        }
        return Side.findSide(rs.getString("side"));
    }

    public Board loadBoard() throws SQLException {
        String findPiecesQuery = "SELECT unitType, side, position FROM piece";
        PreparedStatement pstmt = con.prepareStatement(findPiecesQuery);
        ResultSet rs = pstmt.executeQuery();

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
    }


    public void updateMove(Square beginSquare, Square endSquare) throws SQLException {
        String deleteQuery = "DELETE FROM piece WHERE position = ?";
        PreparedStatement pstmt = con.prepareStatement(deleteQuery);
        pstmt.setString(1, endSquare.toString());
        pstmt.executeUpdate();

        String updateQuery = "UPDATE piece SET position = ? WHERE position = ?";
        pstmt = con.prepareStatement(updateQuery);
        pstmt.setString(1, endSquare.toString());
        pstmt.setString(2, beginSquare.toString());
        pstmt.executeUpdate();
    }

    public void updateGameInfo(GameInfo gameInfo) throws SQLException {
        String updateGameInfoQuery = "UPDATE chessgame SET endflag = ?, side = ?";
        PreparedStatement pstmt = con.prepareStatement(updateGameInfoQuery);
        pstmt.setBoolean(1, gameInfo.isFinished());
        pstmt.setString(2, gameInfo.getTurn());
        pstmt.executeUpdate();
    }

    public boolean checkEmpty() throws SQLException {
        String updateGameInfoQuery = "SELECT * FROM chessgame";
        PreparedStatement pstmt = con.prepareStatement(updateGameInfoQuery);
        ResultSet rs = pstmt.executeQuery();
        return rs == null || !rs.next();
    }
}
