package data;

import chess.board.ChessBoard;
import chess.location.Location;
import chess.piece.type.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardVO {
    private List<PieceVO> boardValue;

    public BoardVO(ChessBoard chessBoard) {
        boardValue = new ArrayList();
        Map<Location, Piece> board = chessBoard.getBoard();
        for (Map.Entry<Location, Piece> elem : board.entrySet()) {
            PieceVO pieceVO = new PieceVO(elem.getKey(), elem.getValue());
            boardValue.add(pieceVO);
        }
    }

    public List<PieceVO> getBoardValue() {
        return boardValue;
    }
}
