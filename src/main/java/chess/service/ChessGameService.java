package chess.service;

import chess.dao.PieceDao;
import chess.dao.TurnDao;
import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.turn.GameTurn;
import chess.domain.turn.Turn;
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
        GameTurn gameTurn = findGameTurn();
        return gameTurn.pieces();
    }

    public void start() {
        GameTurn gameTurn = findGameTurn();
        if (!gameTurn.isEnd()) {
            throw new IllegalStateException("아직 진행 중인 게임이 있습니다.");
        }
        pieceDao.deleteAllPiece();
        pieceDao.savePieces(PieceFactory.createNewChessBoard());
        turnDao.updateTurn(findCurrentTurn(), Turn.WHITE_TURN);
    }

    public void move(Position source, Position target) {
        GameTurn gameTurn = findGameTurn();
        gameTurn.movePiece(source, target);

        pieceDao.deletePiece(target);
        pieceDao.updatePiecePosition(source, target);
        turnDao.updateTurn(gameTurn.currentTurn(), gameTurn.nextTurn());
    }

    public void promotion(PromotionPiece promotionPiece) {
        GameTurn gameTurn = findGameTurn();
        Entry<Position, Piece> changedPiece = gameTurn.promotion(promotionPiece);
        pieceDao.updatePiece(changedPiece.getKey(), changedPiece.getValue());
        turnDao.updateTurn(gameTurn.currentTurn(), gameTurn.nextTurn());
    }

    public Map<Color, Double> currentScore() {
        GameTurn gameTurn = findGameTurn();
        return gameTurn.currentScore();
    }

    public boolean isEndGame() {
        return findGameTurn().isEnd();
    }

    public Color winner() {
        ChessBoard chessBoard = new ChessBoard(pieceDao.findAllPieces());
        return chessBoard.winner();
    }

    private GameTurn findGameTurn() {
        Turn currentTurn = findCurrentTurn();
        ChessBoard chessBoard = new ChessBoard(pieceDao.findAllPieces());
        return currentTurn.createGameTurn(chessBoard);
    }

    private Turn findCurrentTurn() {
        return turnDao.findCurrentTurn()
                .orElseThrow(() -> new RuntimeException("현재 턴이 존재하지 않습니다."));
    }
}
