package chess.controller;

import chess.domain.game.ChessBoard;
import chess.domain.game.ChessBoardFactory;
import chess.domain.game.Player;
import chess.dto.ChessBoardDto;
import chess.dto.RequestDto;
import chess.dto.ResponseDto;
import chess.domain.result.Result;

public class ChessController {
    private static final Player START_PLAYER = Player.WHITE;

    private Player turn;
    private ChessBoard chessBoard;
    private boolean playing;

    public ChessController() {
        playing = false;
    }

    public ResponseDto start() {
        if (playing) {
            throw new IllegalStateException("게임을 시작할 수 없습니다.");
        }

        chessBoard = ChessBoardFactory.create();
        turn = START_PLAYER;

        return load(chessBoard, turn);
    }

    public ResponseDto load(ChessBoard chessBoard, Player turn) {
        this.chessBoard = chessBoard;
        this.playing = true;
        this.turn = turn;

        ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
        Result result = chessBoard.createResult();
        return new ResponseDto(chessBoardDto, result, turn);
    }

    public ResponseDto move(RequestDto requestDto) {
        if (!playing) {
            throw new IllegalStateException("게임을 실행할 수 없습니다.");
        }

        if (!chessBoard.validateTurn(requestDto.getFrom(), turn)) {
            throw new IllegalArgumentException("차례가 아닙니다.");
        }

        chessBoard.move(requestDto.getFrom(), requestDto.getTo());

        turn = Player.reversePlayer(turn);
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
        Result result = chessBoard.createResult();

        if (result.isGameFinished()) {
            playing = false;
        }

        return new ResponseDto(chessBoardDto, result, turn);
    }

    public ResponseDto end() {
        playing = false;

        ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
        Result result = chessBoard.createResult();
        return new ResponseDto(chessBoardDto, result, turn);
    }
}
