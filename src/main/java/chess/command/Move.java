package chess.command;

import chess.domain.ChessGame;
import chess.domain.GameStatus;
import chess.domain.Score;
import chess.domain.position.Position;
import chess.view.OutputView;

public class Move implements Command {

    private final Position from;
    private final Position to;

    public Move(final String from, final String to) {
        this.from = Position.from(from);
        this.to = Position.from(to);
    }

    @Override
    public void execute(final ChessGame chessGame) {
        try {
            checkBeforePlaying(chessGame);
            chessGame.move(from, to);
            OutputView.printChessBoard(chessGame.findAllPiece());
            checkKingDie(chessGame);
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private void checkBeforePlaying(final ChessGame chessGame) {
        if (chessGame.isSameStatus(GameStatus.READY)) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private void checkKingDie(final ChessGame chessGame) {
        if (chessGame.isSameStatus(GameStatus.KING_DIE)) {
            OutputView.printResult(new Score(chessGame.findAllPiece()));
        }
    }
}
