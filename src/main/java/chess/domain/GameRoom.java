package chess.domain;

import chess.domain.position.Position;

public class GameRoom {

    private final RoomNumber roomNumber;
    private final ChessGame chessGame;

    public GameRoom(final RoomNumber roomNumber, final ChessGame chessGame) {
        this.roomNumber = roomNumber;
        this.chessGame = chessGame;
    }

    public void start() {
        chessGame.initialize();
    }

    public void executeMove(final Position source, final Position destination) {
        chessGame.executeMove(source, destination);
    }

    public boolean isGameNotEnd() {
        return chessGame.isNotEnd();
    }

    public double calculateScore(final Team team) {
        return chessGame.calculateScore(team);
    }

    public void end() {
        chessGame.end();
    }

    public Team findWinningTeam() {
        return chessGame.findWinningTeam();
    }

    public boolean isKingDead() {
        return chessGame.isKingDead();
    }

    public boolean isFirstTurn() {
        return chessGame.isFirstTurn();
    }

    public boolean isSameRoom(final RoomNumber roomNumber) {
        return this.roomNumber.equals(roomNumber);
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }
}
