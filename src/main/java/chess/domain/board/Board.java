package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Board {
    private final Map<Coordinate, Piece> pieces = new HashMap<>();

    public Board(final Map<Coordinate, Piece> pieces) {
        this.pieces.putAll(pieces);
    }

    public Board() {
        this(BoardFactory.createInitialPieces());
    }

    public Piece findByCoordinate(final Coordinate coordinate) {
        return pieces.get(coordinate);
    }

    public void move(final Coordinate source, final Coordinate target) {
        validateSourceExist(source);
        Piece sourcePiece = findByCoordinate(source);
        List<Coordinate> coordinates = sourcePiece.legalNextCoordinates(source, target);
        if (sourcePiece.canMove(source, target, makeBoardInformation(coordinates))) {
            swap(source, target, sourcePiece);
            return;
        }
        throw new IllegalStateException("해당 기물은 목적지 좌표에 갈 수 없습니다.");
    }

    private void swap(final Coordinate source, final Coordinate target, final Piece sourcePiece) {
        pieces.put(target, sourcePiece.updateAfterMove());
        pieces.remove(source);
    }

    private Map<Coordinate, Piece> makeBoardInformation(final List<Coordinate> coordinates) {
        Map<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        coordinates.forEach(coordinate -> boardInformation.put(coordinate, findByCoordinate(coordinate)));
        return boardInformation;
    }

    private void validateSourceExist(final Coordinate source) {
        if (!pieces.containsKey(source)) {
            throw new NoSuchElementException("보드에 움직일 대상 기물이 없습니다.");
        }
    }
}
