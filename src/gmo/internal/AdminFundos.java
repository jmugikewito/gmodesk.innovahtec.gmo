package gmo.internal;

import acore.principalvalues;
import java.awt.CardLayout;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import jmugi.voids.JMethods;
import jmugi.voids.JOptionPane_methods;
import gmo.model.Fundo;
import jmugi.voids.PrintMethods;

/**

 @author Asus
 */
public class AdminFundos extends javax.swing.JInternalFrame {

    public Object[][] DATA;
    public Window Frame;
    public String ID;
    public CardLayout CARDLAYOUT;

    public ArrayList<Object[]> LISTA_DATA;

    public int[] COL_TAM = new int[]{0, 0, 120, 160, 90, 90, 120, 120, 60, 140, 200};
    public String[] COL_TITLE = Fundo.cols;
    public int COL_CANT = COL_TITLE.length;

    public int FILA = 0;
    public int EDITNUEVO = 0;//0 para Nuevo y 1 para EDITAR

    public AdminFundos(Window w) {
        initComponents();
        this.Frame = w;
        initComponents();
        CARDLAYOUT = (CardLayout) contenedor.getLayout();
        CARDLAYOUT.show(contenedor, "cardGeneral");
        toolbar.setVisible(!toolbar.isVisible());

        aplyEvents();
        gettin_data();
    }

    public void gettin_data() {
//        tabla.setCon(Main.CONECT_JDATA.con);
//        tabla.setCONSULTA("EXEC GetListFundos '" + jkeys.IDDATABASE + "','" + jkeys.IDEMPRESA + "','" + ((cbo_estado.getSelectedIndex() == 2) ? "" : cbo_estado.getSelectedIndex()) + "';");
//        tabla.getDateSQL();
//        JMethods.updateInternalJTable(this, tabla);
//        DATA = tabla.getDATATOTAL();
//        tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatRegistroActivo(8, "AdminGeneral"));
    }

    public void clearData() {
        JMethods.Window_ResetValues(
                new JTextField[]{edtDescripc, edtCantPlantas, edtCantHecta},
                new JCheckBox[]{chkEsPacking},
                new JTextArea[]{textObservaciones});
        edtDescripc.requestFocus();
    }

    public void aplyEvents() {

        //<editor-fold defaultstate="collapsed" desc="Agregar una Nueva Entrada">
        toolbar.setAGREGARCALLBACK(() -> {
            clearData();
            lbl_title.setText("Nuevo Fundo");
            EDITNUEVO = 0;
            CARDLAYOUT.show(contenedor, "cardNuevoEdit");
            toolbar.applyAgregar();
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Editar una Entrada Seleccionada">
        toolbar.setEDITARCALLBACK(() -> {
            if (ID != null) {
                settinData(new Fundo(DATA[tabla.getSelectedRow()]));
                EDITNUEVO = 1;
                CARDLAYOUT.show(contenedor, "cardNuevoEdit");
                toolbar.applyEdit();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una Entrada");
            }
        });
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Cancelar la Edicion de una Entrada Seleccionada">
        toolbar.setCANCELARCallback(() -> {
            PrintMethods.printer("CANCELAR");
            CARDLAYOUT.show(contenedor, "cardGeneral");
            toolbar.applyCancelar();
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Guardar una Nueva Entrada">
        toolbar.setGUARDARCALLBACK(
                () -> {
                    applyEditarAgregar();
                }
        );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Anular una Entrada Seleccionada">
        toolbar.setANULARCALLBACK(() -> {
            JOptionPane_methods.MostrarConfirmacion(Frame, (frame) -> {
//                TrySQL.sql_procedure_try(Frame, Main.CONECT_JDATA.con, "exec UpFundo ?,?;", new Object[]{0, ID}, (Frame) -> {
//                    JOptionPane.showMessageDialog(Frame, "Anulacion Correcta");
//                    gettin_data();
//                });
            });
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Eliminar una Entrada Seleccionada">
        toolbar.setELIMINARCALLBACK(() -> {
//            JOptionPane_methods.MostrarConfirmacion(Frame, (frame) -> {
//                TrySQL.sql_procedure_try(Frame, Main.CONECT_JDATA.con, "exec UpFundo ?,?;", new Object[]{1, ID}, (Frame) -> {
//                    JOptionPane.showMessageDialog(Frame, "Eliminacion Correcta");
//                    gettin_data();
//                });
//            });
        });
        //</editor-fold>

    }

    public void settinData(Fundo objeto) {
        lbl_title.setText("Edicion de Fundo");
        edtID.setText(objeto.getIdfundo());
        edtDescripc.setText(objeto.getDescripcion());
        edtCantPlantas.setText(objeto.getCantplantas() + "");
        edtCantHecta.setText(objeto.getCanthctas() + "");
        chkEsPacking.setSelected(objeto.getEspacking() == 1);
        chkEsCampo.setSelected(objeto.getEscampo() == 1);
        chk_Activo.setSelected(objeto.getActivo() == 1);
        textObservaciones.setText(objeto.getObservaciones());
    }

    public void applyEditarAgregar() {
     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        contenedor = new javax.swing.JPanel();
        panelGeneral = new views.JMPanel.JMPanel();
        cbo_estado = new views.JMCombBox.JMComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        panelEdit = new javax.swing.JPanel();
        jMPanel3 = new views.JMPanel.JMPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lbl_title = new views.JMLabel.JmugiLabelNormal();
        jMPanel4 = new views.JMPanel.JMPanel();
        jPanel4 = new javax.swing.JPanel();
        edtID = new views.JMEditText.JMaterialEditText();
        edtDescripc = new views.JMEditText.JMaterialEditText();
        edtCantPlantas = new views.JMEditText.JMaterialEditText();
        jScrollPane2 = new javax.swing.JScrollPane();
        textObservaciones = new views.JMTextArea.JMTextArea();
        jPanel2 = new javax.swing.JPanel();
        chk_Activo = new views.JMCheckBox.JMCheckBox();
        chkEsCampo = new views.JMCheckBox.JMCheckBox();
        chkEsPacking = new views.JMCheckBox.JMCheckBox();
        jLabel1 = new javax.swing.JLabel();
        edtCantHecta = new views.JMEditText.JMaterialEditText();
        toolbar = new views.JMToolbar.JMToolbar();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Administracion de Fundos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        contenedor.setOpaque(false);
        contenedor.setLayout(new java.awt.CardLayout());

        cbo_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INACTIVOS", "ACTIVOS", "TODOS" }));
        cbo_estado.setSelectedIndex(2);
        cbo_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_estadoActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabla);

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(cbo_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbo_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(panelGeneral, "cardGeneral");

        panelEdit.setBackground(new java.awt.Color(255, 255, 255));
        panelEdit.setLayout(new java.awt.GridBagLayout());

        jMPanel3.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(principalvalues.colorPrimary);

        lbl_title.setForeground(new java.awt.Color(255, 255, 255));
        lbl_title.setText("Nuevo  Fundo");
        lbl_title.setESITALIC(true);
        lbl_title.setFONT_SIZE(32.0F);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMPanel4.setLayout(new java.awt.BorderLayout());

        jPanel4.setOpaque(false);

        edtID.setLabel("Ingrese Id del Fundo");
        edtID.setModo(views.JMEditText.JMaterialEditText.MODO.NORMAL);

        edtDescripc.setLabel("Ingrese la Descripcion del Fundo");
        edtDescripc.setModo(views.JMEditText.JMaterialEditText.MODO.EMAIL);

        edtCantPlantas.setDigit_limit(15);
        edtCantPlantas.setLabel("#Plantas");
        edtCantPlantas.setModo(views.JMEditText.JMaterialEditText.MODO.NUMEROS);

        jScrollPane2.setViewportView(textObservaciones);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        chk_Activo.setSelected(true);
        chk_Activo.setText("Activo");
        jPanel2.add(chk_Activo);

        chkEsCampo.setSelected(true);
        chkEsCampo.setText("Es Campo");
        jPanel2.add(chkEsCampo);

        chkEsPacking.setText("Es Packing");
        jPanel2.add(chkEsPacking);

        jLabel1.setText("Observaciones");

        edtCantHecta.setDigit_limit(15);
        edtCantHecta.setLabel("#Hectareas");
        edtCantHecta.setModo(views.JMEditText.JMaterialEditText.MODO.NUMEROS_DECIMAL);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(edtDescripc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                .addComponent(edtID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(edtCantPlantas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtCantHecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(edtID, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtDescripc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtCantPlantas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtCantHecta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMPanel4.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jMPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMPanel3.add(jPanel3, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        panelEdit.add(jMPanel3, gridBagConstraints);

        contenedor.add(panelEdit, "cardNuevoEdit");

        jPanel1.add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar.setPreferredSize(new java.awt.Dimension(56, 366));
        jPanel1.add(toolbar, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbo_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_estadoActionPerformed
        gettin_data();
    }//GEN-LAST:event_cbo_estadoActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
           if (evt.getClickCount() == 2) {
            int f = tabla.getSelectedRow();
            ID = tabla.getValueAt(f, 2).toString();
            toolbar.setVisible(true);
        }
    }//GEN-LAST:event_tablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private views.JMCombBox.JMComboBox cbo_estado;
    private views.JMCheckBox.JMCheckBox chkEsCampo;
    private views.JMCheckBox.JMCheckBox chkEsPacking;
    private views.JMCheckBox.JMCheckBox chk_Activo;
    private javax.swing.JPanel contenedor;
    private views.JMEditText.JMaterialEditText edtCantHecta;
    private views.JMEditText.JMaterialEditText edtCantPlantas;
    private views.JMEditText.JMaterialEditText edtDescripc;
    private views.JMEditText.JMaterialEditText edtID;
    private javax.swing.JLabel jLabel1;
    private views.JMPanel.JMPanel jMPanel3;
    private views.JMPanel.JMPanel jMPanel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private views.JMLabel.JmugiLabelNormal lbl_title;
    private javax.swing.JPanel panelEdit;
    private views.JMPanel.JMPanel panelGeneral;
    private kevin.component.tabla.TablaSmart tabla;
    private views.JMTextArea.JMTextArea textObservaciones;
    private views.JMToolbar.JMToolbar toolbar;
    // End of variables declaration//GEN-END:variables
}
