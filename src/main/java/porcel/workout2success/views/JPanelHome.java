package porcel.workout2success.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import porcel.workout2success.Main;
import net.miginfocom.swing.MigLayout;
import porcel.workout2success.listeners.MyCalendarListeners;
import porcel.workout2success.listeners.hasWorkoutsEventArgs;

public class JPanelHome extends javax.swing.JPanel {

    private Main jFrameMain;
    private JPanelHomeUsers jPanelUsers;
    private JPanelCalendar jPanelCalendar;

    String sessionUsername = Main.getUsername();

    public JPanelHome(Main jFrameMain) {
        initComponents();
        this.jFrameMain = jFrameMain;
        jPanelUsers = new JPanelHomeUsers(this, jFrameMain);
        jPanelCalendar = new JPanelCalendar(this, jFrameMain);

        this.setSize(1280, 720); //No es necesario al indicarselo desde main?
        
        InicializejPanels();
        InicialiceColors();
        inicialiceImages();
        addPanelOptionsResizeListener();
    }

    public void InicializejPanels() {
        jPanelOptions.setLayout(new MigLayout("wrap 2, fill", "5[fill]5[fill]5", "5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5"));
        //jPanelShowInfo.setLayout(new MigLayout("wrap 1, fill", "5[fill]5", "5[grow, fill]5"));

        jLabelQuestion.setHorizontalAlignment(JTextField.CENTER);
        jLabelUserSession.setHorizontalAlignment(JTextField.CENTER);
        jSplitPane.setDividerLocation(300); // Ajusta la posición del divisor
        jSplitPane.setResizeWeight(0.2);   // Porcentaje del espacio para el lado izquierdo
        jSplitPane.setContinuousLayout(true);

        this.add(jSplitPane, java.awt.BorderLayout.CENTER);
        jSplitPane.setLeftComponent(jPanelOptions);
        jPanelOptions.add(jLabelQuestion, "dock north");
        jPanelOptions.add(jButtonShowMyUsers);
        jPanelOptions.add(jButtonCalendar);
        jPanelOptions.add(jButtonOptions);
        jPanelOptions.add(jLabelUserSession, "dock south");
        jSplitPane.setRightComponent(jPanelShowInfo);
        jPanelShowInfo.add(jPanelUsers, "cell 0 0");
        jPanelShowInfo.add(jPanelCalendar, "cell 0 0");

        jPanelUsers.setVisible(true);
        jPanelCalendar.setVisible(false);
        jPanelShowInfo.setVisible(true);
        jLabelUserSession.setVisible(true);
        jLabelUserSession.setText("Welcome: " + sessionUsername);
        jPanelCalendar.initializeCalendar();
    }

    private void InicialiceColors() {
        this.setBackground(Color.black);
        jPanelOptions.setBackground(Color.white);
        jPanelShowInfo.setBackground(Color.white);
        jPanelUsers.setBackground(Color.decode("#FFFFFF"));
        jPanelCalendar.setBackground(Color.decode("#FFFFFF"));

        jButtonShowMyUsers.setBackground(Color.decode("#800020")); // Cambiar el color de fondo
        jButtonShowMyUsers.setForeground(Color.white); // Cambiar el color del texto (letra blanca)
        jButtonShowMyUsers.setFont(jButtonShowMyUsers.getFont().deriveFont(Font.BOLD, 14f));

        jButtonCalendar.setBackground(Color.decode("#D98888")); // Cambiar el color de fondo
        jButtonCalendar.setForeground(Color.white); // Cambiar el color del texto (letra blanca)
        jButtonCalendar.setFont(jButtonShowMyUsers.getFont().deriveFont(Font.BOLD, 14f)); // Negrita, tamaño 14

        jButtonOptions.setBackground(Color.decode("#D98888")); // Cambiar el color de fondo
        jButtonOptions.setForeground(Color.white); // Cambiar el color del texto (letra blanca)
        jButtonOptions.setFont(jButtonShowMyUsers.getFont().deriveFont(Font.BOLD, 14f)); // Ne
    }

    private void inicialiceImages() {
        jButtonShowMyUsers.setHorizontalTextPosition(SwingConstants.CENTER);
        jButtonShowMyUsers.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButtonCalendar.setHorizontalTextPosition(SwingConstants.CENTER);
        jButtonCalendar.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButtonOptions.setHorizontalTextPosition(SwingConstants.CENTER);
        jButtonOptions.setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    // Listener para alinear los iconos si jPanelOptions se hace pequeño
    private void addPanelOptionsResizeListener() {
        jPanelOptions.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                MigLayout layout = (MigLayout) jPanelOptions.getLayout();
                if (jPanelOptions.getWidth() < 230) {
                    // Configurar restricciones globales
                    layout.setLayoutConstraints("wrap 1, fill");
                    layout.setColumnConstraints("5[grow, fill]5");
                    layout.setRowConstraints("5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5");
                } else {
                    layout.setLayoutConstraints("wrap 2, fill");
                    layout.setColumnConstraints("5[grow, fill]5[grow, fill]5");
                    layout.setRowConstraints("5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5[grow, fill]5");
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
        jButtonCalendar = new javax.swing.JButton();
        jLabelUserSession = new javax.swing.JLabel();
        jButtonShowMyUsers = new javax.swing.JButton();
        jButtonOptions = new javax.swing.JButton();
        jPanelShowInfo = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanelOptions.setBackground(new java.awt.Color(102, 102, 102));
        jPanelOptions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(80, 0, 2), 3, true));
        jPanelOptions.setLayout(new java.awt.GridLayout(1, 0));

        jLabelQuestion.setText("What are we gonna do today?");
        jPanelOptions.add(jLabelQuestion);

        jButtonCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/weekly-calendar-outline-event-interface-symbol_icon-icons.com_73108.png"))); // NOI18N
        jButtonCalendar.setText("Calendar");
        jButtonCalendar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(80, 0, 20), 2, true));
        jButtonCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonCalendarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonCalendarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonCalendarMousePressed(evt);
            }
        });
        jButtonCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalendarActionPerformed(evt);
            }
        });
        jPanelOptions.add(jButtonCalendar);
        jPanelOptions.add(jLabelUserSession);

        jButtonShowMyUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user-outline-male-symbol-of-interface_icon-icons.com_73113.png"))); // NOI18N
        jButtonShowMyUsers.setText("Users");
        jButtonShowMyUsers.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(80, 0, 20), 2, true));
        jButtonShowMyUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonShowMyUsersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonShowMyUsersMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonShowMyUsersMousePressed(evt);
            }
        });
        jButtonShowMyUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowMyUsersActionPerformed(evt);
            }
        });
        jPanelOptions.add(jButtonShowMyUsers);

        jButtonOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/gear-outlined-symbol_icon-icons.com_73242.png"))); // NOI18N
        jButtonOptions.setText("Options");
        jButtonOptions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(80, 0, 20), 2, true));
        jButtonOptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonOptionsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonOptionsMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonOptionsMousePressed(evt);
            }
        });
        jButtonOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOptionsActionPerformed(evt);
            }
        });
        jPanelOptions.add(jButtonOptions);

        jSplitPane.setLeftComponent(jPanelOptions);

        jPanelShowInfo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(80, 0, 20), 3, true));
        jPanelShowInfo.setLayout(new javax.swing.BoxLayout(jPanelShowInfo, javax.swing.BoxLayout.LINE_AXIS));
        jSplitPane.setRightComponent(jPanelShowInfo);

        add(jSplitPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalendarActionPerformed
        jPanelUsers.setVisible(false);

//        if (jPanelCalendar != null) {
//            this.remove(jPanelCalendar); // Eliminar del JFrame
//        }
//
//        jPanelCalendar = new JPanelCalendar(this, jFrameMain);
        jPanelCalendar.setColor(Color.RED);

        //jPanelShowInfo.add(jPanelCalendar);
//
//        jPanelCalendar.initializeCalendar();
        jPanelCalendar.setListeners(new MyCalendarListeners() {
            @Override
            public void hasWorkoutListener(hasWorkoutsEventArgs eventArg) {
                //jTextArea.setText(eventArg.toString());
            }
        });

        jPanelCalendar.setVisible(true);
        this.revalidate();
        this.repaint();

    }//GEN-LAST:event_jButtonCalendarActionPerformed

    private void jButtonShowMyUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowMyUsersActionPerformed
        jPanelUsers.setVisible(true);
        jPanelCalendar.setVisible(false);
        jPanelUsers.revalidate();
        jPanelUsers.repaint();
        

    }//GEN-LAST:event_jButtonShowMyUsersActionPerformed

    private void jButtonShowMyUsersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonShowMyUsersMouseEntered
        //jButtonShowMyUsers.setBackground(Color.decode("#800020")); // Cambiar el color de fondo        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonShowMyUsersMouseEntered

    private void jButtonShowMyUsersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonShowMyUsersMouseExited
        //jButtonShowMyUsers.setBackground(Color.decode("#FFFFFF"));
    }//GEN-LAST:event_jButtonShowMyUsersMouseExited

    private void jButtonCalendarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCalendarMouseEntered
        //jButtonHideMyUsers.setBackground(Color.decode("#800020")); 
    }//GEN-LAST:event_jButtonCalendarMouseEntered

    private void jButtonCalendarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCalendarMouseExited
        //jButtonHideMyUsers.setBackground(Color.decode("#FFFFFF"));
    }//GEN-LAST:event_jButtonCalendarMouseExited

    private void jButtonShowMyUsersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonShowMyUsersMousePressed
        jButtonShowMyUsers.setBackground(Color.decode("#800020"));
        jButtonShowMyUsers.setForeground(Color.decode("#FFFFFF"));
        jButtonCalendar.setBackground(Color.decode("#D98888"));
        jButtonOptions.setBackground(Color.decode("#D98888"));
    }//GEN-LAST:event_jButtonShowMyUsersMousePressed

    private void jButtonCalendarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCalendarMousePressed
        jButtonCalendar.setBackground(Color.decode("#800020"));
        jButtonCalendar.setForeground(Color.decode("#FFFFFF"));
        jButtonShowMyUsers.setBackground(Color.decode("#D98888"));
        jButtonOptions.setBackground(Color.decode("#D98888"));
    }//GEN-LAST:event_jButtonCalendarMousePressed

    private void jButtonOptionsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonOptionsMouseEntered

    }//GEN-LAST:event_jButtonOptionsMouseEntered

    private void jButtonOptionsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonOptionsMouseExited

    }//GEN-LAST:event_jButtonOptionsMouseExited

    private void jButtonOptionsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonOptionsMousePressed
        jButtonOptions.setBackground(Color.decode("#800020"));
        jButtonOptions.setForeground(Color.decode("#FFFFFF"));
        jButtonShowMyUsers.setBackground(Color.decode("#D98888"));
        jButtonCalendar.setBackground(Color.decode("#D98888"));
    }//GEN-LAST:event_jButtonOptionsMousePressed

    private void jButtonOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOptionsActionPerformed
        jPanelUsers.setVisible(false);
        jPanelCalendar.setVisible(false);
    }//GEN-LAST:event_jButtonOptionsActionPerformed

    private javax.swing.JPanel jMenuBar1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCalendar;
    private javax.swing.JButton jButtonOptions;
    private javax.swing.JButton jButtonShowMyUsers;
    private javax.swing.JLabel jLabelQuestion;
    private javax.swing.JLabel jLabelUserSession;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelShowInfo;
    private javax.swing.JSplitPane jSplitPane;
    // End of variables declaration//GEN-END:variables
}
