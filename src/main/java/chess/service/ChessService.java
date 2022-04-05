package chess.service;

import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.dao.TurnDao;
import chess.dao.TurnDaoImpl;
import chess.domain.ChessWebGame;
import chess.domain.Position;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.dto.MoveDto;
import chess.dto.PieceDto;
import chess.dto.TurnDto;
import chess.view.ChessMap;

import java.util.List;

public class ChessService {

    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessService() {
        pieceDao = new PieceDaoImpl();
        turnDao = new TurnDaoImpl();
    }

    public ChessMap initializeGame(final ChessWebGame chessWebGame) {
        pieceDao.endPieces();
        pieceDao.initializePieces(new Player(new BlackGenerator(), Team.BLACK));
        pieceDao.initializePieces(new Player(new WhiteGenerator(), Team.WHITE));
        turnDao.resetTurn();

        return chessWebGame.initializeChessGame();
    }

    public ChessMap load(final ChessWebGame chessWebGame) {
        final List<PieceDto> whitePieces = pieceDao.findPiecesByTeam(Team.WHITE);
        final List<PieceDto> blackPieces = pieceDao.findPiecesByTeam(Team.BLACK);
        final TurnDto turnDto = turnDao.findTurn();

        chessWebGame.loadPlayers(whitePieces, blackPieces);
        chessWebGame.changeTurn(turnDto);

        return ChessMap.load(whitePieces, blackPieces);
    }

    public ChessMap move(final ChessWebGame chessWebGame, final MoveDto moveDto) {
        final Position currentPosition = Position.of(moveDto.getCurrentPosition());
        final Position destinationPosition = Position.of(moveDto.getDestinationPosition());
        final TurnDto turnDto = turnDao.findTurn();

        chessWebGame.move(currentPosition, destinationPosition);
        pieceDao.removePieceByCaptured(moveDto);
        pieceDao.updatePiece(moveDto);
        turnDao.updateTurn(turnDto.getTurn());

        return chessWebGame.createMap();
    }
}
