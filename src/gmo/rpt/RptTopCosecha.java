package gmo.rpt;

import app.RunMain;
import gmo.dialog.BuscarDialog;
import gmo.utils.jkeys;
import java.awt.CardLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import jmugi.voids.DateTimeUtil;
import jmugi.voids.JMethods;
import jmugi.voids.JOptionPane_methods;
import kevin.component.defaults;
import utils.ExecHTTP;

/**

 @author Asus
 */
public class RptTopCosecha extends javax.swing.JInternalFrame {

    int TIPO = 0, CONCONSUMIDOR = 0;
    public Object[][] DATA;
    Window Frame;
    public CardLayout CARDLAYOUT;
    String FECHA_DATE1 = "", FECHA_DATE2 = "", IDUSER = "", TYPEREPORT = "ALC", IDACTIVIDAD = "", IDLABOR = "", IDCONSUMIDOR = "", OBSERVACIONES = "";

    String titles[];
    int tams[];

    BuscarDialog buscarLaborActividad;
    ArrayList<Object[]> DNIS;

    public RptTopCosecha(Window w) {
        this.Frame = w;
        initComponents();

        chooserFecha1.setDate(new Date());
        FECHA_DATE1 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha1.getDate());
        chooserFecha1.setCallback(() -> {
            FECHA_DATE1 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha1.getDate());
        });
        tablaDetalle.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        chooserFecha2.setDate(new Date());
        FECHA_DATE2 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha2.getDate());
        chooserFecha2.setCallback(() -> {
            FECHA_DATE2 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha2.getDate());
        });

        toolbar1.setRECARGAR_CALLBACK(this::gettin_data);
        toolbar1.setEXCEL_CALLBACK(() -> {
            tablaDetalle.exportExcel((JFrame) Frame, getTitle());
        });

        gettin_data();
    }

    private void gettin_data() {
        tabla.setTYPES(new Class[]{String.class, String.class, Boolean.class});
        tabla.setIsBooleanColumn(true);
        tabla.GetDatosHTTP_2022(
                RunMain.gettin_pages.api_get()
                + ExecHTTP.parseQuery2022("exec GetRptTopCosechaResume ?1,?2,?3", false,
                        jkeys.IDDATABASE,
                        jkeys.IDEMPRESA,
                        FECHA_DATE1,
                        FECHA_DATE2
                )
        );
        JMethods.updateInternalJTable(this, tabla);
    }

    private void gettin_cosechadores() {

        Object[] fechasx = DateTimeUtil.DatesBetweenStringInt(chooserFecha1.getDate(), chooserFecha2.getDate());
        int cant = Integer.parseInt(fechasx[0].toString());
        String tit = fechasx[1].toString();

        tablaDetalle.GetDatosHTTP_2022(
                RunMain.gettin_pages.api_get()
                + ExecHTTP.parseQuery2022("exec GetRptTopCosecha ?1,?2,?3,?4,?5,?6,?7", false,
                        jkeys.IDDATABASE,
                        jkeys.IDEMPRESA,
                        FECHA_DATE1,
                        FECHA_DATE2,
                        selected(),
                        (swi_detallado.isOnOff() ? 1 : 0),
                        0)
        );

        JMethods.updateInternalJTable(this, tablaDetalle);
    }

    private String selected() {
        String dataSele = "";
        ArrayList<String> dataSel = new ArrayList<>();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if ((Boolean) tabla.getValueAt(i, 2) == true)
                dataSel.add(tabla.getValueAt(i, 0).toString());
        }
        if (dataSel.size() > 0) {
            for (int i = 0; i < dataSel.size() - 1; i++) {
                dataSele = dataSele + dataSel.get(i) + ",";
            }
            dataSele = dataSele + dataSel.get(dataSel.size() - 1);
        } else {
            JOptionPane.showMessageDialog(this, "No Hay Datos Seleccionados");
        }
        return dataSele;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        smpExportar = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        toolbar1 = new kevin.component.toolbar.Toolbar();
        cardData = new javax.swing.JPanel();
        button1 = new kevin.component.button.Button();
        etiqueta4 = new kevin.component.label.Etiqueta();
        swi_enNisira = new kevin.component.switchbox.SwitchBox();
        etiqueta5 = new kevin.component.label.Etiqueta();
        swi_detallado = new kevin.component.switchbox.SwitchBox();
        jPanel1 = new javax.swing.JPanel();
        buttonMaterialIcon1 = new kevin.component.button.ButtonMaterialIcon();
        checkBox1 = new kevin.component.checkbox.CheckBox();
        jLabel1 = new javax.swing.JLabel();
        etiqueta1 = new kevin.component.label.Etiqueta();
        chooserFecha1 = new kevin.component.date.MaterialDateChooser();
        etiqueta2 = new kevin.component.label.Etiqueta();
        chooserFecha2 = new kevin.component.date.MaterialDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalle = new kevin.component.tabla.TablaSmart();

        smpExportar.setText("jMenuItem1");
        smpExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smpExportarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(smpExportar);

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table);

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte Top de Cosechadores");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        toolbar1.setEXCEL(true);
        toolbar1.setFILTRAR(false);
        toolbar1.setHISTORIAL(false);
        toolbar1.setRECARGAR(true);
        toolbar1.setMaximumSize(new java.awt.Dimension(42, 240));
        toolbar1.setMinimumSize(new java.awt.Dimension(42, 240));
        toolbar1.setPreferredSize(new java.awt.Dimension(42, 240));
        jPanel4.add(toolbar1, java.awt.BorderLayout.WEST);

        cardData.setBackground(new java.awt.Color(255, 255, 255));

        button1.setText("Consultar");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        etiqueta4.setText("ERP Externo");

        swi_enNisira.setToolTipText("Desea Consultar los datos en el ERP Externo?");
        swi_enNisira.setEnabled(false);
        swi_enNisira.setOnOff(false);

        etiqueta5.setText("Detallado");

        swi_detallado.setToolTipText("Desea Consultar los datos en el ERP Externo?");
        swi_detallado.setOnOff(false);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        buttonMaterialIcon1.setText("buttonMaterialIcon1");
        buttonMaterialIcon1.setICO(iconfont.MATERIALICON.MATERIALICONIC.VISIBILITY);
        buttonMaterialIcon1.setPreferredSize(new java.awt.Dimension(36, 36));
        buttonMaterialIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonMaterialIcon1);

        checkBox1.setText("Sel. Todo");
        checkBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(checkBox1);

        jLabel1.setText("    ");
        jPanel1.add(jLabel1);

        etiqueta1.setText("  Inicio  ");
        jPanel1.add(etiqueta1);

        chooserFecha1.setWeekOfYearVisible(false);
        jPanel1.add(chooserFecha1);

        etiqueta2.setText("  Fin  ");
        jPanel1.add(etiqueta2);

        chooserFecha2.setWeekOfYearVisible(false);
        jPanel1.add(chooserFecha2);

        jPanel2.setOpaque(false);

        jScrollPane4.setBorder(null);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(420, 400));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setSelectionBackground(defaults.colorAccent);
        jScrollPane4.setViewportView(tabla);

        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);

        tablaDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaDetalle.setOpaque(false);
        jScrollPane2.setViewportView(tablaDetalle);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cardDataLayout = new javax.swing.GroupLayout(cardData);
        cardData.setLayout(cardDataLayout);
        cardDataLayout.setHorizontalGroup(
            cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDataLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(cardDataLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiqueta4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(swi_enNisira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiqueta5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(swi_detallado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        cardDataLayout.setVerticalGroup(
            cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardDataLayout.createSequentialGroup()
                .addGroup(cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardDataLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiqueta4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardDataLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(swi_detallado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(etiqueta5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(swi_enNisira, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jPanel4.add(cardData, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void smpExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smpExportarActionPerformed

    }//GEN-LAST:event_smpExportarActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        JOptionPane_methods.MostrarConfirmacion(Frame, (Window frame) -> {
            gettin_cosechadores();
        });
    }//GEN-LAST:event_button1ActionPerformed

    private void checkBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox1ActionPerformed
        tabla.checkALL(checkBox1.isSelected(), 2);
    }//GEN-LAST:event_checkBox1ActionPerformed

    private void buttonMaterialIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon1ActionPerformed
        jScrollPane4.setVisible(!jScrollPane4.isVisible());
    }//GEN-LAST:event_buttonMaterialIcon1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.Button button1;
    private javax.swing.ButtonGroup buttonGroup1;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon1;
    private javax.swing.JPanel cardData;
    private kevin.component.checkbox.CheckBox checkBox1;
    private kevin.component.date.MaterialDateChooser chooserFecha1;
    private kevin.component.date.MaterialDateChooser chooserFecha2;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta4;
    private kevin.component.label.Etiqueta etiqueta5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JMenuItem smpExportar;
    private kevin.component.switchbox.SwitchBox swi_detallado;
    private kevin.component.switchbox.SwitchBox swi_enNisira;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.tabla.TablaSmart tablaDetalle;
    private javax.swing.JTable table;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables
}
