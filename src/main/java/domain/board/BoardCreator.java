package domain.board;

import domain.square.Square;
import domain.piece.Side;
import domain.piece.Bishop;
import domain.piece.Empty;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
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
