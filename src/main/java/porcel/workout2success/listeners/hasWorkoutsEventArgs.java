package porcel.workout2success.listeners;

import porcel.workout2success.dto.Workout;
import java.util.EventObject;
import java.util.List;
import porcel.workout2success.dto.WorkoutCalendar;

public class hasWorkoutsEventArgs extends EventObject {

    private List<WorkoutCalendar> workouts;

    public hasWorkoutsEventArgs(Object source, List<WorkoutCalendar> workouts) {
        super(source);
        this.workouts = workouts;
    }

    public List<WorkoutCalendar> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutCalendar> workouts) {
        this.workouts = workouts;
    }

    @Override
    public String toString() {
        if (workouts == null || workouts.isEmpty()) {
            return "No hay entrenamientos programados para este día.";
        }

        StringBuilder sb = new StringBuilder("Entrenamientos programados:\n");
        for (WorkoutCalendar workout : workouts) {
            sb.append(String.format(
                    "ID: %d\nUsuario: %s\nComentario: %s\nNúmero de Ejercicios: %d\n\n",
                    workout.getId(),
                    workout.getUserName(),
                    workout.getComment(),
                    workout.getNumberOfExercises()
            ));
        }
        return sb.toString();
    }

}
