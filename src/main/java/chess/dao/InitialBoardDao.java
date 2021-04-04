package chess.dao;

import chess.domain.board.Position;
import chess.domain.exceptions.DatabaseException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static chess.dao.DbConnection.getConnection;

public class InitialBoardDao {

    public HashMap<Position, Piece> findInitialBoard() {
        String query = "SELECT position.address, piece.piece_kind, piece.piece_color FROM ((initial_board " +
            "INNER JOIN piece ON initial_board.piece_id = piece.pid) " +
            "INNER JOIN position ON initial_board.position_id = position.pid)";

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()) {
            HashMap<Position, Piece> board = new HashMap<>();

            while (rs.next()) {
                Position position = Position.from(rs.getString("address"));
                PieceKind pieceKind = PieceKind.pieceKindByName(rs.getString("piece_kind"));
                PieceColor pieceColor = PieceColor.pieceColorByName(rs.getString("piece_color"));
                Piece piece = new Piece(pieceKind, pieceColor);

                board.put(position, piece);
            }

            return board;
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }
}
