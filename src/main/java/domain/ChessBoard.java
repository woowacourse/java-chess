package domain;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

    public void move(Position current, Position target) {
        if (current.equals(target)) {
            throw new IllegalArgumentException("source 위치와 target 위치가 같습니다.");
        }
        if (!board.containsKey(current)) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }
        Map<Position, Piece> piecesOnPath = collectPiecesOnPath(current, target);

        Piece piece = board.get(current);
        boolean canPieceMove = piece.canMove(current, target, piecesOnPath);

        if (canPieceMove) {
            board.remove(current);
            board.put(target, piece);
        } else {
            throw new IllegalArgumentException("이동할 수 없는 target 입니다.");
        }
    }

    private Map<Position, Piece> collectPiecesOnPath(Position current, Position target) {
        if (current.isDiagonal(target)) { // 대각
            List<File> files = current.findBetweenFile(target);
            List<Rank> ranks = current.findBetweenRank(target);

            List<Position> path = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                path.add(new Position(files.get(i), ranks.get(i)));
            }
            System.out.println(path);

            return makePiecesOnPath(target, path);
        }

        if (current.isSameRank(target)) { // 수평
            List<File> files = current.findBetweenFile(target);

            List<Position> path = files.stream()
                    .map(current::createWithSameRank)
                    .toList();

            return makePiecesOnPath(target, path);
        }

        if (current.isSameFile(target)) { // 수직
            List<Rank> files = current.findBetweenRank(target);

            List<Position> path = files.stream()
                    .map(current::createWithSameFile)
                    .toList();

            return makePiecesOnPath(target, path);
        }

        return new LinkedHashMap<>();
    }

    private Map<Position, Piece> makePiecesOnPath(Position target, List<Position> positions) {
        Map<Position, Piece> pieces = board.entrySet().stream()
                .filter(entry -> positions.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (board.containsKey(target)) {
            pieces.put(target, board.get(target));
        }
        return pieces;
    }

    public Piece piece(Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public boolean isBlack(Position current) {
        return board.containsKey(current) && board.get(current).isBlack();
    }

    public Piece get(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
        return board.get(position);
    }
}
