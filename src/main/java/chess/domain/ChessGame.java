package chess.domain;

import chess.domain.exception.StartCommandException;
import chess.dto.GameStatusDto;
import chess.dto.ScoreDto;
import chess.dto.SquareMoveDto;
import chess.controller.command.CommandType;

public class ChessGame {

    private final Board board;
    private Turn turn;

    public ChessGame() {
        this.board = Board.create();
        this.turn = Turn.getFirstTurn();
    }

    public ChessGame(final Board board, final Turn turn) {
        this.board = board;
        this.turn = turn;
    }

    public void start(final CommandType commandType) {
        if (commandType.equals(CommandType.START)) {
            return;
        }
        throw new StartCommandException();
    }

    public void move(final SquareMoveDto squareMoveDto) {
        final Turn nextTurn = Turn.next(turn);
        board.validateTurn(turn, squareMoveDto.getCurrent());
        board.move(squareMoveDto.getCurrent(), squareMoveDto.getDestination());
        turn = nextTurn;
    }

    public void restart() {
        board.reset();
        turn = Turn.getFirstTurn();
    }

    public boolean isEnd() {
        return board.isKingCaught();
    }

    public ScoreDto getScore() {
        final Score whiteScore = board.calculateWhiteScore();
        final Score blackScore = board.calculateBlackScore();
        return new ScoreDto(whiteScore, blackScore);
    }

    public GameStatusDto getGameStatus() {
        return GameStatusDto.from(board);
    }

    public Turn getTurn() {
        return turn;
    }

    public Board getBoard() {
        return board;
    }
}
