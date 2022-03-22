package gmo.rpt;

import app.RunMain;
import color.MaterialColor;
import gmo.utils.jkeys;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ListSelectionModel;
import jmugi.voids.DateTimeUtil;
import jmugi.voids.JMethods;
import kevin.component.dialog.SmartLoader;
import model.BarChartGraph;

/**

 @author Asus
 */
public class RptCosechaPagos extends javax.swing.JInternalFrame {

    JInternalFrame internal;
    JFrame frame;
    String FECHA_DATE1, FECHA_DATE2, IDCOSECHA = "";
    SmartLoader load;
    private int REPORT;
    private int[] TAMS;
    private String[] TITLES;
    private JRadioButtonMenuItem MENU;

    public RptCosechaPagos(JFrame frame) {
        initComponents();
        this.frame = frame;
        this.internal = this;

        tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        REPORT = 1;
        MENU = rm_produDiaJaba;

        toolbar1.setRECARGAR_CALLBACK(() -> {
            gettin_data();
        });

        chooserDate1.setDate(new Date());
        FECHA_DATE1 = DateTimeUtil.getDate_yyyyMMdd(chooserDate1.getDate());
        chooserDate1.setCallback(() -> {
            FECHA_DATE1 = DateTimeUtil.getDate_yyyyMMdd(chooserDate1.getDate());
        });

        chooserDate2.setDate(new Date());
        FECHA_DATE2 = DateTimeUtil.getDate_yyyyMMdd(chooserDate2.getDate());
        chooserDate2.setCallback(() -> {
            FECHA_DATE2 = DateTimeUtil.getDate_yyyyMMdd(chooserDate2.getDate());
        });

        gettin_data();


        toolbar1.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel(frame, getTitle());
        });

    }

    private void gettin_data() {
        setTitle("Reporte Produccion de Cosecha - " + MENU.getText());
        switch (REPORT) {
            case 1:
                panChart.setVisible(true);
                tabla.initHttp("",
                        "Fecha, Total\nJabas, Total\nTrabajadores, Promedio",
                        "fecha, total_jabas, total_trab, promedio",
                        "DateSQLx1,Doublex3",
                        RunMain.gettin_pages.api_get() + "exec GetRptCosechaProduccion '" + jkeys.IDDATABASE + "','" + jkeys.IDEMPRESA + "','" + FECHA_DATE1 + "','" + FECHA_DATE2 + "'," + REPORT + ";"
                );
                break;
            case 2:
                panChart.setVisible(true);
                tabla.initHttp("",
                        "Fecha,Variedad,Total\nJabas, Total\nTrabajadores, Promedio",
                        "fecha, descripcion, total_jabas, total_trab, promedio",
                        "DateSQLx1,Stringx1,Doublex3",
                        RunMain.gettin_pages.api_get() + "exec GetRptCosechaProduccion '" + jkeys.IDDATABASE + "','" + jkeys.IDEMPRESA + "','" + FECHA_DATE1 + "','" + FECHA_DATE2 + "'," + REPORT + ";"
                );
                break;
            case 3:
                panChart.setVisible(false);
                tabla.initHttp("",
                        "Fecha, idsupervisor, Supervisor, idapuntador, Apuntador, Variedad,Total\nJabas, Total\nTrabajadores, Promedio",
                        "fecha, idsupervisor, supervisor, idapuntador, apuntador, descripcion, total_jabas, total_trab, promedio",
                        "DateSQLx1,Stringx5,Doublex3",
                        RunMain.gettin_pages.api_get() + "exec GetRptCosechaProduccion '" + jkeys.IDDATABASE + "','" + jkeys.IDEMPRESA + "','" + FECHA_DATE1 + "','" + FECHA_DATE2 + "'," + REPORT + ";"
                );
                break;
            case 4:
                panChart.setVisible(false);
                tabla.initHttp("",
                        "idsupervisor,Supervisor,Total\nDias,Total\nJabas, Total\nTrabajadores, Promedio",
                        "idsupervisor, supervisor, total_dias, total_jabas, total_trab, promedio",
                        "Stringx2,Doublex4",
                        RunMain.gettin_pages.api_get() + "exec GetRptCosechaProduccion '" + jkeys.IDDATABASE + "','" + jkeys.IDEMPRESA + "','" + FECHA_DATE1 + "','" + FECHA_DATE2 + "'," + REPORT + ";"
                );
                break;
            case 5:
                panChart.setVisible(false);
                tabla.initHttp("",
                        "idapuntador,Apuntador,Total\nDias,Total\nJabas, Total\nTrabajadores, Promedio",
                        "idapuntador, apuntador, total_dias, total_jabas, total_trab, promedio",
                        "Stringx2,Doublex4",
                        RunMain.gettin_pages.api_get() + "exec GetRptCosechaProduccion '" + jkeys.IDDATABASE + "','" + jkeys.IDEMPRESA + "','" + FECHA_DATE1 + "','" + FECHA_DATE2 + "'," + REPORT + ";"
                );
                break;
        }

        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) frame, true,
                "Descargando Datos de Cosecha",
                "Se estan Descargando los Datos de Cosecha Seleccionados...",
                (Window frame) -> {
                    tabla.GetDatosHTTP();
                    JMethods.updateInternalJTable(internal, tabla);
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) frame, load, MaterialColor.BLUEGREY_900, 0.8f);
        load = null;

        switch (REPORT) {
            case 1:
                addChart();
                break;
            case 2:
                addChart();
                break;
        }

    }

    private void addChart() {
        contentChart.removeAll();
        contentChart.updateUI();
        ArrayList<Object[]> D1 = new ArrayList<>();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            D1.add(new Object[]{
                (REPORT == 1 ? Double.parseDouble(tabla.getValueAt(i, 1).toString()) : Double.parseDouble(tabla.getValueAt(i, 2).toString())),
                (REPORT == 1 ? tabla.getValueAt(i, 0).toString() : tabla.getValueAt(i, 0).toString() + " " + tabla.getValueAt(i, 1).toString()),
                "Jabas " + FECHA_DATE1 + " - " + FECHA_DATE2
            });
        }
        BarChartGraph barChart = new BarChartGraph(
                getTitle(),
                "XDXD",
                "Fechas y Cantidad Jabas",
                "Cantidad Jabas"
        );
        barChart.addDataset(D1);
        barChart.createBarChart8(tabla.getRowCount());
        contentChart.add(barChart.createDemoPanel());
        contentChart.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        etiqueta1 = new kevin.component.label.Etiqueta();
        chooserDate1 = new kevin.component.date.MaterialDateChooser();
        etiqueta2 = new kevin.component.label.Etiqueta();
        chooserDate2 = new kevin.component.date.MaterialDateChooser();
        toolbar1 = new kevin.component.toolbar.Toolbar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        panChart = new javax.swing.JPanel();
        panel3 = new kevin.component.panel.Panel();
        contentChart = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        rm_produDiaJaba = new javax.swing.JRadioButtonMenuItem();
        rm_produDiaVarJaba = new javax.swing.JRadioButtonMenuItem();
        rm_produDetallado = new javax.swing.JRadioButtonMenuItem();
        rm_produxSuper = new javax.swing.JRadioButtonMenuItem();
        rm_produxApuntador = new javax.swing.JRadioButtonMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte Produccion de Cosecha");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        etiqueta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta1.setText("Fecha Inicio");

        etiqueta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta2.setText("Fecha Fin");

        toolbar1.setEXCEL(true);
        toolbar1.setRECARGAR(true);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

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
        jScrollPane2.setViewportView(tabla);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        panChart.setOpaque(false);
        panChart.setPreferredSize(new java.awt.Dimension(800, 518));

        panel3.setLayout(new java.awt.BorderLayout());

        contentChart.setOpaque(false);
        contentChart.setLayout(new java.awt.BorderLayout());
        panel3.add(contentChart, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panChartLayout = new javax.swing.GroupLayout(panChart);
        panChart.setLayout(panChartLayout);
        panChartLayout.setHorizontalGroup(
            panChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panChartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                .addContainerGap())
        );
        panChartLayout.setVerticalGroup(
            panChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panChartLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addGap(173, 173, 173))
        );

        jPanel2.add(panChart, java.awt.BorderLayout.EAST);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(toolbar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(etiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chooserDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiqueta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chooserDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(631, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chooserDate2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(etiqueta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooserDate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiqueta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Configuracion");

        buttonGroup1.add(rm_produDiaJaba);
        rm_produDiaJaba.setSelected(true);
        rm_produDiaJaba.setText("Produccion por Dia");
        rm_produDiaJaba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rm_produDiaJabaActionPerformed(evt);
            }
        });
        jMenu1.add(rm_produDiaJaba);

        buttonGroup1.add(rm_produDiaVarJaba);
        rm_produDiaVarJaba.setText("Produccion por Dia y Variedad");
        rm_produDiaVarJaba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rm_produDiaVarJabaActionPerformed(evt);
            }
        });
        jMenu1.add(rm_produDiaVarJaba);

        buttonGroup1.add(rm_produDetallado);
        rm_produDetallado.setText("Produccion Detallada");
        rm_produDetallado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rm_produDetalladoActionPerformed(evt);
            }
        });
        jMenu1.add(rm_produDetallado);

        buttonGroup1.add(rm_produxSuper);
        rm_produxSuper.setText("Produccion por Supervisor");
        rm_produxSuper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rm_produxSuperActionPerformed(evt);
            }
        });
        jMenu1.add(rm_produxSuper);

        buttonGroup1.add(rm_produxApuntador);
        rm_produxApuntador.setText("Produccion por Apuntador");
        rm_produxApuntador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rm_produxApuntadorActionPerformed(evt);
            }
        });
        jMenu1.add(rm_produxApuntador);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rm_produDiaJabaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rm_produDiaJabaActionPerformed
        REPORT = 1;
        MENU = rm_produDiaJaba;
        gettin_data();
    }//GEN-LAST:event_rm_produDiaJabaActionPerformed

    private void rm_produDetalladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rm_produDetalladoActionPerformed
        REPORT = 3;
        MENU = rm_produDetallado;
        gettin_data();
    }//GEN-LAST:event_rm_produDetalladoActionPerformed

    private void rm_produxSuperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rm_produxSuperActionPerformed
        REPORT = 4;
        MENU = rm_produxSuper;
        gettin_data();
    }//GEN-LAST:event_rm_produxSuperActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked

    }//GEN-LAST:event_tablaMouseClicked

    private void rm_produDiaVarJabaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rm_produDiaVarJabaActionPerformed
        REPORT = 2;
        MENU = rm_produDiaVarJaba;
        gettin_data();
    }//GEN-LAST:event_rm_produDiaVarJabaActionPerformed

    private void rm_produxApuntadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rm_produxApuntadorActionPerformed
        REPORT = 5;
        MENU = rm_produxApuntador;
        gettin_data();
    }//GEN-LAST:event_rm_produxApuntadorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private kevin.component.date.MaterialDateChooser chooserDate1;
    private kevin.component.date.MaterialDateChooser chooserDate2;
    private javax.swing.JPanel contentChart;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panChart;
    private kevin.component.panel.Panel panel3;
    private javax.swing.JRadioButtonMenuItem rm_produDetallado;
    private javax.swing.JRadioButtonMenuItem rm_produDiaJaba;
    private javax.swing.JRadioButtonMenuItem rm_produDiaVarJaba;
    private javax.swing.JRadioButtonMenuItem rm_produxApuntador;
    private javax.swing.JRadioButtonMenuItem rm_produxSuper;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables
}
