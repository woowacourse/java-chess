package chess.model;

import chess.model.board.Board;
import chess.model.board.Position;
import chess.model.board.Square;
import chess.model.rule.Rule;
import chess.model.unit.Piece;

import java.util.List;
import java.util.stream.Collectors;

public class Play {
    private static final String SIDE_MISMATCH = "자신의 말이 아닙니다.";
    private static final String NOT_VALID_MOVE = "이동규칙에 어긋납니다.";

    private final Board board;
    private Side side = Side.WHITE;

    public Play(final Board board) {
        this.board = board;
    }

    public void movePieceAndTurnSide(final Square target, final Square destination) throws IllegalArgumentException {
        movePiece(side, target, destination);
        turnSide();
    }

    private void movePiece(final Side side, final Square target, final Square destination) throws IllegalArgumentException {
        final Piece piece = board.getPiece(target);
        checkValidMove(side, target, destination, piece);
        board.setPiece(destination, piece);
        board.removePiece(target);
    }

    private void checkValidMove(final Side side, final Square target, final Square destination, final Piece piece) {
        if (piece.getSide() != side) {
            throw new IllegalArgumentException(SIDE_MISMATCH);
        }
        if (!Rule.isValidMove(board, target, destination)) {
            throw new IllegalArgumentException(NOT_VALID_MOVE);
        }
    }

    private void turnSide() {
        this.side = (this.side == Side.WHITE ? Side.BLACK : Side.WHITE);
    }

    public Board getBoard() {
        return board;
    }

    public Side getSide() {
        return side;
    }

    public List<Position> getAllPositions() {
        return board.getAllPositions();
    }

    private List<Position> getSidePositions(final Side side) {
        return getAllPositions().stream()
                .filter(pos -> pos.getPiece().getSide() == side)
                .collect(Collectors.toList());
    }

    public double calcScore(final Side side) {
        final List<Position> sidePositions = getSidePositions(side);
        double score = 0;
        for (Position position : sidePositions) {
            score += Rule.getPieceScore(board, position);
        }
        return score;
    }
}
