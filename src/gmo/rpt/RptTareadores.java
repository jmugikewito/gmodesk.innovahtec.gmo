package gmo.rpt;

import app.RunMain;
import color.MaterialColor;
import gmo.utils.jkeys;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import jmugi.voids.DateTimeUtil;
import jmugi.voids.JMethods;
import kevin.component.defaults;
import kevin.component.dialog.SmartLoader;
import kevin.component.menu.GMOMenuItemRadio;
import model.AreaChartGraph;

/**

 @author kevin leandro
 */
public class RptTareadores extends javax.swing.JInternalFrame {

    public Object[][] DATA;
    public Window Frame;
    private String FECHA_DATE;
    private int TYPE = 1;
    SmartLoader load;
    private String IDUSUARIO = "";
    GMOMenuItemRadio item;

    private int MIN = 2, MAY = 2;
    private ArrayList<Integer> ORDER;

    public RptTareadores(JFrame frame) {
        initComponents();
        this.Frame = frame;
        this.item = sm_ultimaSemana;
        ORDER = new ArrayList<>();
        edtDateChooser.setVisible(false);
        edtDateChooser.setDate(new Date());
        FECHA_DATE = DateTimeUtil.getDate_yyyyMMdd(edtDateChooser.getDate());
        edtDateChooser.setCallback(() -> {
            FECHA_DATE = DateTimeUtil.getDate_yyyyMMdd(edtDateChooser.getDate());
            gettin_data();
        });

        toolbar1.setRECARGAR_CALLBACK(() -> {
            gettin_data();
        });

        toolbar1.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) Frame, getTitle());
        });


        gettin_data();
    }

    private void gettin_data() {
        setTitle("Reporte de Tareadores - " + item.getText());
        contentChart.removeAll();
        contentChart.updateUI();
        switch (TYPE) {
            case 5:
            case 6:
                edtDateChooser.setVisible(true);
                break;
            default:
                edtDateChooser.setVisible(false);
                break;

        }
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos",
                "Actualizando Datos de los Tareos Filtrados○\n" + item.getText(), (Window frame) -> {

            tabla.initHttp("",
                    "idusuario,nombres,LUN,MAR,MIE,JUE,VIE,SAB,DOM,TOTAL",
                    "idusuario,nombres,LUN,MAR,MIE,JUE,VIE,SAB,DOM,TOTAL",
                    "Stringx2,Integerx8",
                    RunMain.gettin_pages.api_get() + "exec GetRptTareadores " + TYPE + ",'" + jkeys.IDEMPRESA + "','" + FECHA_DATE + "';"
            );
            tabla.GetDatosHTTP2022();

            tablaError.initHttp("",
                    "idusuario,nombres,LUN,MAR,MIE,JUE,VIE,SAB,DOM,TOTAL",
                    "idusuario,nombres,LUN,MAR,MIE,JUE,VIE,SAB,DOM,TOTAL",
                    "Stringx2,Integerx8",
                    RunMain.gettin_pages.api_get() + "exec GetRptTareadoresError " + TYPE + ",'" + jkeys.IDEMPRESA + "','" + FECHA_DATE + "';"
            );
            tablaError.GetDatosHTTP2022();

            JMethods.updateInternalJTable(this, tabla);
            DATA = tabla.getDATA();
            load.dispose();
            JDialog.setDefaultLookAndFeelDecorated(true);
        });
        JMethods.settingGlassPane((JFrame) Frame, load, MaterialColor.BLUEGREY_900, 0.8f);
        load = null;

//        tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatTareoEspera());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        edtDateChooser = new kevin.component.date.MaterialDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panel2 = new kevin.component.panel.Panel();
        jPanel1 = new javax.swing.JPanel();
        etiqueta1 = new kevin.component.label.Etiqueta();
        etiqueta2 = new kevin.component.label.Etiqueta();
        etDiaMoreProd = new kevin.component.label.Etiqueta();
        etDiaMenProd = new kevin.component.label.Etiqueta();
        jPanel5 = new javax.swing.JPanel();
        panel1 = new kevin.component.panel.Panel();
        contentChart = new javax.swing.JPanel();
        panel3 = new kevin.component.panel.Panel();
        contentChartError = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaError = new kevin.component.tabla.TablaSmart();
        toolbar1 = new kevin.component.toolbar.Toolbar();
        gMOMenuBar1 = new kevin.component.menu.GMOMenuBar();
        jMenu1 = new javax.swing.JMenu();
        sm_ultimaSemana = new kevin.component.menu.GMOMenuItemRadio();
        sm_dosSemana = new kevin.component.menu.GMOMenuItemRadio();
        sm_ultimoMes = new kevin.component.menu.GMOMenuItemRadio();
        sm_dosMes = new kevin.component.menu.GMOMenuItemRadio();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        sm_customSemana = new kevin.component.menu.GMOMenuItemRadio();
        sm_customMes = new kevin.component.menu.GMOMenuItemRadio();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte de Tareadores");
        setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(520, 599));

        panel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(2, 2, 5, 5));

        etiqueta1.setBackground(defaults.colorAccent);
        etiqueta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta1.setText("Dia mas Productivo");
        etiqueta1.setOpaque(true);
        jPanel1.add(etiqueta1);

        etiqueta2.setBackground(defaults.colorAccent);
        etiqueta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta2.setText("Dia Menos Productivo");
        etiqueta2.setOpaque(true);
        jPanel1.add(etiqueta2);

        etDiaMoreProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etDiaMoreProd.setText("");
        jPanel1.add(etDiaMoreProd);

        etDiaMenProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etDiaMenProd.setText("");
        jPanel1.add(etDiaMenProd);

        panel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

        panel1.setLayout(new java.awt.BorderLayout());

        contentChart.setOpaque(false);
        contentChart.setLayout(new java.awt.BorderLayout());
        panel1.add(contentChart, java.awt.BorderLayout.CENTER);

        jPanel5.add(panel1);

        panel3.setLayout(new java.awt.BorderLayout());

        contentChartError.setOpaque(false);
        contentChartError.setLayout(new java.awt.BorderLayout());
        panel3.add(contentChartError, java.awt.BorderLayout.CENTER);

        jPanel5.add(panel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.EAST);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jTabbedPane1.addTab("Datos de Trabajadores", jScrollPane1);

        tablaError.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaError.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaErrorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaError);

        jTabbedPane1.addTab("Datos de Incidentes", jScrollPane2);

        jPanel3.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(edtDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edtDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        toolbar1.setEXCEL(true);
        toolbar1.setRECARGAR(true);
        getContentPane().add(toolbar1, java.awt.BorderLayout.WEST);

        jMenu1.setText("Configuracion");

        buttonGroup1.add(sm_ultimaSemana);
        sm_ultimaSemana.setSelected(true);
        sm_ultimaSemana.setText("Ultima Semana");
        sm_ultimaSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sm_ultimaSemanaActionPerformed(evt);
            }
        });
        jMenu1.add(sm_ultimaSemana);

        buttonGroup1.add(sm_dosSemana);
        sm_dosSemana.setText("Dos Ultimas Semanas");
        sm_dosSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sm_dosSemanaActionPerformed(evt);
            }
        });
        jMenu1.add(sm_dosSemana);

        buttonGroup1.add(sm_ultimoMes);
        sm_ultimoMes.setText("Ultimo Mes");
        sm_ultimoMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sm_ultimoMesActionPerformed(evt);
            }
        });
        jMenu1.add(sm_ultimoMes);

        buttonGroup1.add(sm_dosMes);
        sm_dosMes.setText("Dos Ultimos Meses");
        sm_dosMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sm_dosMesActionPerformed(evt);
            }
        });
        jMenu1.add(sm_dosMes);
        jMenu1.add(jSeparator1);

        buttonGroup1.add(sm_customSemana);
        sm_customSemana.setText("Personalizado Semana");
        sm_customSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sm_customSemanaActionPerformed(evt);
            }
        });
        jMenu1.add(sm_customSemana);

        buttonGroup1.add(sm_customMes);
        sm_customMes.setText("Personalizado Mes");
        sm_customMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sm_customMesActionPerformed(evt);
            }
        });
        jMenu1.add(sm_customMes);

        gMOMenuBar1.add(jMenu1);

        setJMenuBar(gMOMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sm_ultimaSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sm_ultimaSemanaActionPerformed
        TYPE = 1;
        this.item = sm_ultimaSemana;
        gettin_data();
    }//GEN-LAST:event_sm_ultimaSemanaActionPerformed

    private void sm_dosSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sm_dosSemanaActionPerformed
        TYPE = 2;
        this.item = sm_dosSemana;
        gettin_data();
    }//GEN-LAST:event_sm_dosSemanaActionPerformed

    private void sm_ultimoMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sm_ultimoMesActionPerformed
        TYPE = 3;
        this.item = sm_ultimoMes;
        gettin_data();
    }//GEN-LAST:event_sm_ultimoMesActionPerformed

    private void sm_dosMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sm_dosMesActionPerformed
        TYPE = 4;
        this.item = sm_dosMes;
        gettin_data();
    }//GEN-LAST:event_sm_dosMesActionPerformed

    private void sm_customSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sm_customSemanaActionPerformed
        TYPE = 5;
        this.item = sm_customSemana;
        gettin_data();
    }//GEN-LAST:event_sm_customSemanaActionPerformed

    private void sm_customMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sm_customMesActionPerformed
        TYPE = 6;
        this.item = sm_customMes;
        gettin_data();
    }//GEN-LAST:event_sm_customMesActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (tabla.getRowCount() > 0) {
            IDUSUARIO = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
            contentChart.removeAll();
            contentChart.updateUI();
            contentChartError.removeAll();
            contentChartError.updateUI();
            ORDER.clear();

            int fi = -1;
            for (int i = 0; i < tablaError.getRowCount(); i++) {
                if (tablaError.getValueAt(i, 0).toString().equals(IDUSUARIO)) {
                    fi = i;
                    break;
                }
            }

            ArrayList<Object[]> D1 = new ArrayList<>();
            ArrayList<Object[]> D2 = new ArrayList<>();
            for (int i = 2; i < 9; i++) {
                D1.add(new Object[]{
                    tabla.getValueAt(tabla.getSelectedRow(), i),
                    tabla.getValueAt(tabla.getSelectedRow(), 1),
                    tabla.getTITLES()[i]
                });
                if (fi >= 0)
                    D2.add(new Object[]{
                        tablaError.getValueAt(fi, i),
                        tablaError.getValueAt(fi, 1),
                        tablaError.getTITLES()[i]
                    });
                ORDER.add(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), i).toString()));
            }
            Collections.sort(ORDER);
//-----------------------------------------------------------------------------------------
            AreaChartGraph areaChart = new AreaChartGraph(
                    getTitle(),
                    tabla.getValueAt(tabla.getSelectedRow(), 1) + " = " + Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 9).toString()),
                    tabla.getValueAt(tabla.getSelectedRow(), 0).toString(),
                    "Cantidad Personas"
            );
            areaChart.addDataset(D1);
            areaChart.createAreaChart1(defaults.colorAccent);
            contentChart.add(areaChart.createDemoPanel());

//-----------------------------------------------------------------------------------------
            if (fi >= 0) {
                AreaChartGraph areaChart2 = new AreaChartGraph(
                        "Reporte de Incidentes - " + item.getText(),
                        tablaError.getValueAt(fi, 1) + " = " + Integer.parseInt(tablaError.getValueAt(fi, 9).toString()),
                        tablaError.getValueAt(fi, 0).toString(),
                        "Cantidad Incidentes"
                );
                areaChart2.addDataset(D2);
                areaChart2.createAreaChart1(MaterialColor.RED_500);
                contentChartError.add(areaChart2.createDemoPanel());
            }

//-----------------------------------------------------------------------------------------
            contentChart.updateUI();
            contentChartError.updateUI();

            etDiaMoreProd.setText(ORDER.get(0) + "");
            etDiaMenProd.setText(ORDER.get(6) + "");
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void tablaErrorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaErrorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaErrorMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel contentChart;
    private javax.swing.JPanel contentChartError;
    private kevin.component.date.MaterialDateChooser edtDateChooser;
    private kevin.component.label.Etiqueta etDiaMenProd;
    private kevin.component.label.Etiqueta etDiaMoreProd;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.menu.GMOMenuBar gMOMenuBar1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private kevin.component.panel.Panel panel1;
    private kevin.component.panel.Panel panel2;
    private kevin.component.panel.Panel panel3;
    private kevin.component.menu.GMOMenuItemRadio sm_customMes;
    private kevin.component.menu.GMOMenuItemRadio sm_customSemana;
    private kevin.component.menu.GMOMenuItemRadio sm_dosMes;
    private kevin.component.menu.GMOMenuItemRadio sm_dosSemana;
    private kevin.component.menu.GMOMenuItemRadio sm_ultimaSemana;
    private kevin.component.menu.GMOMenuItemRadio sm_ultimoMes;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.tabla.TablaSmart tablaError;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables
}
