package chess.domain.board;

import chess.domain.position.Direction;
import chess.domain.piece.ColorType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Square;
import chess.domain.position.SquareDifferent;
import chess.domain.state.Turn;
import chess.domain.state.WhiteTurn;

import java.util.Map;

public class Board {

    private static final String NOT_YOUR_TURN_ERROR = "움직이려고 하는 말이 본인 진영의 말이 아닙니다.";
    private static final String SAME_COLOR_ERROR = "목적지에 같은 편 말이 있어 이동할 수 없습니다.";
    private static final String CANNOT_MOVE_ERROR = "해당 말의 규칙으로는 도착지로 갈 수 없습니다.";
    private static final String PATH_BLOCKED_ERROR = "막힌 경로입니다.";
    public static final String PAWN_CANNOT_CATCH_STRAIGHT_ERROR = "폰은 직선 경로로 상대 말을 잡을 수 없습니다.";

    private final Map<Square, Piece> board;
    private Turn turn;

    public Board() {
        this.board = new BoardFactory().create();
        this.turn = new WhiteTurn();
    }

    public void move(Square source, Square destination) {
        Piece sourcePiece = board.get(source);
        Piece destinationPiece = board.get(destination);

        turn = turn.checkMovable(board, source, destination, sourcePiece, destinationPiece);

        moveOrCatch(source, destination);
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

    public Piece findPieceBySquare(Square square) {
        return board.get(square);
    }
}
