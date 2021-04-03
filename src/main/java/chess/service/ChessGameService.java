package chess.service;

import chess.dao.ChessGameDAO;
import chess.dao.PieceDAO;
import chess.domain.board.Board;
import chess.domain.game.*;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;
import chess.exception.AlreadyPlayingChessGameException;
import chess.exception.NoSuchPermittedChessPieceException;
import chess.exception.NotFoundPlayingChessGameException;
import chess.view.dto.ChessGameDto;
import chess.view.dto.ScoreDto;

import java.util.List;
import java.util.Optional;

public class ChessGameService {
    private final ChessGameDAO chessGameDAO;
    private final PieceDAO pieceDAO;

    public ChessGameService(final ChessGameDAO chessGameDAO, final PieceDAO pieceDAO) {
        this.chessGameDAO = chessGameDAO;
        this.pieceDAO = pieceDAO;
    }

    public ChessGameDto createNewChessGame() {
        Optional<ChessGameEntity> latestChessGame = chessGameDAO.findByStateIsBlackTurnOrWhiteTurn();

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

    public ChessGameDto moveChessPiece(final Position source, final Position target) {
        ChessGameEntity chessGameEntity = findLatestPlayingGame();
        Long chessGameId = chessGameEntity.getId();
        ChessGame chessGame = findChessGameByChessGameId(chessGameEntity, chessGameId);
        Piece sourcePiece = pieceDAO.findOneByPosition(chessGameId, source.getRow(), source.getColumn())
                .orElseThrow(NoSuchPermittedChessPieceException::new);
        chessGame.move(sourcePiece.getPosition(), target);
        pieceDAO.findOneByPosition(chessGameId, target.getRow(), target.getColumn())
                .ifPresent(piece -> pieceDAO.delete(chessGameId, target.getRow(), target.getColumn()));

        sourcePiece.setPosition(target);
        pieceDAO.update(chessGameId, sourcePiece);
        chessGameDAO.updateState(chessGameId, chessGame.getState().getValue());

        return new ChessGameDto(chessGame);
    }

    public ChessGameDto findLatestGame() {
        Optional<ChessGameEntity> chessGameEntityOptional = chessGameDAO.findByStateIsBlackTurnOrWhiteTurn();
        boolean isExistPlayingGame = chessGameEntityOptional.isPresent();
        if (!isExistPlayingGame) {
            return ChessGameDto.createFinishedDto();
        }

        ChessGameEntity chessGameEntity = chessGameEntityOptional
                .filter(ChessGameEntity::isPlaying)
                .orElseThrow(NotFoundPlayingChessGameException::new);
        Long chessGameId = chessGameEntity.getId();
        ChessGame chessGame = findChessGameByChessGameId(chessGameEntity, chessGameId);
        return new ChessGameDto(chessGame);
    }

    private ChessGameEntity findLatestPlayingGame() {
        return chessGameDAO.findByStateIsBlackTurnOrWhiteTurn()
                .orElseThrow(() -> new NotFoundPlayingChessGameException());

    }

    public ChessGameDto endGame() {
        ChessGameEntity chessGameEntity = findLatestPlayingGame();
        Long chessGameId = chessGameEntity.getId();
        ChessGame chessGame = findChessGameByChessGameId(chessGameEntity, chessGameId);
        chessGame.end();
        chessGameDAO.updateState(chessGameId, chessGame.getState().getValue());

        return new ChessGameDto(chessGame);
    }

    public ScoreDto calculateScores() {
        ChessGameEntity chessGameEntity = findLatestPlayingGame();
        Long id = chessGameEntity.getId();
        ChessGame chessGame = findChessGameByChessGameId(chessGameEntity, id);

        return new ScoreDto(chessGame);
    }

    private ChessGame findChessGameByChessGameId(final ChessGameEntity chessGameEntity, final Long chessGameId) {
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(chessGameId);
        Board board = new Board(pieces);
        ChessGame chessGame = new ChessGame(board);
        State currentState = StateFactory.valueOf(chessGameEntity.getState(), chessGame);
        chessGame.changeState(currentState);

        return chessGame;
    }

}
