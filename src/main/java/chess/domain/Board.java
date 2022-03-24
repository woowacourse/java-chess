package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.PositionY;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public boolean isCastable(Color turnColor, Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        return isCastablePieces(turnColor, sourcePiece, targetPiece) && isEmptyBetween(source, target);
    }

    private boolean isCastablePieces(Color turnColor, Piece sourcePiece, Piece targetPiece) {
        return isCastableSourcePiece(turnColor, sourcePiece) && isCastableTargetPiece(turnColor, targetPiece);
    }

    private boolean isCastableSourcePiece(Color turnColor, Piece sourcePiece) {
        return sourcePiece.isSameColor(turnColor) && sourcePiece.isKing() && sourcePiece.isNeverDisplaced();
    }

    private boolean isCastableTargetPiece(Color turnColor, Piece targetPiece) {
        return targetPiece.isSameColor(turnColor) && targetPiece.isRook() && targetPiece.isNeverDisplaced();
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

    public void validateMovement(Color turnColor, Position source, Position target) {
        validatePieceChoice(turnColor, source, target);
        validateTargetChoice(source, target);
        validateRoute(source, target);
    }

    private void validatePieceChoice(Color turnColor, Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        if (!sourcePiece.isSameColor(turnColor) || targetPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("올바른 기물 선택이 아닙니다.");
        }
    }

    private void validateTargetChoice(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isMovable(source, target)) {
            throw new IllegalArgumentException("기물은 해당 위치로 이동할 수 없습니다.");
        }
    }

    private void validateRoute(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        List<Position> route = sourcePiece.findRoute(source, target);
        for (Position node : route) {
            validateRouteNode(node);
        }
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

    public double calculateScoreOf(Color color) {
        double score = board.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();

        return score - 0.5 * countSameRankPawnsOf(color);
    }

    private long countSameRankPawnsOf(Color color) {
        List<Position> pawnPositions = findPawnPositionsOf(color);
        Map<PositionY, List<Position>> pawnGroup = Position.groupByPositionY(pawnPositions);

        return pawnGroup.values()
                .stream()
                .filter(list -> list.size() > 1)
                .mapToInt(List::size)
                .sum();
    }

    private List<Position> findPawnPositionsOf(Color color) {
        return board.keySet().stream()
                .filter(position -> board.get(position).isSameColor(color))
                .filter(position -> board.get(position).isPawn())
                .collect(Collectors.toList());
    }

    public boolean isBothKingsAlive() {
        return countKingsOnBoard() == 2;
    }

    private long countKingsOnBoard() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
