package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Square;
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

    public void move(final Square source, final Square target) {
        if (!board.isSameCamp(source, turn)) {
            throw new IllegalArgumentException("자신의 말만 이동할 수 있습니다.");
        }
        if (!board.isMovable(source, target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }

        board.move(source, target);
        turn = Camp.nextTurn(turn);
    }

    public double calculateScore(final Camp camp) {
        final double score = sumPiecesScore(camp);
        final int verticalPawnCount = board.countVerticalPawn(camp);

        return score
                - PieceType.PAWN.value() * verticalPawnCount
                + PieceType.VERTICAL_PAWN.value() * verticalPawnCount;
    }

    private double sumPiecesScore(final Camp camp) {
        return getPieces().stream()
                .filter(piece -> piece.isSameCamp(camp))
                .mapToDouble(piece -> piece.pieceType().value())
                .sum();
    }

    public Camp judgeWinner() {
        if (!board.isKingExist(Camp.WHITE)) {
            return Camp.BLACK;
        }
        if (!board.isKingExist(Camp.BLACK)) {
            return Camp.WHITE;
        }
        return Camp.EMPTY;
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }

    public Board getBoard() {
        return board;
    }
}
