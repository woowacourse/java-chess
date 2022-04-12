package chess.domain.game;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.QueenMovingPattern;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.ChessGameDto;
import chess.dto.RequestDto;
import chess.dto.ResultDto;

public class ChessGame {
    private static final Color START_PLAYER = Color.WHITE;

    private Color turn;
    private Board board;
    private boolean playing;

    public ChessGame() {
        playing = false;
    }

    public void start() {
        if (playing) {
            throw new IllegalStateException("게임을 시작할 수 없습니다.");
        }

        board = Board.initialBoard();
        turn = START_PLAYER;

        load(board, turn);
    }

    public void load(Board board, Color turn) {
        this.board = board;
        this.playing = true;
        this.turn = turn;
    }

    public void move(RequestDto requestDto) {
        if (!playing) {
            throw new IllegalStateException("게임이 실행중이 아닙니다.");
        }

        Position source = requestDto.getFrom();
        Position target = requestDto.getTo();

        boolean isCastable = board.isCastable(turn, source, target);
        boolean isEnPassantAvailable = board.isEnPassantAvailable(turn, source, target);

        if (isCastable) {
            board.castle(source, target);
        }
        if (!isCastable && isEnPassantAvailable) {
            board.doEnPassant(turn, source, target);
        }
        if (!isCastable && !isEnPassantAvailable) {
            board.movePieceIfValid(turn, source, target);
        }

        checkPromotion(target);
        turn = turn.nextTurn();
    }

    private void checkPromotion(Position target) {
        if (board.isPromotable(target)) {
            board.promoteTo(target, new Piece(turn, PieceType.QUEEN, new QueenMovingPattern(), 0));
        }
    }

    public void end() {
        playing = false;
    }

    public ChessGameDto createGameDto() {
        ResultDto result = board.createResult();
        if (result.isGameFinished()) {
            playing = false;
        }
        return new ChessGameDto(new BoardDto(board.getBoard()), result, turn);
    }
}
