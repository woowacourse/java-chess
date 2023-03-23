package chess.domain.game;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Game {

    private Team turn;
    private final Board board;

    public Game() {
        this.turn = Team.WHITE;
        this.board = BoardFactory.createBoard();
    }

    public void movePiece(Position source, Position target) {
        // TODO: 2023/03/22 Team 검증은 게임 추상화 수준에서
        board.move(source, target, turn);
        changeTurn();
    }

    private void changeTurn() {
        turn = turn.alter();
    }

    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }
}
