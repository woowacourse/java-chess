package chess.dao;

public class PositionDAO {
//
//    public Position add(Position position) throws SQLException {
//        String query = "INSERT INTO position (file_value, rank_value) VALUES (?, ?)";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, position.file().value());
//        pstmt.setString(2, String.valueOf(position.rank().value()));
//        pstmt.executeUpdate();
//        return findByFileRank(position.file(), position.rank());
//    }
//
//    public Position findById(Long id) throws SQLException {
//        String query = "SELECT * FROM position WHERE id = ?";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, String.valueOf(id));
//        ResultSet rs = pstmt.executeQuery();
//        if (!rs.next()) return null;
//
//        return new Position(rs.getString("id"), rs.getString("file_value"),
//            rs.getString("rank_value"));
//    }
//
//    public Position findByFileRank(File file, Rank rank) throws SQLException {
//        String query = "SELECT * FROM position WHERE file_value = ? AND rank_value = ?";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, file.value());
//        pstmt.setString(2, String.valueOf(rank.value()));
//        ResultSet rs = pstmt.executeQuery();
//        if (!rs.next()) return null;
//
//        return new Position(
//            rs.getString("id"),
//            rs.getString("file_value"),
//            rs.getString("rank_value"));
//    }
//
//    public Position update(Position newPosition) throws SQLException {
//        String query = "UPDATE position SET file_value = ?, rank_value = ? WHERE id = ?";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, newPosition.file().value());
//        pstmt.setString(2, String.valueOf(newPosition.rank().value()));
//        pstmt.setString(3, String.valueOf(newPosition.getId()));
//        pstmt.executeUpdate();
//        return findById(newPosition.getId());
//    }
//
//    public void delete(Position position) throws SQLException {
//        String query = "DELETE FROM position WHERE id = ?";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, String.valueOf(position.getId()));
//        pstmt.executeUpdate();
//    }
//
//    public void deleteAll() throws SQLException {
//        String query = "DELETE FROM position";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.executeUpdate();
//    }
}
