package chess.service;

import chess.controller.dto.GameDto;
import chess.controller.dto.MessageDto;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;
import chess.repository.GameRepository;
import spark.Response;

public class StartService {

    private final String gameId;
    private final Response response;

    public StartService(String gameId, Response response) {
        this.gameId = gameId;
        this.response = response;
    }

    public Object startNewGame() {
        ChessGame chessGame = null;

        try {
            validateChessGameIdExist(gameId);
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

        GameRepository.save(gameId, chessGame);

        return chessGame;
    }

    private void validateChessGameIdExist(String gameId) {
        if (GameRepository.findByGameId(gameId).isPresent()) {
            throw new IllegalArgumentException("게임 ID가 이미 존재합니다.");
        }
    }

}
