package chess.dto;

import chess.domain.game.ChessGame;

public class GameDto {

    private final long gameId;
    private final PlayersDto playersDto;
    private final boolean finished;
    private final String currentTurnColor;

    private GameDto(final long gameId, final PlayersDto playersDto, final boolean finished, final String currentTurnColor) {
        this.gameId = gameId;
        this.playersDto = playersDto;
        this.finished = finished;
        this.currentTurnColor = currentTurnColor;
    }

    public static GameDto toDto(final ChessGame chessGame) {
        return new GameDto(
                chessGame.getId(), PlayersDto.toDto(chessGame.getPlayers()),
                chessGame.isFinished(), chessGame.getColorOfCurrentTurn().getName());
    }

    public long getGameId() {
        return gameId;
    }

    public PlayersDto getPlayersDto() {
        return playersDto;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getCurrentTurnColor() {
        return currentTurnColor;
    }
}
