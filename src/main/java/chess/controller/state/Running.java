package chess.controller.state;

import chess.domain.board.Square;
import chess.domain.game.Game;
import chess.domain.piece.Camp;
import chess.view.OutputView;

public class Running extends State {
    Running(final Game game) {
        super(game);
    }

    @Override
    public State next(final Square source, final Square target) {
        game().move(source, target);
        OutputView.printChessBoard(game().getPieces());

        if (game().judgeWinner() != Camp.EMPTY) {
            return new KingDead(game());
        }
        return new Running(game());
    }

    @Override
    public State status() {
        final double whiteScore = game().calculateScore(Camp.WHITE);
        final double blackScore = game().calculateScore(Camp.BLACK);
        final Camp winner = game().judgeWinner();
        OutputView.printStatus(whiteScore, blackScore, winner);

        return this;
    }
}
