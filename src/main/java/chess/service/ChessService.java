package chess.service;


import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.MoveDto;
import chess.dto.ResponseDto;
import chess.dto.StatusDto;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ChessService {

    private ChessGame chessGame = new ChessGame();

    public Map<String, String> getCurrentBoard() {
        Map<Position, Piece> board = chessGame.getBoard();
        Map<String, String> boardDto = new LinkedHashMap<>();
        for (Entry<Position, Piece> positionPieceEntry : board.entrySet()) {
            boardDto.put(positionPieceEntry.getKey().getPosition(), positionPieceEntry.getValue().getName());
        }

        return boardDto;
    }

    public String getCurrentTurn() {
        return chessGame.getTurn();
    }

    public ResponseDto move(final MoveDto moveDto) {
        final String from = moveDto.getFrom();
        final String to = moveDto.getTo();
        try {
            boolean movable = chessGame.move(Position.create(from), Position.create(to));
            return checkFinished(movable);
        } catch (Exception e) {
            return new ResponseDto("400", e.getMessage(), chessGame.getTurn());
        }
    }

    private ResponseDto checkFinished(boolean movable) {
        if (!movable) {
            return new ResponseDto("300", "끝", chessGame.getTurn());
        }
        return new ResponseDto("200", "성공", chessGame.getTurn());
    }

    public void start() {
        chessGame = new ChessGame();
    }

    public StatusDto status() {
        return chessGame.getStatus();
    }

    public void end() {
    }
}
