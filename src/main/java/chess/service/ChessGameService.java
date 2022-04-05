package chess.service;

import chess.dao.PieceDao;
import chess.dao.TurnDao;
import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.state.ChessGameState;
import chess.domain.state.Turn;
import java.util.Map;
import java.util.Map.Entry;

public class ChessGameService {

    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessGameService(PieceDao pieceDao, TurnDao turnDao) {
        this.pieceDao = pieceDao;
        this.turnDao = turnDao;
    }

    public Map<Position, Piece> currentChessBoard() {
        ChessGameState chessGameState = findGameTurn();
        return chessGameState.pieces();
    }

    public void start() {
        ChessGameState chessGameState = findGameTurn();
        if (!chessGameState.isEnd()) {
            throw new IllegalStateException("아직 진행 중인 게임이 있습니다.");
        }
        pieceDao.deleteAllPiece();
        pieceDao.savePieces(PieceFactory.createNewChessBoard());
        turnDao.updateTurn(findCurrentTurn(), Turn.WHITE_TURN);
    }

    public void move(Position source, Position target) {
        ChessGameState chessGameState = findGameTurn();
        chessGameState.movePiece(source, target);

        pieceDao.deletePiece(target);
        pieceDao.updatePiecePosition(source, target);
        turnDao.updateTurn(chessGameState.currentTurn(), chessGameState.nextTurn());
    }

    public void promotion(PromotionPiece promotionPiece) {
        ChessGameState chessGameState = findGameTurn();
        Entry<Position, Piece> changedPiece = chessGameState.promotion(promotionPiece);
        pieceDao.updatePiece(changedPiece.getKey(), changedPiece.getValue());
        turnDao.updateTurn(chessGameState.currentTurn(), chessGameState.nextTurn());
    }

    public Map<Color, Double> currentScore() {
        ChessGameState chessGameState = findGameTurn();
        return chessGameState.currentScore();
    }

    public boolean isEndGame() {
        return findGameTurn().isEnd();
    }

    public Color winner() {
        ChessBoard chessBoard = new ChessBoard(pieceDao.findAllPieces());
        return chessBoard.winner();
    }

    private ChessGameState findGameTurn() {
        Turn currentTurn = findCurrentTurn();
        ChessBoard chessBoard = new ChessBoard(pieceDao.findAllPieces());
        return currentTurn.createGameTurn(chessBoard);
    }

    private Turn findCurrentTurn() {
        return turnDao.findCurrentTurn()
                .orElseThrow(() -> new RuntimeException("현재 턴이 존재하지 않습니다."));
    }
}
