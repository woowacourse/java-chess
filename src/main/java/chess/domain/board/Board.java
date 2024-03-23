package chess.domain.board;

import chess.domain.piece.ColorType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.square.Square;
import chess.domain.board.dto.BoardOutput;

import java.util.Map;

public class Board {

    private static final String NOT_YOUR_TURN_ERROR = "움직이려고 하는 말이 본인 진영의 말이 아닙니다.";
    private static final String SAME_COLOR_ERROR = "목적지에 같은 편 말이 있어 이동할 수 없습니다.";
    private static final String CANNOT_MOVE_ERROR = "해당 경로로는 말을 이동할 수 없습니다.";

    private final Map<Square, Piece> board;
    private final Turn turn;

    public Board() {
        this.board = new BoardFactory().create();
        this.turn = new Turn();
    }

    public BoardOutput toBoardOutput() {
        return new BoardOutput(board);
    }

    public Piece findPieceBySquare(Square square) {
        return board.get(square);
    }

    public void move(Square source, Square destination) {
        checkMovable(source, destination);

        moveOrCatch(source, destination);
        turn.next();
    }

    private void checkMovable(Square source, Square destination) {
        Piece sourcePiece = board.get(source);
        Piece destinationPiece = board.get(destination);

        checkTurn(sourcePiece);
        checkCanMove(source, destination, sourcePiece);
        checkSameColor(sourcePiece, destinationPiece);
    }

    private void checkTurn(Piece sourcePiece) {
        if (turn.isBlackTurn() && sourcePiece.isWhite()) {
            throw new IllegalArgumentException(NOT_YOUR_TURN_ERROR);
        }

        if (turn.isWhiteTurn() && sourcePiece.isBlack()) {
            throw new IllegalArgumentException(NOT_YOUR_TURN_ERROR);
        }
    }

    private void checkCanMove(Square source, Square destination, Piece sourcePiece) {
        if (!sourcePiece.canMove(source, destination, this)) {
            throw new IllegalArgumentException(CANNOT_MOVE_ERROR);
        }
    }

    private void checkSameColor(Piece sourcePiece, Piece destinationPiece) {
        if (sourcePiece.isSameColor(destinationPiece)) {
            throw new IllegalArgumentException(SAME_COLOR_ERROR);
        }
    }

    private void moveOrCatch(Square source, Square destination) {
        Piece sourcePiece = board.get(source);
        Piece destinationPiece = board.get(destination);

        if (destinationPiece.isNotEmpty()) {
            board.replace(source, new Piece(PieceType.EMPTY, ColorType.EMPTY));
            board.replace(destination, sourcePiece);
            return;
        }

        board.replace(source, destinationPiece);
        board.replace(destination, sourcePiece);
    }
}
