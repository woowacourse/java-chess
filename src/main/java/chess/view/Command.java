package chess.view;


import chess.board.BoardGenerator;
import chess.board.MoveResult;
import chess.manager.ChessManager;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {
    private final static Pattern MOVE = Pattern.compile("move ([a-h][1-8]) ([a-h][1-8])");
    private final Supplier<String> commandSupplier;
    private ChessManager chessManager;
    private boolean isNotEnd = true;

    public Command(final Supplier<String> commandSupplier) {
        this.commandSupplier = commandSupplier;
    }

    public void action() {
        String command = commandSupplier.get();
        Matcher matcher = MOVE.matcher(command);
        if (matcher.find()) {
            String source = matcher.group(1);
            String target = matcher.group(2);
            if (MoveResult.WIN == chessManager.move(source, target)) {
                isNotEnd = false;
                OutputView.showChessBoard(this.chessManager.getChessBoard());
                return;
            }
        }
        if ("start".equals(command)) {
            this.chessManager = new ChessManager(new BoardGenerator().create());
        }
        if ("status".equals(command)) {
            OutputView.showScore(this.chessManager.getCurrentTeam(),
                    this.chessManager.calculateCurrentTeamScore());
        }

        OutputView.showChessBoard(this.chessManager.getChessBoard());

        isNotEnd = !"end".equals(command);
    }


    public boolean isNotEnd() {
        return isNotEnd;
    }
}
