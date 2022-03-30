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
        pieces.addAll(lineUpRoyalPieces(color, rank));
    }

    private List<Piece> lineUpRoyalPieces(Color color, Rank rank) {
        return List.of(
                new Rook(color, Square.of(File.A, rank)),
                new Knight(color, Square.of(File.B, rank)),
                new Bishop(color, Square.of(File.C, rank)),
                new Queen(color, Square.of(File.D, rank)),
                new King(color, Square.of(File.E, rank)),
                new Bishop(color, Square.of(File.F, rank)),
                new Knight(color, Square.of(File.G, rank)),
                new Rook(color, Square.of(File.H, rank))
        );
    }

    private void initPawns(List<Piece> pieces, Rank rank, Color color) {
        for (File file : File.values()) {
            pieces.add(new Pawn(color, Square.of(file, rank)));
        }
    }

    private void initEmpty(List<Piece> pieces) {
        for (Rank rank : Rank.emptyBaseLine()) {
            initEmptiesInRank(pieces, rank);
        }
    }

    private void initEmptiesInRank(List<Piece> pieces, Rank rank) {
        for (File file : File.values()) {
            pieces.add(new Empty(Square.of(file, rank)));
        }
    }

    private void initWhite(List<Piece> pieces) {
        initPawns(pieces, Rank.TWO, Color.WHITE);
        initBaseLine(pieces, Rank.ONE, Color.WHITE);
    }
}
