/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas.dto;

/**
 *
 * @author estel
 */
public class PersonaDTO {
    private int id_persona;
    private String nombre;
    private String apellido;
        public PersonaDTO(){}
            public PersonaDTO(int id_persona){
                this.id_persona=id_persona;
            }
            //Seteamos el ID de la persona
            public void setId_Persona(int id_persona){
                this.id_persona=id_persona;
            }
            public int getId_Persona(){
                return id_persona;
            }
            
            //TRAEMOS EL NOMBRE SETEADO DE LA PERSONA:
            public String getNombre(){
                return nombre;
            }
            //SETEAMOS EL NOMBRE DE LA PERSONA:
            public void setNombre(String nombre){
                this.nombre=nombre;
            }
            //TRAEMOS EL APELLIDO SETEADO DE LA PERSONA:
            public String getApellido(){
                return apellido;
            }
            //SETEAMOS EL APELLIDO DE LA PERSONA:
            public void setApellido(String apellido){
                this.apellido=apellido;
            }
            
            //SOBRE ESCRIBIMOS EL METODO DE SALIDA PARA CUANDO SEA LLAMADA:
            @Override
            public String toString(){
                return "Persona {"+"id_persona= "+id_persona+", nombre= "+nombre+", apellido= "+apellido+'}';
            }
}
