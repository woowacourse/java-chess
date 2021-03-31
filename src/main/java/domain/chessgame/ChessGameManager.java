package domain.chessgame;

import domain.board.Board;
import domain.command.Command;
import domain.command.Commands;
import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;

public class ChessGameManager {

    private final ChessGame chessGame;
    private final Commands commands;
    private boolean isStatusCommandCalled;

    public ChessGameManager(ChessGame chessGame, Commands commands) {
        this.chessGame = chessGame;
        this.commands = commands;
        this.isStatusCommandCalled = false;
    }

    public void runChessGame(List<String> inputs) {
        Command command = commands.command(inputs);
        checkStatusCommandCalled(command);
        command.execute(chessGame, inputs);
    }

    public boolean isStatusCommandCalled() {
        return isStatusCommandCalled;
    }

    private void checkStatusCommandCalled(Command command) {
        if (command.isStatusCommand()) {
            isStatusCommandCalled = true;
            return;
        }
        isStatusCommandCalled = false;
    }

    public boolean isPlaying() {
        return chessGame.isPlaying();
    }

    public Board board() {
        return chessGame.board();
    }

    public Map<Position,Piece> pieces(){
        return chessGame.pieces();
    }

    public boolean isBlackKingAlive(){
        return chessGame.isKingAlive(Color.BLACK);
    }

    public boolean isWhiteKingAlive(){
        return chessGame.isKingAlive(Color.WHITE);
    }

}
