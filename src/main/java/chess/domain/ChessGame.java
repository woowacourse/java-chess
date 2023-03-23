package chess.domain;

import java.util.List;

import chess.domain.board.*;
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

    public void saveScoreBySide() {
        Score whiteScore = board.calculateSideScore(Side.WHITE);
        Score blackScore = board.calculateSideScore(Side.BLACK);
        resultCalculator.saveTotalScoreBySide(Side.WHITE, whiteScore);
        resultCalculator.saveTotalScoreBySide(Side.BLACK, blackScore);
    }

    public void saveGameResultBySide() {
        resultCalculator.saveGameResultBySide();
    }

    public boolean isTargetPieceOppositeKing(Position sourcePosition, Position targetPosition) {
        return board.isTargetPieceOppositeKing(sourcePosition, targetPosition);
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }

    public ResultCalculator getResultCalculator() {
        return resultCalculator;
    }

    public ScoreBySide getScoreBySide() {
        return resultCalculator.getScoreBySide();
    }

    public GameResultBySide getGameResultBySide() {
        return resultCalculator.getGameResultBySide();
    }
}
