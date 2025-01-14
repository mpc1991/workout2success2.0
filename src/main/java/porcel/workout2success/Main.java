// Macia Porcel Cifre
package porcel.workout2success;

import at.favre.lib.crypto.bcrypt.BCrypt;
import porcel.workout2success.views.JDialogConnectionStatus;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import porcel.workout2success.data.DataAccess;
import porcel.workout2success.data.UsuariDAO;
import porcel.workout2success.data.UsuariDAOImpl;
import porcel.workout2success.dto.Usuari;
import porcel.workout2success.views.JDialogHomeUsersAdd;
import porcel.workout2success.views.JDialogHomeUsersExerciciAdd;
import porcel.workout2success.views.JDialogHomeUsersWorkoutsAdd;
import porcel.workout2success.views.JPanelHome;
import porcel.workout2success.views.JPanelLogin;
import porcel.workout2success.views.JDialogAbout;
import porcel.workout2success.views.JDialogUnderDevelopment;

public class Main extends javax.swing.JFrame {

    //Guardamos la sesión del entrenador
    private static String sessionUsername;
    private static String sessionPassword;

    private JPanelLogin jPanelLogin;
    private JPanelHome jPanelHome;
    private JDialogAbout jDialogAbout;

    public Main() {
        initComponents();

        setSize(1280, 720);
        setResizable(true);

        jPanelHome = new JPanelHome(this);
        jPanelLogin = new JPanelLogin(this);
        showLoginPanel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBarHome = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemLogout = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuBarHome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(80, 0, 20), 3, true));

        jMenuFile.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenuFile.setText("File");

        jMenuItemLogout.setText("Logout...");
        jMenuItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogoutActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemLogout);

        jMenuBarHome.add(jMenuFile);

        jMenuEdit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenuEdit.setText("Edit");

        jMenuItemAbout.setText("About...");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemAbout);

        jMenuBarHome.add(jMenuEdit);

        setJMenuBar(jMenuBarHome);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        jDialogAbout = new JDialogAbout(this, true);
        setLocationRelativeTo(this); // Centramos el dialog
        jDialogAbout.setVisible(true); // Mostramos el dialog
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    public void showLoginPanel() {
        // Especificamos qué queremos ver cuando se abra la app
        jPanelLogin.setBounds(0, 0, 1280, 720);
        getContentPane().add(jPanelLogin);

        jPanelLogin.setVisible(true);
        jPanelHome.setVisible(false);
        jMenuBarHome.setVisible(false);
    }

    public void showHomePanel() {
        // Creamos nuevo panel para que almacene correctamente sessionUsername una vez iniciada la sesión.
        jPanelHome = null;
        jPanelHome = new JPanelHome(this);
        jPanelHome.InicializejPanels();
        jPanelHome.setBounds(0, 40, 1220, 600);

        getContentPane().add(jPanelHome);
        getContentPane().add(jMenuBarHome);

        jPanelLogin.setVisible(false);
        jPanelHome.setVisible(true);
        jMenuBarHome.setVisible(true);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public void showConnectionDialog(String username, String password) {
        setUsername(username);
        setPassword(password);

        JDialogConnectionStatus jDialogConnectionStatus;
        //jDialogConnectionStatus = new JDialogConnectionStatus(this, true, sessionUsername, sessionPassword);
        //jDialogConnectionStatus.setLocationRelativeTo(this); // Lo centramos en mitad de la pantalla
        //jDialogConnectionStatus.setVisible(true);
        
        try {
            Connection conn = DataAccess.getConnection();
            if (conn != null) {
                //jLabelConnectionResult.setText("Connection successfully to the database");
                UsuariDAO employeeDAO = new UsuariDAOImpl();
                try {
                    Usuari usuari = employeeDAO.get(username);
                    String dbHashedPassword = usuari.getPasswordHash();
                    var result = BCrypt.verifyer().verify(password.toCharArray(), dbHashedPassword);
                    if (result.verified) {
                        if (usuari.isInstructor()) {
                            //jLabelConnectionResult.setText("Login verifyed: welcome " + usuari.getNom());
                            //mainFrame.showHomePanel();
                            showHomePanel();
                        } else {
                            //jLabelConnectionResult.setText("This function is only for Instructors");
                            jPanelLogin.setJLabelLoginInfo("This function is only for Instructors");
                        }
                    } else {
                        //jLabelConnectionResult.setText("Error02: Incorrect username or password.");
                        jPanelLogin.setJLabelLoginInfo("Error02: Incorrect username or password.");
                    }
                } catch (Exception e) {
                    //jLabelConnectionResult.setText("Error01: Incorrect username or password.");
                    jPanelLogin.setJLabelLoginInfo("Error01: Incorrect username or password.");
                }
            } else {
                //jLabelConnectionResult.setText("Error: Can not access to the database.");
                jPanelLogin.setJLabelLoginInfo("Error: Can not access to the database.");
            }
        } catch (SQLException ex) {
            //jLabelConnectionResult.setText("Error: " + ex.getMessage());
            jPanelLogin.setJLabelLoginInfo("Error: " + ex.getMessage());
        }
    }

    public void showAddUsersDialog() {
        JDialogHomeUsersAdd jDialogAddUsers = new JDialogHomeUsersAdd(this, true);
        jDialogAddUsers.setLocationRelativeTo(this);
        jDialogAddUsers.setVisible(true);
    }

    public void showAddWorkoutsDialog() {
        JDialogHomeUsersWorkoutsAdd jDialogAddWorkouts = new JDialogHomeUsersWorkoutsAdd(this, true, sessionUsername);
        jDialogAddWorkouts.setLocationRelativeTo(this);
        jDialogAddWorkouts.setVisible(true);
    }

    public void showAddExercicisDialog() {
        JDialogHomeUsersExerciciAdd jDialogAddExercicis = new JDialogHomeUsersExerciciAdd(this, true, sessionUsername);
        jDialogAddExercicis.setLocationRelativeTo(this);
        jDialogAddExercicis.setVisible(true);
    }

    public void UnderDevelopment() {
        JDialogUnderDevelopment jDialogUnderDevelopment = new JDialogUnderDevelopment(this, true);
        jDialogUnderDevelopment.setLocationRelativeTo(this);
        jDialogUnderDevelopment.setVisible(true);
    }

    private void jMenuItemLogoutActionPerformed(ActionEvent evt) {
        // Indicamos qué queremos ver cuando el usuario cierre sesión.
        jPanelLogin.setVisible(true);
        jPanelHome.setVisible(false);
        jMenuBarHome.setVisible(false);
    }

    public static String getUsername() {
        return sessionUsername;
    }

    public static void setUsername(String username) {
        sessionUsername = username;
    }

    public static void setPassword(String password) {
        sessionPassword = password;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("CDE/Motif".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBarHome;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemLogout;
    // End of variables declaration//GEN-END:variables

}
