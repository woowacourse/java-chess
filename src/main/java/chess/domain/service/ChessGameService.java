package chess.domain.service;

import chess.domain.dao.PieceDao;
import chess.domain.dao.TurnDao;
import chess.domain.factory.PlayersFactory;
import chess.domain.model.Score;
import chess.domain.model.player.Color;
import chess.domain.model.player.Players;
import chess.domain.model.position.Position;
import chess.dto.response.PiecesResponse;
import chess.ui.OutputView;

public class ChessGameService {

    private final TurnDao turnDao;
    private final PieceDao pieceDao;
    private Players players;

    public ChessGameService(TurnDao turnDao, PieceDao pieceDao) {
        this.turnDao = turnDao;
        this.pieceDao = pieceDao;
    }

    public void initialize() {
        players = PlayersFactory.initialize(pieceDao, turnDao);
    }

    public PiecesResponse start() {
        players = PlayersFactory.initialize(pieceDao, turnDao);
        return new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK));
    }

    public PiecesResponse move(Position source, Position target) {
            players.movePiece(source, target);
            return new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK));
    }

    public boolean isEveryKingAlive() {
        return !players.isEveryKingAlive();
    }

    public void finishGame() {
        if (players.isEveryKingAlive()) {
            OutputView.printWinner(players.getWinnerColorName());
            pieceDao.deleteAll();
        }
    }

    public Score calculateScore() {
        return players.calculateScore();
    }
}
