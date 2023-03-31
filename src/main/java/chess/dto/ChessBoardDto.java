package chess.dto;

import chess.domain.board.ChessBoard;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

import java.util.Map;

public class ChessBoardDto {
    private final Map<Coordinate, Piece> pieces;
    
    public ChessBoardDto(ChessBoard chessBoard) {
        this.pieces = chessBoard.pieces();
    }
    
    public Map<Coordinate, Piece> pieces() {
        return pieces;
    }
}
