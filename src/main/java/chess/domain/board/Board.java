package chess.domain.board;

import chess.domain.piece.NullPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class Board {
    private static final int TOTAL_KING_COUNT = 2;
    private static final String NO_PIECE_TO_MOVE = "이동할 수 있는 기물이 없습니다.";
    private static final String TURN_OPPOSITE_CAMP = "상대 진영의 차례입니다.";
    private static final String CANT_MOVE_WHEN_OBSTACLE_IN_PATH = "경로에 기물이 있어 움직일 수 없습니다.";
    private static final String CANT_MOVE_TO_SAME_CAMP = "같은 팀 기물이 있는 위치로는 이동할 수 없습니다.";

    private final Map<Position, Piece> value;
    private boolean isWhiteTurn;

    public Board() {
        this.isWhiteTurn = true;
        this.value = BoardFactory.generate();
    }

    public void move(Position beforePosition, Position afterPosition) {
        checkValidPiece(beforePosition);
        checkValidTurn(beforePosition);
        checkObstacles(beforePosition, afterPosition);

        this.isWhiteTurn = turnOff();

        movePiece(beforePosition, afterPosition);
    }

    private void checkValidPiece(final Position beforePosition) {
        Piece beforePiece = getPieceFrom(beforePosition);
        if (beforePiece.isNullPiece()) {
            throw new IllegalArgumentException(NO_PIECE_TO_MOVE);
        }
    }

    private Piece getPieceFrom(final Position position) {
        return this.value.get(position);
    }

    private void checkValidTurn(final Position position) {
        Piece piece = getPieceFrom(position);
        if (piece.isBlack() == isWhiteTurn) {
            throw new IllegalArgumentException(TURN_OPPOSITE_CAMP);
        }
    }

    private void checkObstacles(final Position beforePosition, final Position afterPosition) {
        if (checkNotKnight(beforePosition) && !isValidPath(beforePosition, afterPosition)) {
            throw new IllegalArgumentException(CANT_MOVE_WHEN_OBSTACLE_IN_PATH);
        }
    }

    private boolean checkNotKnight(final Position beforePosition) {
        return getPieceNameFrom(beforePosition) != PieceName.KNIGHT;
    }

    private boolean turnOff() {
        return !isWhiteTurn;
    }

    private void movePiece(final Position beforePosition, final Position afterPosition) {
        Piece beforePiece = getPieceFrom(beforePosition);
        if (isMoveToBlank(afterPosition)) {
            beforePiece.move(beforePosition, afterPosition, moveFunction(beforePosition, afterPosition));
            return;
        }
        if (isMoveToOtherCampPiece(beforePosition, afterPosition)) {
            beforePiece.capture(beforePosition, afterPosition, moveFunction(beforePosition, afterPosition));
            return;
        }
        throw new IllegalArgumentException(CANT_MOVE_TO_SAME_CAMP);
    }

    private PieceName getPieceNameFrom(final Position position) {
        final Piece piece = this.value.get(position);
        return piece.pieceName();
    }

    private boolean isValidPath(Position beforePosition, Position afterPosition) {
        List<Position> path = beforePosition.pathTo(afterPosition);
        return path.stream()
            .allMatch(this::isMoveToBlank);
    }

    private Consumer<Piece> moveFunction(Position beforePosition, Position afterPosition) {
        return (piece) -> {
            this.value.put(afterPosition, piece);
            this.value.put(beforePosition, new NullPiece(null));
        };
    }

    private boolean isMoveToBlank(Position position) {
        return value.get(position).isNullPiece();
    }

    private boolean isMoveToOtherCampPiece(Position beforePosition, Position afterPosition) {
        Piece beforePiece = getPieceFrom(beforePosition);
        Piece afterPiece = getPieceFrom(afterPosition);
        return !beforePiece.isSameCampWith(afterPiece);
    }

    public boolean hasKingCaptured() {
        return TOTAL_KING_COUNT != collectKing().size();
    }

    private List<Piece> collectKing() {
        return this.value.values()
            .stream()
            .filter(piece -> piece.pieceName() == PieceName.KING)
            .collect(Collectors.toList());
    }

    public boolean hasBlackKingCaptured() {
        return collectKing().stream()
            .noneMatch(Piece::isBlack);
    }

    public boolean hasWhiteKingCaptured() {
        return collectKing().stream()
            .allMatch(Piece::isBlack);
    }

    public Map<Position, Piece> getValue() {
        return Collections.unmodifiableMap(value);
    }
}
