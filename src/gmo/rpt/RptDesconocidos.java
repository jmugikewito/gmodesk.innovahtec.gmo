package gmo.rpt;

import app.RunMain;
import color.MaterialColor;
import gmo.utils.jkeys;
import java.awt.CardLayout;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.TableCellRenderer;
import jmugi.voids.JCallback;
import jmugi.voids.JMethods;
import kevin.component.dialog.SmartLoader;
import other.tables.FormatTareoEspera;
import utils.ExecHTTP;

/**

 @author Asus
 */
public class RptDesconocidos extends javax.swing.JInternalFrame {

    public Object[][] DATA;
    public Window Frame;
    String FECHA_DATE;
    String IDCULTIVO = "";
    public CardLayout CARDLAYOUT;

    SmartLoader load;

    public RptDesconocidos(Window w) {
        this.Frame = w;
        initComponents();
        setSize(860, 560);

        CARDLAYOUT = (CardLayout) contenedor.getLayout();
        CARDLAYOUT.show(contenedor, "cardData");

        toolbar1.setRECARGAR_CALLBACK(() -> {
            gettin_data();
        });
        toolbar1.setCHART_CALLBACK(() -> {
            CARDLAYOUT.show(contenedor, "cardChart");
        });
        toolbar1.setEXCEL_CALLBACK(new JCallback() {
            @Override
            public void action() {
                export_excel();
            }
        });
    }

    private void export_excel() {
        tabla.exportExcel((JFrame) Frame, getTitle());
    }

    private void gettin_data() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos",
                "Actualizando Lista de Trabajadores tareados que no estan en Planilla", (Window frame) -> {
                    tabla.initHttp("",
                            "doc,idusuario,fecha,dni,nombres,itemid,item,jor,rend,ava",
                            "doc,idusuario,fecha,dni,nombres,itemid,item,jor,rend,ava",
                            "Stringx2,DateSQLx1,Stringx4,Doublex3",
                            RunMain.gettin_pages.api_get() + ExecHTTP.parseQL("exec GetLisTareoTrabajadoresUnknow  ", "", jkeys.IDEMPRESA, inicio.toStringDate(), fin.toStringDate(), 1));
                    tabla.GetDatosHTTP2022();
                    JMethods.updateInternalJTable(this, tabla);

                    tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatTareoEspera());
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, MaterialColor.BLUEGREY_900, 0.8f);
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
        inicio = new kevin.component.date.MaterialDateChooser();
        etiquetaSmart1 = new kevin.component.label.EtiquetaSmart();
        fin = new kevin.component.date.MaterialDateChooser();
        panelChart = new kevin.component.panel.Panel();
        jPanel4 = new javax.swing.JPanel();
        toolbar1 = new kevin.component.toolbar.Toolbar();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte de Trabajadores Desconocidos");

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

        etiquetaSmart1.setText("Seleccionar Fecha");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                .addGap(5, 5, 5))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaSmart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fin, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inicio, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(etiquetaSmart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fin, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelData.add(jPanel3, java.awt.BorderLayout.CENTER);

        contenedor.add(panelData, "cardData");

        panelChart.setLayout(new java.awt.BorderLayout());

        jPanel4.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        panelChart.add(jPanel4, java.awt.BorderLayout.CENTER);

        contenedor.add(panelChart, "cardChart");

        jPanel1.add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar1.setCHART(false);
        toolbar1.setELIMINAR(false);
        toolbar1.setEXCEL(true);
        toolbar1.setRECARGAR(true);
        jPanel1.add(toolbar1, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedor;
    private kevin.component.label.EtiquetaSmart etiquetaSmart1;
    private kevin.component.date.MaterialDateChooser fin;
    private kevin.component.date.MaterialDateChooser inicio;
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
