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

    public int pieceSize() {
        return pieces.size();
    }

    public void move(Coordinate source, Coordinate target) {
        if (!pieces.containsKey(source)) {
            throw new NoSuchElementException("보드에 움직일 대상 기물이 없습니다.");
        }

        if (pieces.containsKey(target)) {
            throw new IllegalStateException("목적지 좌표에 기물이 이미 존재합니다.");
        }

        Piece sourcePiece = pieces.get(source);
        List<Coordinate> possibleCoordinate = sourcePiece.findMovablePath(source, target);

        if (!possibleCoordinate.contains(target)) {
            throw new IllegalStateException("해당 기물은 목적지 좌표에 갈 수 없습니다.");
        }

        List<Coordinate> realPath = new ArrayList<>();
        for (Coordinate coordinate : possibleCoordinate) {
            if (pieces.containsKey(coordinate)) {
                break;
            }
            realPath.add(coordinate);
        }

        if (!realPath.contains(target)) {
            throw new IllegalStateException("경로 중간에 기물이 존재해 이동할 수 없습니다.");
        }

        if (sourcePiece instanceof Pawn && !(source.getRank() == INITIAL_BLACK_PAWN_RANK || source.getRank() == INITIAL_WHITE_PAWN_RANK)) {
            if (Math.abs(source.getRank() - target.getRank()) != 1) {
                throw new IllegalStateException("폰이 초기 위치가 아니면, 2칸 전진할 수 없습니다.");
            }
        }

        pieces.put(target, sourcePiece);
        pieces.remove(source);
    }
}
