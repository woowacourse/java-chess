package chess.web.service;

import chess.domain.Color;
import chess.domain.Result;
import chess.domain.board.Board;
import chess.domain.board.RegularRuleSetup;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.web.dto.CommendDto;
import chess.web.dto.GameStateDto;
import chess.web.dto.PieceDto;
import chess.web.dto.ResultDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameService {

    private Board board;

    public void startNewGame() {
        board = new Board(new RegularRuleSetup());
    }

    public void move(CommendDto commendDto) {
        board.move(commendDto.getSource(), commendDto.getTarget());
    }

    public ResultDto getFinalResultDto() {
        int whiteScore = (int) board.calculateScore(Color.WHITE);
        int blackScore = (int) board.calculateScore(Color.BLACK);
        Map<Result, Color> winner = new HashMap<>();
        winner.put(Result.WIN, board.winnersColor());
        return new ResultDto(whiteScore, blackScore, winner);
    }

    public ResultDto getResultDto() {
        int whiteScore = (int) board.calculateScore(Color.WHITE);
        int blackScore = (int) board.calculateScore(Color.BLACK);
        return new ResultDto(whiteScore, blackScore, board.gameResult());
    }

    public GameStateDto getGameStateDto() {
        return GameStateDto.from(board);
    }

    public List<PieceDto> getPieceDtos() {
        Map<Position, Piece> pieces = board.getPieces();
        return pieces.keySet().stream()
                .map(position -> PieceDto.from(position, pieces.get(position)))
                .collect(Collectors.toList());
    }

}
