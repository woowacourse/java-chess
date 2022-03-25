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

    public void move(final Position source, final Position target) {
        checkNullSource(source);
        checkCurrentTurn(source);
        checkAvailableTarget(target);
        checkGoThroughPosition(source, target);

        movePiece(source, target);
    }

    private void movePiece(final Position source, final Position target) {
        board.put(target, board.get(source));
        board.put(source, null);

        changeTurn();
    }

    private void checkNullSource(final Position source) {
        if (board.get(source) == null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 기물이 존재하지 않습니다.");
        }
    }

    private void checkCurrentTurn(final Position source) {
        if (!board.get(source).checkSameTeamColor(this.currentTurn)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 자신의 기물의 위치가 아닙니다.");
        }
    }

    private void checkAvailableTarget(final Position target) {
        if (board.get(target) != null && board.get(target).checkSameTeamColor(currentTurn)) {
            throw new IllegalArgumentException("[ERROR] 자신의 기물이 위치한 곳으로 이동할 수 없습니다.");
        }
    }

    private void checkGoThroughPosition(final Position source, final Position target) {
        Piece piece = board.get(source);
        checkUnavailableMove(source, target, piece);
        if (board.get(source) instanceof Pawn) {
            checkPawnMovement(source, target);
            return;
        }
        List<Position> routePositions = calculateRoutePositions(target, piece);
        for (Position position : routePositions) {
            checkWayPointNull(position);
        }
    }

    private void checkPawnMovement(final Position source, final Position target) {
        if (checkPawnUpDownDirection(source, target)) {
            checkPawnMoveForward(source, target);
            return;
        }
        checkPawnAttack(target);
    }

    private boolean checkPawnUpDownDirection(final Position source, final Position target) {
        Piece piece = board.get(source);
        return ((Pawn) piece).checkUpDownDirection(source, target);
    }

    private void checkPawnMoveForward(final Position source, final Position target) {
        Piece piece = board.get(source);

        if (((Pawn) piece).checkMoveOneSpace(target)) {
            checkBoardPositionIsNull(target);
            return;
        }
        checkWayPointNullPawn(piece, target);
    }

    private void checkPawnAttack(final Position target) {
        if (board.get(target) == null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치에 상대 기물이 없습니다.");
        }
    }

    private void checkBoardPositionIsNull(final Position position) {
        if (board.get(position) != null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void checkWayPointNullPawn(final Piece piece, final Position target) {
        List<Position> positions = ((Pawn) piece).calculateForwardRouteByPawn(target);
        boolean checkExistNotNullInPositions = positions.stream()
                .anyMatch(position -> board.get(position) != null);

        if (checkExistNotNullInPositions) {
            throw new IllegalArgumentException("[ERROR] 다른 기물에 의해 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void checkUnavailableMove(final Position source, final Position target, final Piece piece) {
        if (!piece.availableMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private List<Position> calculateRoutePositions(final Position target, final Piece piece) {
        List<Position> baseRoutePositions = piece.calculateRoute(target);
        if (baseRoutePositions.size() == 0) {
            return null;
        }
        return baseRoutePositions.subList(0, baseRoutePositions.indexOf(target));
    }

    private void checkWayPointNull(final Position position) {
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
