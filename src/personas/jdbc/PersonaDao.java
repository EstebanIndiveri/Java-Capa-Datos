/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas.jdbc;
import java.sql.SQLException;
import java.util.List;
import personas.dto.PersonaDTO;
/*
MEDIANTE ESTA INTERFAZ SE CREARAN METODOS ABSTRACTOS CON LAS OPERACIONES 
BASICAS QUE REALIZARÁ EL USUARIO SOBRE LA TABLA DE PERSONAS:
CRUD(CREATE, READ,UPLOAD AND DELETE)
SE DEBE CREAR UNA CLASE CONCRETA PARA IMPLEMENTAR EL CODIGO ASOCIADO A CADA METODO

*/
/**
 *
 * @author estel
 */
public interface PersonaDao {
    
        //Metodo que crea una persona en la tabla
        public int insert(PersonaDTO persona) throws SQLException;
        
        //Metodo que realiza un update de la persona
        public int update(PersonaDTO persona) throws SQLException;
        
        //Metodo que eliminaría la persona
        public int delete(PersonaDTO persona) throws SQLException;
        
        //trae la lista de personas:
        public List<PersonaDTO>select() throws SQLException;
    
}
