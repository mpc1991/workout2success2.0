package porcel.workout2success.views;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import porcel.workout2success.Main;
import porcel.workout2success.dto.Exercici;
import porcel.workout2success.dto.ExerciciDAO;
import porcel.workout2success.dto.ExerciciDAOImpl;
import porcel.workout2success.dto.UsuariDAOImpl;
import porcel.workout2success.dto.Usuari;
import porcel.workout2success.dto.UsuariDAO;
import porcel.workout2success.dto.Workout;
import porcel.workout2success.dto.WorkoutDAO;
import porcel.workout2success.dto.WorkoutDAOImpl;

public class JPanelHomeUsers extends javax.swing.JPanel {

    private JPanelHome jPanelHome;
    private Main main;

    String sessionUsername = Main.getUsername();

    private javax.swing.JList<Usuari> jListUsuaris;

    public JPanelHomeUsers(JPanelHome jPanelHome, Main main) {
        initComponents();
        this.jPanelHome = jPanelHome;
        this.main = main;

        setSize(900, 600);

        inicializeTables();
        InicializejPanels();
        InicialiceColors();
        inicialiceImages();
        refresh();
    }

    private void inicializeTables() {
        jListUsuaris = new javax.swing.JList<>();

        DefaultTableModel dtmUserWorkouts = new DefaultTableModel();
        dtmUserWorkouts.setColumnIdentifiers(new String[]{"ID", "Fecha", "ID Usuario", "Comentarios"});
        jTableUsersWorkouts.setModel(dtmUserWorkouts);

        DefaultTableModel dtmjTableUsersExercicis = new DefaultTableModel();
        dtmjTableUsersExercicis.setColumnIdentifiers(new String[]{"ID", "Nom Exercici", "Descripció", "Demo foto"});
        jTableUsersExercicis.setModel(dtmjTableUsersExercicis);

    }

    private void refresh() {
        inicializeJLitesteners();
        getListMyUsers();
        getListMyUsersWorkouts(0);
        getListExercicisPerWorkout(0);
        getListAllMyUsersWorkouts();
        getListAllExercicisPerWorkout();
    }

    private void inicializeJLitesteners() {
        jListUsuaris.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListUsuarisValueChanged(evt);
            }
        });

        jTableUsersWorkouts.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jTableUsuarisWorkoutsValuechanged(evt);
            }
        });
    }

    // Metodo para obtener la lista de usuarios del instructor que ha iniciado sesión en la aplicación.
    public void getListMyUsers() {
        if (jListUsuaris == null) {
            jListUsuaris = new javax.swing.JList<>();
            jScrollPaneUsersList.setViewportView(jListUsuaris);
        }

        UsuariDAO usuariDAO = new UsuariDAOImpl();
        try {
            var usuaris = usuariDAO.getMyUsers(sessionUsername);
            DefaultListModel<Usuari> dfmu = new DefaultListModel<>();
            for (Usuari u : usuaris) {
                dfmu.addElement(u);
            }
            jListUsuaris.setModel(dfmu);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Metodo para obtener la lista de workouts del usuario que hemos marcado en la lista de usuarios.
    private void getListMyUsersWorkouts(int id) {
        WorkoutDAO workoutDAO = new WorkoutDAOImpl();
        try {
            var workouts = workoutDAO.getWorkoutsPerUser(id);
            DefaultTableModel dtm = null;
            dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{"ID", "Fecha", "ID Usuario", "Comentarios"});

            for (Workout u : workouts) {
                dtm.addRow(new Object[]{
                    u.getId(),
                    u.getForDate(),
                    u.getUserId(),
                    u.getComments()
                });
            }

            jTableUsersWorkouts.setModel(dtm);

            // Como cojemos los datos de las columnas, necesito que los datos estén impresos en ella, entonces para simplificarlo ocultamos la primera columna.
            jTableUsersWorkouts.getColumnModel().getColumn(0).setMinWidth(0);
            jTableUsersWorkouts.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableUsersWorkouts.getColumnModel().getColumn(0).setWidth(0);

            // Como cojemos los datos de las columnas, necesito que los datos estén impresos en ella, entonces para simplificarlo ocultamos la primera columna.
            jTableUsersWorkouts.getColumnModel().getColumn(2).setMinWidth(0);
            jTableUsersWorkouts.getColumnModel().getColumn(2).setMaxWidth(0);
            jTableUsersWorkouts.getColumnModel().getColumn(2).setWidth(0);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Metodo para obtener la lista de todos los workouts que existen en la BBDD.
    private void getListAllMyUsersWorkouts() {
        WorkoutDAO workoutDAO = new WorkoutDAOImpl();
        try {
            var workouts = workoutDAO.getAll();

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{"ID", "Fecha", "ID Usuario", "Comentarios"});

            for (Workout u : workouts) {
                dtm.addRow(new Object[]{
                    u.getId(),
                    u.getForDate(),
                    u.getUserId(),
                    u.getComments()
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Metodo para obtener la lista de Ejercicios del Workouto que hemos marcado en la lista de workouts.
    private void getListExercicisPerWorkout(int id) {
        ExerciciDAO exerciciDAO = new ExerciciDAOImpl();
        try {
            var exercicis = exerciciDAO.getExercicisPerWorkout(id);

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{"ID", "Nom Exercici", "Descripció", "Demo foto"});

            for (Exercici exercici : exercicis) {
                dtm.addRow(new Object[]{
                    exercici.getId(),
                    exercici.getNomExercici(),
                    exercici.getDescripcio(),
                    exercici.getDemoFoto()
                });
            }
            jTableUsersExercicis.setModel(dtm);

            // Como cojemos los datos de las columnas, necesito que los datos estén impresos en ella, entonces para simplificarlo ocultamos la primera columna.
            jTableUsersExercicis.getColumnModel().getColumn(0).setMinWidth(0);
            jTableUsersExercicis.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableUsersExercicis.getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Metodo para obtener la lista de todos los Ejercicios que existen en la BBDD.
    private void getListAllExercicisPerWorkout() {
        ExerciciDAO exerciciDAO = new ExerciciDAOImpl();
        try {
            var exercicis = exerciciDAO.getAll();

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{"ID", "Nom Exercici", "Descripció", "Demo foto"});

            for (Exercici exercici : exercicis) {
                dtm.addRow(new Object[]{
                    exercici.getId(),
                    exercici.getNomExercici(),
                    exercici.getDescripcio(),
                    exercici.getDemoFoto()
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void jListUsuarisValueChanged(javax.swing.event.ListSelectionEvent evt) {
        Usuari selectedUser = jListUsuaris.getSelectedValue();
        if (selectedUser != null) {
            int userId = selectedUser.getId();
            getListMyUsersWorkouts(userId);
        } else {
        }
    }

    private void jTableUsuarisWorkoutsValuechanged(javax.swing.event.ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            int selectedRow = jTableUsersWorkouts.getSelectedRow();

            if (selectedRow != -1) { // Verifica que haya una fila seleccionada
                int selectedWorkout = (int) jTableUsersWorkouts.getValueAt(selectedRow, 0);
                getListExercicisPerWorkout(selectedWorkout);
            } else {
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHomeUsers = new javax.swing.JPanel();
        jLabelUserList = new javax.swing.JLabel();
        jScrollPaneUsersList = new javax.swing.JScrollPane();
        jButtonAddUser = new javax.swing.JButton();
        jButtonChangeUser = new javax.swing.JButton();
        jButtonDeleteUser = new javax.swing.JButton();
        jLabelUsersWorkouts = new javax.swing.JLabel();
        jScrollPaneUsersWorkout = new javax.swing.JScrollPane();
        jTableUsersWorkouts = new javax.swing.JTable();
        jButtonAddWorkout = new javax.swing.JButton();
        jButtonChangeWorkout = new javax.swing.JButton();
        jButtonDeleteWorkout = new javax.swing.JButton();
        jLabelUsersExercicis = new javax.swing.JLabel();
        jScrollPaneUsersExercicis = new javax.swing.JScrollPane();
        jTableUsersExercicis = new javax.swing.JTable();
        jButtonAddExercice = new javax.swing.JButton();
        jButtonChangeExercice = new javax.swing.JButton();
        jButtonDeleteExercice = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setLayout(new java.awt.BorderLayout());

        jPanelHomeUsers.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelHomeUsers.setLayout(new java.awt.BorderLayout());

        jLabelUserList.setText("My users");
        jPanelHomeUsers.add(jLabelUserList, java.awt.BorderLayout.CENTER);

        jScrollPaneUsersList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelHomeUsers.add(jScrollPaneUsersList, java.awt.BorderLayout.PAGE_START);

        jButtonAddUser.setText("Add");
        jButtonAddUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddUserActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddUser, java.awt.BorderLayout.PAGE_END);

        jButtonChangeUser.setText("Change");
        jButtonChangeUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonChangeUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeUserActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonChangeUser, java.awt.BorderLayout.LINE_END);

        jButtonDeleteUser.setText("Delete");
        jButtonDeleteUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelHomeUsers.add(jButtonDeleteUser, java.awt.BorderLayout.LINE_START);

        jLabelUsersWorkouts.setText("User workouts");
        jPanelHomeUsers.add(jLabelUsersWorkouts, java.awt.BorderLayout.CENTER);

        jScrollPaneUsersWorkout.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableUsersWorkouts.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTableUsersWorkouts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneUsersWorkout.setViewportView(jTableUsersWorkouts);

        jPanelHomeUsers.add(jScrollPaneUsersWorkout, java.awt.BorderLayout.CENTER);

        jButtonAddWorkout.setText("Add Workout");
        jButtonAddWorkout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddWorkoutActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddWorkout, java.awt.BorderLayout.CENTER);

        jButtonChangeWorkout.setText("Change Workout");
        jButtonChangeWorkout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonChangeWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeWorkoutActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonChangeWorkout, java.awt.BorderLayout.CENTER);

        jButtonDeleteWorkout.setText("Delete Workout");
        jButtonDeleteWorkout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonDeleteWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteWorkoutActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonDeleteWorkout, java.awt.BorderLayout.CENTER);

        jLabelUsersExercicis.setText("User exercice");
        jPanelHomeUsers.add(jLabelUsersExercicis, java.awt.BorderLayout.CENTER);

        jScrollPaneUsersExercicis.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableUsersExercicis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneUsersExercicis.setViewportView(jTableUsersExercicis);

        jPanelHomeUsers.add(jScrollPaneUsersExercicis, java.awt.BorderLayout.CENTER);

        jButtonAddExercice.setText("Add exercice");
        jButtonAddExercice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddExercice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddExerciceActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddExercice, java.awt.BorderLayout.CENTER);

        jButtonChangeExercice.setText("Change exercice");
        jButtonChangeExercice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonChangeExercice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeExerciceActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonChangeExercice, java.awt.BorderLayout.CENTER);

        jButtonDeleteExercice.setText("Delete exercice");
        jButtonDeleteExercice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonDeleteExercice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteExerciceActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonDeleteExercice, java.awt.BorderLayout.CENTER);

        jButtonRefresh.setText("Refresh");
        jButtonRefresh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonRefresh, java.awt.BorderLayout.CENTER);

        add(jPanelHomeUsers, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonChangeExerciceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeExerciceActionPerformed
        main.UnderDevelopment();
    }//GEN-LAST:event_jButtonChangeExerciceActionPerformed

    private void jButtonAddExerciceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddExerciceActionPerformed
        main.showAddExercicisDialog();
    }//GEN-LAST:event_jButtonAddExerciceActionPerformed

    private void jButtonAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUserActionPerformed
        //Main main = new Main();
        main.showAddUsersDialog();
    }//GEN-LAST:event_jButtonAddUserActionPerformed

    private void jButtonChangeUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeUserActionPerformed
        main.UnderDevelopment();
    }//GEN-LAST:event_jButtonChangeUserActionPerformed

    private void jButtonAddWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddWorkoutActionPerformed
        main.showAddWorkoutsDialog();
    }//GEN-LAST:event_jButtonAddWorkoutActionPerformed

    private void jButtonDeleteExerciceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteExerciceActionPerformed
        int selectedRow = jTableUsersExercicis.getSelectedRow();
        ExerciciDAO exerciciDAO = new ExerciciDAOImpl();
        if (selectedRow != -1) { // Verifica que haya una fila seleccionada
            int selectedWorkout = (int) jTableUsersExercicis.getValueAt(selectedRow, 0);
            try {
                int result = exerciciDAO.deleteExercici(selectedWorkout);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el ejercicio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
        }
        refresh();
    }//GEN-LAST:event_jButtonDeleteExerciceActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        refresh();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonDeleteWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteWorkoutActionPerformed
        int selectedRow = jTableUsersWorkouts.getSelectedRow();
        WorkoutDAO workoutDAO = new WorkoutDAOImpl();
        if (selectedRow != -1) { // Verifica que haya una fila seleccionada
            int selectedWorkout = (int) jTableUsersWorkouts.getValueAt(selectedRow, 0);
            try {
                int result = workoutDAO.deleteWorkout(selectedWorkout);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: Delete asociated exercice first");
            }
        } else {
        }
        refresh();
    }//GEN-LAST:event_jButtonDeleteWorkoutActionPerformed

    private void jButtonChangeWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeWorkoutActionPerformed
        main.UnderDevelopment();
    }//GEN-LAST:event_jButtonChangeWorkoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddExercice;
    private javax.swing.JButton jButtonAddUser;
    private javax.swing.JButton jButtonAddWorkout;
    private javax.swing.JButton jButtonChangeExercice;
    private javax.swing.JButton jButtonChangeUser;
    private javax.swing.JButton jButtonChangeWorkout;
    private javax.swing.JButton jButtonDeleteExercice;
    private javax.swing.JButton jButtonDeleteUser;
    private javax.swing.JButton jButtonDeleteWorkout;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JLabel jLabelUserList;
    private javax.swing.JLabel jLabelUsersExercicis;
    private javax.swing.JLabel jLabelUsersWorkouts;
    private javax.swing.JPanel jPanelHomeUsers;
    private javax.swing.JScrollPane jScrollPaneUsersExercicis;
    private javax.swing.JScrollPane jScrollPaneUsersList;
    private javax.swing.JScrollPane jScrollPaneUsersWorkout;
    private javax.swing.JTable jTableUsersExercicis;
    private javax.swing.JTable jTableUsersWorkouts;
    // End of variables declaration//GEN-END:variables

    private void InicializejPanels() {
//        jPanelHomeUsers.setBounds(0, 0, 900, 600);
//        jLabelUserList.setBounds(10, 10, 150, 30);
//        jScrollPaneUsersList.setBounds(10, 40, 150, 510);
//        jListUsuaris.setBounds(10, 10, 130, 495);
//        jButtonAddUser.setBounds(10, 555, 50, 30);
//        jButtonChangeUser.setBounds(60, 555, 50, 30);
//        jButtonDeleteUser.setBounds(110, 555, 50, 30);
//
//        jLabelUsersWorkouts.setBounds(170, 10, 370, 30);
//        jScrollPaneUsersWorkout.setBounds(170, 40, 350, 210);
//        jTableUsersWorkouts.setBounds(5, 5, 350, 195);//
//        jButtonAddWorkout.setBounds(170, 256, 119, 30);
//        jButtonChangeWorkout.setBounds(284, 256, 119, 30);
//        jButtonDeleteWorkout.setBounds(404, 256, 117, 30);
//
//        jLabelUsersExercicis.setBounds(525, 10, 370, 30);
//        jScrollPaneUsersExercicis.setBounds(525, 40, 365, 210);
//        jTableUsersExercicis.setBounds(5, 5, 355, 180);//
//        jButtonAddExercice.setBounds(525, 256, 120, 30);
//        jButtonChangeExercice.setBounds(645, 256, 120, 30);
//        jButtonDeleteExercice.setBounds(765, 256, 125, 30);
//
//        jButtonRefresh.setBounds(828, 1, 70, 30);
        jPanelHomeUsers.setLayout(new MigLayout(
                "wrap 10, fill",
                "5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5",
                "5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5"
        ));
        this.add(jPanelHomeUsers, java.awt.BorderLayout.CENTER);
        jPanelHomeUsers.add(jLabelUserList, "cell 0 0, span 3");
        jPanelHomeUsers.add(jScrollPaneUsersList, "cell 0 1, span 3 8");
        jPanelHomeUsers.add(jButtonAddUser, "cell 0 10");
        jPanelHomeUsers.add(jButtonChangeUser, "cell 1 10");
        jPanelHomeUsers.add(jButtonDeleteUser, "cell 2 10");

        jPanelHomeUsers.add(jLabelUsersWorkouts, "cell 4 0, span 6");
        jPanelHomeUsers.add(jScrollPaneUsersWorkout, "cell 4 1, span 7 2");
        jPanelHomeUsers.add(jButtonAddWorkout, "cell 4 4, span 2");
        jPanelHomeUsers.add(jButtonChangeWorkout, "cell 6 4, span 2");
        jPanelHomeUsers.add(jButtonDeleteWorkout, "cell 8 4, span 3");

        jPanelHomeUsers.add(jLabelUsersExercicis, "cell 4 6, span 6");
        jPanelHomeUsers.add(jScrollPaneUsersExercicis, "cell 4 7, span 7 2");
        jPanelHomeUsers.add(jButtonAddExercice, "cell 4 10, span 2");
        jPanelHomeUsers.add(jButtonChangeExercice, "cell 6 10, span 2");
        jPanelHomeUsers.add(jButtonDeleteExercice, "cell 8 10, span 3");

        jPanelHomeUsers.add(jButtonRefresh, "cell 10 0");
//
//        jPanelHomeUsers.add(jScrollPaneUsersList, "span 3 4");
        jScrollPaneUsersList.setViewportView(jListUsuaris);
//        jPanelHomeUsers.add(jButtonAddUser, "dock south");
//        jPanelHomeUsers.add(jButtonChangeUser, "dock south");
//        jPanelHomeUsers.add(jButtonDeleteUser, "dock south");
//
//        
//        jPanelHomeUsers.add(jScrollPaneUsersWorkout, "span 5 2");
//        jScrollPaneUsersWorkout.setViewportView(jTableUsersWorkouts);
//        jPanelHomeUsers.add(jButtonAddWorkout);
//        jPanelHomeUsers.add(jButtonChangeWorkout);
//        jPanelHomeUsers.add(jButtonDeleteWorkout);
//
//        jPanelHomeUsers.add(jLabelUsersExercicis);
//        jPanelHomeUsers.add(jScrollPaneUsersExercicis);
//        jScrollPaneUsersExercicis.setViewportView(jTableUsersExercicis);
//        jPanelHomeUsers.add(jButtonAddExercice);
//        jPanelHomeUsers.add(jButtonChangeExercice);
//        jPanelHomeUsers.add(jButtonDeleteExercice);
    }

    private void InicialiceColors() {
        jPanelHomeUsers.setBackground(Color.white); // Cambiar el color de fondo
        List<AbstractButton> butons = new ArrayList<>();
        butons.add(jButtonAddUser);
        butons.add(jButtonChangeUser);
        butons.add(jButtonDeleteUser);
        butons.add(jButtonAddWorkout);
        butons.add(jButtonChangeWorkout);
        butons.add(jButtonDeleteWorkout);
        butons.add(jButtonAddExercice);
        butons.add(jButtonChangeExercice);
        butons.add(jButtonDeleteExercice);
        butons.add(jButtonRefresh);

        for (AbstractButton buton : butons) {
            buton.setBackground(Color.decode("#D98888")); // Cambiar el color de fondo
            buton.setForeground(Color.white); // Cambiar el color del texto (letra blanca)
            buton.setFont(jButtonAddUser.getFont().deriveFont(Font.BOLD, 14f));
            buton.setBorder(BorderFactory.createLineBorder(new Color(80, 0, 20), 2));
        }
    }

    private void inicialiceImages() {

    }
}
