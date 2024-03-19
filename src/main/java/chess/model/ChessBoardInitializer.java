package chess.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardInitializer {
    public ChessBoardInitializer() {
    }

    public List<Piece> create() {
        List<Piece> board = new ArrayList<>();
        board.addAll(createSpecialPieces(Side.BLACK));
        board.addAll(createPawns(Side.BLACK));
        board.addAll(createSpecialPieces(Side.WHITE));
        board.addAll(createPawns(Side.WHITE));
        return board;
    }

    private List<Piece> createSpecialPieces(Side side) {
        Rank rank = Rank.ONE;
        if (side == Side.BLACK) {
            rank = Rank.EIGHT;
        }
        return List.of(
                new Rook(side,new ChessPosition(File.A, rank)),
                new Knight(side, new ChessPosition(File.B, rank)),
                new Bishop(side, new ChessPosition(File.C, rank)),
                new Queen(side, new ChessPosition(File.D, rank)),
                new King(side, new ChessPosition(File.E, rank)),
                new Bishop(side, new ChessPosition(File.F, rank)),
                new Knight(side, new ChessPosition(File.G, rank)),
                new Rook(side, new ChessPosition(File.H, rank))
        );
    }

    private List<Piece> createPawns(Side side) {
        Rank rank = Rank.TWO;
        if (side == Side.BLACK) {
            rank = Rank.SEVEN;
        }
        return List.of(
                new Pawn(side, new ChessPosition(File.A, rank)),
                new Pawn(side, new ChessPosition(File.B, rank)),
                new Pawn(side, new ChessPosition(File.C, rank)),
                new Pawn(side, new ChessPosition(File.D, rank)),
                new Pawn(side, new ChessPosition(File.E, rank)),
                new Pawn(side, new ChessPosition(File.F, rank)),
                new Pawn(side, new ChessPosition(File.G, rank)),
                new Pawn(side, new ChessPosition(File.H, rank))
        );
    }
}
