package chess.service;

import chess.controller.dto.PieceDto;
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

    public ResponseDto start(List<String> parameters) {
        chessGame.start();
        Map<Team, Double> score = chessGame.getStatus();
        return new ResponseDto(createBoardDto(), score);
    }

    public ResponseDto end(List<String> parameters) {
        chessGame.end();
        Map<Team, Double> score = chessGame.getStatus();
        return new ResponseDto(createBoardDto(), score);
    }

    public ResponseDto move(List<String> parameters) {
        chessGame.move(MoveParameter.of(parameters));
        Map<Team, Double> score = chessGame.getStatus();
        return new ResponseDto(createBoardDto(), score);
    }

    public ResponseDto status(List<String> parameters) {
        Map<Team, Double> score = chessGame.getStatus();
        return new ResponseDto(createBoardDto(), score);
    }

    private Map<Position, PieceDto> createBoardDto() {
        Board board = chessGame.getBoard();
        return board.getBoard()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> PieceDto.of(entry.getValue())
                ));
    }
}
