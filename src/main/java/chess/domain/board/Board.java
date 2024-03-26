package chess.domain.board;

import static chess.domain.board.BoardFactory.initialEmpty;

import chess.domain.piece.DummyPiece;
import chess.domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Board {
    private final Map<Coordinate, Piece> pieces;

    public Board(final Map<Coordinate, Piece> pieces) {
        this.pieces = initialEmpty();
        this.pieces.putAll(pieces);
    }

    public Board() {
        pieces = BoardFactory.createInitialPieces();
    }

    public Piece findByCoordinate(final Coordinate coordinate) {
        return pieces.get(coordinate);
    }

    public void move(final Coordinate source, final Coordinate target) {
        validateCoordinates(source, target);
        Piece sourcePiece = findByCoordinate(source);
        List<Coordinate> coordinates = sourcePiece.legalNextCoordinates(source, target);
        if (sourcePiece.canMove(source, target, makeBoardInformation(coordinates))) {
            swap(source, target);
            return;
        }
        throw new IllegalStateException("해당 기물은 목적지 좌표에 갈 수 없습니다.");
    }

    private void swap(final Coordinate source, final Coordinate target) {
        Piece sourcePiece = pieces.get(source);
        pieces.put(target, sourcePiece.updateAfterMove());
        pieces.put(source, DummyPiece.getInstance());
    }

    private Map<Coordinate, Piece> makeBoardInformation(final List<Coordinate> coordinates) {
        Map<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        coordinates.forEach(coordinate -> boardInformation.put(coordinate, findByCoordinate(coordinate)));
        return boardInformation;
    }

    private void validateCoordinates(final Coordinate source, final Coordinate target) {
        if (pieces.get(source).isEmpty()) {
            throw new NoSuchElementException("보드에 움직일 대상 기물이 없습니다.");
        }
        if (source.equals(target)) {
            throw new IllegalArgumentException("동일한 위치로 이동할 수 없습니다.");
        }
    }
}
