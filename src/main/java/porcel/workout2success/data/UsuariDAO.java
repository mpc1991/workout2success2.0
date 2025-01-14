/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package porcel.workout2success.data;

import porcel.workout2success.data.DAO;
import java.sql.SQLException;
import java.util.List;
import porcel.workout2success.dto.Usuari;

public interface UsuariDAO extends DAO<Usuari> {

    Usuari get(String email) throws SQLException;

    List<Usuari> getAll() throws SQLException;

    List<Usuari> getMyUsers(String mail) throws SQLException;
}
