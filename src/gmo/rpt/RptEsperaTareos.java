package gmo.rpt;

import app.RunMain;
import gmo.utils.jkeys;
import java.awt.CardLayout;
import java.awt.Window;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.TableCellRenderer;
import jmugi.voids.DateTimeUtil;
import jmugi.voids.JMethods;
import kevin.component.defaults;
import kevin.component.dialog.SmartLoader;
import other.tables.FormatTareoEspera;

/**

 @author Asus
 */
public class RptEsperaTareos extends javax.swing.JInternalFrame {

    public Object[][] DATA;
    public Window Frame;
    String FECHA_DATE;
    String IDCULTIVO = "";
    public CardLayout CARDLAYOUT;

    SmartLoader load;

    public RptEsperaTareos(Window w) {
        this.Frame = w;
        initComponents();
        setSize(720, 640);

        CARDLAYOUT = (CardLayout) contenedor.getLayout();
        CARDLAYOUT.show(contenedor, "cardData");

        chooserFecha.setDate(new Date());
        FECHA_DATE = DateTimeUtil.getDate_yyyyMMdd(chooserFecha.getDate());
        chooserFecha.setCallback(() -> {
            FECHA_DATE = DateTimeUtil.getDate_yyyyMMdd(chooserFecha.getDate());
            gettin_data();
        });

        toolbar1.setRECARGAR_CALLBACK(() -> {
            gettin_data();
        });
        toolbar1.setCHART_CALLBACK(() -> {
            CARDLAYOUT.show(contenedor, "cardChart");
        });
        toolbar1.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) Frame, getTitle());
        });
//        setCultivos();
    }

    private void gettin_data() {

        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Estados de los Tareos...",
                "Se estan Descargando la cantidad de trabajadores por Tareador",
                (Window frame) -> {
                    tabla.initHttp(
                            "50,80,220,60,60,60,60,60,60,60",
                            "cul,idusuario,nombres,LUN,MAR,MIE,JUE,VIE,SAB,DOM",
                            "idcultivo,idusuario,nombres,LUN,MAR,MIE,JUE,VIE,SAB,DOM",
                            "Stringx3,Integerx7",
                            RunMain.gettin_pages.GetQuery() + "exec GetRptEsperaTareo '" + jkeys.IDEMPRESA + "','" + FECHA_DATE + "';"
                    );
                    tabla.GetDatosHTTP();
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.5f);
        load = null;
        JMethods.updateInternalJTable(this, tabla);
        tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatTareoEspera());

        if (tabla.getRowCount() > 0)
            for (int i = 0; i < 7; i++)
                tablaResumen.setValueAt(tabla.getValueAt(tabla.getRowCount() - 1, i + 3), 0, i);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        etiqueta1 = new kevin.component.label.Etiqueta();
        etiqueta2 = new kevin.component.label.Etiqueta();
        etiqueta3 = new kevin.component.label.Etiqueta();
        etiqueta4 = new kevin.component.label.Etiqueta();
        etiqueta5 = new kevin.component.label.Etiqueta();
        etiqueta6 = new kevin.component.label.Etiqueta();
        etiqueta7 = new kevin.component.label.Etiqueta();
        etiqueta8 = new kevin.component.label.Etiqueta();
        etiqueta9 = new kevin.component.label.Etiqueta();
        etiqueta10 = new kevin.component.label.Etiqueta();
        etiqueta11 = new kevin.component.label.Etiqueta();
        etiqueta12 = new kevin.component.label.Etiqueta();
        etiqueta13 = new kevin.component.label.Etiqueta();
        etiqueta14 = new kevin.component.label.Etiqueta();
        jPanel1 = new javax.swing.JPanel();
        contenedor = new javax.swing.JPanel();
        panelData = new kevin.component.panel.Panel();
        jPanel3 = new javax.swing.JPanel();
        chooserFecha = new kevin.component.date.MaterialDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaResumen = new kevin.component.tabla.TablaSmart();
        etiqueta15 = new kevin.component.label.Etiqueta();
        panelChart = new kevin.component.panel.Panel();
        jPanel4 = new javax.swing.JPanel();
        toolbar1 = new kevin.component.toolbar.Toolbar();

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(2, 7, 2, 2));

        etiqueta1.setBackground(defaults.colorPrimaryDark);
        etiqueta1.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta1.setText("Lunes");
        etiqueta1.setOpaque(true);
        jPanel2.add(etiqueta1);

        etiqueta2.setBackground(defaults.colorPrimaryDark);
        etiqueta2.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta2.setText("Martes");
        etiqueta2.setOpaque(true);
        jPanel2.add(etiqueta2);

        etiqueta3.setBackground(defaults.colorPrimaryDark);
        etiqueta3.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta3.setText("Miercoles");
        etiqueta3.setOpaque(true);
        jPanel2.add(etiqueta3);

        etiqueta4.setBackground(defaults.colorPrimaryDark);
        etiqueta4.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta4.setText("Jueves");
        etiqueta4.setOpaque(true);
        jPanel2.add(etiqueta4);

        etiqueta5.setBackground(defaults.colorPrimaryDark);
        etiqueta5.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta5.setText("Viernes");
        etiqueta5.setOpaque(true);
        jPanel2.add(etiqueta5);

        etiqueta6.setBackground(defaults.colorPrimaryDark);
        etiqueta6.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta6.setText("Sabado");
        etiqueta6.setOpaque(true);
        jPanel2.add(etiqueta6);

        etiqueta7.setBackground(defaults.colorPrimaryDark);
        etiqueta7.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta7.setText("Domingo");
        etiqueta7.setOpaque(true);
        jPanel2.add(etiqueta7);

        etiqueta8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta8.setText("0");
        jPanel2.add(etiqueta8);

        etiqueta9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta9.setText("0");
        jPanel2.add(etiqueta9);

        etiqueta10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta10.setText("0");
        jPanel2.add(etiqueta10);

        etiqueta11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta11.setText("0");
        jPanel2.add(etiqueta11);

        etiqueta12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta12.setText("0");
        jPanel2.add(etiqueta12);

        etiqueta13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta13.setText("0");
        jPanel2.add(etiqueta13);

        etiqueta14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta14.setText("0");
        jPanel2.add(etiqueta14);

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Reporte de Tareo Semanal");
        setMaximumSize(new java.awt.Dimension(720, 560));
        setMinimumSize(new java.awt.Dimension(720, 560));
        setPreferredSize(new java.awt.Dimension(720, 560));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

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
        tabla.setIsAutomaticResize(false);
        jScrollPane1.setViewportView(tabla);

        tablaResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "LUN", "MAR", "MIE", "JUE", "VIE", "SAB", "DOM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaResumen.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaResumen.setRowHeight(29);
        jScrollPane2.setViewportView(tablaResumen);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiqueta15, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(etiqueta15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
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
            .addGap(0, 677, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
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
    private kevin.component.date.MaterialDateChooser chooserFecha;
    private javax.swing.JPanel contenedor;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta10;
    private kevin.component.label.Etiqueta etiqueta11;
    private kevin.component.label.Etiqueta etiqueta12;
    private kevin.component.label.Etiqueta etiqueta13;
    private kevin.component.label.Etiqueta etiqueta14;
    private kevin.component.label.Etiqueta etiqueta15;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta3;
    private kevin.component.label.Etiqueta etiqueta4;
    private kevin.component.label.Etiqueta etiqueta5;
    private kevin.component.label.Etiqueta etiqueta6;
    private kevin.component.label.Etiqueta etiqueta7;
    private kevin.component.label.Etiqueta etiqueta8;
    private kevin.component.label.Etiqueta etiqueta9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private kevin.component.panel.Panel panelChart;
    private kevin.component.panel.Panel panelData;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.tabla.TablaSmart tablaResumen;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables
}
