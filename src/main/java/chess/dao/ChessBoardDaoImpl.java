package chess.dao;

import chess.database.Connector;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardDaoImpl implements ChessBoardDao {



    public void addPiece(Piece piece) throws SQLException {
        String query = "INSERT INTO chess(name,xposition,yposition,team) VALUES (?, ?, ?, ?)";
        Connection connection = Connector.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setString(1, piece.getPieceName());
        pstmt.setString(2, piece.getXPosition());
        pstmt.setString(3, piece.getYPosition());
        pstmt.setString(4, piece.getTeamStrategy().toString());
        pstmt.executeUpdate();

        pstmt.close();
        connection.close();
    }

    public void deletePiece(Position targetPosition) throws SQLException {
        String query = "delete from chess where xposition=? and yposition=?";
        Connection connection = Connector.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setString(1, targetPosition.getXPosition());
        pstmt.setString(2, targetPosition.getYPosition());

        pstmt.executeUpdate();

        pstmt.close();
        connection.close();

    }

    public void updatePiece(Position sourcePosition, Position targetPosition) throws SQLException {
        String query = "UPDATE chess SET xposition = ?, yposition = ? WHERE xposition=? AND yposition=?";
        Connection connection = Connector.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setString(1, targetPosition.getXPosition());
        pstmt.setString(2, targetPosition.getYPosition());
        pstmt.setString(3, sourcePosition.getXPosition());
        pstmt.setString(4, sourcePosition.getYPosition());

        pstmt.executeUpdate();

        pstmt.close();
        connection.close();
    }

    public void deleteAll() throws SQLException {
        String query = "delete from chess";
        Connection connection = Connector.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.executeUpdate();

        pstmt.close();
        connection.close();

    }

    public ChessBoard find() throws SQLException {
        String query = "SELECT * from chess";
        Connection connection = Connector.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        List<Piece> pieces = new ArrayList<>();
        while (rs.next()) {
            String xPosition = rs.getString("xposition");
            String yPosition = rs.getString("yposition");
            pieces.add(PieceFactory.create(rs.getString("name"), Position.of(xPosition + yPosition)));
        }

        rs.close();
        pstmt.close();
        connection.close();

        return ChessBoard.create(pieces);
    }

    public void addPieces(List<Piece> pieces) throws SQLException {
        for (Piece piece : pieces) {
            addPiece(piece);
        }
    }
}
