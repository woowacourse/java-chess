package chess.chessboard;

import chess.chessboard.position.Position;
import chess.game.Player;
import chess.piece.*;

import java.util.*;

import static chess.game.Player.*;

public final class Board {

    private final Map<Position, Piece> board;

    public Board() {
        board = PieceFactory.initBoard();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void move(final Position source, final Position target) {
        Piece piece = board.get(source);
        checkNoneInSource(piece);
        checkMovable(source, target, piece);
        board.put(target, board.get(source));
        board.put(source, new Blank(NONE, Symbol.BLANK));
    }

    private void checkMovable(final Position source, final Position target, final Piece piece) {
        if (!piece.canMove(source, target, board)) {
            throw new IllegalArgumentException("[ERROR] 기물이 해당 위치로 갈 수 없습니다.");
        }
    }

    private void checkNoneInSource(final Piece piece) {
        if (piece.isSame(NONE)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치에 기물이 없습니다.");
        }
    }

    public boolean isEndSituation() {
        return isKilledKing();
    }

    private boolean isKilledKing() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count() == 1;
    }

    public boolean checkRightTurn(final Player player, final Position source) {
        return board.get(source).isSame(player);
    }

    public boolean isSamePlayerIn(final Position position, final Player player) {
        return board.get(position).isSame(player);
    }

    public double addPieceScore(final Position position, final double score) {
        return board.get(position).addTo(score);
    }

    public boolean isPawn(final Position position) {
        return board.get(position).isPawn();
    }
}
