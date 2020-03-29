package chess.service;

import chess.controller.dto.ResponseDto;
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

    public ResponseDto start() {
        chessGame.start();
        return new ResponseDto(createBoardDto());
    }

    public ResponseDto end() {
        chessGame.end();
        return new ResponseDto(createBoardDto());
    }

    public ResponseDto move(List<String> parameters) {
        chessGame.move(MoveParameter.of(parameters));
        return new ResponseDto(createBoardDto());
    }

    public ResponseDto status() {
        Map<Team, Double> score = chessGame.getStatus();
        return new ResponseDto(createBoardDto(), score);
    }

    private Map<Position, String> createBoardDto() {
        Board board = chessGame.getBoard();
        return board.getBoard()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().getFigure()
                ));
    }
}
