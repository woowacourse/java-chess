package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;
    private final Map<Position, Piece> board;

    public ChessBoard(List<Piece> whitePieces, List<Piece> blackPieces) {
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
        this.board = ChessBoardFactory.initializeBoard();
    }
}
