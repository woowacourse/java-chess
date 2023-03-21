package chess.domain.board;

import chess.domain.*;
import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private static final Color FIRST_TURN_COLOR = Color.WHITE;

    private final Map<Square, MovablePiece> board;
    private Side turn;

    Board(final Map<Square, MovablePiece> board) {
        this.board = board;
        this.turn = Side.from(FIRST_TURN_COLOR);
    }

    public Piece findPiece(final File file, final Rank rank) {
        Square piece = Square.of(file, rank);
        if (!board.containsKey(piece)) {
            return new VacantPiece();
        }
        return board.get(piece);
    }

    public void makeMove(final Square sourceSquare, final Square targetSquare) {
        validate(sourceSquare, targetSquare);

        Direction direction = Direction.of(sourceSquare, targetSquare);
        MovablePiece sourcePiece = board.get(sourceSquare);
        int distance = sourceSquare.calculateDistance(targetSquare);

        validateCanNotMove(targetSquare, direction, sourcePiece, distance);
        validateIsNotEmptyPath(sourceSquare, direction, distance);

        movePiece(sourceSquare, targetSquare, sourcePiece);
    }

    private void validate(final Square sourceSquare, final Square targetSquare) {
        validateEmptySourceSquare(sourceSquare);
        validateSourceEqualsTarget(sourceSquare, targetSquare);
        validateSideWrongTurn(sourceSquare);
    }

    private void validateEmptySourceSquare(final Square sourceSquare) {
        if (!board.containsKey(sourceSquare)) {
            throw new IllegalArgumentException("해당 칸에 기물이 없습니다.");
        }
    }

    private void validateSourceEqualsTarget(final Square sourceSquare, final Square targetSquare) {
        if (sourceSquare.equals(targetSquare)) {
            throw new IllegalArgumentException("동일한 칸으로는 이동할 수 없습니다.");
        }
    }

    private void validateSideWrongTurn(final Square sourceSquare) {
        MovablePiece sourcePiece = board.get(sourceSquare);
        if (!sourcePiece.isSameSide(turn)) {
            throw new IllegalArgumentException("진영에 맞는 말을 움직여주세요.");
        }
    }

    private void validateCanNotMove(final Square targetSquare, final Direction direction, final MovablePiece sourcePiece, final int distance) {
        boolean possible = isPossibleMove(targetSquare, direction, sourcePiece, distance);

        if (!possible) {
            throw new IllegalArgumentException("상대 기물이나 빈 공간으로만 이동할 수 있습니다.");
        }
    }

    private boolean isPossibleMove(final Square targetSquare, final Direction direction, final MovablePiece sourcePiece, final int distance) {
        if (board.containsKey(targetSquare)) {
            return sourcePiece.canAttack(direction, distance, board.get(targetSquare));
        }
        return sourcePiece.canMove(direction, distance);
    }

    private void validateIsNotEmptyPath(final Square sourceSquare, final Direction direction, final int distance) {
        List<Square> path = findPath(sourceSquare, direction, distance);
        for (Square square : path) {
            validateIsNotEmptySquare(square);
        }
    }

    private void movePiece(final Square sourceSquare, final Square targetSquare, final MovablePiece sourcePiece) {
        MovablePiece piece = sourcePiece;
        if (sourcePiece.getRole().equals(Role.INITIAL_PAWN)) {
            piece = ((InitialPawn) sourcePiece).changeState();
        }
        board.put(targetSquare, piece);
        board.remove(sourceSquare);
        turn = turn.findOpponent();
    }

    private void validateIsNotEmptySquare(final Square square) {
        if (board.containsKey(square)) {
            throw new IllegalArgumentException("해당 칸으로는 이동할 수 없습니다.");
        }
    }

    private List<Square> findPath(final Square sourceSquare, final Direction direction, final int distance) {
        List<Square> squares = new ArrayList<>();
        Square nextSquare = sourceSquare;
        for (int i = 0; i < distance - 1; i++) {
            nextSquare = nextSquare.next(direction);
            squares.add(nextSquare);
        }
        return squares;
    }
}
