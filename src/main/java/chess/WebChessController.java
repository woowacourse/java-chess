package chess;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Result;

import chess.dto.BoardDto;
import chess.dto.RequestDto;
import chess.dto.ResponseDto;

public class WebChessController {
    private static final Color START_PLAYER = Color.WHITE;

    private Color turn;
    private Board board;
    private boolean playing;

    public WebChessController() {
        playing = false;
    }

    public ResponseDto start() {
        if (playing) {
            throw new IllegalStateException("게임을 시작할 수 없습니다.");
        }

        board = Board.initialBoard();
        turn = START_PLAYER;

        return load(board, turn);
    }

    public ResponseDto load(Board board, Color turn) {
        this.board = board;
        this.playing = true;
        this.turn = turn;

        Result result = board.createResult();
        return new ResponseDto(new BoardDto(board.getBoard()), result, turn);
    }

    public ResponseDto move(RequestDto requestDto) {
        if (!playing) {
            throw new IllegalStateException("게임을 실행할 수 없습니다.");
        }

        board.movePieceIfValid(turn, requestDto.getFrom(), requestDto.getTo());

        turn = turn.nextTurn();
        Result result = board.createResult();

        if (result.isGameFinished()) {
            playing = false;
        }

        return new ResponseDto(new BoardDto(board.getBoard()), result, turn);
    }

    public ResponseDto end() {
        playing = false;
        Result result = board.createResult();
        return new ResponseDto(new BoardDto(board.getBoard()), result, turn);
    }
}