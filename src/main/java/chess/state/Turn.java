package chess.state;

import chess.chessBoard.Board;
import chess.game.Player;
import chess.chessBoard.position.Position;

import java.util.List;

import static chess.state.Command.*;

public class Turn extends Running{
    private final Player player;
    private final String command;

    protected Turn(String command, Board board, Player player) {
        super(board);
        this.player = player;
        this.command = command;
        movePiece();
    }

    private void checkTurn(Position source) {
        if (!board.checkRightTurn(player, source)) {
            throw new IllegalArgumentException("[ERROR] 상대편 기물은 선택 할 수 없습니다.");
        }
    }

    @Override
    public State proceed(String command) {
        if (END.startsWith(command) || board.isEndSituation()) {
            return new End(board);
        }
        if (!MOVE.startsWith(command)) {
            throw new IllegalArgumentException("[ERROR] 올바른 명령이 아닙니다.");
        }
        if (player.equals(Player.WHITE)) {
            return new Turn(command, board, Player.BLACK);
        }
        return new Turn(command, board, Player.WHITE);
    }

    private void movePiece() {
        List<String> option = List.of(command.split(" "));
        Position source = Position.from(option.get(1));
        Position target = Position.from(option.get(2));
        checkTurn(source);
        board.move(source, target);
    }
}
