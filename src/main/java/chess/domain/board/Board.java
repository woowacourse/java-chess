package chess.domain.board;

import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;
import chess.domain.side.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private static final Color FIRST_TURN_COLOR = Color.WHITE;

    private final Map<Square, Piece> board;
    private Side turn;

    Board(final Map<Square, Piece> board) {
        this.board = board;
        this.turn = Side.from(FIRST_TURN_COLOR);
    }

    public Piece findPiece(final File file, final Rank rank) {
        return board.getOrDefault(Square.of(file, rank), Role.VACANT_PIECE.create(Side.from(Color.NOTHING)));
    }

    public void makeMove(final Square sourceSquare, final Square targetSquare) {
        validateSourceAndTarget(sourceSquare, targetSquare);

        Direction direction = Direction.of(sourceSquare, targetSquare);
        Piece sourcePiece = board.get(sourceSquare);
        Piece targetPiece = board.get(targetSquare);
        int distance = sourceSquare.calculateDistance(targetSquare);

        validateMoveOrAttack(sourcePiece, targetPiece, direction, distance);
        validateMovablePath(sourceSquare, direction, distance);

        movePiece(sourceSquare, targetSquare, sourcePiece);
    }

    public void turnSwitch() {
        turn = turn.findOpponent();
    }

    private void movePiece(final Square sourceSquare, final Square targetSquare, final Piece sourcePiece) {
        Piece vacantPiece = Role.VACANT_PIECE.create(Side.from(Color.NOTHING));
        board.put(sourceSquare, vacantPiece);
        board.put(targetSquare, sourcePiece.update());
    }

    private void validateSourceAndTarget(final Square sourceSquare, final Square targetSquare) {
        validateAvailableSquare(sourceSquare);
        validateAvailableSquare(targetSquare);
        validateCurrentTurn(sourceSquare);
        validateSourceNotEqualsTarget(sourceSquare, targetSquare);
    }

    private void validateAvailableSquare(final Square square) {
        if (!board.containsKey(square)) {
            throw new IllegalArgumentException("해당 칸은 체스판 위에 존재하지 않습니다.");
        }
    }

    private void validateCurrentTurn(final Square sourceSquare) {
        Piece sourcePiece = board.get(sourceSquare);
        Side sourceSide = sourcePiece.getSide();
        if (sourceSide.isOpponent(turn) || sourcePiece.isRole(Role.VACANT_PIECE)) {
            throw new IllegalArgumentException("진영에 맞는 말을 움직여주세요.");
        }
    }

    private void validateSourceNotEqualsTarget(final Square sourceSquare, final Square targetSquare) {
        if (sourceSquare.equals(targetSquare)) {
            throw new IllegalArgumentException("동일한 칸으로는 이동할 수 없습니다.");
        }
    }

    private void validateMoveOrAttack(final Piece sourcePiece, final Piece targetPiece, final Direction direction, final int distance) {
        if (targetPiece.isNotVacant()) {
            validateCanAttack(direction, distance, sourcePiece, targetPiece);
            return;
        }
        validateCanMove(direction, distance, sourcePiece);
    }

    private void validateCanMove(final Direction direction, final int distance, final Piece sourcePiece) {
        if (!sourcePiece.canMove(direction, distance)) {
            throw new IllegalArgumentException("이동 할 수 없는 경로입니다.");
        }
    }

    private void validateCanAttack(final Direction direction, final int distance, final Piece sourcePiece, final Piece targetPiece) {
        if (!sourcePiece.canAttack(direction, distance, targetPiece)) {
            throw new IllegalArgumentException("공격할 수 없는 대상입니다.");
        }
    }

    private void validateMovablePath(final Square sourceSquare, final Direction direction, final int distance) {
        List<Square> path = findPath(sourceSquare, direction, distance);
        for (Square square : path) {
            validateMovableSquare(square);
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

    private void validateMovableSquare(final Square square) {
        Piece pieceOnPath = board.get(square);
        if (pieceOnPath.isNotVacant()) {
            throw new IllegalArgumentException("해당 칸으로는 이동할 수 없습니다.");
        }
    }
}
