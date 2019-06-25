package chess.domain;

import chess.domain.boardcell.ChessPiece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 좌표를 키로 하여 게임판 위에 살아있는 말들을 갖는 일급 컬렉션.
 */
public class LivingPieceGroup {
    private final Map<CoordinatePair, ChessPiece> livingPieces;

    private LivingPieceGroup(Map<CoordinatePair, ChessPiece> livingPieces) {
        this.livingPieces = Collections.unmodifiableMap(livingPieces);
    }

    public static LivingPieceGroup of(Map<CoordinatePair, ChessPiece> state) {
        return new LivingPieceGroup(state);
    }

    public ChessPiece at(CoordinatePair coordinate) {
        return livingPieces.get(coordinate);
    }

    public LivingPieceGroup movePiece(CoordinatePair from, CoordinatePair to) {
        Map<CoordinatePair, ChessPiece> newState = new HashMap<>(livingPieces);
        newState.put(to, newState.get(from));
        newState.remove(from);
        newState.remove(from);
        return of(newState);
    }

    public Stream<Map.Entry<CoordinatePair, ChessPiece>> entryStream() {
        return livingPieces.entrySet().stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivingPieceGroup that = (LivingPieceGroup) o;
        return Objects.equals(livingPieces, that.livingPieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livingPieces);
    }
}
