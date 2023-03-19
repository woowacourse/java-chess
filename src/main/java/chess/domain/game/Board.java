package chess.domain.game;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        board = new HashMap<>();
        init();
    }

    private void init() {
        for (PieceSettings pieceSetting : PieceSettings.values()) {
            board.put(pieceSetting.getPosition(), pieceSetting.getPiece());
        }
    }

    public void move(Position source, Position target) {
        validateSource(source);
        validateMovable(source, target);
        Piece sourcePiece = board.get(source);
        validatePathBeforeTarget(sourcePiece.findPath(source, target));
        movePiece(source, target);
    }

    private void validateSource(Position source) {
        if (haveNoPieceInPosition(source)) {
            throw new IllegalArgumentException("[ERROR] source 위치에 기물이 없습니다.");
        }
    }

    private void validateMovable(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Team targetTeam = decideTargetTeam(target);
        if (!sourcePiece.isMovable(source, target, targetTeam)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }
    }

    private Team decideTargetTeam(Position target) {
        if (haveNoPieceInPosition(target)) {
            return Team.EMPTY;
        }

        Piece targetPiece = board.get(target);
        if (targetPiece.isSameTeam(Team.WHITE)) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    private void validatePathBeforeTarget(List<Position> path) {
        for (int index = 0; index < path.size() - 1; index++) {
            validateBlocked(path, index);
        }
    }

    private void validateBlocked(List<Position> path, int index) {
        if (havePieceInPosition(path.get(index))) {
            throw new IllegalArgumentException("[ERROR] 이동 경로에 기물이 있습니다.");
        }
    }

    private void movePiece(Position source, Position target) {
        Piece piece = board.get(source);
        board.remove(source);
        board.put(target, piece);
    }

    private boolean havePieceInPosition(Position position) {
        return board.containsKey(position);
    }

    private boolean haveNoPieceInPosition(Position position) {
        return !board.containsKey(position);
    }

    public boolean isCorrectTeam(Position source, Team team) {
        validateSource(source);
        return board.get(source).isSameTeam(team);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
