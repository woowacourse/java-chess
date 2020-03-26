package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;

public class Rank {
    private static final int SIZE = 8;

    private List<Piece> pieces;

    public Rank(List<Piece> pieces) {
        validate(pieces);
        this.pieces = new ArrayList<>(pieces);
    }

    private void validate(List<Piece> pieces) {
        if (pieces.size() != SIZE) {
            throw new IllegalArgumentException("체스판 한 줄은 8개 이어야 합니다.");
        }
    }

    public static Rank createBlanks(int y) {
        List<Piece> pieces = new ArrayList<>();
        for (int x = 0; x < SIZE; x++) {
            pieces.add(new Blank(Position.of(x, y)));
        }
        return new Rank(pieces);
    }

    public static Rank createPawns(int y, Color color) {
        List<Piece> pieces = new ArrayList<>();
        for (int x = 0; x < SIZE; x++) {
            pieces.add(new Pawn(Position.of(x, y), color));
        }
        return new Rank(pieces);
    }

    public static Rank createPieces(int y, Color color) {
        List<String> pieceSequence = Arrays.asList("r", "n", "b", "q", "k", "b", "n", "r");
        List<Piece> pieces = new ArrayList<>();
        for (int x = 0; x < SIZE; x++) {
            pieces.add(PieceFactory.create(pieceSequence.get(x), Position.of(x, y), color));
        }
        return new Rank(pieces);
    }

    public boolean hasKing() {
        return pieces.stream()
            .anyMatch(piece -> piece instanceof King);
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public Piece findPiece(int x) {
        return pieces.get(x);
    }

    public void changePiece(int x, Piece piece) {
        pieces.set(x, piece);
    }

    public double calculateScore(Color color) {
        return pieces.stream()
            .filter(piece -> piece.isSameColor(color))
            .mapToDouble(Piece::score)
            .sum();
    }
}
