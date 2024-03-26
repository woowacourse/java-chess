package chess.domain.board;

import chess.domain.game.Turn;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.Movement;
import chess.domain.square.Square;
import chess.dto.PieceResponse;

import java.util.*;
import java.util.Map.Entry;

public class Board {

    private static final String INVALID_PIECE_MOVEMENT = "해당 기물은 위치로 이동할 수 없습니다.";
    private static final String NO_PIECE_EXCEPTION = "해당 위치에 기물이 없습니다.";

    private final Map<Square, Piece> pieces;

    public Board(final Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Square from, final Square to) {
        Piece sourcePiece = pieces.get(from);
        validateSourcePiece(sourcePiece);
        Piece targetPiece = pieces.get(to);
        validateTargetColor(sourcePiece, targetPiece);

        Movement movement = new Movement(from, to);
        validate(sourcePiece, targetPiece, movement);

        pieces.remove(from);
        pieces.put(to, sourcePiece);
    }

    private void validateSourcePiece(final Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException(NO_PIECE_EXCEPTION);
        }
    }

    private void validateTargetColor(final Piece source, final Piece target) {
        if (source.isSameColor(target)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVEMENT);
        }
    }

    private void validate(final Piece source, final Piece target, final Movement movement) {
        checkCanMove(source, target, movement);
        checkRoute(movement);
    }

    private void checkCanMove(final Piece source, final Piece target, final Movement movement) {
        if (!source.canMove(movement, target)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVEMENT);
        }
    }

    private void checkRoute(final Movement movement) {
        Set<Square> squares = movement.findRoute();
        for (Square square : squares) {
            checkIsEmpty(square);
        }
    }

    private void checkIsEmpty(final Square square) {
        if (pieces.get(square) != null) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVEMENT);
        }
    }

    public boolean checkTurn(final Square square, final Turn turn) {
        Piece piece = pieces.get(square);
        validateSourcePiece(piece);
        return turn.isTurn(piece.color());
    }

    public List<PieceResponse> createBoardStatus() {
        List<PieceResponse> responses = new ArrayList<>();
        for (Entry<Square, Piece> positionToPiece : pieces.entrySet()) {
            responses.add(PieceResponse.of(positionToPiece.getKey(), positionToPiece.getValue()));
        }
        return responses;
    }

    public Map<Square, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
