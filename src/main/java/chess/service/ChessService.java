package chess.service;

import chess.controller.web.dto.PieceDto;
import chess.domain.board.Board;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerFactory;
import chess.domain.order.MoveResult;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import chess.domain.statistics.ChessGameStatistics;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ChessService {
    private ChessGameManager chessGameManager = ChessGameManagerFactory.createNotStartedGameManager();

    public void start() {
        chessGameManager = ChessGameManagerFactory.createRunningGame();
    }

    public MoveResult move(Position from, Position to) {
        MoveResult move = chessGameManager.move(from, to);
        if (chessGameManager.isKingDead()) {
            chessGameManager = chessGameManager.end();
        }
        return move;
    }

    public boolean isEnd() {
        return chessGameManager.isEnd();
    }

    public Map<String, PieceDto> getPieces() {
        Board board = chessGameManager.getBoard();
        return board.getAliveSquares().stream()
                .collect(toMap(square -> square.getPosition().toString()
                        , square -> new PieceDto(square.getNotationText(), square.getColor().name())));
    }

    public Color nextColor() {
        try {
            return chessGameManager.nextColor();
        } catch (UnsupportedOperationException e) {
            return Color.BLANK;
        }
    }

    public ChessGameStatistics getStatistics(){
        return chessGameManager.getStatistics();
    }
}
