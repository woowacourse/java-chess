package chess.controller.command;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.renderer.CommendRenderer;
import chess.renderer.FileInputRenderer;
import chess.renderer.RankInputRenderer;
import chess.view.validator.ValidateType;

import java.util.List;

public class RunningCommand extends Command {
    public RunningCommand(ChessController chessController) {
        super(chessController);
    }

    @Override
    public boolean operate(ChessGame chessGame) {
        List<String> commend = inputView.requestCommend(List.of(ValidateType.PLAY,
                ValidateType.MOVE_SIZE,
                ValidateType.OUT_OF_RANGE));
        if (CommendRenderer.render(commend.get(0)).equals(CommandType.END)) {
            this.chessController.setCommend(new EndCommand(chessController));
            return true;
        }
        chessGame.move(makeSquare(commend.get(1)), makeSquare(commend.get(2)));
        outputView.printChessBoard(chessGame.getChessboard());
        return true;
    }

    private Square makeSquare(String fileRank) {
        File file = FileInputRenderer.renderString(String.valueOf(fileRank.charAt(0)));
        Rank rank = RankInputRenderer.renderString(String.valueOf(fileRank.charAt(1)));
        return new Square(file, rank);
    }
}
