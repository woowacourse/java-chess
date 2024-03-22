package domain;

import domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class PathPieces {

    private final List<Piece> pathPieces;

    public PathPieces() {
        this.pathPieces = new ArrayList<>();
    }

    public PathPieces(List<Piece> pathPieces) {
        this.pathPieces = new ArrayList<>(pathPieces);
    }

    public boolean notAllEmpty() {
        return pathPieces.stream()
                .anyMatch(Piece::isNotEmpty);
    }
}
