package chess.service;

import chess.dao.GridDAO;
import chess.dao.PieceDAO;
import chess.dao.RoomDAO;
import chess.domain.grid.Grid;
import chess.domain.grid.Line;
import chess.domain.grid.Lines;
import chess.domain.grid.gridStrategy.CustomGridStrategy;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.dto.PieceDto;
import chess.dto.requestdto.MoveRequestDto;
import chess.dto.requestdto.StartRequestDto;
import chess.dto.response.Response;
import chess.dto.response.ResponseCode;
import chess.dto.responsedto.GridAndPiecesResponseDto;
import chess.dto.responsedto.GridResponseDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessService {

    private final Grid grid;
    private final RoomDAO roomDAO;
    private final GridDAO gridDAO;
    private final PieceDAO pieceDAO;

    public ChessService() {
        grid = new Grid(new NormalGridStrategy());
        roomDAO = new RoomDAO();
        gridDAO = new GridDAO();
        pieceDAO = new PieceDAO();
    }

    public Response move(MoveRequestDto requestDto) {
        try {
            System.out.println(requestDto.getPiecesDto());
            List<Piece> pieces = requestDto.getPiecesDto().stream()
                    .map(pieceDto -> {
                        Color color = null;
                        if (pieceDto.isBlack()) {
                            color = Color.BLACK;
                        }
                        if (!pieceDto.isBlack()) {
                            color = Color.WHITE;
                        }
                        return PieceFactory.from(
                                pieceDto.getName().charAt(0),
                                color, pieceDto.getPosition().charAt(0),
                                pieceDto.getPosition().charAt(1)
                        );
                    })
                    .collect(Collectors.toList());
            List<Line> lines = Lines.from(pieces).lines();
            Grid grid = new Grid(new CustomGridStrategy(lines));
            grid.move(requestDto.getSourcePosition(), requestDto.getTargetPosition());
            return new Response(ResponseCode.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new Response(ResponseCode.WRONG_ARGUMENTS.getCode(), e.getMessage());
        }
    }

    public void start(long gridId) throws SQLException {
        gridDAO.changeToStarting(gridId);
    }

    public GridAndPiecesResponseDto getGridAndPieces(StartRequestDto requestDto) throws SQLException {
        String roomName = requestDto.getRoomName();
        Optional<Long> roomId = roomDAO.findRoomIdByName(roomName);
        if (!roomId.isPresent()) {
            long createdRoomId = roomDAO.createRoom(roomName);
            return createGridAndPiece(createdRoomId);
        }
        GridResponseDto gridResponseDto = gridDAO.findRecentGridByRoomId(roomId.get());
        List<PieceDto> piecesResponseDto = pieceDAO.findPiecesByGridId(gridResponseDto.getGridId());
        return new GridAndPiecesResponseDto(gridResponseDto, piecesResponseDto);
    }

//    public Response checkFinished() {
//        return new Response(ResponseCode.OK, new CheckFinishedResponseDto(grid.isFinished()));
//    }
//
//    public Response getWinner() {
//        return new Response(ResponseCode.OK, new WinnerResponseDto(grid.winnerColor()));
//    }
//
//    public Response restart() {
//        grid.resetGame();
//        return new Response(ResponseCode.NO_CONTENT);
//    }

    private GridAndPiecesResponseDto createGridAndPiece(long roomId) throws SQLException {
        Grid grid = new Grid(new NormalGridStrategy());
        List<Piece> pieces = grid.pieces();
        long gridId = gridDAO.createGrid(roomId);
        for (Piece piece : pieces) {
            pieceDAO.createPiece(gridId, piece);
        }
        GridResponseDto gridResponseDto = gridDAO.findGridByGridId(gridId);
        List<PieceDto> piecesResponseDto = pieceDAO.findPiecesByGridId(gridId);
        return new GridAndPiecesResponseDto(gridResponseDto, piecesResponseDto);
    }
}
