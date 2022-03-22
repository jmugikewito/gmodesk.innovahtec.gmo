package gmo.rpt;

import app.RunMain;
import gmo.utils.jkeys;
import java.awt.CardLayout;
import java.awt.Window;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import jmugi.voids.JMethods;
import kevin.component.defaults;
import kevin.component.dialog.SmartLoader;

/**

 @author Asus
 */
public class RptAnalisisTareo extends javax.swing.JInternalFrame {

    public Object[][] DATA;
    public Window Frame;
    String FECHA_DATE;
    String IDCULTIVO = "";
    public CardLayout CARDLAYOUT;

    SmartLoader load;

    public RptAnalisisTareo(Window w) {
        this.Frame = w;
        initComponents();
        setSize(860, 560);

        CARDLAYOUT = (CardLayout) contenedor.getLayout();
        CARDLAYOUT.show(contenedor, "cardData");
        chooserFecha1.setDate(new Date());
        chooserFecha2.setDate(new Date());

        toolbar1.setRECARGAR_CALLBACK(() -> {
            gettin_data();
        });
        toolbar1.setCHART_CALLBACK(() -> {
            CARDLAYOUT.show(contenedor, "cardChart");
        });
        toolbar1.setEXCEL_CALLBACK(this::export_excel);
    }

    private void export_excel() {
        tabla.exportExcel((JFrame) Frame, getTitle());
    }

    private void gettin_data() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos",
                "Actualizando Analisis de Inputacion de SubTareos...", (Window frame) -> {

                    tabla.initHttp("0",
                            "idtareo, numero, fecha, item, actividad, observacion, trabajadores",
                            "idtareo, numero, fecha, item, actividad, observacion, trabajadores",
                            "Stringx2,DateSQLx1,Stringx3,Integerx1",
                            RunMain.gettin_pages.api_get() + "EXEC GetRptTareoAnalisis '" + jkeys.IDEMPRESA + "','" + chooserFecha1.toStringDate() + "', '" + chooserFecha2.toStringDate() + "';");
                    tabla.GetDatosHTTP();
                    JMethods.updateInternalJTable(this, tabla);
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.8f);
        load = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        contenedor = new javax.swing.JPanel();
        panelData = new kevin.component.panel.Panel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        etiqueta1 = new kevin.component.label.Etiqueta();
        etiqueta2 = new kevin.component.label.Etiqueta();
        chooserFecha1 = new kevin.component.date.MaterialDateChooser();
        chooserFecha2 = new kevin.component.date.MaterialDateChooser();
        panelChart = new kevin.component.panel.Panel();
        jPanel4 = new javax.swing.JPanel();
        toolbar1 = new kevin.component.toolbar.Toolbar();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte de Analisis de Tareos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        contenedor.setOpaque(false);
        contenedor.setLayout(new java.awt.CardLayout());

        panelData.setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        etiqueta1.setText("Buscando fallos de la inputacion de SubTareos y los respectivos datos de los trabajadores...");

        etiqueta2.setText("Fechas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addComponent(etiqueta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(etiqueta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooserFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooserFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(chooserFecha1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooserFecha2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelData.add(jPanel3, java.awt.BorderLayout.CENTER);

        contenedor.add(panelData, "cardData");

        panelChart.setLayout(new java.awt.BorderLayout());

        jPanel4.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        panelChart.add(jPanel4, java.awt.BorderLayout.CENTER);

        contenedor.add(panelChart, "cardChart");

        jPanel1.add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar1.setCHART(false);
        toolbar1.setELIMINAR(false);
        toolbar1.setEXCEL(true);
        toolbar1.setRECARGAR(true);
        toolbar1.setPreferredSize(new java.awt.Dimension(42, 844));
        jPanel1.add(toolbar1, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.date.MaterialDateChooser chooserFecha1;
    private kevin.component.date.MaterialDateChooser chooserFecha2;
    private javax.swing.JPanel contenedor;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private kevin.component.panel.Panel panelChart;
    private kevin.component.panel.Panel panelData;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables
}
