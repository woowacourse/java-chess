package controller.command;

import domain.chessGame.ChessBoard;

public class GameEnd extends GameCommand {

    private final ChessBoard chessBoard;

    public GameEnd(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public Command execute() {
        return this;
    }

    @Override
    public Command readNextCommand() {
        throw new UnsupportedOperationException("[ERROR] 게임이 종료되어 명령어를 입력할 수 없습니다.");
    }

    // Todo : 다른 클래스에도 구현해줘야 함 + 상위 추상 클래스로 묶을지 여부 고민하기
    public boolean isEnd() {
        return true;
    }
}
