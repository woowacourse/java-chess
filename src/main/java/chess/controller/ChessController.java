package chess.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import chess.controller.dto.PieceDTO;
import chess.controller.dto.RoundStatusDTO;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.converter.StringPositionConverter;
import chess.domain.piece.Piece;
import chess.service.ChessService;
import java.util.List;
import java.util.Map;

public class ChessController {

    private final StringPositionConverter stringPositionConverter;
    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.stringPositionConverter = new StringPositionConverter();
        this.chessService = chessService;
    }

    public List<PieceDTO> startGame(Long id) {
        ChessGame chessGame =
            chessService.loadChess(id);
        return chessGame.pieces().asList().stream()
            .map(PieceDTO::new)
            .collect(toList());
    }

    public RoundStatusDTO roundStatus(Long gameId) {
        ChessGame chessGame = chessService.loadChess(gameId);
        List<Piece> pieces = chessGame.currentColorPieces();
        return new RoundStatusDTO(
            mapMovablePositions(pieces),
            chessGame.currentColor(),
            chessGame.gameResult(),
            chessGame.isChecked(),
            chessGame.isKingDead()
        );
    }

    private Map<String, List<String>> mapMovablePositions(List<Piece> pieces) {
        return pieces.stream()
            .collect(toMap(
                piece -> piece.currentPosition().columnAndRow(),
                piece -> piece.movablePositions()
                    .stream()
                    .map(Position::columnAndRow)
                    .collect(toList())
            ));
    }

    public void move(Long gameId, String currentPosition, String targetPosition) {
        ChessGame chessGame = chessService.loadChess(gameId);

        Position current = stringPositionConverter.convert(currentPosition);
        Position target = stringPositionConverter.convert(targetPosition);
        chessGame.movePiece(current, target);
    }

    public void restart(Long gameId) {
        chessService.restart(gameId);
    }

    public void exitGame(Long gameId) {
        chessService.exitGame(gameId);
    }

    public void saveGame(Long gameId) {
        chessService.saveGame(gameId);
    }
}
