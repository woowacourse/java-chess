package chess.service;

import chess.dao.ChessGameDAO;
import chess.dao.PieceDAO;
import chess.domain.board.Board;
import chess.domain.game.*;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;
import chess.exception.AlreadyPlayingChessGameException;
import chess.view.dto.ChessGameDto;
import chess.exception.NotFoundPlayingChessGameException;
import chess.view.dto.ScoreDtos;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ChessGameService {
    private final ChessGameDAO chessGameDAO;
    private final PieceDAO pieceDAO;

    public ChessGameService(final ChessGameDAO chessGameDAO, final PieceDAO pieceDAO) {
        this.chessGameDAO = chessGameDAO;
        this.pieceDAO = pieceDAO;
    }

    public ChessGameDto createNewChessGame() throws SQLException {
        Optional<ChessGameEntity> latestChessGame = chessGameDAO.findLatestOne()
                .filter(chessGameEntity -> chessGameEntity.isPlaying());
        if (latestChessGame.isPresent()) {
            throw new AlreadyPlayingChessGameException();
        }

        Long chessGameId = chessGameDAO.create();
        List<Piece> pieces = PieceFactory.createPieces();
        pieceDAO.saveAll(chessGameId, pieces);
        ChessGame chessGame = new ChessGame(new Board(pieces));
        chessGame.changeState(new BlackTurn(chessGame));
        return new ChessGameDto(chessGame);
    }

    public ChessGameDto moveChessPiece(final Position source, final Position target) throws SQLException {
        ChessGameEntity chessGameEntity = findLatestPlayingGame();
        Long chessGameId = chessGameEntity.getId();
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(chessGameId);
        ChessGame chessGame = assembleChessGame(chessGameEntity, pieces);
        chessGame.move(source, target);

        pieceDAO.findOneByPosition(chessGameId, target.getRow(), target.getColumn())
                .ifPresent(piece -> pieceDAO.delete(chessGameId, target.getRow(), target.getColumn()));

        pieceDAO.update(chessGameId, source.getRow(), source.getColumn(), target.getRow(), target.getColumn());
        chessGameDAO.updateState(chessGameId, chessGame.getState().getValue());

        return new ChessGameDto(chessGame);
    }

    public ChessGameDto findLatestGame() throws SQLException {
        boolean isExistPlayingGame = chessGameDAO.findLatestOne()
                .filter(ChessGameEntity::isPlaying)
                .isPresent();
        if (!isExistPlayingGame) {
            return new ChessGameDto(true);
        }

        ChessGameEntity chessGameEntity = findLatestPlayingGame();
        Long chessGameId = chessGameEntity.getId();
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(chessGameId);
        ChessGame chessGame = assembleChessGame(chessGameEntity, pieces);
        return new ChessGameDto(chessGame);
    }

    private ChessGameEntity findLatestPlayingGame() throws SQLException {
        return chessGameDAO.findLatestOne()
                .filter(ChessGameEntity::isPlaying)
                .orElseThrow(() -> new NotFoundPlayingChessGameException());
    }

    public ChessGameDto endGame() throws SQLException {
        ChessGameEntity chessGameEntity = findLatestPlayingGame();
        Long chessGameId = chessGameEntity.getId();
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(chessGameId);
        ChessGame chessGame = assembleChessGame(chessGameEntity, pieces);
        chessGame.end();
        chessGameDAO.updateState(chessGameId, chessGame.getState().getValue());

        return new ChessGameDto(chessGame);
    }

    public ScoreDtos calculateScores() throws SQLException {
        ChessGameEntity chessGameEntity = findLatestPlayingGame();
        Long id = chessGameEntity.getId();
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(id);
        ChessGame chessGame = assembleChessGame(chessGameEntity, pieces);

        return new ScoreDtos(chessGame);
    }

    private ChessGame assembleChessGame(final ChessGameEntity chessGameEntity, final List<Piece> pieces) {
        Board board = new Board(pieces);
        ChessGame chessGame = new ChessGame(board);
        State currentState = StateFactory.valueOf(chessGameEntity.getState(), chessGame);
        chessGame.changeState(currentState);

        return chessGame;
    }
}
