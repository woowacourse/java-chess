package chess.domain;

import chess.domain.command.MoveCommand;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void movePiece(Color turnColor, MoveCommand command) {
        validateMovement(turnColor, command);
        Piece sourcePiece = pieces.get(command.from());
        pieces.replace(command.from(), new Blank());
        pieces.replace(command.to(), sourcePiece.displaced());
    }

    private void validateMovement(Color turnColor, MoveCommand command) {
        validatePieceChoice(turnColor, command.from());
        validateTargetChoice(turnColor, command.to());
        validateMovable(command.from(), command.to());
        validateRoute(command.from(), command.to());
    }

    private void validatePieceChoice(Color turnColor, Position source) {
        Piece sourcePiece = pieces.get(source);
        if (!sourcePiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("올바른 기물 선택이 아닙니다.");
        }
    }

    private void validateTargetChoice(Color turnColor, Position target) {
        Piece targetPiece = pieces.get(target);
        if (targetPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("목적지에 이미 자신의 기물이 있습니다.");
        }
    }

    private void validateMovable(Position source, Position target) {
        Piece sourcePiece = pieces.get(source);
        Piece targetPiece = pieces.get(target);
        if (!sourcePiece.isCorrectMovement(source, target, targetPiece)) {
            throw new IllegalArgumentException("해당 기물이 움직일 수 있는 행마법이 아닙니다.");
        }
    }

    private void validateRoute(Position source, Position target) {
        Piece sourcePiece = pieces.get(source);
        if (sourcePiece.canJumpOverPieces()) {
            return;
        }
        List<Position> route = findRoute(source, target);
        for (Position node : route) {
            validatePieceOnRoute(node);
        }
    }

    private List<Position> findRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();

        int routeLength = source.calculateMaxLinearLengthTo(target);
        int xSlope = source.calculateXSlope(target, routeLength);
        int ySlope = source.calculateYSlope(target, routeLength);

        for (int step = 1; step < routeLength; step++) {
            Position routeNode = source.displacedOf(xSlope * step, ySlope * step);
            route.add(routeNode);
        }
        return route;
    }

    private void validatePieceOnRoute(Position node) {
        Piece nodePiece = pieces.get(node);
        if (!nodePiece.isBlank()) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
        }
    }

    public double calculateScoreOf(Color color) {
        double score = pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();

        return score - 0.5 * countSameRankPawnsOf(color);
    }

    private long countSameRankPawnsOf(Color color) {
        List<Position> pawnPositions = findPawnPositionsOf(color);
        Map<Row, List<Position>> pawnGroup = Position.groupByRow(pawnPositions);

        return pawnGroup.values()
                .stream()
                .filter(list -> list.size() > 1)
                .mapToInt(List::size)
                .sum();
    }

    private List<Position> findPawnPositionsOf(Color color) {
        return pieces.keySet().stream()
                .filter(position -> pieces.get(position).isSameColor(color))
                .filter(position -> pieces.get(position).isPawn())
                .collect(Collectors.toList());
    }

    public boolean hasBothKings() {
        return countKingsOnBoard() == 2;
    }

    private long countKingsOnBoard() {
        return pieces.values()
                .stream()
                .filter(Piece::isKing)
                .count();
    }

    public Map<Position, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
