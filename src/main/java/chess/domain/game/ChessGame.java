package chess.domain.game;

import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;

public class ChessGame {
    private Player turn;
    private ChessBoard chessBoard;

    public ChessGame() {
        this.turn = Player.WHITE;
    }

    public ResponseDto start(RequestDto requestDto) {
        Command command = requestDto.getCommand();

        if (command != Command.START) {
            throw new IllegalStateException("게임을 진행하기 위해서는 start(시작) 명령어를 먼저 입력해야 합니다.");
        }

        chessBoard = new ChessBoard(PieceFactory.create());

        return new ResponseDto(chessBoard, chessBoard.createResult(), turn);
    }

    public ResponseDto move(RequestDto requestDto) {
        if (!chessBoard.validateTurn(requestDto.getFrom(), turn)) {
            throw new IllegalArgumentException("차례가 아닙니다.");
        }

        chessBoard.move(requestDto.getFrom(), requestDto.getTo());
        this.turn = Player.reversePlayer(turn);
        return new ResponseDto(chessBoard, chessBoard.createResult(), turn);
    }

    public ResponseDto status(RequestDto requestDto) {
        return new ResponseDto(chessBoard, chessBoard.createResult(), turn);
    }

    public ResponseDto end(RequestDto requestDto) {
        return new ResponseDto(chessBoard, chessBoard.createResult(), turn);
    }
}
