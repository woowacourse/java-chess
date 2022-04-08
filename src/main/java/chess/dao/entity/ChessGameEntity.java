package chess.dao.entity;

import chess.domain.game.ChessGame;

public class ChessGameEntity {

    private final String name;
    private final boolean isOn;
    private final String teamValueOfTurn;

    public ChessGameEntity(String name, boolean isOn, String teamValueOfTurn) {
        this.name = name;
        this.isOn = isOn;
        this.teamValueOfTurn = teamValueOfTurn;
    }

    public ChessGameEntity(final ChessGame chessGame) {
        this.name = chessGame.getName();
        this.isOn = chessGame.isOn();
        this.teamValueOfTurn = chessGame.getTurn().getNow().getValue();
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
