package chess.model.board;

import static chess.model.Team.NONE;

import chess.model.Team;
import chess.model.piece.Blank;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Collections;
import java.util.Map;

public class Board {

    private static final int KING_COUNT_IN_BOARD = 2;

    private final Map<Position, Piece> board;

    public Board() {
        this.board = BoardCreator.create();
    }

    public void checkSameTeam(Team team, Position source) {
        if (!board.get(source).isSameTeam(team)) {
            throw new IllegalArgumentException("[ERROR] 상대편 기물은 움직일 수 없습니다.");
        }
    }

    public void move(final Position source, final Position target) {
        checkPieceIn(source);
        checkPieceCanMove(source, target);
        board.put(target, board.get(source));
        board.put(source, new Blank(NONE));
    }

    private void checkPieceIn(final Position source) {
        if (board.get(source).isSameTeam(NONE)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치에 기물이 없습니다.");
        }
    }

    private void checkPieceCanMove(final Position source, final Position target) {
        if (!board.get(source).canMove(source, target, board)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물을 이동 시킬수 없는 위치가 입력 됬습니다.");
        }
    }

    public boolean isKingDead() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count() < KING_COUNT_IN_BOARD;
    }

    public GameResult calculateScore() {
        GameResult gameResult = new GameResult();
        gameResult.createScoreResult(board);
        return gameResult;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
