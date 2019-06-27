package chess.application.chessround;

import chess.application.ChessJDBCTemplate;
import chess.application.chessround.dto.ChessPieceDTO;
import chess.application.chessround.dto.ChessPointDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessPlayerDAO {
    private static ChessPlayerDAO chessPlayerDAO = null;

    private ChessPlayerDAO() {
    }

    public static ChessPlayerDAO getInstance() {
        if (chessPlayerDAO == null) {
            chessPlayerDAO = new ChessPlayerDAO();
        }
        return chessPlayerDAO;
    }

    public void deleteChessPiece(final ChessPointDTO chessPointDTO, final boolean isWhiteTurn) {
        String query = "DELETE FROM chess_player WHERE row_num=? AND column_num=? AND is_white_team=? AND round=1";

//        List<Object> queryValues = new ArrayList<>();
//        queryValues.add(chessPointDTO.getRow());
//        queryValues.add(chessPointDTO.getColumn());
//        queryValues.add(isWhiteTurn);

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        chessJDBCTemplate.executeUpdate(query,
                (PreparedStatement pstmt) -> {
                    pstmt.setInt(1, chessPointDTO.getRow());
                    pstmt.setInt(2, chessPointDTO.getColumn());
                    pstmt.setBoolean(3, isWhiteTurn);

                    return null;
                });
    }

    public void insertChessPiece(ChessPieceDTO chessPieceDTO, boolean isWhiteTurn) {
        String query = "INSERT INTO chess_player VALUES(?, ?, ?, ?, 1)";
//
//        List<Object> queryValues = new ArrayList<>();
//        queryValues.add(chessPieceDTO.getRow());
//        queryValues.add(chessPieceDTO.getColumn());
//        queryValues.add(chessPieceDTO.getName());
//        queryValues.add(isWhiteTurn);

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        chessJDBCTemplate.executeUpdate(query, (PreparedStatement pstmt) -> {
            pstmt.setInt(1, chessPieceDTO.getRow());
            pstmt.setInt(2, chessPieceDTO.getColumn());
            pstmt.setString(3, chessPieceDTO.getName());
            pstmt.setBoolean(4, isWhiteTurn);

            return null;
        });
    }

    public ChessPieceDTO getChessPiece(ChessPointDTO chessPointDTO, boolean isWhiteTurn) {
        String query = "SELECT chess_piece FROM chess_player WHERE row_num=? AND column_num=? AND is_white_team=? AND round=1";

//        List<Object> queryValues = new ArrayList<>();
//        queryValues.add(chessPointDTO.getRow());
//        queryValues.add(chessPointDTO.getColumn());
//        queryValues.add(isWhiteTurn);

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        List<String> chessPieces = chessJDBCTemplate.executeQuery(query,
                (PreparedStatement pstmt) -> {
                    pstmt.setInt(1, chessPointDTO.getRow());
                    pstmt.setInt(2, chessPointDTO.getColumn());
                    pstmt.setBoolean(3, isWhiteTurn);

                    return null;
                },
                (ResultSet rs, int rowNum) -> rs.getString("chess_piece"));

        return new ChessPieceDTO(chessPointDTO.getRow(), chessPointDTO.getColumn(), chessPieces.get(0));
    }

    public List<ChessPieceDTO> getAllChessPieces(boolean isWhiteTurn) {
        String query = "SELECT * FROM chess_player WHERE is_white_team=? AND round=1";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(isWhiteTurn);

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
//        List<Map<String, Object>> results = chessJDBCTemplate.executeQuery(query, queryValues);

        return chessJDBCTemplate.executeQuery(query,
                (PreparedStatement pstmt) -> {
                    pstmt.setBoolean(1, isWhiteTurn);

                    return null;
                },
                (ResultSet rs, int rowNum) -> new ChessPieceDTO(
                        rs.getInt("row_num"),
                        rs.getInt("column_num"),
                        rs.getString("chess_piece"))
        );
    }

    private List<ChessPieceDTO> makeChessPieces(List<Map<String, Object>> results) {
        List<ChessPieceDTO> chessPieceDTOs = new ArrayList<>();
        for (Map<String, Object> resultRow : results) {
            int row = (int) resultRow.get("row_num");
            int column = (int) resultRow.get("column_num");
            String chessPiece = (String) resultRow.get("chess_piece");
            ChessPieceDTO chessPieceDTO = new ChessPieceDTO(row, column, chessPiece);
            chessPieceDTOs.add(chessPieceDTO);
        }
        return chessPieceDTOs;
    }

    public void clear() {
        String query = "DELETE FROM chess_player WHERE round=1";

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        chessJDBCTemplate.executeUpdate(query);
    }
}
