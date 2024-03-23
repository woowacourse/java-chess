package chess.domain.board;

import chess.domain.position.Direction;
import chess.domain.piece.ColorType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Square;
import chess.domain.position.SquareDifferent;

import java.util.Map;

public class Board {

    private static final String NOT_YOUR_TURN_ERROR = "움직이려고 하는 말이 본인 진영의 말이 아닙니다.";
    private static final String SAME_COLOR_ERROR = "목적지에 같은 편 말이 있어 이동할 수 없습니다.";
    private static final String CANNOT_MOVE_ERROR = "해당 말의 규칙으로는 도착지로 갈 수 없습니다.";
    private static final String PATH_BLOCKED_ERROR = "막힌 경로입니다.";
    public static final String PAWN_CANNOT_CATCH_STRAIGHT_ERROR = "폰은 직선 경로로 상대 말을 잡을 수 없습니다.";

    private final Map<Square, Piece> board;
    private final Turn turn;

    public Board() {
        this.board = new BoardFactory().create();
        this.turn = new Turn();
    }

    public BoardOutput toBoardOutput() {
        return new BoardOutput(board);
    }

    public void move(Square source, Square destination) {
        checkMovable(source, destination);

        moveOrCatch(source, destination);
        turn.update();
    }

    private void checkMovable(Square source, Square destination) {
        Piece sourcePiece = board.get(source);
        Piece destinationPiece = board.get(destination);

        checkTurn(sourcePiece);
        checkCanMove(source, destination, sourcePiece);
        checkSameColor(sourcePiece, destinationPiece);

        if (sourcePiece.isSameType(PieceType.PAWN)) {
            checkPawnCanCatch(source, destination, destinationPiece);
        }

        checkPathBlocked(source, destination, sourcePiece);
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
        if (!sourcePiece.canMove(source, destination)) {
            throw new IllegalArgumentException(CANNOT_MOVE_ERROR);
        }
    }

    private void checkSameColor(Piece sourcePiece, Piece destinationPiece) {
        if (sourcePiece.isSameColor(destinationPiece)) {
            throw new IllegalArgumentException(SAME_COLOR_ERROR);
        }
    }


    private void checkPawnCanCatch(Square source, Square destination, Piece destinationPiece) {
        SquareDifferent squareDifferent = source.calculateDiff(destination);
        Direction direction = Direction.findDirectionByDiff(squareDifferent);

        if (!direction.isDiagonal() && destinationPiece.isNotEmpty()) {
            throw new IllegalArgumentException(PAWN_CANNOT_CATCH_STRAIGHT_ERROR);
        }
    }

    private void checkPathBlocked(Square source, Square destination, Piece sourcePiece) {
        SquareDifferent diff = source.calculateDiff(destination);

        if (needFindPathCondition(source, sourcePiece)) {
            findPath(source, destination, diff);
        }
    }

    private boolean needFindPathCondition(Square source, Piece sourcePiece) {
        return !sourcePiece.isSameType(PieceType.KNIGHT)
                && !(sourcePiece.isSameType(PieceType.PAWN) && source.isPawnStartSquare())
                && !sourcePiece.isSameType(PieceType.KING);
    }

    private void findPath(Square source, Square destination, SquareDifferent diff) {
        Square candidate = source;
        Direction direction = Direction.findDirectionByDiff(diff);

        while (!candidate.equals(destination)) {
            checkBlocked(source, candidate);
            candidate = direction.nextSquare(candidate);
        }
    }

    private void checkBlocked(Square source, Square candidate) {
        if (!source.equals(candidate) && board.get(candidate).isNotEmpty()) {
            throw new IllegalArgumentException(PATH_BLOCKED_ERROR);
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
