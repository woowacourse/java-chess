package chess.domain.dto;

import chess.domain.ChessGame;
import chess.domain.board.Team;

import java.util.List;

public class GameInfoDto {
    private List<SquareDto> squares;
    private Team turn;
    private ScoresDto scores;
    private Team winner;

    public GameInfoDto(ChessGame chessGame) {
        this.squares = new SquaresDto(chessGame.board()).squares();
        this.turn = chessGame.turn();
        this.scores = new ScoresDto(chessGame.pointDto());
        this.winner = chessGame.winner();
    }

    public List<SquareDto> squares() {
        return squares;
    }

    public Team turn() {
        return turn;
    }

    public List<ScoreDto> scores() {
        return scores.scores();
    }

    public Team winner() {
        if (winner == null) {
            throw new IllegalArgumentException("[ERROR] 승자가 결정되지 않았습니다.");
        }
        return winner;
    }
}
