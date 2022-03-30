package chess.model.board;

import static chess.model.Team.NONE;
import static chess.model.Team.WHITE;

import chess.model.Team;
import chess.model.direction.route.Route;
import chess.model.piece.Blank;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private static final int KING_COUNT = 2;

    private final Map<Position, Piece> board;
    private final Team currentTeam;

    private Board(Map<Position, Piece> board, Team team) {
        this.board = board;
        this.currentTeam = team;
    }

    public static Board init() {
        return new Board(BoardCreator.create(), WHITE);
    }

    public static Board of(final Board otherBoard) {
        return new Board(otherBoard.board, otherBoard.currentTeam.opponent());
    }

    public Board move(final Position source, final Position target) {
        checkMovablePiece(source, target);
        checkSameWithCurrentTeam(source);
        checkPawnCanMove(source, target);
        checkPieceCanMove(source, target);
        movePiece(source, target);
        return new Board(board, currentTeam);
    }

    private void checkSameWithCurrentTeam(final Position source) {
        if (board.get(source).isOpponent(currentTeam)) {
            throw new IllegalArgumentException("[ERROR] 상대편 기물은 움직일 수 없습니다.");
        }
    }

    private void checkMovablePiece(final Position source, final Position target) {
        if (board.get(source).isSame(NONE)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치에 기물이 없습니다.");
        }
        if (board.get(target).isSame(board.get(source))) {
            throw new IllegalArgumentException("[ERROR] 타겟 위치에 같은 편 기물이 있어 이동 할 수 없습니다");
        }
    }

    private void checkPieceCanMove(final Position source, final Position target) {
        Route route = board.get(source).findRoute(source, target);
        Position nowPosition = source.createPositionTo(route);
        while (!target.equals(nowPosition) && board.get(nowPosition).isSame(NONE)) {
            nowPosition = nowPosition.createPositionTo(route);
        }
        if (!target.equals(nowPosition)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    private void checkPawnCanMove(final Position source, final Position target) {
        if (board.get(source).isPawn() && !board.get(source).canMove(source, target, board)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    private void movePiece(final Position source, final Position target) {
        board.put(target, board.get(source));
        board.put(source, new Blank(NONE));
    }

    public double calculateScore(final Team team) {
        List<Piece> teamPieces = findPiecesOf(team);
        double score = 0;
        for (Piece piece : teamPieces) {
            score = piece.addTo(score);
        }
        return score;
    }

    private List<Piece> findPiecesOf(final Team team) {
        return board.keySet().stream()
                .filter(position -> board.get(position).isSame(team))
                .map(board::get)
                .collect(Collectors.toList());
    }

    public boolean isKingDead() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count() < KING_COUNT;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
