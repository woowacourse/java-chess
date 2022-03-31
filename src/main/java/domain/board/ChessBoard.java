package domain.board;

import domain.piece.property.PieceFeature;
import domain.piece.property.Team;
import domain.piece.unit.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;

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
        if (isPawn(source)) {
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

    private boolean isPawn(Position source) {
        return board.get(source).isPawn();
    }

    private void movePawn(final Position source, final Position target) {
        board.get(source).calculateDirections(source);
        validateMovePawn(source, target);
        movePiece(source, target);
    }

    private void validateMovePawn(final Position source, final Position target) {
        Piece piece = board.get(source);
        boolean isNullTarget = board.get(target) == null;
        boolean isNullRoute = checkRouteNull(source, target);
        if (!piece.availableMove(source, target, isNullRoute, isNullTarget)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private boolean checkRouteNull(final Position source, final Position target) {
        final List<Position> routePositions = board.get(source).calculateRoute(source, target);
        if (routePositions.size() == 0) {
            return true;
        }
        return !routePositions.stream().anyMatch(position -> board.get(position) != null);
    }

    private void validateRoute(final Position source, final Position target) {
        final Piece sourcePiece = board.get(source);
        validateUnavailableMove(source, target, sourcePiece);
        validateRoutePositionsNull(target, sourcePiece);
    }

    private void validateUnavailableMove(final Position source, final Position target, final Piece piece) {
        if (!piece.availableMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void validateRoutePositionsNull(final Position target, final Piece sourcePiece) {
        final List<Position> routePositions = calculateRoutePositions(target, sourcePiece);
        for (Position position : routePositions) {
            validateWayPointNull(position);
        }
    }

    private List<Position> calculateRoutePositions(final Position target, final Piece sourcePiece) {
        List<Position> baseRoutePositions = sourcePiece.calculateRoute(target);
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
