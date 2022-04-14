package chess.domain.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.position.Square;
import chess.view.OutputView;

public class Move extends Command{
    private static final int MOVE_SOURCE_INDEX = 0;
    private static final int MOVE_TARGET_INDEX = 1;

    private final List<String> squares;

    public Move(List<String> commands) {
        super(commands);
        this.squares = commands.subList(1, 3);
    }

    public boolean execute(ChessGame chessGame) {
        checkNotInGame(chessGame);
        checkKingDie(chessGame);
        Square source = new Square(squares.get(MOVE_SOURCE_INDEX));
        Square target = new Square(squares.get(MOVE_TARGET_INDEX));
        chessGame.move(source, target);
        OutputView.showBoard(chessGame.getBoard());
        announceKingDie(chessGame);
        return true;
    }

    private void announceKingDie(ChessGame chessGame) {
        if (chessGame.isKingDie()) {
            OutputView.printKingDieMessage();
        }
    }
}
