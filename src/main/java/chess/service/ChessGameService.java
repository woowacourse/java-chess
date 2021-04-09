package chess.service;

import chess.domain.board.Board;
import chess.domain.dao.ChessGameDAO;
import chess.domain.dao.PieceDAO;
import chess.domain.dto.ChessBoardDto;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameEntity;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

import java.util.List;
import java.util.Optional;

public class ChessGameService {
    private final PieceDAO pieceDAO;
    private final ChessGameDAO chessGameDAO;

    public ChessGameService(final PieceDAO pieceDAO, final ChessGameDAO chessGameDAO) {
        this.pieceDAO = pieceDAO;
        this.chessGameDAO = chessGameDAO;
    }

    public ChessBoardDto createNewChessGame(String roomID) {
        Optional<ChessGameEntity> existChessGame = chessGameDAO.findRoomID(roomID);
        if(existChessGame.isPresent()) {
            throw new IllegalArgumentException();
        }

        chessGameDAO.create(roomID);
        List<Piece> pieces = PieceFactory.createPieces();
        pieceDAO.saveAll(roomID, pieces);
        ChessGame chessGame = new ChessGame(new Board(pieces), roomID);
        chessGame.start();

        return new ChessBoardDto(chessGame);
    }
}
