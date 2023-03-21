package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.maker.EmptyPiecesFactory;
import chess.domain.board.maker.StartingPiecesFactory;
import chess.domain.piece.Piece;

import java.util.List;

public class ChessGame {
    private final Board board;
    private Color currentTurnColor;

    private ChessGame(final Board board) {
        this.board = board;
        this.currentTurnColor = Color.WHITE;
    }

    public static ChessGame createWithSetBoard() {
        return new ChessGame(Board.createBoardWith(new StartingPiecesFactory()));
    }

    public static ChessGame createWithEmptyBoard() {
        return new ChessGame(Board.createBoardWith(new EmptyPiecesFactory()));
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

    public boolean hasRun() {
        return board.hasPieces();
    }

    public List<Piece> getExistingPieces() {
        return board.getPieces();
    }
}
