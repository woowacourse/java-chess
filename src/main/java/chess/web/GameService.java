package chess.web;

import chess.GameManager;
import chess.domain.board.Piece;
import chess.domain.position.Position;
import chess.web.dao.PieceDao;
import chess.web.dto.ChessDto;
import chess.web.dto.MoveDto;
import chess.web.dto.StatusDto;

public class GameService {

    private GameManager gameManager;
    private PieceDao pieceDao = new PieceDao();

    public ChessDto start() {
        gameManager = new GameManager();
        pieceDao.savePieces(gameManager.getBoard());
        return ChessDto.from(gameManager);
    }

    public ChessDto move(MoveDto moveDto) {

        gameManager.move(moveDto.getSource(), moveDto.getDestination());

        pieceDao.deletePiece(Position.of(moveDto.getDestination()));
        Piece piece = pieceDao.findPieceByPosition(Position.of(moveDto.getSource()));
        pieceDao.updatePosition(Position.of(moveDto.getSource()), Position.of(moveDto.getDestination()));


        return ChessDto.from(gameManager);
    }

    public StatusDto status() {
        return StatusDto.from(gameManager.getScores(), gameManager.getResult());
    }
}
