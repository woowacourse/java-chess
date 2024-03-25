package chess.domain.board;

import chess.domain.piece.Side;
import chess.domain.square.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BoardCreator {

    public Map<Square, Piece> create() {
        Map<Square, Piece> board = new LinkedHashMap<>();
        generateSquares(board);
        initializeSquares(board);
        return board;
    }

    private void generateSquares(Map<Square, Piece> board) {
        Square.allPositions().forEach(
                position -> board.put(position, Empty.instance())
        );
    }

    private void initializeSquares(Map<Square, Piece> board) {
        initializeSide(board, Side.BLACK);
        initializeSide(board, Side.WHITE);
    }

    private void initializeSide(Map<Square, Piece> board, Side side) {
        initializePieces(board, InitialPosition.ROOK.positions(side), () -> new Rook(side));
        initializePieces(board, InitialPosition.KNIGHT.positions(side), () -> new Knight(side));
        initializePieces(board, InitialPosition.BISHOP.positions(side), () -> new Bishop(side));
        initializePieces(board, InitialPosition.QUEEN.positions(side), () -> new Queen(side));
        initializePieces(board, InitialPosition.KING.positions(side), () -> new King(side));
        initializePieces(board, InitialPosition.PAWN.positions(side), () -> new Pawn(side));
    }

    private void initializePieces(Map<Square, Piece> board, List<Square> initialSquares, Supplier<Piece> pieceProvider) {
        initialSquares.forEach(
                position -> board.put(position, pieceProvider.get())
        );
    }
}
