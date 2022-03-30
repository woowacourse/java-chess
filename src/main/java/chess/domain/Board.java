package chess.domain;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.*;

public final class Board {

    private static final double PAWN_PENALTY_SCORE = 0.5;
    private static final String ERROR_PIECE_NOT_EXIST = "해당 위치에 말이 존재하지 않습니다.";
    private static final String ERROR_CANNOT_CATCH_SAME_COLOR = "목적지에 같은 색의 기물이 있으면 움직일 수 없습니다.";
    private static final String ERROR_CANNOT_GO_THROUGH = "이동경로에 다른 기물이 있으면 움직일 수 없습니다.";
    private static final String ERROR_PAWN_GO_DIAGONAL_WHEN_CATCH_OTHER = "폰은 상대 기물을 잡을 때만 대각선으로 이동 가능합니다.";
    private static final String ERROR_PAWN_CANNOT_GO_THROUGH_OTHER = "폰은 직진할 때 상대 기물을 지나치거나 집을 수 없습니다.";
    private static final String ERROR_SOURCE_AND_TARGET_SAME = "출발지와 목적지가 동일합니다.";

    private final Pieces pieces;
    private Color turn = Color.WHITE;

    public Board(final Initiator initiator) {
        pieces = new Pieces(initiator);
    }

    public Optional<Piece> piece(final Position position) {
        return pieces.findPiece(position);
    }

    private Piece findPiece(Position source) {
        Optional<Piece> piece = piece(source);
        if (piece.isEmpty()) {
            throw new IllegalArgumentException(ERROR_PIECE_NOT_EXIST);
        }
        return piece.get();
    }

    public void move(String source, String target) {
        Position sourcePosition = Position.of(source);
        Position targetPosition = Position.of(target);
        validatePositionsNotEquals(sourcePosition, targetPosition);

        Piece piece = findPiece(sourcePosition);
        validateTargetNotSameColor(targetPosition, piece);

        checkMovableAndMovePiece(sourcePosition, targetPosition, piece);
    }


    private void validateTargetNotSameColor(Position target, Piece piece) {
        if (pieces.pieceExist(target) && piece.isSameColor(findPiece(target))) {
            throw new IllegalArgumentException(ERROR_CANNOT_CATCH_SAME_COLOR);
        }
    }

    private void checkMovableAndMovePiece(Position source, Position target, Piece piece) {
        validateCorrectTurn(piece);
        if (piece.isMovable(source, target)) {
            checkPawnMovement(source, target, piece);
            validatePathEmpty(source, target);
            movePiece(source, target);
        }
    }

    private void validateCorrectTurn(Piece piece) {
        if (!piece.isSameColor(turn)) {
            throw new IllegalArgumentException("지금은 " + turn.value() + "의 턴입니다.");
        }
    }

    private void checkPawnMovement(Position source, Position target, Piece piece) {
        if (piece.isPawn() && Direction.calculate(source, target) == Direction.DIAGONAL) {
            checkPawnTargetExist(target);
        }
        if (piece.isPawn() && Direction.calculate(source, target) == Direction.VERTICAL) {
            checkPawnTargetNotExist(target);
        }
    }

    private void checkPawnTargetExist(Position target) {
        if (!pieces.pieceExist(target)) {
            throw new IllegalArgumentException(ERROR_PAWN_GO_DIAGONAL_WHEN_CATCH_OTHER);
        }
    }

    private void checkPawnTargetNotExist(Position target) {
        if (pieces.pieceExist(target)) {
            throw new IllegalArgumentException(ERROR_PAWN_CANNOT_GO_THROUGH_OTHER);
        }
    }

    private void movePiece(Position source, Position target) {
        pieces.move(source, target);
        turn = Color.opposite(turn);
    }

    private void validatePositionsNotEquals(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException(ERROR_SOURCE_AND_TARGET_SAME);
        }
    }

    private void validatePathEmpty(Position source, Position target) {
        Direction direction = Direction.calculate(source, target);
        if (direction.isIgnore()) {
            return;
        }
        validatePiecesNotExistOnPath(source.calculatePath(target, direction));
    }

    private void validatePiecesNotExistOnPath(List<Position> positions) {
        for (Position position : positions) {
            validatePieceNotExist(position);
        }
    }

    private void validatePieceNotExist(Position position) {
        if (pieces.pieceExist(position)) {
            throw new IllegalArgumentException(ERROR_CANNOT_GO_THROUGH);
        }
    }

    public boolean isEnd() {
        return pieces.kingCaught();
    }

    public Color winnersColor() {
        Piece aliveKing = pieces.findAliveKing();
        if (aliveKing.isSameColor(Color.WHITE)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public double calculateScore(Color color) {
        return pieces.calculateBasicScore(color) - pieces.countPenaltyPawns(color) * PAWN_PENALTY_SCORE;
    }

    public Map<Result, Color> gameResult() {
        Map<Result, Color> gameResult = new HashMap<>();
        if (calculateScore(Color.WHITE) > calculateScore(Color.BLACK)) {
            gameResult.put(Result.WIN, Color.WHITE);
        }
        if (calculateScore(Color.WHITE) < calculateScore(Color.BLACK)) {
            gameResult.put(Result.WIN, Color.BLACK);
        }
        return gameResult;
    }
}
