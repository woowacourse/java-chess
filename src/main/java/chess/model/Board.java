package chess.model;

import static chess.vo.PieceColor.*;
import static chess.vo.Rank.*;

import java.util.Arrays;
import java.util.Map;

import chess.model.boardinitializer.BoardInitializer;
import chess.model.piece.EmptyPiece;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.turndecider.TurnDecider;
import chess.vo.File;
import chess.vo.MoveType;
import chess.vo.Path;
import chess.vo.Position;

public class Board {

    static final String SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE = "[ERROR] 출발 위치에는 말이 있어야 합니다.";
    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece(EMPTY);

    private final Map<Position, Piece> values;
    private final TurnDecider turnDecider;

    public Board(TurnDecider turnDecider, BoardInitializer initializer) {
        this.values = initializer.apply();
        this.turnDecider = turnDecider;
    }

    public boolean move(Position source, Position target) {
        turnDecide(source);
        validateSourceNotEmpty(source);
        boolean isFinished = pieceAt(target) instanceof King;

        changePieces(source, target);

        if (!isFinished) {
            turnDecider.nextState();
        }
        return isFinished;
    }

    private void turnDecide(Position source) {
        if (!turnDecider.isTurnOf(pieceAt(source))) {
            throw new IllegalArgumentException("[ERROR] 현재 올바르지 않은 팀 선택입니다. ");
        }
    }

    private void validateSourceNotEmpty(Position source) {
        if (isEmptyPiece(pieceAt(source))) {
            throw new IllegalArgumentException(SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE);
        }
    }

    private void changePieces(Position source, Position target) {
        Piece sourcePiece = pieceAt(source);
        Piece targetPiece = pieceAt(target);
        MoveType moveType = moveType(targetPiece);

        if (!sourcePiece.isMovable(new Path(source, target), moveType) || isBlocked(source, target)
            || isFriendly(moveType)) {
            System.out.println(moveType);
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }

        values.put(target, sourcePiece);
        values.put(source, EMPTY_PIECE);
    }

    private boolean isFriendly(MoveType moveType) {
        return moveType == MoveType.FRIENDLY;
    }

    private MoveType moveType(Piece targetPiece) {
        if (isEmptyPiece(targetPiece)) {
            return MoveType.EMPTY;
        }
        if (turnDecider.isTurnOf(targetPiece)) {
            return MoveType.FRIENDLY;
        }
        return MoveType.ENEMY;
    }

    private boolean isBlocked(Position source, Position target) {
        if (pieceAt(source) instanceof Knight) {
            return false;
        }

        for (Position position : source.positionsToMove(target)) {
            return !isEmptyPiece(pieceAt(position));
        }
        return false;
    }

    private boolean isEmptyPiece(Piece piece) {
        return piece.equals(EMPTY_PIECE);
    }

    private Piece pieceAt(Position position) {
        return values.get(position);
    }

    public double calculateScore() {
        return values.values()
            .stream()
            .filter(turnDecider::isTurnOf)
            .mapToDouble(Piece::getScore)
            .sum() - adjustPawnScore();
    }

    public double adjustPawnScore() {
        return Arrays.stream(File.values())
            .map(this::getPawnCountInOneFile)
            .filter(count -> count > 1)
            .mapToDouble(count -> count * 0.5)
            .sum();
    }

    private long getPawnCountInOneFile(File file) {
        return reverseValues().stream()
            .map(rank -> new Position(rank, file))
            .filter(position -> pieceAt(position) instanceof Pawn
                && turnDecider.isTurnOf(pieceAt(position)))
            .count();
    }

    public Map<Position, Piece> getValues() {
        return values;
    }
}
