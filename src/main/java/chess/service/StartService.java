package chess.service;

import chess.controller.dto.GameDto;
import chess.controller.dto.MessageDto;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;
import chess.repository.GameRepository;
import spark.Response;

import java.sql.SQLException;

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
            chessGame = saveGameAndStart(gameId);
        } catch (RuntimeException | SQLException e) {
            response.status(400);
            return new MessageDto(e.getMessage());
        }

        return new GameDto(chessGame);
    }

    private ChessGame saveGameAndStart(String gameId) throws SQLException {
        ChessGame chessGame = new ChessGame(new Board(PieceFactory.createPieces()));
        chessGame.start();

        if(GameRepository.isExistGameIdInDAO(gameId)){
            throw new IllegalArgumentException("이미 존재하는 게임 아이디 입니다.");
        }

        GameRepository.saveToCache(gameId, chessGame);
        GameRepository.saveToDAO(gameId, chessGame);

        return chessGame;
    }

}
