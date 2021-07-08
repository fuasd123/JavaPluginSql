package cf.thdisstudio;

import java.sql.*;

public class sql {
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/log?useUnicode=true&passwordCharacterEncoding=utf-8";
    static Connection conn = null;
    public sql(){
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(
                    DB_URL, "root", "1106");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();

        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        }
    }
    public void save(String uuid, String nickname, String ip){
        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery("INSERT INTO `log`.`player_join` (`uuid`, `nickname`, `ip`) VALUES ('"+uuid+"', '"+nickname+"', '"+ip+"');");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean is_player_frist_join(String uuid) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeQuery("use re");
        ResultSet rt = stmt.executeQuery("SELECT * FROM re WHERE uuid LIKE '%" + uuid + "%';");
        return rt.next();
    }
    public void savee(String player, String uuid){
        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery("INSERT INTO `re`.`re` (`uuid`, `player`) VALUES ('"+uuid+"', '"+player+"');");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean is_player_uuid(String player,String uuid) throws SQLException {
        Statement stmt = conn.createStatement();


        ResultSet rt = stmt.executeQuery("SELECT * FROM re WHERE uuid LIKE '%" + uuid +"%';");
        if(rt.next()){
            ResultSet rts = stmt.executeQuery("SELECT * FROM re WHERE player LIKE '%" + player +"%';");
            return rts.next();
        }else
            return false;
    }
}
