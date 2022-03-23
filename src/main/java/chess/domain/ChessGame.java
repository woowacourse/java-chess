package chess.domain;

import chess.GameCommand;
import chess.domain.piece.Piece;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    private void start() {
        if (isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 실행 중 입니다.");
        }
        this.state = state.start();

        Map<Location, Piece> board = state.getBoard();

        for (Rank rank : Rank.reverseValues()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (File file : File.values()) {
                stringBuilder.append(board.get(Location.of(file, rank)).toString());
            }
            System.out.println(stringBuilder);
        }
    }

    public void execute(List<String> commandList) {
        if (GameCommand.isStart(commandList.get(0))) {
            start();
        }

        if (GameCommand.isEnd(commandList.get(0))) {
            end();
        }

//        if (GameCommand.isMove(commandList[0])) {
//            move(commandList[1], commandList[2]);
//        }

    }

    private void end() {
        if (!isRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
        }
        this.state = state.end();
    }

}
