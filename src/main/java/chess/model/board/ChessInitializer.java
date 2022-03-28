package chess.model.board;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChessInitializer implements BoardInitializer {

    @Override
    public List<Piece> initPieces() {
        List<Piece> pieces = new ArrayList<>();
        initBlack(pieces);
        initEmpty(pieces);
        initWhite(pieces);
        return pieces;
    }

    private void initBlack(List<Piece> pieces) {
        initBaseLine(pieces, Rank.EIGHT, Color.BLACK);
        initPawns(pieces, Rank.SEVEN, Color.BLACK);
    }

    private void initBaseLine(List<Piece> pieces, Rank rank, Color color) {
        for (Piece piece : lineUpRoyalPieces(color, rank)) {
            pieces.add(piece);
        }
    }

    private List<Piece> lineUpRoyalPieces(Color color, Rank rank) {
        return List.of(
                new Rook(color, new Square(File.A, rank)),
                new Knight(color, new Square(File.B, rank)),
                new Bishop(color, new Square(File.C, rank)),
                new Queen(color, new Square(File.D, rank)),
                new King(color, new Square(File.E, rank)),
                new Bishop(color, new Square(File.F, rank)),
                new Knight(color, new Square(File.G, rank)),
                new Rook(color, new Square(File.H, rank))
        );
    }

    private void initPawns(List<Piece> pieces, Rank rank, Color color) {
        for (File file : File.values()) {
            pieces.add(new Pawn(color, new Square(file, rank)));
        }
    }

    private void initEmpty(List<Piece> pieces) {
        for (Rank rank : Rank.emptyBaseLine()) {
            initEmptiesInRank(pieces, rank);
        }
    }

    private void initEmptiesInRank(List<Piece> pieces, Rank rank) {
        for (File file : File.values()) {
            pieces.add(new Empty(new Square(file, rank)));
        }
    }

    private void initWhite(List<Piece> pieces) {
        initPawns(pieces, Rank.TWO, Color.WHITE);
        initBaseLine(pieces, Rank.ONE, Color.WHITE);
    }
}
