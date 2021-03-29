package chess.domain.dto;

import chess.domain.ChessGame;
import chess.domain.board.Team;

import java.util.List;

public class GameInfoDto {
    private List<SquareDto> squares;
    private Team turn;

    public GameInfoDto(ChessGame chessGame) {
        this.squares = new SquaresDto(chessGame.board()).squares();
        this.turn = chessGame.turn();
    }

    public List<SquareDto> getSquares() {
        return squares;
    }

    public Team getTurn() {
        return turn;
    }
}
