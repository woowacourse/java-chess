package chess.domain;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.ResultCalculator;
import chess.domain.board.Score;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.position.Position;

public class ChessGame {

    private final Board board;
    private final ResultCalculator resultCalculator;

    public ChessGame(Board board, ResultCalculator resultCalculator) {
        this.board = board;
        this.resultCalculator = resultCalculator;
    }

    public void checkPieceMoveCondition(Position sourcePosition, Position targetPosition) {
        board.checkPieceMoveCondition(sourcePosition, targetPosition);
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        board.movePiece(sourcePosition, targetPosition);
    }

    public Piece findPieceByPosition(Position sourcePosition) {
        return board.findPieceByPosition(sourcePosition);
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }
}
