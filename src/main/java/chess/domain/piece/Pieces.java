package chess.domain.piece;

import chess.domain.board.Score;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pieces {

    private static Map<Class<? extends Piece>, Score> scoreByPiece = Map.of(
            Queen.class, new Score(new BigDecimal("9.0")),
            Rook.class, new Score(new BigDecimal("5.0")),
            Bishop.class, new Score(new BigDecimal("3.0")),
            Knight.class, new Score(new BigDecimal("2.5")),
            Pawn.class, new Score(new BigDecimal("1.0")),
            King.class, new Score(new BigDecimal("0.0"))
    );

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

    public boolean isPieceExistOnPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position));
    }
    public void changePiece(final Piece pieceBeforeChange, final Piece pieceToChange) {
        pieces.set(pieces.indexOf(pieceBeforeChange), pieceToChange);
    }

    public void removePiece(Piece pieceToRemove) {
        pieces.remove(pieceToRemove);
    }

    public List<Piece> getWhitePieces() {
        return getPiecesBySide(Side.WHITE);
    }

    public List<Piece> getBlackPieces() {
        return getPiecesBySide(Side.BLACK);
    }

    private List<Piece> getPiecesBySide(Side side) {
        return pieces.stream()
                .filter(piece -> piece.isSameSide(side))
                .collect(Collectors.toUnmodifiableList());
    }
    
    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }
}
