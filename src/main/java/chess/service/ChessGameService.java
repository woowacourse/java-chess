package chess.service;

import chess.dao.PieceDao;
import chess.dao.TurnDao;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.turn.GameTurn;
import chess.domain.turn.Turn;
import java.util.Map.Entry;

public class ChessGameService {

    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessGameService(PieceDao pieceDao, TurnDao turnDao) {
        this.pieceDao = pieceDao;
        this.turnDao = turnDao;
    }

    public void start() {
        GameTurn gameTurn = findGameTurn();
        if (!gameTurn.isEnd()) {
            throw new IllegalStateException("아직 진행 중인 게임이 있습니다.");
        }
        pieceDao.savePieces(PieceFactory.createNewChessBoard());
    }

    public void move(Position source, Position target) {
        GameTurn gameTurn = findGameTurn();
        gameTurn.movePiece(source, target);

        pieceDao.deletePiece(target);
        pieceDao.updatePiecePosition(source, target);
    }

    private GameTurn findGameTurn() {
        Turn currentTurn = turnDao.findCurrentTurn()
                .orElseThrow(() -> new RuntimeException("현재 턴이 존재하지 않습니다."));
        ChessBoard chessBoard = new ChessBoard(pieceDao.findAllPieces());
        return currentTurn.createGameTurn(chessBoard);
    }

    public void promotion(PromotionPiece promotionPiece) {
        GameTurn gameTurn = findGameTurn();
        Entry<Position, Piece> changedPiece = gameTurn.promotion(promotionPiece);
        pieceDao.updatePiece(changedPiece.getKey(), changedPiece.getValue());
    }
}
