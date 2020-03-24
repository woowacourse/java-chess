package chess.piece;

import chess.board.Location;
import chess.piece.location.strategy.*;

import java.util.*;
import java.util.stream.Collectors;

public class PieceSet {
    private final boolean isBlack;
    private final Map<Location, Piece> pieceSet;

    public PieceSet(boolean isBlack) {
        this.isBlack = isBlack;
        this.pieceSet = new HashMap<>();
        makePieceSet();
    }

    public void makePieceSet() {
        for (PieceType pieceType : PieceType.values()) {
            Queue<Location> locations = new LinkedList<>(pieceType.getInitialLocation());
            putPieceLocations(pieceType, locations);
        }
    }

    private void putPieceLocations(PieceType pieceType, Queue<Location> locations) {
        for (Piece piece : find(pieceType)) {
            pieceSet.put(locations.poll(), piece);
        }
    }

    private List<Piece> find(PieceType pieceType) {
        return makePieces().stream()
                .filter(piece -> piece.is(pieceType))
                .collect(Collectors.toList());
    }

    private List<Piece> makePieces() {
        List<Piece> pieces = new ArrayList<>();

        for (PieceType pieceType : PieceType.values()) {
            addPieces(pieces, pieceType);
        }
        return Collections.unmodifiableList(pieces);
    }

    private void addPieces(List<Piece> pieces, PieceType pieceType) {
        for (int i = 0; i < pieceType.getSize(); i++) {
            pieces.add(Piece.of(pieceType, isBlack));
        }
    }

    public Map<Location, Piece> getPieceSet() {
        return pieceSet;
    }
}
