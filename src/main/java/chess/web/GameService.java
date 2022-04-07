package chess.web;

import chess.domain.GameManager;
import chess.domain.board.Board;
import chess.domain.board.Piece;
import chess.domain.position.Position;
import chess.domain.state.Turn;
import chess.web.dao.PieceDao;
import chess.web.dao.TurnDao;
import chess.web.dto.ChessDto;
import chess.web.dto.MoveDto;
import chess.web.dto.StatusDto;
import java.util.Map;

public class GameService {

    private GameManager gameManager;
    private final PieceDao pieceDao = new PieceDao();
    private final TurnDao turnDao = new TurnDao();

    public ChessDto start() {
        gameManager = new GameManager();
        pieceDao.removeAll();
        pieceDao.savePieces(gameManager.getBoard());
        turnDao.updateTurn(turnDao.findCurrentTurn(), gameManager.getCurrentTurn());
        return ChessDto.from(gameManager);
    }

    public ChessDto move(MoveDto moveDto) {
        gameManager.move(moveDto.getSource(), moveDto.getDestination());

        pieceDao.deletePiece(Position.of(moveDto.getDestination()));
        pieceDao.updatePosition(Position.of(moveDto.getSource()), Position.of(moveDto.getDestination()));
        turnDao.updateTurn(turnDao.findCurrentTurn(), gameManager.getCurrentTurn());
        return ChessDto.from(gameManager);
    }

    public ChessDto load() {
        gameManager = new GameManager();
        Map<Position, Piece> value = pieceDao.findAll();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("이전에 저장된 게임이 없습니다");
        }
        Turn currentTurn = turnDao.findCurrentTurn();
        gameManager.load(new Board(value), currentTurn);
        return ChessDto.from(gameManager);
    }

    public StatusDto status() {
        return StatusDto.from(gameManager.getScores(), gameManager.getResult());
    }
}
