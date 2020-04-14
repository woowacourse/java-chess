package chess.controller.dto;

import chess.domain.game.ChessGame;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.Map;
import java.util.stream.Collectors;

public class ResponseDto {

    private Map<Position, PieceDto> board;
    private Map<Team, Double> scores;
    private Team turn;
    private Team winner;
    private String message;

    private ResponseDto(final Map<Position, PieceDto> board, final Map<Team, Double> scores, final Team turn) {
        this.board = board;
        this.scores = scores;
        this.turn = turn;
    }

    private ResponseDto(final Map<Position, PieceDto> board, final Map<Team, Double> scores, final Team turn, final Team winner, final String message) {
        this.board = board;
        this.scores = scores;
        this.turn = turn;
        this.winner = winner;
        this.message = message;
    }

    public static ResponseDto of(ChessGame chessGame) {
        Map<Position, PieceDto> board = createBoardDto(chessGame.getBoard());
        Map<Team, Double> scoreDto = chessGame.getStatus();
        Team turnDto = chessGame.getTurn();
        if (chessGame.isEnd()) {
            Team winner = chessGame.getWinner();
            String message = winner.toString() + "가 승리했습니다.";
            return new ResponseDto(board, scoreDto, turnDto, winner, message);
        }
        return new ResponseDto(board, scoreDto, turnDto);
    }

    private static Map<Position, PieceDto> createBoardDto(Map<Position, PieceState> board) {
        return board.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> PieceDto.of(entry.getValue())
                ));
    }

    public Map<Position, PieceDto> getBoard() {
        return board;
    }

    public String getMessage() {
        return message;
    }

    public Map<Team, Double> getScores() {
        return scores;
    }

    public Team getTurn() {
        return turn;
    }

    public Team getWinner() {
        return winner;
    }
}
