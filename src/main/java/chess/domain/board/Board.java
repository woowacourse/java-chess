package chess.domain.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public class Board {

    private static final int INITIAL_WHITE_SPECIAL_RANK = 1;
    private static final int INITIAL_WHITE_PAWN_RANK = 2;
    private static final int INITIAL_BLACK_SPECIAL_RANK = 8;
    private static final int INITIAL_BLACK_PAWN_RANK = 7;

    private final Map<Coordinate, Piece> pieces = new HashMap<>();

    public Board(Map<Coordinate, Piece> pieces) {
        this.pieces.putAll(pieces);
    }

    public Board() {
        this(BoardFactory.createInitialPieces(
                INITIAL_WHITE_SPECIAL_RANK,
                INITIAL_WHITE_PAWN_RANK,
                INITIAL_BLACK_SPECIAL_RANK,
                INITIAL_BLACK_PAWN_RANK
        ));
    }

    public Piece findByCoordinate(Coordinate coordinate) {
        return pieces.get(coordinate);
    }

    public void move(Coordinate source, Coordinate target) {
        Piece sourcePiece = findSource(source, target);

        pieces.put(target, sourcePiece);
        pieces.remove(source);
    }

    private Piece findSource(Coordinate source, Coordinate target) {
        validateSourceExist(source);
        validateTargetExist(target);
        validateMovable(source, target);
        validateCasePawn(source, target);

        return pieces.get(source);
    }

    private void validateSourceExist(Coordinate source) {
        if (!pieces.containsKey(source)) {
            throw new NoSuchElementException("보드에 움직일 대상 기물이 없습니다.");
        }
    }

    private void validateTargetExist(Coordinate target) {
        if (pieces.containsKey(target)) {
            throw new IllegalStateException("목적지 좌표에 기물이 이미 존재합니다.");
        }
    }

    private void validateMovable(Coordinate source, Coordinate target) {
        Piece sourcePiece = pieces.get(source);
        List<Coordinate> movablePath = sourcePiece.findMovablePath(source, target);
        List<Coordinate> realPath = createRealPath(movablePath);

        validatePath(target, movablePath);
        validateStuckPath(target, realPath);
    }

    private List<Coordinate> createRealPath(List<Coordinate> possibleCoordinate) {
        List<Coordinate> realPath = new ArrayList<>();
        for (Coordinate coordinate : possibleCoordinate) {
            if (pieces.containsKey(coordinate)) {
                break;
            }

            realPath.add(coordinate);
        }

        return realPath;
    }

    private void validatePath(Coordinate target, List<Coordinate> possibleCoordinate) {
        if (!possibleCoordinate.contains(target)) {
            throw new IllegalStateException("해당 기물은 목적지 좌표에 갈 수 없습니다.");
        }
    }

    private void validateStuckPath(Coordinate target, List<Coordinate> realPath) {
        if (!realPath.contains(target)) {
            throw new IllegalStateException("경로 중간에 기물이 존재해 이동할 수 없습니다.");
        }
    }

    private void validateCasePawn(Coordinate source, Coordinate target) {
        if (!(pieces.get(source) instanceof Pawn)) {
            return;
        }

        if (Math.abs(source.getRank() - target.getRank()) == 2 &&
                !(source.getRank() == INITIAL_BLACK_PAWN_RANK || source.getRank() == INITIAL_WHITE_PAWN_RANK)) {
            throw new IllegalStateException("폰이 초기 위치가 아니면, 2칸 전진할 수 없습니다.");
        }
    }
}
