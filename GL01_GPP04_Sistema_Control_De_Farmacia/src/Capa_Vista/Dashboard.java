/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Capa_Vista;

import Capa_DAO.AlertaVencimientoDAOImpl;
import Capa_DAO.MovimientoDAOImpl;
import Capa_DAO.UbicacionDAOImpl;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */


    public Dashboard() {
        setTitle("Dashboards");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ((javax.swing.JPanel) getContentPane()).setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        initComponents();
        
        cargarCursorEnTabla();
        cargarDatosDeVista();
        cargarTablaMovientos();
        cargarDatos();
    }

    //Carga datos de los Totales Resumen 
    private void cargarDatos() {
        try {

            Capa_DAO.IAlertaVencimiento resumen = new AlertaVencimientoDAOImpl();
            Capa_Modelo.AlertaVencimiento datosResumen = resumen.CargarDatosResumen();

            if (datosResumen != null) {

                lblCantE.setText(String.valueOf(datosResumen.getTotalEntradas()));
                lblCantS.setText(String.valueOf(datosResumen.getTotalSalidas()));

                lblTotalC.setText("$" + String.valueOf(datosResumen.getTotalCompras()));
                lblTotalV.setText("$" + String.valueOf(datosResumen.getTotalVentas()));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            lblCantE.setText("0.1"); 
            lblCantS.setText("0.1"); 
        }

    }
    
    //Cargar los Datos de la tabla Movimientos con los registros de la BD
    private void cargarTablaMovientos() {

        String[] columnas = {
            "Tipo", "Fecha", "Referencia", "Cantidad"
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaMovimientos.setModel(modeloTabla);

        try {

            Capa_DAO.IMovimientoDAO movimientoDAO = new MovimientoDAOImpl();
            java.util.List<Capa_Modelo.Movimiento> listaMovs = movimientoDAO.CargarMovimientos();

            for (Capa_Modelo.Movimiento mov : listaMovs) {
                Object[] fila = {
                    mov.getTipo_movimiento(),
                    mov.getFecha_movimiento(),
                    mov.getReferencia(),
                    mov.getCantidad()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar las alertas:\n" + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    //Cargar los Datos de la tabla Alertas Stock con los registros de la BD 
    private void cargarCursorEnTabla() {
        String[] columnas = {"Estantería", "Descripción de Alerta", "Urgencia"};

        javax.swing.table.DefaultTableModel modeloTabla = new javax.swing.table.DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaAlertasStock.setModel(modeloTabla);

        try {

            modeloTabla.setRowCount(0);

            Capa_DAO.IUbicacionDAO ubicacionDAO = new UbicacionDAOImpl();
            java.util.List<Capa_Modelo.Ubicacion> listaUbis = ubicacionDAO.CargarAlertasStock();

            for (Capa_Modelo.Ubicacion ubi : listaUbis) {
                Object[] fila = {
                    ubi.getPosicion(),
                    ubi.getAviso_stock(),
                    ubi.getNivel_urgencia()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error ejecutando el cursor de inventario:\n" + e.getMessage(),
                    "Error SQL",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    //Cargar los Datos de la tabla Alertas Vencimiento con los registros de la BD 
    private void cargarDatosDeVista() {
        String[] columnas = {
            "Medicamento", "Lote", "Vencimiento",
            "Estantería", "Tipo", "Stock", "Días Faltantes"
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaAlertas.setModel(modeloTabla);

        try {
            Capa_DAO.IAlertaVencimiento alertaDAO = new Capa_DAO.AlertaVencimientoDAOImpl();

            java.util.List<Capa_Modelo.AlertaVencimiento> listaAlertas = alertaDAO.listarAlertasPorVencer();

            for (Capa_Modelo.AlertaVencimiento alerta : listaAlertas) {
                Object[] fila = {
                    alerta.getMedicamento(),
                    alerta.getLote(),
                    alerta.getFechaVencimiento(),
                    alerta.getEstanteria(),
                    alerta.getTipoUbicacion(),
                    alerta.getCantidadDisponible(),
                    alerta.getDiasFaltantes()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar las alertas:\n" + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAlertasStock = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlertas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaMovimientos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalV = new javax.swing.JLabel();
        lblTotalC = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblCantS = new javax.swing.JLabel();
        lblCantE = new javax.swing.JLabel();
        btnEliminarLote = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaAlertasStock.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaAlertasStock);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 320, 190));

        tablaAlertas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaAlertas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlertasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlertas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, 620, 190));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Dashboards Informativos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 270, 40));

        tablaMovimientos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tablaMovimientos);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 950, 220));

        jLabel2.setText("Movimientos:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, -1, -1));

        jLabel3.setText("Reporte de Bajo Stock:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, -1, -1));

        jLabel4.setText("Reporte de Medicamentos por Vencer:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, -1, -1));

        jLabel5.setText("Total Ventas:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel6.setText("Total Compras:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        lblTotalV.setText("$0.00");
        jPanel1.add(lblTotalV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        lblTotalC.setText("$0.00");
        jPanel1.add(lblTotalC, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel9.setText("Total medicamentos que han salido:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, -1, 20));

        jLabel10.setText("Total medicamentos que han entrado:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        lblCantS.setText("0");
        jPanel1.add(lblCantS, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 90, -1));

        lblCantE.setText("0");
        jPanel1.add(lblCantE, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 70, -1));

        btnEliminarLote.setText("Eliminar Medicamento por Vencer");
        btnEliminarLote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarLoteMouseClicked(evt);
            }
        });
        btnEliminarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLoteActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 980, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Se llama a la capa DAO para vaciar todo el medicamento que este vencido o proximo a vencer
    private void btnEliminarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLoteActionPerformed
        // TODO add your handling code here:
        int fila = tablaAlertas.getSelectedRow();

        int idLotePorVencer = -1;

        idLotePorVencer = Integer.parseInt(tablaAlertas.getValueAt(fila, 1).toString());

        if (idLotePorVencer == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un lote de la lista.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar este lote vencido? Esta acción vaciará el stock físico asociado.",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmar == JOptionPane.YES_OPTION) {

            try {
                Capa_DAO.IAlertaVencimiento vencimiento = new AlertaVencimientoDAOImpl();

                boolean resultado = vencimiento.VaciarStockVencido(idLotePorVencer);

                if (resultado == true) {
                    JOptionPane.showMessageDialog(this, "Lote eliminado con éxito.\nSe ha registrado la baja en el Kardex.");

                    // Aquí refrescas tus tablas en la pantalla para que desaparezca el lote destruido
                    cargarTablaMovientos();
                    cargarDatosDeVista(); // Actualiza el stock global de la pantalla principal
                    cargarDatos();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el lote:\n" + e.getMessage(),
                        "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarLoteActionPerformed

    private void tablaAlertasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlertasMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_tablaAlertasMouseClicked

    private void btnEliminarLoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarLoteMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_btnEliminarLoteMouseClicked

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(() -> {
            Dashboard pantalla = new Dashboard();
            pantalla.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarLote;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCantE;
    private javax.swing.JLabel lblCantS;
    private javax.swing.JLabel lblTotalC;
    private javax.swing.JLabel lblTotalV;
    private javax.swing.JTable tablaAlertas;
    private javax.swing.JTable tablaAlertasStock;
    private javax.swing.JTable tablaMovimientos;
    // End of variables declaration//GEN-END:variables
}
