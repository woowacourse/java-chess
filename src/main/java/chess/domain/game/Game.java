package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;

public class Game {
    private final Board board;
    private Camp turn;

    public Game() {
        this.board = new Board();
        turn = Camp.WHITE;
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }

    public void move(final Square source, final Square target) {
        if (!board.isSameCamp(source, turn)) {
            throw new IllegalArgumentException("자신의 말만 이동할 수 있습니다.");
        }
        board.move(source, target);
        turn = Camp.nextTurn(turn);
    }

    public double calculateScore(final Camp camp) {
        final List<Piece> pieces = getPieces();
        final double score = pieces.stream()
                .filter(piece -> piece.isSameCamp(camp))
                .mapToDouble(piece -> piece.pieceType().value())
                .sum();

        if (board.isVerticalPawn(camp)) {
            return calculateVerticalPawnScore(score, pieces, camp);
        }

        return score;
    }

    private double calculateVerticalPawnScore(final double score, final List<Piece> pieces, final Camp camp) {
        final int pawnCount = (int) pieces.stream()
                .filter(piece -> board.isSameCampPawn(camp, piece))
                .count();
        return score
                - pawnCount * PieceType.PAWN.value()
                + pawnCount * PieceType.VERTICAL_PAWN.value();
    }
}
