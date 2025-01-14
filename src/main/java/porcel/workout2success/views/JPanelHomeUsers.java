package porcel.workout2success.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import porcel.workout2success.Main;
import porcel.workout2success.dto.Exercici;
import porcel.workout2success.data.ExerciciDAO;
import porcel.workout2success.data.ExerciciDAOImpl;
import porcel.workout2success.data.UsuariDAOImpl;
import porcel.workout2success.dto.Usuari;
import porcel.workout2success.data.UsuariDAO;
import porcel.workout2success.dto.Workout;
import porcel.workout2success.data.WorkoutDAO;
import porcel.workout2success.data.WorkoutDAOImpl;

public class JPanelHomeUsers extends javax.swing.JPanel {

    private JPanelHome jPanelHome;
    private Main main;
    String sessionUsername = Main.getUsername();

    private javax.swing.JList<Usuari> jListUsuaris;

    public JPanelHomeUsers(JPanelHome jPanelHome, Main main) {
        initComponents();
        this.jPanelHome = jPanelHome;
        this.main = main;

        inicializeTables();
        InicializejPanels();
        InicialiceColors();
        inicialiceImages();
        
        //addPanelOptionsResizeListener(); No termina de funcionar, pdte revisar
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
        jPaneUsersList.setLayout(new MigLayout(
                "wrap 1, fill",
                "5[grow, fill]5",
                "5[grow, fill]5"
        ));

        jPaneUsersList.removeAll();

        UsuariDAO usuariDAO = new UsuariDAOImpl();
        try {
            var usuaris = usuariDAO.getMyUsers(sessionUsername);
            CustomButtonRenderer buttonRenderer = new CustomButtonRenderer();

            for (Usuari u : usuaris) {
                JButton userButton = new JButton(u.getNom());
                userButton.setActionCommand(String.valueOf(u.getId()));
                userButton.setFont(jButtonAddUser.getFont().deriveFont(Font.BOLD, 14f));

                // Estilo por defecto
                buttonRenderer.styleButton(userButton, false);
                // Agregar hover y click
                buttonRenderer.addHoverAndClickListener(userButton);
                // Acción al hacer clic
                userButton.addActionListener(e -> {
                    String userId = e.getActionCommand();
                    int userIdInt = Integer.parseInt(userId);
                    getListMyUsersWorkouts(userIdInt);
                });

                // Agregar el botón al JPanel con MigLayout
                jPaneUsersList.add(userButton, "grow, push");
            }

            jPaneUsersList.revalidate();
            jPaneUsersList.repaint();
            jScrollPaneUsersList.revalidate();
            jScrollPaneUsersList.repaint();

            jScrollPaneUsersList.setViewportView(jPaneUsersList);
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
            // Aplicar el CustomTableCellRenderer para la tabla
            jTableUsersWorkouts.setDefaultRenderer(Object.class, new CustomTableCellRenderer(jTableUsersWorkouts, Color.decode("#D98888"), Color.decode("#800020")));

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
            jTableUsersExercicis.setDefaultRenderer(Object.class, new CustomTableCellRenderer(jTableUsersExercicis, Color.decode("#D98888"), Color.decode("#800020")));

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
        jPaneUsersList = new javax.swing.JPanel();
        jButtonAddUser = new javax.swing.JButton();
        jButtonChangeUser = new javax.swing.JButton();
        jButtonDeleteUser = new javax.swing.JButton();
        jLabelUsersWorkouts = new javax.swing.JLabel();
        jButtonAddWorkout = new javax.swing.JButton();
        jButtonChangeWorkout = new javax.swing.JButton();
        jButtonDeleteWorkout = new javax.swing.JButton();
        jLabelUsersExercicis = new javax.swing.JLabel();
        jButtonAddExercice = new javax.swing.JButton();
        jScrollPaneUsersExercicis = new javax.swing.JScrollPane();
        jTableUsersExercicis = new javax.swing.JTable();
        jButtonChangeExercice = new javax.swing.JButton();
        jButtonDeleteExercice = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jScrollPaneUsersWorkout = new javax.swing.JScrollPane();
        jTableUsersWorkouts = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jLabelUserList.setText("My users");
        jPanelHomeUsers.add(jLabelUserList);

        jScrollPaneUsersList.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(80, 0, 20), 2, true));
        jScrollPaneUsersList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jScrollPaneUsersListMouseEntered(evt);
            }
        });
        jScrollPaneUsersList.setViewportView(jPaneUsersList);

        jPanelHomeUsers.add(jScrollPaneUsersList);

        jButtonAddUser.setText("Add");
        jButtonAddUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddUserActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddUser);

        jButtonChangeUser.setText("Change");
        jButtonChangeUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonChangeUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeUserActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonChangeUser);

        jButtonDeleteUser.setText("Delete");
        jButtonDeleteUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelHomeUsers.add(jButtonDeleteUser);

        jLabelUsersWorkouts.setText("User workouts");
        jPanelHomeUsers.add(jLabelUsersWorkouts);

        jButtonAddWorkout.setText("Add Workout");
        jButtonAddWorkout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddWorkoutActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddWorkout);

        jButtonChangeWorkout.setText("Change Workout");
        jButtonChangeWorkout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonChangeWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeWorkoutActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonChangeWorkout);

        jButtonDeleteWorkout.setText("Delete Workout");
        jButtonDeleteWorkout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonDeleteWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteWorkoutActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonDeleteWorkout);

        jLabelUsersExercicis.setText("User exercice");
        jPanelHomeUsers.add(jLabelUsersExercicis);

        jButtonAddExercice.setText("Add exercice");
        jButtonAddExercice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddExercice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddExerciceActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddExercice);

        jScrollPaneUsersExercicis.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneUsersExercicis.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(80, 0, 20), 2, true));
        jScrollPaneUsersExercicis.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPaneUsersExercicis.setMinimumSize(new java.awt.Dimension(100, 100));
        jScrollPaneUsersExercicis.setPreferredSize(new java.awt.Dimension(250, 250));

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
        jTableUsersExercicis.setPreferredSize(null);
        jTableUsersExercicis.setRequestFocusEnabled(false);
        jScrollPaneUsersExercicis.setViewportView(jTableUsersExercicis);

        jPanelHomeUsers.add(jScrollPaneUsersExercicis);

        jButtonChangeExercice.setText("Change exercice");
        jButtonChangeExercice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonChangeExercice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeExerciceActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonChangeExercice);

        jButtonDeleteExercice.setText("Delete exercice");
        jButtonDeleteExercice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonDeleteExercice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteExerciceActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonDeleteExercice);

        jButtonRefresh.setText("Refresh");
        jButtonRefresh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonRefresh);

        jScrollPaneUsersWorkout.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(80, 0, 20), 2, true));
        jScrollPaneUsersWorkout.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPaneUsersWorkout.setPreferredSize(new java.awt.Dimension(550, 550));

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
        jTableUsersWorkouts.setPreferredSize(null);
        jScrollPaneUsersWorkout.setViewportView(jTableUsersWorkouts);

        jPanelHomeUsers.add(jScrollPaneUsersWorkout);

        add(jPanelHomeUsers, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonChangeExerciceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeExerciceActionPerformed
        main.UnderDevelopment();
    }//GEN-LAST:event_jButtonChangeExerciceActionPerformed

    private void jButtonAddExerciceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddExerciceActionPerformed
        main.showAddExercicisDialog();
    }//GEN-LAST:event_jButtonAddExerciceActionPerformed

    private void jButtonAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUserActionPerformed
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

    private void jScrollPaneUsersListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPaneUsersListMouseEntered
        jListUsuaris.setSelectionBackground(Color.decode("#D98888"));
        jListUsuaris.setSelectionForeground(Color.WHITE);
    }//GEN-LAST:event_jScrollPaneUsersListMouseEntered


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
    private javax.swing.JPanel jPaneUsersList;
    private javax.swing.JPanel jPanelHomeUsers;
    private javax.swing.JScrollPane jScrollPaneUsersExercicis;
    private javax.swing.JScrollPane jScrollPaneUsersList;
    private javax.swing.JScrollPane jScrollPaneUsersWorkout;
    private javax.swing.JTable jTableUsersExercicis;
    private javax.swing.JTable jTableUsersWorkouts;
    // End of variables declaration//GEN-END:variables

    private void InicializejPanels() {
        jPanelHomeUsers.setLayout(new MigLayout(
                "wrap 10, fill",
                "5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5",
                 "5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5"
        ));
        this.add(jPanelHomeUsers, java.awt.BorderLayout.CENTER);
        jPanelHomeUsers.add(jLabelUserList, "cell 0 0, span 3");
        jPanelHomeUsers.add(jScrollPaneUsersList, "cell 0 1, span 3 8");
        jPanelHomeUsers.add(jButtonAddUser, "cell 0 9");
        jPanelHomeUsers.add(jButtonChangeUser, "cell 1 9");
        jPanelHomeUsers.add(jButtonDeleteUser, "cell 2 9");

        jPanelHomeUsers.add(jLabelUsersWorkouts, "cell 4 0, span 6 0");
        jPanelHomeUsers.add(jScrollPaneUsersWorkout, "cell 4 1, span 7 3");
        jPanelHomeUsers.add(jButtonAddWorkout, "cell 4 4, span 3 0");
        jPanelHomeUsers.add(jButtonChangeWorkout, "cell 7 4, span 2 0");
        jPanelHomeUsers.add(jButtonDeleteWorkout, "cell 9 4, span 2 0");

        jPanelHomeUsers.add(jLabelUsersExercicis, "cell 4 5, span 7 0");
        jPanelHomeUsers.add(jScrollPaneUsersExercicis, "cell 4 6, span 7 3");
        jPanelHomeUsers.add(jButtonAddExercice, "cell 4 9, span 3 0");
        jPanelHomeUsers.add(jButtonChangeExercice, "cell 7 9, span 2 0");
        jPanelHomeUsers.add(jButtonDeleteExercice, "cell 9 9, span 2 0");

        jPanelHomeUsers.add(jButtonRefresh, "cell 10 0");
        jScrollPaneUsersList.setViewportView(jListUsuaris);

    }

    private void InicialiceColors() {
        jPanelHomeUsers.setBackground(Color.white);
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
            buton.setBackground(Color.decode("#D98888"));
            buton.setForeground(Color.white);
            buton.setFont(jButtonAddUser.getFont().deriveFont(Font.BOLD, 14f));
            buton.setBorder(BorderFactory.createLineBorder(new Color(80, 0, 20), 2));
            
            buton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buton.setBackground(Color.decode("#800020"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buton.setBackground(Color.decode("#D98888"));
            }
        });
        }

        jPaneUsersList.setBackground(Color.white);
        jTableUsersExercicis.setBackground(Color.white);
    }

    private void inicialiceImages() {

    }

    // Los siguientes métodos están adaptados con MUCHA ayuda de chatGPT pero necesitaba que esto existiese para el look & feel.
    public class CustomListRenderer extends DefaultListCellRenderer {

        private int hoverIndex = -1;

        public CustomListRenderer(JList<?> list) {
            list.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    int index = list.locationToIndex(e.getPoint());
                    if (hoverIndex != index) {
                        hoverIndex = index;
                        list.repaint(); 
                    }
                }
            });

            list.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    hoverIndex = -1;
                    list.repaint(); // Restablece el estilo al salir del área de la lista
                }
            });
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (isSelected) {
                component.setBackground(Color.decode("#800020"));
                component.setForeground(Color.WHITE);
            } else if (index == hoverIndex) {
                component.setBackground(Color.decode("#D98888"));
                component.setForeground(Color.BLACK);
            } else {
                component.setBackground(Color.WHITE);
                component.setForeground(Color.BLACK);
            }
            return component;
        }
    }

    public class CustomTableCellRenderer extends DefaultTableCellRenderer {

        private int hoverIndex = -1;
        private Color hoverColor = Color.decode("#D98888"); // Color por defecto para hover
        private Color selectedColor = Color.decode("#800020"); // Color por defecto para selección

        public CustomTableCellRenderer(JTable table, Color hoverColor, Color selectedColor) {
            this.hoverColor = hoverColor != null ? hoverColor : this.hoverColor;
            this.selectedColor = selectedColor != null ? selectedColor : this.selectedColor;

            table.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (hoverIndex != row) {
                        hoverIndex = row;
                        table.repaint(); // Redibuja la tabla
                    }
                }
            });

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    hoverIndex = -1;
                    table.repaint(); // Restablece el estilo al salir del área de la tabla
                }
            });
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                component.setBackground(selectedColor);
                component.setForeground(Color.WHITE);
            } else if (row == hoverIndex) {
                component.setBackground(hoverColor);
                component.setForeground(Color.BLACK);
            } else {
                component.setBackground(Color.WHITE);
                component.setForeground(Color.BLACK);
            }

            return component;
        }
    }

    public class CustomButtonRenderer {

        private int hoverIndex = -1;
        private Color hoverColor = Color.decode("#D98888"); 
        private Color selectedColor = Color.decode("#800020"); 
        private JButton selectedButton = null; 

        public CustomButtonRenderer() {
            // No se necesita panel porque cada botón maneja su propio estado.
        }

        // Método que configura los botones
        public void styleButton(JButton button, boolean isSelected) {
            if (isSelected) {
                button.setBackground(selectedColor);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
        }

        // Agregar listeners para el hover y click
        public void addHoverAndClickListener(JButton button) {
            button.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    // Solo cambiar el color si no está seleccionado
                    if (selectedButton != button) {
                        button.setBackground(hoverColor); // Cambiar color de fondo al pasar el ratón
                    }
                }
            });

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    // Si el botón no está seleccionado, restaurar el color original
                    if (selectedButton != button) {
                        button.setBackground(Color.WHITE);
                        button.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // Si el botón no está seleccionado, seleccionarlo
                    if (selectedButton != button) {
                        // Restaurar el color original del botón seleccionado anterior
                        if (selectedButton != null) {
                            selectedButton.setBackground(Color.WHITE);
                            selectedButton.setForeground(Color.BLACK);
                        }

                        // Marcar este botón como seleccionado
                        selectedButton = button;
                        button.setBackground(selectedColor);
                        button.setForeground(Color.WHITE);
                    }
                }
            });
        }
    }
    
    // Listener para que los botones se pongan en una sola columna si el ancho de jScrollPaneUsersList baja por debajo de cierta cantidad
    private void addPanelOptionsResizeListener() {
        jPaneUsersList.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                MigLayout layout = (MigLayout) jPaneUsersList.getLayout();
                if (jScrollPaneUsersList.getWidth() < 286) {
                    // Configurar restricciones globales
                    layout.setLayoutConstraints("wrap 1, fill");
                    layout.setColumnConstraints("5[grow, fill]5");
                    layout.setRowConstraints("5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5");
                } else {
                    layout.setLayoutConstraints("wrap 2, fill");
                    layout.setColumnConstraints("5[grow, fill]5[grow, fill]5");
                    layout.setRowConstraints("5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5");
                }
                jPaneUsersList.revalidate();
                jPaneUsersList.repaint();
            }
        });
    }
}
