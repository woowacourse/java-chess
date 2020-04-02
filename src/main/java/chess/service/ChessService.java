package chess.service;

import chess.controller.dto.PieceDto;
import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private ChessGame chessGame = new ChessGame();

    public boolean isEnd() {
        return chessGame.isEnd();
    }

    public void start(List<String> parameters) {
        chessGame.start();
    }

    public void end(List<String> parameters) {
        chessGame.end();
    }

    public void move(List<String> parameters) {
        chessGame.move(MoveParameter.of(parameters));
    }

    public void status(List<String> parameters) {
        Map<Team, Double> score = chessGame.getStatus();
    }

    public Map<Position, PieceDto> createBoardDto() {
        Board board = chessGame.getBoard();
        return board.getBoard()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> PieceDto.of(entry.getValue())
                ));
    }

    public Map<Team, Double> createScoreDto() {
        return chessGame.getStatus();
    }

    public Team getWinner() {
        return chessGame.getWinner();
    }
}
