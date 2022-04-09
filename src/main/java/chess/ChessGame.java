package chess;

import chess.domain.game.Board;
import chess.domain.game.Color;
import chess.domain.game.Result;

import chess.domain.piece.Queen;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.RequestDto;
import chess.dto.ChessGameDto;

public class ChessGame {
    private static final Color START_PLAYER = Color.WHITE;

    private Color turn;
    private Board board;
    private boolean playing;

    public ChessGame() {
        playing = false;
    }

    public ChessGameDto start() {
        if (playing) {
            throw new IllegalStateException("게임을 시작할 수 없습니다.");
        }

        board = Board.initialBoard();
        turn = START_PLAYER;

        return load(board, turn);
    }

    public ChessGameDto load(Board board, Color turn) {
        this.board = board;
        this.playing = true;
        this.turn = turn;

        Result result = board.createResult();
        return new ChessGameDto(new BoardDto(board.getBoard()), result, turn);
    }

    public ChessGameDto move(RequestDto requestDto) {
        if (!playing) {
            throw new IllegalStateException("게임이 실행중이 아닙니다.");
        }

        Position source = requestDto.getFrom();
        Position target = requestDto.getTo();

        boolean isCastable = board.isCastable(turn, source, target);
        boolean isEnPassantAvailable = board.isEnPassantAvailable(turn, source, target);

        if(isCastable){
            board.castle(source, target);
        }
        if(!isCastable && isEnPassantAvailable){
            board.doEnPassant(turn, source, target);
        }
        if(!isCastable && !isEnPassantAvailable){
            board.movePieceIfValid(turn, source, target);
        }

        checkPromotion(target);
        turn = turn.nextTurn();

        Result result = board.createResult();

        if (result.isGameFinished()) {
            playing = false;
        }

        return new ChessGameDto(new BoardDto(board.getBoard()), result, turn);
    }

    private void checkPromotion(Position target) {
        if (board.isPromotable(target)) {
            board.promoteTo(target, new Queen(turn));
        }
    }

    public ChessGameDto end() {
        playing = false;
        Result result = board.createResult();
        return new ChessGameDto(new BoardDto(board.getBoard()), result, turn);
    }
}