package chess.dao;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.ChessGameDTO;
import chess.dto.PieceDTO;
import chess.dto.PiecesDTO;
import chess.dto.TeamDTO;
import chess.view.PieceNameConverter;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessDAO {
    private Gson gson = new Gson();

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }


    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void saveGame(ChessGame chessGame) throws SQLException {
        String query = "INSERT INTO game(data) VALUES (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gson.toJson(createChessGameDto(chessGame)));
        pstmt.executeUpdate();
    }

    private ChessGameDTO createChessGameDto(ChessGame chessGame) {
        final Map<Position, String> chessBoard = convertToBlackPrintName(chessGame);
        chessBoard.putAll(convertToWhitePrintName(chessGame));
        List<PieceDTO> pieceDtos = new ArrayList<>();
        for (Map.Entry<Position, String> entry : chessBoard.entrySet()) {
            pieceDtos.add(new PieceDTO(entry.getKey().getKey(), entry.getValue()));
        }

        PiecesDTO piecesDto = new PiecesDTO(pieceDtos);

        TeamDTO blackTeamDTO = new TeamDTO(chessGame.getBlackTeam().getName(),
                String.valueOf(chessGame.getBlackTeam().calculateTotalScore()),
                chessGame.getBlackTeam().isCurrentTurn());

        TeamDTO whiteTeamDTO = new TeamDTO(chessGame.getWhiteTeam().getName(),
                String.valueOf(chessGame.getWhiteTeam().calculateTotalScore()),
                chessGame.getWhiteTeam().isCurrentTurn());

        ChessGameDTO chessGameDto = new ChessGameDTO(piecesDto, blackTeamDTO, whiteTeamDTO, !chessGame.isEnd());
        return chessGameDto;
    }

    private static Map<Position, String> convertToBlackPrintName(final ChessGame chessGame) {
        final Map<Position, Piece> blackPosition = chessGame.getBlackTeam().getPiecePosition();
        final Map<Position, String> blackPrintFormat = new HashMap<>();
        for (Position position : blackPosition.keySet()) {
            final Piece piece = blackPosition.get(position);
            blackPrintFormat.put(position, PieceNameConverter.convert(piece).toUpperCase());
        }
        return blackPrintFormat;
    }

    private static Map<Position, String> convertToWhitePrintName(final ChessGame chessGame) {
        final Map<Position, Piece> whitePosition = chessGame.getWhiteTeam().getPiecePosition();
        final Map<Position, String> whitePrintFormat = new HashMap<>();
        for (Position position : whitePosition.keySet()) {
            final Piece piece = whitePosition.get(position);
            whitePrintFormat.put(position, PieceNameConverter.convert(piece).toLowerCase());
        }
        return whitePrintFormat;
    }
}
