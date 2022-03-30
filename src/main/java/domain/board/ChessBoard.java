package domain.board;

import domain.piece.property.Direction;
import domain.piece.property.PieceFeature;
import domain.piece.property.Team;
import domain.piece.unit.Piece;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.classification.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoard {

    private static final int DEFAULT_KING_COUNT = 2;
    private static final Team START_TEAM = Team.WHITE;

    private final Map<Position, Piece> board;
    private Team currentTurn;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
        this.currentTurn = START_TEAM;
    }

    public void move(final Position source, final Position target) {
        validateSource(source);
        validateTarget(target);
        validateCurrentTurn(source, target);
        if (board.get(source).isPawn()) {
            movePawn(source, target);
            return;
        }
        validateRoute(source, target);
        movePiece(source, target);
    }

    private void validateSource(final Position source) {
        if (board.get(source) == null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 기물이 존재하지 않습니다.");
        }
    }

    private void validateTarget(final Position target) {
        if (!(board.get(target) == null || !board.get(target).checkSameTeam(currentTurn))) {
            throw new IllegalArgumentException("[ERROR] 자신의 기물이 위치한 곳으로 이동할 수 없습니다.");
        }
    }

    private void validateCurrentTurn(final Position source, final Position target) {
        if (!board.get(source).checkSameTeam(this.currentTurn) || source.equals(target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 자신의 기물의 위치가 아닙니다.");
        }
    }

    private void movePawn(final Position source, final Position target) {
        validateMovePawn(source, target);
        movePiece(source, target);
    }

    private void validateMovePawn(final Position source, final Position target) {
        Piece piece = board.get(source);
        if (!piece.availableMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치로 이동할 수 없습니다.");
        }
        if (piece.checkOneAndTwoSouthNorthDirections(target)) {
            validateBoardPositionIsNull(target);
            validateRouteNullForPawn(source, target);
            return;
        }
        validatePawnAttack(target);
    }

    private void validateBoardPositionIsNull(final Position position) {
        if (board.get(position) != null) {
            throw new IllegalArgumentException("[ERROR] 다른 기물이 존재합니다.");
        }
    }

    private void validateRouteNullForPawn(final Position source, final Position target) {
        final Direction direction = board.get(source).getDirection(target);
        final List<Position> routePositions = calculateRoutePositionForPawn(source, target, direction);
        boolean checkExistNotNullInPositions = routePositions.stream()
                .anyMatch(position -> board.get(position) != null);

        if (checkExistNotNullInPositions) {
            throw new IllegalArgumentException("[ERROR] 다른 기물에 의해 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private List<Position> calculateRoutePositionForPawn(final Position source, final Position target,
                                                         final Direction direction) {
        List<Position> routePositions = new ArrayList<>();
        routePositions.add(target);
        final Position wayPoint = Position.of(
                XPosition.of(source.getXPosition() + direction.getXPosition() / 2),
                YPosition.of(source.getYPosition() + direction.getYPosition() / 2)
        );
        if (!wayPoint.equals(source) && !wayPoint.equals(target)) {
            routePositions.add(wayPoint);
        }
        return routePositions;
    }

    private void validatePawnAttack(final Position target) {
        if (board.get(target) == null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치에 상대 기물이 없습니다.");
        }
    }

    private void validateRoute(final Position source, final Position target) {
        final Piece piece = board.get(source);
        validateUnavailableMove(source, target, piece);
        validateRoutePositionsNull(target, piece);
    }

    private void validateUnavailableMove(final Position source, final Position target, final Piece piece) {
        if (!piece.availableMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void validateRoutePositionsNull(final Position target, final Piece piece) {
        final List<Position> routePositions = calculateRoutePositions(target, piece);
        for (Position position : routePositions) {
            validateWayPointNull(position);
        }
    }

    private List<Position> calculateRoutePositions(final Position target, final Piece piece) {
        List<Position> baseRoutePositions = piece.calculateRoute(target);
        if (baseRoutePositions.size() == 0) {
            return null;
        }
        return baseRoutePositions.subList(0, baseRoutePositions.indexOf(target));
    }

    private void validateWayPointNull(final Position position) {
        if (board.get(position) != null) {
            throw new IllegalArgumentException("[ERROR] 다른 기물에 의해 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void movePiece(final Position source, final Position target) {
        board.put(target, board.get(source));
        board.put(source, null);

        changeTurn();
    }

    private void changeTurn() {
        if (this.currentTurn == Team.BLACK) {
            this.currentTurn = Team.WHITE;
            return;
        }
        this.currentTurn = Team.BLACK;
    }

    public Team getOpponentTeam() {
        if (currentTurn == Team.BLACK) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public boolean checkKingExist() {
        return board.values().stream()
                .filter(piece -> piece != null)
                .filter(piece -> piece.symbol().equals(PieceFeature.KING.symbol()))
                .count() == DEFAULT_KING_COUNT;
    }

    public Team calculateWhoWinner() {
        return getOpponentTeam();
    }

    public Team getCurrentTurn() {
        return currentTurn;
    }

    public String symbol(final Position position) {
        final Piece piece = board.get(position);
        if (piece == null) {
            return ".";
        }
        return piece.getSymbolByTeam();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "board=" + board +
                '}';
    }
}
