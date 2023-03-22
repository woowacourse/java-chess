package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.maker.PiecesGenerator;
import chess.domain.piece.property.Color;
import chess.domain.position.Position;

import java.util.Set;

public class ChessGame {

    private final Board board;
    private Color currentTurnColor;

    private ChessGame(final Board board) {
        this.board = board;
        this.currentTurnColor = Color.WHITE;
    }

    public static ChessGame createWith(final PiecesGenerator piecesGenerator) {
        return new ChessGame(Board.createBoardWith(piecesGenerator));
    }

    public void move(final Position currentPosition, final Position targetPosition) {
        validateTurnColor(currentPosition);
        board.move(currentPosition, targetPosition);
        changeTurnColor();
    }

    private void validateTurnColor(final Position currentPosition) {
        if (!board.isSameColor(currentPosition, currentTurnColor)) {
            throw new IllegalArgumentException("해당 색의 말을 이동시킬 순서가 아닙니다.");
        }
    }

    private void changeTurnColor() {
        currentTurnColor = currentTurnColor.getOppositeColor();
    }

    public Set<Piece> getExistingPieces() {
        return board.getExistingPieces();
    }
}
