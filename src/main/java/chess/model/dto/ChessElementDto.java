package chess.model.dto;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Piece;
import java.util.Map;

public class ChessElementDto {

    private final Map<BoardSquare, Piece> chessBoard;
    private final Color gameTurn;

    public ChessElementDto(Map<BoardSquare, Piece> chessBoard, Color gameTurn) {
        this.chessBoard = chessBoard;
        this.gameTurn = gameTurn;
    }

    public Map<BoardSquare, Piece> getChessBoard() {
        return chessBoard;
    }

    public Color getGameTurn() {
        return gameTurn;
    }
}
