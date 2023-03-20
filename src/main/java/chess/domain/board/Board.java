package chess.domain.board;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Position, Piece> chessBoard;

    public Board(final BoardFactory boardFactory) {
        this.chessBoard = boardFactory.createInitialBoard();
    }

    public void move(Position from, Position to, final Color nextTurn) {
        validateMoveFromEmpty(from);
        validateTurn(from, nextTurn);

        final Piece currentMovePiece = findPieceFrom(from);
        Path path = currentMovePiece.searchPathTo(from, to, Optional.ofNullable(findPieceFrom(to)));

        validateObstacle(path);
        movePiece(from, to);
    }

    private void validateTurn(final Position from, final Color nextTurn) {
        final Piece currentTurnPiece = findPieceFrom(from);

        if (currentTurnPiece.isDifferentColor(nextTurn)) {
            throw new IllegalArgumentException("차례에 맞는 말을 선택해 주세요");
        }
    }

    private void validateMoveFromEmpty(final Position from) {
        if (!chessBoard.containsKey(from)) {
            throw new IllegalArgumentException("출발점에 말이 없습니다.");
        }
    }

    private void validateObstacle(final Path path) {
        if (path.hasIntersection(chessBoard.keySet())) {
            throw new IllegalStateException("중간에 다른 기물이 존재합니다.");
        }
    }

    private void movePiece(Position from, Position to) {
        final Piece movingPiece = chessBoard.remove(from);
        chessBoard.put(to, movingPiece);
    }

    private Piece findPieceFrom(Position position) {
        return chessBoard.get(position);
    }

    public Map<Position, Piece> chessBoard() {
        return chessBoard;
    }
}
