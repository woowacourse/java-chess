package chess.view.command;

import chess.domain.ChessBoard;

public class Running implements Command {

    private final ChessBoard chessBoard;

    public Running(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public Command run(final String command) {
        if (command.equals("end")) {
            return null;
        }
        throw new IllegalArgumentException("게임 진행상태에서 불가능한 명령어입니다.");
   }
}
