/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package porcel.workout2success.data;

import porcel.workout2success.data.DAO;
import java.sql.SQLException;
import java.util.List;
import porcel.workout2success.dto.Exercici;

public interface ExerciciDAO extends DAO<Exercici> {

    List<Exercici> getAll() throws SQLException;

    List<Exercici> getExercicisPerWorkout(int oid) throws SQLException;

    //int insertExerciciPerWorkout(int idWorkout, String nomExercici, String descripcio, String demoFoto) throws SQLException;
    int insertExerciciPerWorkout(int idWorkout, int idExercici) throws SQLException;

    int deleteExercici(int oid) throws SQLException;
}
