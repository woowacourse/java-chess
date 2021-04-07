package chess.dao;

import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import chess.dto.response.ResponseCode;
import chess.exception.ChessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PieceDAO {
    private static final int FIRST_PARAMETER_INDEX = 1;
    private static final int FIRST_COLUMN = 1;
    private static final int SECOND_COLUMN = 2;
    private static final int THIRD_COLUMN = 3;
    private static final int FOURTH_COLUMN = 4;
    private static final int FIFTH_COLUMN = 5;
    private static final int SECOND_PARAMETER_INDEX = 2;
    private static final int THIRD_PARAMETER_INDEX = 3;
    private static final int FOURTH_PARAMETER_INDEX = 4;

    private final ConnectionSetup con;

    public PieceDAO() {
        con = new ConnectionSetup();
    }

    public long createPiece(long gridId, Piece piece) throws SQLException {
        try (Connection connection = con.getConnection()) {
            boolean isBlack = piece.isBlack();
            String position = String.valueOf(piece.position().x()) + String.valueOf(piece.position().y());
            String name = String.valueOf(piece.name());
            String query = "INSERT INTO piece (isBlack, position, gridId, name) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setBoolean(FIRST_PARAMETER_INDEX, isBlack);
            pstmt.setString(SECOND_PARAMETER_INDEX, position);
            pstmt.setLong(THIRD_PARAMETER_INDEX, gridId);
            pstmt.setString(FOURTH_PARAMETER_INDEX, name);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(FIRST_COLUMN);
            }
            throw new ChessException(ResponseCode.WRONG_ARGUMENTS_INSERT_ERROR);
        }
    }

    public List<PieceDto> findPiecesByGridId(long gridId) throws SQLException {
        try (Connection connection = con.getConnection()) {
            String query = "SELECT * FROM piece WHERE gridId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setLong(FIRST_PARAMETER_INDEX, gridId);
            ResultSet rs = pstmt.executeQuery();
            List<PieceDto> pieces = new ArrayList<>();
            while (rs.next()) {
                pieces.add(new PieceDto(
                        rs.getLong(FIRST_COLUMN),
                        rs.getBoolean(SECOND_COLUMN),
                        rs.getString(THIRD_COLUMN),
                        rs.getLong(FOURTH_COLUMN),
                        rs.getString(FIFTH_COLUMN)
                ));
            }
            return pieces;
        }
    }

    public void updatePiece(long pieceId, boolean isBlack, char name) throws SQLException {
        try (Connection connection = con.getConnection()) {
            String query = "UPDATE piece SET isBlack = ?, name = ?  WHERE pieceId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(FIRST_PARAMETER_INDEX, isBlack);
            pstmt.setString(SECOND_PARAMETER_INDEX, String.valueOf(name));
            pstmt.setLong(THIRD_PARAMETER_INDEX, pieceId);
            pstmt.executeUpdate();
        }
    }
}
