/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package porcel.workout2success.data;

import porcel.workout2success.data.DAO;
import java.sql.SQLException;
import java.util.List;
import porcel.workout2success.dto.Workout;

public interface WorkoutDAO extends DAO<Workout> {

    List<Workout> getAll() throws SQLException;

    List<Workout> getWorkoutsPerUser(int oid) throws SQLException;

    int deleteWorkout(int oid) throws SQLException;
}
