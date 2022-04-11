package chess.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.command.Command;
import chess.command.CommandType;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Square;
import chess.dto.ScoreDTO;
import chess.dto.SquareAndPiece;

public class DtoService {
    public static void saveInfo(Command command, ChessGame game, Map<String, Object> model) {
        CommandType commandType = command.getCommandType();

        if (commandType == CommandType.END || commandType == CommandType.STATUS) {
            model.put("score", ScoreDTO.of(command.getResult()));
            model.put("exit", true);
            return;
        }

        if (commandType == CommandType.MOVE) {
            checkKingDieAfterMove(game, model);
        }
        packBoardInfo(game, model);
    }

    private static void checkKingDieAfterMove(ChessGame game, Map<String, Object> model) {
        if (game.isKingDie()) {
            model.put("kingDie", true);
        }
    }

    public static void packBoardInfo(ChessGame game, Map<String, Object> model) {
        if (game == null) {
            return;
        }
        Map<Square, Piece> pieceMap = game.getBoard();
        List<SquareAndPiece> infos = new ArrayList<>();
        for (Square square : pieceMap.keySet()) {
            infos.add(SquareAndPiece.of(square, pieceMap.get(square)));
        }
        model.put("infos", infos);
    }
}
