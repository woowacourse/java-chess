package chess.domain;

import static java.lang.String.format;

import chess.domain.board.Board;
import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class ChessGame {

    private final Board board;
    private Color turnColor = Color.WHITE;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public void move(final Square source, final Square destination) {
        final Piece piece = board.findPieceOf(source)
                .orElseThrow(() -> new IllegalArgumentException("움직일 기물이 존재하지 않습니다."));
        validateTurn(piece);
        moveToDestination(piece, source, destination);
        nextTurn();
    }

    private void validateTurn(final Piece piece) {
        if (!piece.isSameColor(turnColor)) {
            throw new IllegalArgumentException(format("%s의 차례입니다.", turnColor));
        }
    }

    private void moveToDestination(final Piece piece, final Square source, final Square destination) {
        final BoardSnapShot boardSnapShot = board.getBoardSnapShot();
        if (!piece.isMovable(source, destination, boardSnapShot)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        board.move(source, destination);
    }

    private void nextTurn() {
        turnColor = turnColor.reverse();
    }

    public Board getBoard() {
        return board;
    }
}
