package chess.domain.board;

import chess.domain.board.factory.BoardFactory;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.Map;

public class Board {

    private Long id;
    private final Map<Position, Piece> chessBoard;
    private Turn turn;

    private Board(final BoardFactory boardFactory) {
        this.chessBoard = boardFactory.createInitialBoard();
        this.turn = new Turn(Color.WHITE);
    }

    private Board(final Long id, final Map<Position, Piece> chessBoard, final Turn turn) {
        this.id = id;
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    public static Board bringBackPreviousGame(final Map<Position, Piece> board, final Turn turn, final Long id) {
        return new Board(id, board, turn);
    }

    public static Board makeNewGame(final BoardFactory boardFactory) {
        return new Board(boardFactory);
    }

    public void move(Position from, Position to) {
        final Piece currentMovePiece = findPieceFrom(from);

        validateMoveFromEmpty(currentMovePiece);
        validateTurn(currentMovePiece);

        final Path path = currentMovePiece.searchPathTo(from, to, findPieceFrom(to));

        validateObstacle(path);
        movePiece(from, to);

        turn = turn.change();
    }

    private Piece findPieceFrom(final Position position) {
        return chessBoard.get(position);
    }

    private void validateMoveFromEmpty(final Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("출발점에 말이 없습니다.");
        }
    }

    private void validateTurn(final Piece currentTurnPiece) {
        if (currentTurnPiece.isNotMyTurn(turn)) {
            throw new IllegalArgumentException("차례에 맞는 말을 선택해 주세요");
        }
    }

    private void validateObstacle(final Path path) {
        if (path.hasIntersection(chessBoard.keySet())) {
            throw new IllegalStateException("중간에 다른 기물이 존재합니다.");
        }
    }

    private void movePiece(final Position from, final Position to) {
        final Piece movingPiece = chessBoard.remove(from);
        chessBoard.put(to, movingPiece);
    }

    public void assignId(final Long id) {
        this.id = id;
    }

    public Map<Position, Piece> chessBoard() {
        return Map.copyOf(chessBoard);
    }

    public Turn turn() {
        return turn;
    }

    public Long id() {
        return id;
    }
}
