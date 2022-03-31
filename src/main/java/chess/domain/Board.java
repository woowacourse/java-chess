package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.InitialPieces;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    public static final int INITIAL_KING_COUNT = 2;
    public static final double SAME_COLUMN_PAWN_SUBTRACT = 0.5;

    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board of(Map<Position, Piece> pieces) {
        return new Board(pieces);
    }

    public static Board initialBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        Arrays.stream(PositionY.values())
                .forEach(positionY -> fillRankWithBlank(pieces, positionY));
        Arrays.stream(InitialPieces.values())
                .forEach(piece -> piece.addTo(pieces));
        return new Board(pieces);
    }

    private static void fillRankWithBlank(Map<Position, Piece> pieces, PositionY positionY) {
        Arrays.stream(PositionX.values())
                .map(positionX -> new Position(positionX, positionY))
                .forEach(position -> pieces.put(position, new Blank()));
    }

    public boolean isCastable(Color turn, Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        return isCastablePieces(turn, sourcePiece, targetPiece) && isEmptyBetween(source, target);
    }

    private boolean isCastablePieces(Color turn, Piece sourcePiece, Piece targetPiece) {
        return isCastableSourcePiece(turn, sourcePiece) && isCastableTargetPiece(turn, targetPiece);
    }

    private boolean isCastableSourcePiece(Color turn, Piece sourcePiece) {
        return sourcePiece.isSameColor(turn) && sourcePiece.isKing() && sourcePiece.isNeverDisplaced();
    }

    private boolean isCastableTargetPiece(Color turn, Piece targetPiece) {
        return targetPiece.isSameColor(turn) && targetPiece.isRook() && targetPiece.isNeverDisplaced();
    }

    private boolean isEmptyBetween(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        List<Position> route = sourcePiece.findRoute(source, target);

        return route.stream()
                .allMatch(node -> board.get(node).isBlank());
    }

    public void castle(Position source, Position target) {
        if (source.calculateDisplacementXTo(target) > 0) {
            castleToKingSide(source, target);
            return;
        }
        castleToQueenSide(source, target);
    }

    private void castleToKingSide(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        board.replace(source, new Blank());
        board.replace(source.displacedOf(2, 0), sourcePiece.displaced());

        board.replace(target, new Blank());
        board.replace(target.displacedOf(-2, 0), targetPiece.displaced());
    }

    private void castleToQueenSide(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        board.replace(source, new Blank());
        board.replace(source.displacedOf(-2, 0), sourcePiece.displaced());

        board.replace(target, new Blank());
        board.replace(target.displacedOf(3, 0), targetPiece.displaced());
    }

    public boolean isEnPassantAvailable(Color turn, Position source, Position target) {
        Piece sourcePiece = board.get(source);

        return sourcePiece.isSameColor(turn)
                && sourcePiece.isPawn()
                && isEnPassant(target, turn);
    }

    private boolean isEnPassant(Position target, Color turn) {
        Color enemy = turn.nextTurn();
        Position enPassantPosition = target.displacedOf(0, enemy.direction());
        Piece enPassantPiece = board.get(enPassantPosition);

        return enPassantPiece.isSameColor(enemy) && enPassantPiece.isEnPassantAvailable();
    }

    public void doEnPassant(Color turn, Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Color enemy = turn.nextTurn();
        Position enPassantPosition = target.displacedOf(0, enemy.direction());

        board.replace(source, new Blank());
        board.replace(target, sourcePiece.displaced());
        board.replace(enPassantPosition, new Blank());
    }

    public void movePieceIfValid(Color turn, Position source, Position target) {
        validateSourceColor(turn, source);
        validateTargetColor(turn, target);
        validateMovable(source, target);
        validateRoute(source, target);

        movePiece(source, target);
    }

    private void validateSourceColor(Color color, Position source) {
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isSameColor(color)) {
            throw new IllegalArgumentException("올바른 기물 선택이 아닙니다.");
        }
    }

    private void validateMovable(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isMovable(board, source, target)) {
            throw new IllegalArgumentException("기물은 해당 위치로 이동할 수 없습니다.");
        }
    }

    private void validateTargetColor(Color color, Position target) {
        Piece targetPiece = board.get(target);
        if (targetPiece.isSameColor(color)) {
            throw new IllegalArgumentException("목적지에 이미 자신의 기물이 있습니다.");
        }
    }

    private void validateRoute(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        List<Position> route = sourcePiece.findRoute(source, target);
        route.forEach(this::validateRouteNode);
    }

    private void validateRouteNode(Position node) {
        Piece nodePiece = board.get(node);
        if (!nodePiece.isBlank()) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
        }
    }

    public void movePiece(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        board.replace(source, new Blank());
        board.replace(target, sourcePiece.displaced());
    }

    public boolean isPromotable(Position target) {
        Piece targetPiece = board.get(target);
        return (targetPiece.isPawn() && target.isEndRank());
    }

    public void promoteTo(Position target, Piece piece) {
        board.replace(target, piece);
    }

    public double calculateScoreOf(Color color) {
        double score = board.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();

        return score - SAME_COLUMN_PAWN_SUBTRACT * countSameColumnPawnsOf(color);
    }

    private long countSameColumnPawnsOf(Color color) {
        List<Position> pawnPositions = findPawnPositionsOf(color);
        Map<PositionX, List<Position>> pawnGroup = Position.groupByPositionX(pawnPositions);

        return pawnGroup.values()
                .stream()
                .filter(list -> list.size() > 1)
                .mapToInt(List::size)
                .sum();
    }

    private List<Position> findPawnPositionsOf(Color color) {
        return board.keySet().stream()
                .filter(position -> board.get(position).isSameColor(color)
                        && board.get(position).isPawn())
                .collect(Collectors.toList());
    }

    public boolean isBothKingsAlive() {
        return countKings() == INITIAL_KING_COUNT;
    }

    private long countKings() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count();
    }

    public Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }
}
