package chess.domain.service;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.command.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.position.Position;

public class ChessGame {

    private final Long id;

    private final Board board;
    private final Turn turn;

    public ChessGame(Long id, Board board, Turn turn) {
        this.id = id;
        this.board = board;
        this.turn = turn;
    }

    public void checkPieceMoveCondition(Position sourcePosition, Position targetPosition) {
        board.checkPieceMoveCondition(sourcePosition, targetPosition);
    }

    public boolean isOnlyMove(Position targetPosition) {
        return board.isOnlyMove(targetPosition);
    }

    public boolean isTakePieceMove(Position targetPosition) {
        return board.isTakePieceMove(targetPosition);
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        board.movePiece(sourcePosition, targetPosition);
    }

    public Piece findPieceByPosition(Position sourcePosition) {
        return board.findPieceByPosition(sourcePosition);
    }

    public Score getTotalScoreBySide(Side side) {
        return board.calculateSideScore(side);
    }

    public boolean isTargetPieceOppositeKing(Position sourcePosition, Position targetPosition) {
        return board.isTargetPieceOppositeKing(sourcePosition, targetPosition);
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }

    public boolean isCorrectTurn(Side side) {
        return turn.isCorrectTurn(side);
    }

    public Turn turnChange() {
        return turn.change();
    }

    public String getTurnDisplayName() {
        return turn.getDisplayName();
    }

    public Long getId() {
        return id;
    }
}
