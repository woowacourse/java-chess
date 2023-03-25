package chess.domain.service;

import java.util.List;

import chess.dao.ChessGameDao;
import chess.domain.board.*;
import chess.domain.command.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.position.Position;

public class ChessGame {

    private final Long id;

    private final Board board;
    private final Turn turn;
    private final ChessGameDao chessGameDao;

    public ChessGame(Board board, Turn turn, ChessGameDao chessGameDao) {
        this.id = chessGameDao.saveNewChessGame();
        this.board = board;
        this.turn = turn;
        this.chessGameDao = chessGameDao;
        savePiece();
    }

    private void savePiece() {
        for (Piece piece : board.getPieces()) {
            chessGameDao.savePiece(piece, id);
        }
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
}
