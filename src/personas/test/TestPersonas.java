/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas.test;
import java.sql.SQLException;
import java.util.List;
import personas.dto.PersonaDTO;
import personas.jdbc.PersonaDao;
import personas.jdbc.PersonasDaoJDBC;

/**
 *
 * @author estel
 */
public class TestPersonas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Utilizamos el tipo interface como referencia a una clase concreta:
        PersonaDao personaDao=new PersonasDaoJDBC();
        
        //Creamos un nuevo Registro
        //Hacemos uso de la clase persona DTO la cual se usa
        //oara transferir la información entre las capas
        //no es necesario especificar la llave primaria
        //ya que en este caso es de tipo autonumerico y la BD se encarga:
        
        //ID persona es automatico:
        
        PersonaDTO persona=new PersonaDTO();
        persona.setNombre("Mario");
        persona.setApellido("Lopez");
        
        //utilizamos la capa DAO para persistir el objeto DTO
        try{
            //instertamos una nueva persona a la tabla
            personaDao.insert(persona);
            
            //eliminamos una persona de la tabal
            //personaDao.delete(new PersonaDTO(3));// debe de existir o emitira un error
            
            //actualizamos un registro
            PersonaDTO personaTemp=new PersonaDTO();
            //personaTemp.setId_persona(2);//actualizamos el registro2
            //personaTmp.setNombre("Julio");
            //personaTmp.setApellido("Noblex");
            //personaDao.update(personaTmp);
            
            //Seleccionamos todos los registros:
            List<PersonaDTO> personas=personaDao.select();
            for(PersonaDTO personaDTO:personas){
                System.out.print(personaDTO);
                System.out.println();
            }
            
        }catch(SQLException e){
            System.out.println("Excepcion en la capa de prueba");
            e.printStackTrace();
        }
    }
    
}
