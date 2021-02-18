/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amvrutinario;

import amvrutinario.dao.ClimaIndicadorDao;
import amvrutinario.dao.IndicadorDao;
import amvrutinario.dao.ProyectoDao;
import amvrutinario.dto.ProyectoDto;
import java.awt.BorderLayout;
import java.net.URL;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class AMVRutinario extends javax.swing.JFrame {

    /**
     * Creates new form MainLayout
     */
    public AMVRutinario() {
        bienvenida = new Bienvenida();
        this.add(bienvenida, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        asignarIcono();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuInicio = new javax.swing.JMenuItem();
        menuProyecto = new javax.swing.JMenuItem();
        menuGuardar = new javax.swing.JMenuItem();
        menuImprimir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AMV Rutinario");
        setMinimumSize(new java.awt.Dimension(800, 450));
        setResizable(false);

        jMenu1.setText("Inicio");

        menuInicio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        menuInicio.setText("Inicio");
        menuInicio.setEnabled(false);
        menuInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInicioActionPerformed(evt);
            }
        });
        jMenu1.add(menuInicio);

        menuProyecto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuProyecto.setText("Nuevo Proyecto");
        menuProyecto.setToolTipText("");
        menuProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProyectoActionPerformed(evt);
            }
        });
        jMenu1.add(menuProyecto);

        menuGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        menuGuardar.setText("Guardar");
        menuGuardar.setEnabled(false);
        menuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(menuGuardar);

        menuImprimir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        menuImprimir.setText("Imprimir");
        menuImprimir.setEnabled(false);
        menuImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuImprimirActionPerformed(evt);
            }
        });
        jMenu1.add(menuImprimir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Soporte Tecnico");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Contenido");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuImprimirActionPerformed
        imprimir();
    }//GEN-LAST:event_menuImprimirActionPerformed

    private void menuProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProyectoActionPerformed
        crearProyecto = new CrearProyecto();
        this.getContentPane().remove(bienvenida);
        this.add(crearProyecto, BorderLayout.CENTER);
        this.pack();

        menuGuardar.setEnabled(true);
        menuInicio.setEnabled(true);
        menuImprimir.setEnabled(false);
        menuProyecto.setEnabled(false);
    }//GEN-LAST:event_menuProyectoActionPerformed

    private void menuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarActionPerformed
        ProyectoDto proyectoDto;
        proyectoDto = crearProyecto.getProyecto();

        ProyectoDao proyectoDao = new ProyectoDao();
        IndicadorDao indicadorDao = new IndicadorDao();
        ClimaIndicadorDao climaIndicadorDao = new ClimaIndicadorDao();

        int proyectoId = proyectoDao.insert(proyectoDto.getProyecto());
        if (Objects.isNull(proyectoId)) {
            JOptionPane.showMessageDialog(this, "Error al guardar", "Error inexperado", JOptionPane.ERROR_MESSAGE);
        } else {
            proyectoDto.getIndicadores().forEach(indicador -> {
                int indicadorId = indicadorDao.insert(indicador, proyectoId);
                indicador.getClimas().forEach(clima -> {
                    climaIndicadorDao.insert(clima, indicadorId);
                });
            });
            JOptionPane.showMessageDialog(this, "Proyecto Almacenado", "Proyecto almacenado", JOptionPane.INFORMATION_MESSAGE);
            imprimir();

        }
    }//GEN-LAST:event_menuGuardarActionPerformed

    private void menuInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInicioActionPerformed
        inicio();
    }//GEN-LAST:event_menuInicioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AMVRutinario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AMVRutinario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AMVRutinario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AMVRutinario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AMVRutinario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem menuGuardar;
    private javax.swing.JMenuItem menuImprimir;
    private javax.swing.JMenuItem menuInicio;
    private javax.swing.JMenuItem menuProyecto;
    // End of variables declaration//GEN-END:variables

    private Bienvenida bienvenida;
    private CrearProyecto crearProyecto;
    private Imprimir imprimir;

    private void asignarIcono() {
        URL imageUrl = this.getClass().getResource("resources/ico.png");
        ImageIcon imagenInterna = null;
        if (Objects.nonNull(imageUrl)) {
            imagenInterna = new ImageIcon(imageUrl);
        }
        if (Objects.nonNull(imagenInterna)) {
            this.setIconImage(imagenInterna.getImage());
        }
    }

    private void inicio() {
        this.getContentPane().remove(crearProyecto);
        bienvenida = new Bienvenida();
        this.add(bienvenida, BorderLayout.CENTER);
        this.pack();

        menuGuardar.setEnabled(false);
        menuInicio.setEnabled(false);
        menuProyecto.setEnabled(true);
    }

    private void imprimir() {
        imprimir = new Imprimir(crearProyecto.getProyecto());
        this.getContentPane().remove(crearProyecto);
        this.add(imprimir, BorderLayout.CENTER);
        this.pack();

        menuGuardar.setEnabled(false);
        menuInicio.setEnabled(true);
        menuImprimir.setEnabled(false);
        menuProyecto.setEnabled(false);
    }

    public void openProject(int id) {
        crearProyecto = new CrearProyecto(id);
        this.getContentPane().remove(bienvenida);
        this.add(crearProyecto, BorderLayout.CENTER);
        this.pack();

        menuGuardar.setEnabled(true);
        menuInicio.setEnabled(true);
        menuProyecto.setEnabled(false);
    }
}