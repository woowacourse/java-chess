package chess.view;

import chess.board.BoardGenerator;
import chess.manager.ChessManager;

import java.util.Objects;
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
        if ("start".equals(command)) {
            this.chessManager = new ChessManager(BoardGenerator.create());
        }
        if (Objects.isNull(chessManager)) {
            return;
        }
        Matcher matcher = MOVE.matcher(command);
        if (matcher.find()) {
            String source = matcher.group(1);
            String target = matcher.group(2);
            chessManager.move(source, target);
        }
        if ("status".equals(command)) {
            OutputView.showScore(this.chessManager.getCurrentTeam(),
                    this.chessManager.calculateCurrentTeamScore());
        }

        OutputView.showChessBoard(this.chessManager.getChessBoard());

        isNotEnd = !"end".equals(command) && chessManager.isKingAlive();
    }

    public boolean isNotEnd() {
        return isNotEnd;
    }
}
