package domain.board;

import domain.position.Position;
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

public class ChessBoardCreator {

    public Map<Position, Piece> create() {
        Map<Position, Piece> board = new LinkedHashMap<>();
        generateSquares(board);
        initializeSquares(board);
        return board;
    }

    private void generateSquares(Map<Position, Piece> board) {
        Position.allPositions().forEach(
                position -> board.put(position, Empty.instance())
        );
    }

    private void initializeSquares(Map<Position, Piece> board) {
        initializeSide(board, Side.BLACK);
        initializeSide(board, Side.WHITE);
    }

    private void initializeSide(Map<Position, Piece> board, Side side) {
        initializePieces(board, InitialPosition.ROOK.positions(side), () -> new Rook(side));
        initializePieces(board, InitialPosition.KNIGHT.positions(side), () -> new Knight(side));
        initializePieces(board, InitialPosition.BISHOP.positions(side), () -> new Bishop(side));
        initializePieces(board, InitialPosition.QUEEN.positions(side), () -> new Queen(side));
        initializePieces(board, InitialPosition.KING.positions(side), () -> new King(side));
        initializePieces(board, InitialPosition.PAWN.positions(side), () -> new Pawn(side));
    }

    private void initializePieces(Map<Position, Piece> board, List<Position> initialPositions, Supplier<Piece> pieceProvider) {
        initialPositions.forEach(
                position -> board.put(position, pieceProvider.get())
        );
    }
}
