package chess.controller.command.operator;

import chess.controller.ChessController;
import chess.controller.command.CommandType;
import chess.domain.ChessGame;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.renderer.CommendRenderer;
import chess.renderer.FileInputRenderer;
import chess.renderer.RankInputRenderer;
import chess.view.OutputView;

import java.util.List;

public class MoveOperator extends Operator {
    public MoveOperator(ChessController chessController, ChessGame chessGame) {
        super(chessController, chessGame);
    }

    @Override
    public boolean operate(List<String> command) {
        if (!CommendRenderer.isSame(command.get(0), CommandType.MOVE)) {
            Operator next = new EndOperator(chessController, chessGame);
            return next.operate(command);
        }
        chessGame.move(makeSquare(command.get(1)), makeSquare(command.get(2)));
        OutputView.printChessBoard(chessGame.getChessboard());
        return chessGame.canKeepGoing();
    }

    private Square makeSquare(String fileRank) {
        File file = FileInputRenderer.renderString(String.valueOf(fileRank.charAt(0)));
        Rank rank = RankInputRenderer.renderString(String.valueOf(fileRank.charAt(1)));
        return new Square(file, rank);
    }
}
