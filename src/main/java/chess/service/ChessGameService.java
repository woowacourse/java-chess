package chess.service;

import chess.dao.PieceDao;
import chess.dao.TurnDao;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.turn.GameTurn;
import chess.domain.turn.Turn;

public class ChessGameService {

    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessGameService(PieceDao pieceDao, TurnDao turnDao) {
        this.pieceDao = pieceDao;
        this.turnDao = turnDao;
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
}
