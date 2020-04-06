package chess.domain.game;

import chess.controller.dto.ChessBoardDto;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;

public class ChessGame {
    private Player turn;
    private ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame() {
        gameStatus = GameStatus.NOT_STARTED;
    }

    public ResponseDto start(RequestDto requestDto) {
        if (gameStatus != GameStatus.NOT_STARTED) {
            throw new IllegalStateException("게임을 시작할 수 없습니다.");
        }

        Command command = requestDto.getCommand();

        if (command != Command.START) {
            throw new IllegalStateException("게임을 진행하기 위해서는 start(시작) 명령어를 먼저 입력해야 합니다.");
        }

        chessBoard = new ChessBoard(PieceFactory.create());
        turn = Player.WHITE;
        gameStatus = GameStatus.RUNNING;

        return new ResponseDto(new ChessBoardDto(chessBoard.getChessBoard()), chessBoard.createResult(), turn);
    }

    public ResponseDto move(RequestDto requestDto) {
        if (gameStatus != GameStatus.RUNNING) {
            throw new IllegalStateException("게임을 실행할 수 없습니다.");
        }

        if (!chessBoard.validateTurn(requestDto.getFrom(), turn)) {
            throw new IllegalArgumentException("차례가 아닙니다.");
        }

        chessBoard.move(requestDto.getFrom(), requestDto.getTo());

        this.turn = Player.reversePlayer(turn);
        return new ResponseDto(new ChessBoardDto(chessBoard.getChessBoard()), chessBoard.createResult(), turn);
    }

    public ResponseDto status(RequestDto requestDto) {
        return new ResponseDto(new ChessBoardDto(chessBoard.getChessBoard()), chessBoard.createResult(), turn);
    }

    public ResponseDto end(RequestDto requestDto) {
        gameStatus = GameStatus.FINISH;
        return new ResponseDto(new ChessBoardDto(chessBoard.getChessBoard()), chessBoard.createResult(), turn);
    }
}
