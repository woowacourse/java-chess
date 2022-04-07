package chess.dto.response;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import java.util.Objects;

public class GameDto {
    private final String gameId;
    private final CurrentTurnDto currentTurnDto;
    private BoardDto boardDto;

    public GameDto(String gameId, CurrentTurnDto currentTurnDto, BoardDto boardDto) {
        this.gameId = gameId;
        this.currentTurnDto = currentTurnDto;
        this.boardDto = boardDto;
    }

    public static GameDto from(String gameId, String turn) {
        return new GameDto(gameId, CurrentTurnDto.from(turn), null); // TODO: null 위험 존재
    }

    public ScoreResultDto getScoreResultDto() {
        return ScoreResultDto.from(toChessGame());
    }

    public ChessGame toChessGame() {
        Board board = Objects.requireNonNull(boardDto).toBoard();
        return ChessGame.of(board, currentTurnDto.toPieceColor());
    }

    public String getGameId() {
        return gameId;
    }

    public CurrentTurnDto getCurrentTurnDto() {
        return currentTurnDto;
    }

    public BoardDto getBoardDto() {
        return boardDto;
    }

    public void setBoardDto(BoardDto boardDto) {
        this.boardDto = boardDto;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "gameId='" + gameId + '\'' +
                ", currentTurnDto=" + currentTurnDto +
                ", boardDto=" + boardDto +
                '}';
    }
}
