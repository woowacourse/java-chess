package chess;

import chess.domain.ChessGame;
import chess.domain.GameCommand;
import chess.domain.MoveLocation;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;

public class ConsoleApplication {
    public static void main(String[] args) {
        OutputView.printChessCommandInfo();
        ChessGame chessGame = new ChessGame();
        do {
            playEachGame(chessGame);
        } while (chessGame.isRunning());
    }

    private static void playEachGame(ChessGame chessGame) {
        try {
            playGameByCommand(chessGame);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e);
            playEachGame(chessGame);
        }
    }

    private static void playGameByCommand(ChessGame chessGame) {
        String command = InputView.askCommand();
        GameCommand gameCommand = GameCommand.of(command);
        if (gameCommand.isStart()) {
            chessGame.start();
            Map<String, Piece> stringPieceMap = chessGame.toMap();
            for (String s : stringPieceMap.keySet()) {
                Piece piece = stringPieceMap.get(s);
                System.out.println(piece);
                System.out.println(piece.getTeam());
                System.out.println(piece.getName());
            }
        }
        if (gameCommand.isMove()) {
            MoveLocation moveLocation = MoveLocation.of(command);
            chessGame.move(moveLocation.getSource(), moveLocation.getTarget());
        }
        if (gameCommand.isStatus()) {
            chessGame.status();
        }
        if (gameCommand.isEnd()) {
            chessGame.end();
        }
    }
}
