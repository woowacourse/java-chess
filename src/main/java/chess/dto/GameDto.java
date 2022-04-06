package chess.dto;

import chess.domain.game.ChessGame;

public class GameDto {

    private final long gameId;
    private final PlayersDto playersDto;
    private final boolean finished;
    private final ColorDto currentTurnColor;

    private GameDto(final long gameId, final PlayersDto playersDto, final boolean finished, final ColorDto currentTurnColor) {
        this.gameId = gameId;
        this.playersDto = playersDto;
        this.finished = finished;
        this.currentTurnColor = currentTurnColor;
    }

    public static GameDto toDto(final ChessGame chessGame) {
        return new GameDto(
                chessGame.getId(), PlayersDto.toDto(chessGame.getPlayers()),
                chessGame.isFinished(), ColorDto.toDto(chessGame.getColorOfCurrentTurn()));
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

    public ColorDto getCurrentTurnColor() {
        return currentTurnColor;
    }
}
