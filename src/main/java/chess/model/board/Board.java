package chess.model.board;

import static chess.model.Team.NONE;

import chess.model.position.File;
import chess.model.Team;
import chess.model.position.Position;
import chess.model.position.Rank;
import chess.model.direction.route.Route;
import chess.model.piece.Blank;
import chess.model.piece.Piece;
import java.util.Collections;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        board = BoardCreator.create();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void move(Position source, Position target) {
        checkMovablePieceAt(source);
        checkPieceCanMove(source, target);
        movePiece(source, target);
    }

    private void checkMovablePieceAt(Position source) {
        if (board.get(source).isSame(NONE)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치에 기물이 없습니다.");
        }
    }

    private void checkPieceCanMove(Position source, Position target) {
        Route route = board.get(source).findRoute(source, target);
        Position nextPosition = source.createPositionTo(route);
        while (!nextPosition.equals(target) && board.get(nextPosition).isSame(NONE)) {
            nextPosition = nextPosition.createPositionTo(route);
        }
        if (!target.equals(nextPosition) || board.get(target).isSame(board.get(source))) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    private void movePiece(Position source, Position target) {
        board.put(target, board.get(source));
        board.put(source, new Blank(NONE, "."));
    }

    public boolean isKilledKing() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count() < 2;
    }

    public boolean checkRightTurn(Team team, Position source) {
        return board.get(source).isSame(team);
    }

    public double calculateScore(Team team) {
        double score = 0;
        for (Rank rank : Rank.values()) {
            int PawnCountInFile = 0;
            for (File file : File.values()) {
                if (board.get(Position.of(rank, file)).isSame(team)) {
                    score = board.get(Position.of(rank, file)).addTo(score);
                    if (board.get(Position.of(rank, file)).isPawn()) {
                        PawnCountInFile += 1;
                    }
                }
            }
            if (PawnCountInFile >= 2) {
                score -= 0.5 * PawnCountInFile;
            }
        }
        return score;
    }
}
