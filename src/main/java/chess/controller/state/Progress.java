package chess.controller.state;

import chess.domain.ChessBoard;
import chess.domain.TeamColor;
import chess.dto.ChessBoardDto;
import java.util.List;

public class Progress implements State {

    private static final String WRONG_REQUEST_ERROR_MESSAGE = "게임이 진행 중일 때는 시작할 수 없습니다.";

    private final ChessBoard chessBoard;
    private TeamColor currentColor;

    public Progress(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.currentColor = TeamColor.WHITE;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException(WRONG_REQUEST_ERROR_MESSAGE);
    }

    @Override
    public void move(List<Integer> source, List<Integer> dest) {
        chessBoard.move(source, dest, currentColor);
        currentColor = currentColor.transfer();
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public ChessBoardDto getBoard() {
        return ChessBoardDto.from(chessBoard);
    }

}
