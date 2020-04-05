package dao;

import chess.board.ChessBoard;
import chess.location.Location;
import chess.piece.type.Piece;
import vo.PieceVO;

import java.sql.SQLException;
import java.util.Map;

public class BoardDAO {

    public void addBoard(ChessBoard chessBoard, int gameId) throws SQLException {
        Map<Location, Piece> board = chessBoard.getBoard();
        PieceDAO pieceDAO = new PieceDAO();

        for (Map.Entry<Location, Piece> entry : board.entrySet()) {
            Location location = entry.getKey();
            Piece piece = entry.getValue();

            PieceVO pieceVO = new PieceVO(gameId
                    , Character.toString(piece.getName())
                    , location.getRowValue()
                    , Character.toString(location.getColValue()));

            pieceDAO.addPiece(pieceVO);
        }
    }

}
