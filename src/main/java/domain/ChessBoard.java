package domain;

import domain.piece.Knight;
import domain.piece.Piece;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public ChessBoard() {
        this(new LinkedHashMap<>());
    }

    public void init() {
        Arrays.stream(Side.values()).forEach(this::initSide);
    }

    private void initSide(Side side) {
        initPiece(InitPosition.ROOK, side, () -> new Rook(side));
        initPiece(InitPosition.KNIGHT, side, () -> new Knight(side));
        initPiece(InitPosition.BISHOP, side, () -> new Bishop(side));
        initPiece(InitPosition.QUEEN, side, () -> new Queen(side));
        initPiece(InitPosition.KING, side, () -> new King(side));
        initPiece(InitPosition.PAWN, side, () -> new Pawn(side));
    }

    private void initPiece(InitPosition initPosition, Side side, Supplier<Piece> pieceSupplier) {
        List<File> files = initPosition.files();
        Rank rank = initPosition.rank(side);
        files.forEach(horizontal -> board.put(new Position(horizontal, rank), pieceSupplier.get()));
    }

    public boolean hasPiece(Position position) {
        return board.containsKey(position);
    }

    public Piece findPiece(Position position) {
        if (!hasPiece(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
        return board.get(position);
    }

    public void move(Position current, Position target) {
        validateNotSamePosition(current, target);

        Piece piece = findMovablePiece(current, target);
        board.remove(current);
        board.put(target, piece);
    }

    private void validateNotSamePosition(Position current, Position target) {
        if (current.equals(target)) {
            throw new IllegalArgumentException("source 위치와 target 위치가 같습니다.");
        }
    }

    private Piece findMovablePiece(Position current, Position target) {
        // MovePath로 수정해야 함
//        Map<Position, Piece> piecesOnPath = findPiecesOnPath(current, target);
//
//        Piece piece = board.get(current);
//        boolean canPieceMove = piece.isRuleBroken(current, target, piecesOnPath);
//        if (!canPieceMove) {
//            throw new IllegalArgumentException("이동할 수 없는 target 입니다.");
//        }
//        return piece;
        throw new IllegalArgumentException("");
    }

    private Map<Position, Piece> findPiecesOnPath(Position current, Position target) {
        if (current.isDiagonal(target)) {
            return makePiecesOnDiagonalPath(current, target);
        }
        if (current.isSameRank(target)) {
            return makePiecesOnHorizontalPath(current, target);
        }
        if (current.isSameFile(target)) {
            return makePiecesOnVerticalPath(current, target);
        }
        return new LinkedHashMap<>();
    }

    private Map<Position, Piece> makePiecesOnDiagonalPath(Position current, Position target) {
        List<File> files = current.findBetweenFiles(target);
        List<Rank> ranks = current.findBetweenRanks(target);

        List<Position> path = IntStream.range(0, files.size())
                .mapToObj(i -> new Position(files.get(i), ranks.get(i)))
                .toList();

        return makePiecesOnPath(target, path);
    }

    private Map<Position, Piece> makePiecesOnHorizontalPath(Position current, Position target) {
        List<File> files = current.findBetweenFiles(target);

        List<Position> path = files.stream()
                .map(current::createWithSameRank)
                .toList();

        return makePiecesOnPath(target, path);
    }

    private Map<Position, Piece> makePiecesOnVerticalPath(Position current, Position target) {
        List<Rank> files = current.findBetweenRanks(target);

        List<Position> path = files.stream()
                .map(current::createWithSameFile)
                .toList();

        return makePiecesOnPath(target, path);
    }

    private Map<Position, Piece> makePiecesOnPath(Position target, List<Position> positions) {
        Map<Position, Piece> pieces = board.entrySet().stream()
                .filter(entry -> positions.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (hasPiece(target)) {
            pieces.put(target, board.get(target));
        }

        return pieces;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
