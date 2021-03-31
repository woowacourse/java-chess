package chess.service;

import chess.domain.board.ChessBoardFactory;
import chess.domain.command.Ready;
import chess.domain.piece.PieceFactory;
import chess.domain.player.ChessGame;
import chess.domain.state.StateFactory;
import chess.service.dto.PiecesStatusDto;
import chess.service.dto.TilesDto;

public class ChessService {
    public ChessService() {
    }

    public TilesDto emptyBoard() {
        return new TilesDto(ChessBoardFactory.initializeBoard());
    }

    public PiecesStatusDto initializeGame() {
        ChessGame chessGame = new ChessGame(StateFactory.initialization(PieceFactory.whitePieces()),
                StateFactory.initialization(PieceFactory.blackPieces()), new Ready());

        return new PiecesStatusDto(chessGame.getAllPieces());
    }
}
