package domain;

import domain.piece.property.TeamColor;
import domain.piece.unit.Pawn;
import domain.piece.unit.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;

public final class ChessBoard {

    private static final TeamColor START_TEAM_COLOR = TeamColor.WHITE;

    private Map<Position, Piece> board;
    private TeamColor currentTurn;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
        this.currentTurn = START_TEAM_COLOR;
    }

    public void move(Position source, Position target) {
        checkNullSource(source);
        checkCurrentTurn(source);
        checkAvailableTarget(target);
        checkGoThroughPosition(source, target);

//        Piece sourcePiece = board.get(source);
//        Piece targetPiece = board.get(target);
//        board.put(source, null);
//        board.put(source, sourcePiece);
        changeTurn();
    }

    private void checkNullSource(Position source) {
        if (board.get(source) == null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 기물이 존재하지 않습니다.");
        }
    }

    private void checkCurrentTurn(Position source) {
        if (!board.get(source).checkSameTeamColor(this.currentTurn)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 자신의 기물의 위치가 아닙니다.");
        }
    }

    private void checkAvailableTarget(Position target) {
        if (board.get(target) != null && board.get(target).checkSameTeamColor(currentTurn)) {
            throw new IllegalArgumentException("[ERROR] 자신의 기물이 위치한 곳으로 이동할 수 없습니다.");
        }
    }

    private void checkGoThroughPosition(Position source, Position target) {
        Piece piece = board.get(source);
        checkUnavailableMove(source, target, piece);
        if (board.get(source) instanceof Pawn) {
            return;
        }
        List<Position> routePositions = calculateRoutePositions(target, piece);
        for (Position position : routePositions) {
            checkWayPointNull(position);
        }
    }

    private void checkUnavailableMove(Position source, Position target, Piece piece) {
        if (!piece.availableMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private List<Position> calculateRoutePositions(Position target, Piece piece) {
        List<Position> baseRoutePositions = piece.calculateRoute(target);
        if (baseRoutePositions.size() == 0) {
            return null;
        }
        return baseRoutePositions.subList(0, baseRoutePositions.indexOf(target));
    }

    private void checkWayPointNull(Position position) {
        if (board.get(position) != null) {
            throw new IllegalArgumentException("[ERROR] 다른 기물에 의해 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void changeTurn() {
        if (this.currentTurn == TeamColor.BLACK) {
            this.currentTurn = TeamColor.WHITE;
            return;
        }
        this.currentTurn = TeamColor.BLACK;
    }

    public String symbol(final Position position) {
        final Piece piece = board.get(position);
        if (piece == null) {
            return ".";
        }
        return piece.symbol();
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "board=" + board +
                '}';
    }
}
