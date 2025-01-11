package porcel.workout2success.views;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import porcel.workout2success.Main;
import net.miginfocom.swing.MigLayout;

public class JPanelHome extends javax.swing.JPanel {

    private Main jFrameMain;
    private JPanelHomeUsers jPanelUsers;

    String sessionUsername = Main.getUsername();

    public JPanelHome(Main jFrameMain) {
        initComponents();
        this.jFrameMain = jFrameMain;
        jPanelUsers = new JPanelHomeUsers(this, jFrameMain);
        setSize(1280, 684); //No es necesario al indicarselo desde main?
        // MigLayout
        //this.setBorder(BorderFactory.createEtchedBorder());
        jPanelOptions.setLayout(new MigLayout("wrap 2, fill", "5[grow, fill]1[grow,fill]5", "5[grow, fill]1[grow, fill]5[grow, fill]1[grow, fill]5"));

        //
        InicializejPanels();
        addPanelOptionsResizeListener();
    }

    public void InicializejPanels() {
//        jPanelOptions.setBounds(1, 1, 300, 600);
        jLabelQuestion.setHorizontalAlignment(JTextField.CENTER);
//        jButtonShowMyUsers.setBounds(40, 90, 210, 30);
//        jButtonHideMyUsers.setBounds(40, 130, 210, 30);
//        jLabelUserSession.setBounds(10, 570, 270, 30);
        jLabelUserSession.setHorizontalAlignment(JTextField.CENTER);
//        jPanelShowInfo.setBounds(350, 0, 900, 600);
        jSplitPane.setDividerLocation(300); // Ajusta la posición del divisor
        jSplitPane.setResizeWeight(0.2);   // Porcentaje del espacio para el lado izquierdo

        this.add(jSplitPane, java.awt.BorderLayout.CENTER);
        jSplitPane.setLeftComponent(jPanelOptions);
        jPanelOptions.add(jLabelQuestion, "dock north");
        jPanelOptions.add(jButtonShowMyUsers);
        jPanelOptions.add(jButtonHideMyUsers);
        jPanelOptions.add(jLabelUserSession, "dock south");
        //this.add(jPanelShowInfo);
        jSplitPane.setRightComponent(jPanelShowInfo);
        jPanelShowInfo.add(jPanelUsers);

        jPanelUsers.setVisible(false);
        jPanelShowInfo.setVisible(true);
        jLabelUserSession.setVisible(true);

        jLabelUserSession.setText("Logged in session: " + sessionUsername);
    }
    // Método para agregar el listener

    private void addPanelOptionsResizeListener() {
        jPanelOptions.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                MigLayout layout = (MigLayout) jPanelOptions.getLayout();
                if (jPanelOptions.getWidth() < 200) {
                    // Configurar restricciones globales
                    layout.setLayoutConstraints("wrap 1, fill");

// Configurar restricciones de columnas
                    layout.setColumnConstraints("5[grow, fill]5");

// Configurar restricciones de filas
                    layout.setRowConstraints("5[grow, fill]1[grow, fill]1[grow, fill]1[grow, fill]5");
                } else {
                    layout.setLayoutConstraints("wrap 2, fill");
                }
                jPanelOptions.revalidate();
                jPanelOptions.repaint();
            }
        });
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane = new javax.swing.JSplitPane();
        jPanelOptions = new javax.swing.JPanel();
        jLabelQuestion = new javax.swing.JLabel();
        jButtonHideMyUsers = new javax.swing.JButton();
        jLabelUserSession = new javax.swing.JLabel();
        jButtonShowMyUsers = new javax.swing.JButton();
        jPanelShowInfo = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanelOptions.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelQuestion.setText("What are we gonna do today?");

        jButtonHideMyUsers.setText("Hide users list");
        jButtonHideMyUsers.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonHideMyUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHideMyUsersActionPerformed(evt);
            }
        });

        jButtonShowMyUsers.setText("Show users list");
        jButtonShowMyUsers.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonShowMyUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowMyUsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelOptionsLayout = new javax.swing.GroupLayout(jPanelOptions);
        jPanelOptions.setLayout(jPanelOptionsLayout);
        jPanelOptionsLayout.setHorizontalGroup(
            jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOptionsLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonShowMyUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonHideMyUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUserSession, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanelOptionsLayout.setVerticalGroup(
            jPanelOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOptionsLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabelQuestion)
                .addGap(14, 14, 14)
                .addComponent(jButtonShowMyUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButtonHideMyUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(340, 340, 340)
                .addComponent(jLabelUserSession, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane.setLeftComponent(jPanelOptions);

        jPanelShowInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelShowInfoLayout = new javax.swing.GroupLayout(jPanelShowInfo);
        jPanelShowInfo.setLayout(jPanelShowInfoLayout);
        jPanelShowInfoLayout.setHorizontalGroup(
            jPanelShowInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        jPanelShowInfoLayout.setVerticalGroup(
            jPanelShowInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 708, Short.MAX_VALUE)
        );

        jSplitPane.setRightComponent(jPanelShowInfo);

        add(jSplitPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonHideMyUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHideMyUsersActionPerformed
        jPanelUsers.setVisible(false);
    }//GEN-LAST:event_jButtonHideMyUsersActionPerformed

    private void jButtonShowMyUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowMyUsersActionPerformed
        jPanelUsers.setVisible(true);

    }//GEN-LAST:event_jButtonShowMyUsersActionPerformed

    private javax.swing.JPanel jMenuBar1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHideMyUsers;
    private javax.swing.JButton jButtonShowMyUsers;
    private javax.swing.JLabel jLabelQuestion;
    private javax.swing.JLabel jLabelUserSession;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelShowInfo;
    private javax.swing.JSplitPane jSplitPane;
    // End of variables declaration//GEN-END:variables
}
