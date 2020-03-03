/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas.jdbc;
import java.sql.*;

/**
 *
 * @author estel
 */
public class Conection {
    private static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    //AGREGAMOS SERVERTIMEZONE PARA VER SI FUNCIONA DESDE URL O MODIFICAR EN MYSQL:
    private static final String JDBC_URL="jdbc:mysql://localhost/sga?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER="root";
    private static final String JDBC_PASS="admin";
    private static Driver driver=null;
    
        public static synchronized Connection getConnection() throws SQLException{
            if(driver==null){
                try{
                    Class jdbcDriverClass=Class.forName(JDBC_DRIVER);
                    driver=(Driver) jdbcDriverClass.newInstance();
                    DriverManager.registerDriver(driver);
                }catch(Exception e){
                    System.out.println("Fallo en cargar el driver JDBC");
                    e.printStackTrace();
                }
            }
            //realiza la conexión a la url indicada con nuestro usuario y el password asignado:
            return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
        }
            //Cerramos con el metodo el ResultSet que nos envía
            public static void close(ResultSet rs){
                try{
                    if(rs!=null){
                        rs.close();
                    }
                }catch(SQLException sqlex){
                    sqlex.printStackTrace();
                }
            }
            //Cerramos el PREPARESTATEMENT:
            public static void close(PreparedStatement stmt){
                try{
                    if(stmt!=null){
                        stmt.close();
                    }
                }catch(SQLException sqlex){
                    sqlex.printStackTrace();
                }
            }
            //CIERRE DE LA CONEXIÓN A BASE:
            public static void close(Connection conn){
                try{
                    if(conn!=null){
                        conn.close();
                    }
                }catch(SQLException sqlex){
                    sqlex.printStackTrace();
                }
            }
}
