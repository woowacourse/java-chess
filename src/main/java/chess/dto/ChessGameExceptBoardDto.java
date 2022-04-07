package chess.dto;

import chess.domain.game.ChessGame;

public class ChessGameExceptBoardDto {

    private final String name;
    private final boolean isOn;
    private final String teamValueOfTurn;

    public ChessGameExceptBoardDto(String name, boolean isOn, String teamValueOfTurn) {
        this.name = name;
        this.isOn = isOn;
        this.teamValueOfTurn = teamValueOfTurn;
    }

    public ChessGameExceptBoardDto(final ChessGame chessGame) {
        this.name = chessGame.getName();
        this.isOn = chessGame.isOn();
        this.teamValueOfTurn = chessGame.getTurn().getNowValue();
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getTeamValueOfTurn() {
        return teamValueOfTurn;
    }
}
