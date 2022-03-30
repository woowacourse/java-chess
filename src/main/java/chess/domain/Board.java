package chess.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.command.MoveCommand;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.PositionY;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void validateMovement(Color turnColor, MoveCommand command) {
        validatePieceChoice(turnColor, command.from());
        validateTargetChoice(turnColor, command.to());
        validateMovable(turnColor, command.from(), command.to());
        validateRoute(command.from(), command.to());
    }

    private void validatePieceChoice(Color turnColor, Position source) {
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("올바른 기물 선택이 아닙니다.");
        }
    }

    private void validateTargetChoice(Color turnColor, Position target) {
        Piece targetPiece = board.get(target);
        if (targetPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("목적지에 이미 자신의 기물이 있습니다.");
        }
    }

    private void validateMovable(Color turnColor, Position source, Position target) {
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isMovable(source, target)) {
            throw new IllegalArgumentException("기물은 해당 위치로 이동할 수 없습니다.");
        }

        if (sourcePiece.isPawn()) {
            validatePawnMovable(turnColor, source, target);
        }
    }

    private void validatePawnMovable(Color turnColor, Position source, Position target) {
        Piece targetPiece = board.get(target);
        Color enemyColor = turnColor.next();
        if (isStraightMove(source, target)) {
            validateStraightMove(targetPiece, enemyColor);
            return;
        }
        validateDiagonalMove(targetPiece, enemyColor);
    }

    private boolean isStraightMove(Position source, Position target) {
        return Math.abs(source.calculateDisplacementXTo(target)) == 0;
    }

    private void validateStraightMove(Piece targetPiece, Color enemyColor) {
        if (targetPiece.isSameColor(enemyColor)) {
            throw new IllegalArgumentException("Pawn은 해당 위치로 이동할 수 없습니다.");
        }
    }

    private void validateDiagonalMove(Piece targetPiece, Color enemyColor) {
        if (!targetPiece.isSameColor(enemyColor)) {
            throw new IllegalArgumentException("Pawn은 해당 위치로 이동할 수 없습니다.");
        }
    }

    private void validateRoute(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        List<Position> route = sourcePiece.findRoute(source, target);
        for (Position node : route) {
            validatePieceOnRoute(node);
        }
    }

    private void validatePieceOnRoute(Position node) {
        Piece nodePiece = board.get(node);
        if (!nodePiece.isBlank()) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
        }
    }

    public void movePiece(MoveCommand command) {
        Piece sourcePiece = board.get(command.from());
        board.replace(command.from(), new Blank());
        board.replace(command.to(), sourcePiece.displaced());
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
