package chess.piece;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private static final int FILE_START_INDEX = 1;
    private static final int FILE_END_INDEX = File.length();

    private final List<Piece> pieces;

    public Pieces() {
        this.pieces = new ArrayList<>(generateInitialPieces());
    }

    private List<Piece> generateInitialPieces() {
        List<Piece> pieces = new ArrayList<>();
        generateRankOnePieces(pieces);
        generateRankTwoPieces(pieces);
        generateRankSevenPieces(pieces);
        generateRankEightPieces(pieces);
        return pieces;
    }

    private void generateRankOnePieces(final List<Piece> pieces) {
        pieces.add(new Rook(new Position(File.A, Rank.ONE), Side.WHITE));
        pieces.add(new Knight(new Position(File.B, Rank.ONE), Side.WHITE));
        pieces.add(new Bishop(new Position(File.C, Rank.ONE), Side.WHITE));
        pieces.add(new Queen(new Position(File.D, Rank.ONE), Side.WHITE));
        pieces.add(new King(new Position(File.E, Rank.ONE), Side.WHITE));
        pieces.add(new Bishop(new Position(File.F, Rank.ONE), Side.WHITE));
        pieces.add(new Knight(new Position(File.G, Rank.ONE), Side.WHITE));
        pieces.add(new Rook(new Position(File.H, Rank.ONE), Side.WHITE));
    }

    private void generateRankTwoPieces(final List<Piece> pieces) {
        for (int fileIndex = FILE_START_INDEX; fileIndex <= FILE_END_INDEX; fileIndex++) {
            pieces.add(new Pawn(new Position(File.of(fileIndex), Rank.TWO), Side.WHITE));
        }
    }

    private void generateRankSevenPieces(final List<Piece> pieces) {
        for (int fileIndex = FILE_START_INDEX; fileIndex <= FILE_END_INDEX; fileIndex++) {
            pieces.add(new Pawn(new Position(File.of(fileIndex), Rank.SEVEN), Side.BLACK));
        }
    }

    private void generateRankEightPieces(final List<Piece> pieces) {
        pieces.add(new Rook(new Position(File.A, Rank.EIGHT), Side.BLACK));
        pieces.add(new Knight(new Position(File.B, Rank.EIGHT), Side.BLACK));
        pieces.add(new Bishop(new Position(File.C, Rank.EIGHT), Side.BLACK));
        pieces.add(new Queen(new Position(File.D, Rank.EIGHT), Side.BLACK));
        pieces.add(new King(new Position(File.E, Rank.EIGHT), Side.BLACK));
        pieces.add(new Bishop(new Position(File.F, Rank.EIGHT), Side.BLACK));
        pieces.add(new Knight(new Position(File.G, Rank.EIGHT), Side.BLACK));
        pieces.add(new Rook(new Position(File.H, Rank.EIGHT), Side.BLACK));
    }

    public Piece findPieceByPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 위치에 존재하는 기물이 없습니다."));
    }

    public void synchronizeMovedPiece(final Piece pieceBeforeMove, final Piece movedPiece) {
        final int pieceBeforeMoveIndex = pieces.indexOf(pieceBeforeMove);
        pieces.set(pieceBeforeMoveIndex, movedPiece);
    }

    public boolean isPieceExistOnPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position));
    }

    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }
}
