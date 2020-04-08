package dto;

import chess.board.ChessBoard;
import chess.location.Location;
import chess.piece.type.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {
    private List<PieceDto> boardValue;

    public BoardDto(ChessBoard chessBoard) {
        boardValue = new ArrayList();
        Map<Location, Piece> board = chessBoard.getBoard();
        for (Map.Entry<Location, Piece> elem : board.entrySet()) {
            PieceDto pieceDto = new PieceDto(elem.getKey(), elem.getValue());
            boardValue.add(pieceDto);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PieceDto pieceDto : boardValue) {
            sb.append(pieceDto.toString()).append("\n");
        }
        return "BoardDto{" +
                "boardValue=" + boardValue +
                '}';
    }
}
