package chess.domain.board;

import chess.domain.piece.property.PieceFeature;
import chess.domain.piece.unit.Piece;
import chess.domain.piece.property.Team;
import chess.domain.position.Position;
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
        validateRoute(source, target);
        validateMove(source, target);
        movePiece(source, target);
    }

    private void validateSource(final Position source) {
        Piece sourcePiece = board.get(source);
        if (sourcePiece == null) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 기물이 존재하지 않습니다.");
        }
        sourcePiece.calculateDirections(source);
    }

    private void validateTarget(final Position target) {
        Piece targetPiece = board.get(target);
        if (!(targetPiece == null || !targetPiece.checkSameTeam(currentTurn))) {
            throw new IllegalArgumentException("[ERROR] 자신의 기물이 위치한 곳으로 이동할 수 없습니다.");
        }
    }

    private void validateCurrentTurn(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        if (!sourcePiece.checkSameTeam(this.currentTurn) || source.equals(target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 자신의 기물의 위치가 아닙니다.");
        }
    }

    private void validateRoute(final Position source, final Position target) {
        boolean isNullRoute = checkRouteNull(source, target);
        if (isNullRoute != true) {
            throw new IllegalArgumentException("[ERROR] 경로에 다른 기물이 존재합니다.");
        }
    }

    private boolean checkRouteNull(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        final List<Position> routePositions = sourcePiece.calculateRoute(source, target);
        return !routePositions.stream()
                .anyMatch(position -> board.get(position) != null);
    }

    private void validateMove(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        boolean targetIsNull = board.get(target) == null;
        if (!sourcePiece.availableMove(source, target, targetIsNull)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치로 이동할 수 없습니다.");
        }
    }

    private void movePiece(final Position source, final Position target) {
        board.put(target, board.get(source));
        board.put(source, null);

        changeTurn();
    }

    public boolean checkKingExist() {
        return board.values().stream()
                .filter(piece -> piece != null)
                .filter(piece -> piece.symbol().equals(PieceFeature.KING.symbol()))
                .count() == DEFAULT_KING_COUNT;
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
