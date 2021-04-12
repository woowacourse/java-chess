package chess.service;

import chess.controller.web.dto.MoveRequestDto;
import chess.dao.ChessDao;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerBundle;
import chess.domain.manager.ChessGameManagerFactory;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import chess.domain.repository.ChessGameRepository;
import chess.domain.statistics.ChessGameStatistics;

public class ChessService {
    private static final long TEMPORARY_ID = 0;

    private final ChessGameRepository chessGameRepository;

    public ChessService(ChessDao chessDao) {
        this.chessGameRepository = new ChessGameRepository(chessDao);
    }

    public ChessGameManager start() {
        long gameId = chessGameRepository.add(ChessGameManagerFactory.createRunningGame(TEMPORARY_ID));
        return ChessGameManagerFactory.createRunningGame(gameId);
    }

    public ChessGameManager end(long gameId) {
        ChessGameManager endGameManager = findById(gameId).end();
        update(endGameManager);
        return endGameManager;
    }

    public ChessGameManagerBundle findRunningGames() {
        return chessGameRepository.findRunningGames();
    }

    public boolean isKindDead(long gameId) {
        return findById(gameId).isKingDead();
    }

    private void update(ChessGameManager chessGameManager) {
        chessGameRepository.update(chessGameManager);
    }

    public ChessGameManager load(long gameId) {
        return chessGameRepository.findById(gameId);
    }

    public void move(long gameId, Position from, Position to) {
        ChessGameManager chessGameManager = chessGameRepository.findById(gameId);
        chessGameManager.move(from, to);
        if (chessGameManager.isKingDead()) {
            chessGameManager = chessGameManager.end();
        }
        chessGameRepository.update(chessGameManager);
    }

    public void move(MoveRequestDto moveRequestDto) {
        move(moveRequestDto.getId(), moveRequestDto.getFromPosition(), moveRequestDto.getToPosition());
    }

    public boolean isEnd(long gameId) {
        return chessGameRepository.isEnd(gameId);
    }

    public ChessGameManager findById(long gameId) {
        return chessGameRepository.findById(gameId);
    }

    public Color nextColor(long gameId) {
        return findById(gameId).nextColor();
    }

    public ChessGameStatistics getStatistics(long gameId) {
        return findById(gameId).getStatistics();
    }
}
