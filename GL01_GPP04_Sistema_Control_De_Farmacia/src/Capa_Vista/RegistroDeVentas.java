/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Capa_Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro
 */
public class RegistroDeVentas extends javax.swing.JFrame {

    private int idMedicamentoSeleccionado = -1;
    private int idLoteSugerido = -1;
    private int idUbicacionSugerida = -1;

    /**
     * Creates new form RegistroDeVentas
     */
    public RegistroDeVentas() {
        initComponents();

        setTitle("Manejo de Ventas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        cargarMedicamentos();
        cargarEmpleados();
        prepararTablaCarrito();
        cargarCategoriasCombo();
        buscarGananciasPorCategoria();
    }

    //Metodo para cargar los datos de la tabla Empleados Utilizando la capa DAO
    public void cargarEmpleados() {
        String[] columnas = {
            "ID", "Nombre Completo", "Turno"
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEmpleados.setModel(modeloTabla);

        try {
            Capa_DAO.IEmpleadoDAO empleadoDAO = new Capa_DAO.EmpleadoDAOImpl();

            java.util.List<Capa_Modelo.Empleado> lista = empleadoDAO.listarTodos();

            for (Capa_Modelo.Empleado emp : lista) {
                Object[] fila = {
                    emp.getIdEmpleado(),
                    emp.getNombreCompleto(),
                    emp.getTurno_empleado()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los empleados en la interfaz:\n" + e.getMessage(),
                    "Error de Sistema",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo para cargar los datos de la tabla Medicamentos Utilizando la capa DAO
    public void cargarMedicamentos() {
        String[] columnas = {
            "ID", "Medicamento", "Cantidad Cajas", "Presentacion"
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaMedicamentos.setModel(modeloTabla);
        tablaMedicamentos.getColumnModel().getColumn(0).setMinWidth(0);
        tablaMedicamentos.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaMedicamentos.getColumnModel().getColumn(0).setWidth(0);

        try {
            Capa_DAO.IMedicamentoDAO medicamentoDAO = new Capa_DAO.MedicamentoDAOImpl();
            java.util.List<Capa_Modelo.Medicamento> listaMed = medicamentoDAO.sotckMedicamentos();

            for (Capa_Modelo.Medicamento med : listaMed) {
                Object[] fila = {
                    med.getId_Medicameto(),
                    med.getNombre_medicamento(),
                    med.getCantidad_actual(),
                    med.getPresentacion()
                };
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar Los medicamentos:\n" + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo para Crear las columnas de la tabla que se utiliza como "Carrito" de la Venta
    public void prepararTablaCarrito() {
        String[] columnasCarrito = {
            "Medicamento", "Cantidad", "Precio Unit", "Subtotal", "ID_Lote", "ID_Ubic", "ID_Emp"
        };

        javax.swing.table.DefaultTableModel modeloCarrito = new javax.swing.table.DefaultTableModel(columnasCarrito, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaCarrito.setModel(modeloCarrito);

        for (int i = 4; i <= 6; i++) {
            tablaCarrito.getColumnModel().getColumn(i).setMinWidth(0);
            tablaCarrito.getColumnModel().getColumn(i).setMaxWidth(0);
            tablaCarrito.getColumnModel().getColumn(i).setWidth(0);
            tablaCarrito.getColumnModel().getColumn(i).setPreferredWidth(0);
        }
    }

    //Metodo que utiliza la capa DAO para cargar los items de la combo box
    public void cargarCategoriasCombo() {
        cbCategorias.removeAllItems();

        cbCategorias.addItem("Todas las categorías");

        try {
            Capa_DAO.IMedicamentoDAO medicamentoDAO = new Capa_DAO.MedicamentoDAOImpl();
            java.util.List<Capa_Modelo.Medicamento> listaMeds = medicamentoDAO.DatosCb();

            for (Capa_Modelo.Medicamento med : listaMeds) {
                cbCategorias.addItem(med.getTipo_medicamento());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar categorías: " + e.getMessage());
        }
    }

    //Metodo que utiliza la capa DAO para mostrar el total vendido por categoria en la tablaResumen
    public void buscarGananciasPorCategoria() {
        if (cbCategorias.getSelectedItem() == null) {
            return;
        }

        String categoriaSeleccionada = cbCategorias.getSelectedItem().toString();

        javax.swing.table.DefaultTableModel modeloResumen = (javax.swing.table.DefaultTableModel) tablaResumen.getModel();
        modeloResumen.setRowCount(0);

        try {
            Capa_DAO.IMedicamentoDAO medicamentoDAO = new Capa_DAO.MedicamentoDAOImpl();
            java.util.List<Capa_Modelo.Medicamento> listaMeds = medicamentoDAO.CargarResumenVentas(categoriaSeleccionada);

            for (Capa_Modelo.Medicamento med : listaMeds) {
                String nombreCat = med.getTipo_medicamento();
                double ganancia = med.getGanancia_total();

                modeloResumen.addRow(new Object[]{
                    nombreCat,
                    "$" + String.format("%.2f", ganancia)
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar todas las ventas: " + e.getMessage());
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
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbCategorias = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaResumen = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMedicamentos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblNombreM = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCantP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblNombreU = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPrecioUnitario = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        btnAgregarV = new javax.swing.JButton();
        btnRegistrarVenta = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbMetodoPago = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Resumen Ventas:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        jLabel10.setText("Seleccione una categoria:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        cbCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbCategoriasMouseClicked(evt);
            }
        });
        cbCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriasActionPerformed(evt);
            }
        });
        jPanel2.add(cbCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 72, 200, 30));

        tablaResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoria", "Total Vendido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablaResumen);
        if (tablaResumen.getColumnModel().getColumnCount() > 0) {
            tablaResumen.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 350, 180));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 880, 270));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMedicamentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaMedicamentos);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 293, 180));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Registrar Venta");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 210, 30));

        jLabel2.setText("Stock Medicamentos:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, -1));

        jLabel3.setText("Empleados:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, -1, -1));

        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaEmpleados);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 300, 190));

        jLabel4.setText("Medicamento:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));
        jPanel3.add(lblNombreM, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 110, 20));

        jLabel6.setText("Cantidad:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        txtCantP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantPActionPerformed(evt);
            }
        });
        jPanel3.add(txtCantP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 70, -1));

        jLabel7.setText("Proviene De:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));
        jPanel3.add(lblNombreU, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 270, 20));

        jLabel9.setText("Precio Unitario:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        txtPrecioUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioUnitarioActionPerformed(evt);
            }
        });
        jPanel3.add(txtPrecioUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 70, -1));

        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicamento", "Cantidad", "Precio Unitario", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCarrito.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tablaCarrito);
        if (tablaCarrito.getColumnModel().getColumnCount() > 0) {
            tablaCarrito.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 460, 180));

        btnAgregarV.setLabel("Agregar a la Venta");
        btnAgregarV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarVMouseClicked(evt);
            }
        });
        btnAgregarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregarV, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, -1));

        btnRegistrarVenta.setLabel("Registrar Venta");
        btnRegistrarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarVentaMouseClicked(evt);
            }
        });
        btnRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVentaActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegistrarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, -1, -1));

        jLabel5.setText("Metodo de Pago:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, 20));

        cbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta", "Transferencia" }));
        jPanel3.add(cbMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 110, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 880, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 941, 792));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantPActionPerformed

    //Metodo que se dispara desde el btnAgregar para el medicamento Seleccionado al "Carrito"
    private void btnAgregarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVActionPerformed
        // TODO add your handling code here:
        int filaEmpleado = tablaEmpleados.getSelectedRow();

        if ("".equals(txtCantP.getText()) || "".equals(txtPrecioUnitario.getText())) {
            JOptionPane.showMessageDialog(this, "Digite una cantidad y Precio para la Venta.");
            return;
        }

        if (filaEmpleado == -1 || idMedicamentoSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado y un medicamento.");
            return;
        }

        int cantidad = Integer.parseInt(txtCantP.getText());
        double precio = Double.parseDouble(txtPrecioUnitario.getText());
        double subtotal = cantidad * precio;

        int idEmpleado = Integer.parseInt(tablaEmpleados.getValueAt(filaEmpleado, 0).toString());

        //Agregamos todo al "Carrito" 
        javax.swing.table.DefaultTableModel modeloCarrito = (javax.swing.table.DefaultTableModel) tablaCarrito.getModel();

        modeloCarrito.addRow(new Object[]{
            lblNombreM.getText(),
            cantidad,
            precio,
            subtotal,
            idLoteSugerido, // Dato oculto 
            idUbicacionSugerida, // Dato oculto 
            idEmpleado // Dato oculto 
        });

        //Limpiamos los campos para el siguiente producto
        lblNombreM.setText("");
        lblNombreU.setText("");
        txtCantP.setText("");
        txtPrecioUnitario.setText("");
        idMedicamentoSeleccionado = -1; // Reseteamos la memoria

    }//GEN-LAST:event_btnAgregarVActionPerformed

    //Metodo que utiliza la capa DAO para el registro de una Venta con todos los productos del "Carrito"
    private void btnRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modeloCarrito = (DefaultTableModel) tablaCarrito.getModel();

        int filaEmpleado = tablaEmpleados.getSelectedRow();

        if (modeloCarrito.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.");
            return;
        }

        try {
            java.util.List<Capa_Modelo.Detalle_Venta> listaCarrito = new java.util.ArrayList<>();

            for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
                int cantidad = Integer.parseInt(modeloCarrito.getValueAt(i, 1).toString());
                double precio = Double.parseDouble(modeloCarrito.getValueAt(i, 2).toString());
                int idLote = Integer.parseInt(modeloCarrito.getValueAt(i, 4).toString());
                int idUbicacion = Integer.parseInt(modeloCarrito.getValueAt(i, 5).toString());

                listaCarrito.add(new Capa_Modelo.Detalle_Venta(idUbicacion, idLote, cantidad, precio));
            }

            int idEmpleado = Integer.parseInt(tablaEmpleados.getValueAt(filaEmpleado, 0).toString());
            String metodoPago = cbMetodoPago.getSelectedItem().toString();

            Capa_Modelo.Venta miVenta = new Capa_Modelo.Venta(idEmpleado, metodoPago, listaCarrito);

            Capa_DAO.IVentaDAO ventaDAO = new Capa_DAO.VentaDAOImpl();
            boolean exito = ventaDAO.registrarVentaTransaccional(miVenta);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Venta Registrada con Éxito!");
                modeloCarrito.setRowCount(0);
                cargarMedicamentos();
                buscarGananciasPorCategoria();
                cargarEmpleados();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la transacción:\n" + e.getMessage(),
                    "Venta Denegada", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnRegistrarVentaActionPerformed

    //Metodo que actualiza los lblNombreM y lblNombreU con los datos automaticos mas proximos a vencer 
    private void tablaMedicamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMedicamentosMouseClicked
        // TODO add your handling code here:
        
        int fila = tablaMedicamentos.getSelectedRow();

        if (fila >= 0) {
            idMedicamentoSeleccionado = Integer.parseInt(tablaMedicamentos.getValueAt(fila, 0).toString());
            String nombreMed = tablaMedicamentos.getValueAt(fila, 1).toString();
            int stockActual = Integer.parseInt(tablaMedicamentos.getValueAt(fila, 2).toString());

            lblNombreM.setText(nombreMed); 

            if (stockActual <= 0) {
                lblNombreU.setText("Sin stock disponible");
                idLoteSugerido = -1;
                idUbicacionSugerida = -1;
                return; 
            }
            try {
                Capa_DAO.IMedicamentoDAO datos = new Capa_DAO.MedicamentoDAOImpl();

                idLoteSugerido = datos.SetIdLote(idMedicamentoSeleccionado);
                idUbicacionSugerida = datos.SetIdUbicacion(idMedicamentoSeleccionado);

                lblNombreU.setText(datos.ActualizarUbicacion(idMedicamentoSeleccionado));

            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al buscar ubicación: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_tablaMedicamentosMouseClicked

    private void btnAgregarVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarVMouseClicked
        // TODO add your handling code he

    }//GEN-LAST:event_btnAgregarVMouseClicked

    private void txtPrecioUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioUnitarioActionPerformed

    private void btnRegistrarVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarVentaMouseClicked

    }//GEN-LAST:event_btnRegistrarVentaMouseClicked

    private void cbCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriasActionPerformed
        // TODO add your handling code here:
        buscarGananciasPorCategoria();
    }//GEN-LAST:event_cbCategoriasActionPerformed

    private void cbCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCategoriasMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cbCategoriasMouseClicked

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
            java.util.logging.Logger.getLogger(RegistroDeVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroDeVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroDeVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroDeVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroDeVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarV;
    private javax.swing.JButton btnRegistrarVenta;
    private javax.swing.JComboBox<String> cbCategorias;
    private javax.swing.JComboBox<String> cbMetodoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblNombreM;
    private javax.swing.JLabel lblNombreU;
    private javax.swing.JTable tablaCarrito;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTable tablaMedicamentos;
    private javax.swing.JTable tablaResumen;
    private javax.swing.JTextField txtCantP;
    private javax.swing.JTextField txtPrecioUnitario;
    // End of variables declaration//GEN-END:variables
}
