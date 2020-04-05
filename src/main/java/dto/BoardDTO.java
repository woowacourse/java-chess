package dto;

import chess.board.ChessBoard;
import chess.location.Location;
import chess.piece.type.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDTO {
    private List<PieceDTO> boardValue;

    public BoardDTO(ChessBoard chessBoard) {
        boardValue = new ArrayList();
        Map<Location, Piece> board = chessBoard.getBoard();
        for (Map.Entry<Location, Piece> elem : board.entrySet()) {
            PieceDTO pieceDTO = new PieceDTO(elem.getKey(), elem.getValue());
            boardValue.add(pieceDTO);
        }
    }
}
