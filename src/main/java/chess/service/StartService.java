package chess.service;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;
import chess.repository.GameRepository;
import chess.web.dto.GameDto;
import chess.web.dto.MessageDto;
import spark.Response;

public class StartService {

    public Object startNewGame(String gameId, Response response) {
        ChessGame chessGame;

        try {
            chessGame = saveGameAndStart(gameId);
        } catch (RuntimeException e) {
            response.status(400);
            return new MessageDto(e.getMessage());
        }

        return new GameDto(chessGame);
    }

    private ChessGame saveGameAndStart(String gameId) {
        ChessGame chessGame = new ChessGame(new Board(PieceFactory.createPieces()));
        chessGame.start();

        if (GameRepository.isGameIdExistingInDB(gameId)) {
            throw new IllegalArgumentException("이미 존재하는 게임 아이디 입니다.");
        }

        GameRepository.saveToCache(gameId, chessGame);

        return chessGame;
    }

}
