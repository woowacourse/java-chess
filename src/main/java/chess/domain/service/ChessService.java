package chess.domain.service;

import chess.domain.ChessResult;
import chess.domain.dto.request.MoveRequest;
import chess.domain.dto.response.Response;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;

public class ChessService {
    private ChessGame chessGame;

    public ChessService() {
        this.chessGame = new ChessGame();
    }

    public Response move(final MoveRequest moveRequest) {
        try {
            chessGame.move(getPositionByCommands(moveRequest.source().split("")),
                getPositionByCommands(moveRequest.target().split("")));

            if (chessGame.isKingDead()) {
                chessGame.changeGameOver();
            }
            chessGame.nextTurn();
            return new Response("200", "성공");
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            return new Response("401", e.getMessage());
        }
    }

    public Response end() {
        if (chessGame.isGameOver()) {
            return new Response("212", "게임 종료");
        }
        return new Response("200", "게임 진행중");
    }

    public ChessResult chessResult() {
        return new ChessResult(chessGame.board());
    }

    public void restart() {
        chessGame = new ChessGame();
    }

    private Position getPositionByCommands(final String[] commands) {
        return new Position(commands[0], commands[1]);
    }
}
