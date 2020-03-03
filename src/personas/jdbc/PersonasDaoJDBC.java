/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas.jdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import personas.dto.PersonaDTO;

/*
Esta clase implementa la clase PersonaDAO es una implementacion con la tecnología
JDBC podriamos hacer otro tipo de implementacion con tencnología como HIBERNATE
IBATIS, SPRINGJDBC, ETC.
*/
/**
 *
 * @author estel
 */
public class PersonasDaoJDBC implements PersonaDao{
    //AL REALIZAR UNA IMPLEMENTACIÓN TENEMOS QUE INSTANCIAR CADA UNO DE LOS METODSO DE LA INTERFAZ
    //IMPLEMENTADA:
        //Creamos la instancia de conexión:
        private Connection userConn;
        
        //Intanciamos cada una de las posibles querys a realizar en nuestra tabla:
        
        //INSERT:
        private final String SQL_INSERT="INSERT INTO persona(nombre, apellido) VALUES(?,?)";
        
        //UPDATE:
        private final String SQL_UPDATE="UPDATE persona SET nombre=?, apellido? WHERE id_persona=?";
        
        //DELETE:
        private final String SQL_DELETE="DELETE FROM persona WHERE id_persona=?";
        
        //SELECT:
        private final String SQL_SELECT="SELECT id_persona, nombre,apellido FROM persona";
    
            public PersonasDaoJDBC(){}
            
            //Esto para que siempre usemos la misma conexión y no cerremos y abramos otra
            //nos posibilita un ROLLBACK en caso de error:
            public PersonasDaoJDBC(Connection conn){
                this.userConn=conn;
            }
            
            /*
            El metodo insert recibe como argumento un objeto DTO,
            el mismo viene de otra capa y se extraen sus valores para crear un nuevo registro:
            */
                //Cuando instanciamos clases de una interface usamos OVERRIDE, aunque no sea una 
                //Sobre escritura normal, ya que es la primera vez que lo instanciamos:
                @Override
                public int insert(PersonaDTO persona) throws SQLException{
                    Connection conn=null;
                    PreparedStatement stmt=null;
                    int rows=0;
                    
                    try{
                        //SI CONN ESTA NULA ENTONCES USAMOS USERCONN YA INSTANCIADA.
                        //SI USERCONN ESTA VACIA ENTONCES LLAMAMOS AL A CLASE CONECTION CON EL METODO
                        //.GETCONNECTION() PARA REALIZAR UNA NUEVA CONEXIÓN:
                        conn=(this.userConn!=null)?this.userConn:Conection.getConnection();
                        stmt=conn.prepareStatement(SQL_INSERT);
                        int index=1;
                        //extraemos cada uno de los valores que necesitemos del objeto PersonaDTO
                        //obtenido anteriormente:
                        stmt.setString(index++,persona.getNombre());
                        stmt.setString(index, persona.getApellido());
                        System.out.println("Ejecutando query:"+SQL_INSERT);
                        //numero de registros afectados
                        rows=stmt.executeUpdate();
                        System.out.println("Numero de registros afectados= "+rows);
                    }
                    //NO AGREGAMOS CATCH ERROR, SI HAY ERROR PERMITIMOS QUE SE PROPAGUE:
                    finally{
                        Conection.close(stmt);
                        if(this.userConn==null){
                            Conection.close(conn);
                        }
                    }
                    return rows;
                }
                
                @Override
                public int update(PersonaDTO persona) throws SQLException{
                    //Recibbe un objeto tambien.
                    Connection conn=null;
                    PreparedStatement stmt=null;
                    int rows=0;
                    
                    try{
                        //Operador ternario si la conexión esta vacia se llama al a clase y su metodo
                        //para obtener conexión nuevamente:
                        conn=(this.userConn!=null)? this.userConn:Conection.getConnection();
                        System.out.println("Ejecutando Query: "+SQL_UPDATE);
                        stmt=conn.prepareStatement(SQL_UPDATE);
                        int index=1;
                        stmt.setString(index++, persona.getNombre());
                        stmt.setString(index, persona.getApellido());
                        stmt.setInt(index, persona.getId_Persona());
                        rows=stmt.executeUpdate();
                        System.out.println("Numero de registros actualizados= "+rows);
                        
                    }finally{
                        Conection.close(stmt);
                        if(this.userConn==null){
                            Conection.close(conn);
                        }
                    }
                    return rows;
                }
                
                @Override
                public int delete(PersonaDTO persona)throws SQLException{
                    Connection conn=null;
                    PreparedStatement stmt=null;
                    int rows=0;
                        try{
                            conn=(this.userConn!=null)?this.userConn:Conection.getConnection();
                            System.out.println("Ejecutando Query: "+SQL_DELETE);
                            stmt=conn.prepareStatement(SQL_DELETE);
                            stmt.setInt(1, persona.getId_Persona());
                            rows=stmt.executeUpdate();
                            System.out.println("Registros Eliminados: "+rows);
                        }finally{
                            Conection.close(stmt);
                            if(this.userConn==null){
                                Conection.close(conn);
                            }
                        }
                        return rows;
                }
                
                @Override
                public List<PersonaDTO>select()throws SQLException{
                    Connection conn=null;
                    PreparedStatement stmt=null;
                    ResultSet rs=null;
                    PersonaDTO personaDTO=null;
                    //instanciamos el listado de personas:
                    List<PersonaDTO>personas=new ArrayList<PersonaDTO>();
                    try{
                        conn=(this.userConn!=null)?this.userConn:Conection.getConnection();
                        stmt=conn.prepareStatement(SQL_SELECT);
                        rs=stmt.executeQuery();
                        while(rs.next()){
                            //Nos trae el id de la primera columna:
                            int idPersonaTemp=rs.getInt(1);
                            String nombreTemp=rs.getString(2);
                            String apellidoTemp=rs.getString(3);
                            //PODRIA HABER HECHO UN CONSTRUCTOR DE TODOS LOS METODOS-
                            //ANOTAR PARA EL PROXIMO PROJECT:
                            personaDTO=new PersonaDTO();
                            personaDTO.setId_Persona(idPersonaTemp);
                            personaDTO.setNombre(nombreTemp);
                            personaDTO.setApellido(apellidoTemp);
                            personas.add(personaDTO);
                        }
                    }finally{
                        Conection.close(rs);
                        Conection.close(stmt);
                        if(this.userConn==null){
                            Conection.close(conn);
                        }
                    }
                    return personas;
                }
                
}
