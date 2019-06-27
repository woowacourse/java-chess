package chess.application.chessround;

import chess.application.ChessJDBCTemplate;
import chess.application.chessround.dto.ChessPieceDTO;
import chess.application.chessround.dto.ChessPointDTO;

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

    public void deleteChessPiece(ChessPointDTO chessPointDTO, boolean isWhiteTurn) {
        String query = "DELETE FROM chess_player WHERE row_num=? AND column_num=? AND is_white_team=? AND round=1";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(chessPointDTO.getRow());
        queryValues.add(chessPointDTO.getColumn());
        queryValues.add(isWhiteTurn);

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        chessJDBCTemplate.executeUpdate(query, queryValues);
    }

    public void insertChessPiece(ChessPieceDTO chessPieceDTO, boolean isWhiteTurn) {
        String query = "INSERT INTO chess_player VALUES(?, ?, ?, ?, 1)";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(chessPieceDTO.getRow());
        queryValues.add(chessPieceDTO.getColumn());
        queryValues.add(chessPieceDTO.getName());
        queryValues.add(isWhiteTurn);

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        chessJDBCTemplate.executeUpdate(query, queryValues);
    }

    public ChessPieceDTO getChessPiece(ChessPointDTO chessPointDTO, boolean isWhiteTurn) {
        String query = "SELECT chess_piece FROM chess_player WHERE row_num=? AND column_num=? AND is_white_team=? AND round=1";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(chessPointDTO.getRow());
        queryValues.add(chessPointDTO.getColumn());
        queryValues.add(isWhiteTurn);

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        List<Map<String, Object>> results = chessJDBCTemplate.executeQuery(query, queryValues);
        Map<String, Object> resultRow = results.get(0);
        return new ChessPieceDTO(chessPointDTO.getRow(), chessPointDTO.getColumn(), (String) resultRow.get("chess_piece"));
    }

    public List<ChessPieceDTO> getAllChessPieces(boolean isWhiteTurn) {
        String query = "SELECT * FROM chess_player WHERE is_white_team=? AND round=1";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(isWhiteTurn);

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        List<Map<String, Object>> results = chessJDBCTemplate.executeQuery(query, queryValues);

        return makeChessPieces(results);
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
