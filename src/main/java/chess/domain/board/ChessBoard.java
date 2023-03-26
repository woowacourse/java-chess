package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.type.EmptyPiece;
import chess.domain.piece.type.Piece;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard() {
        this.chessBoard = new LinkedHashMap<>();
    }

    public void initialize(Map<Position, Piece> chessBoard) {
        this.chessBoard.putAll(chessBoard);
    }

    public Piece findPieceInBoardByPosition(final Position position) {
        if (!chessBoard.containsKey(position)) {
            throw new IllegalArgumentException("해당하는 위치가 체스 보드에 존재하지 않습니다");
        }
        return chessBoard.get(position);
    }

    public void move(Position start, Position end) {
        checkIfMoveToSamePosition(start, end);
        Piece pieceToMove = findPieceInBoardByPosition(start);
        checkIfPieceToMoveEmpty(pieceToMove);
        checkIfPieceMovable(start, end, pieceToMove);
        checkIfOtherPiecesOnPath(start, end);

        movePiece(start, end, pieceToMove);
    }

    private static void checkIfMoveToSamePosition(Position start, Position end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("제자리로는 이동할 수 없습니다");
        }
    }
    private void checkIfPieceToMoveEmpty(final Piece pieceToMove) {
        if (pieceToMove.getPieceType().equals(PieceType.EMPTY_PIECE)) {
            throw new IllegalArgumentException("이동할 수 있는 기물이 없습니다");
        }
    }

    private void checkIfPieceMovable(final Position start, final Position end, final Piece pieceToMove) {
        Color colorOfDestination = findPieceInBoardByPosition(end).getColor();
        if (!pieceToMove.isMovable(start, end, colorOfDestination)) {
            throw new IllegalArgumentException("기물이 이동 할 수 있는 위치가 아닙니다");
        }
    }

    private void checkIfOtherPiecesOnPath(final Position start, final Position end) {
        if (start.findRouteTo(end).stream()
                .anyMatch(position -> !position.equals(end) && chessBoard.get(position).getColor() != Color.NONE)) {
            throw new IllegalArgumentException("이동 경로에 기물이 있으므로 이동할 수 없습니다");
        }
    }

    private void movePiece(final Position start, final Position end, final Piece pieceToMove) {
        chessBoard.replace(start, EmptyPiece.of());
        chessBoard.replace(end, pieceToMove);
    }

    public boolean isInitialized() {
        return chessBoard.size() != 0;
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }

}
