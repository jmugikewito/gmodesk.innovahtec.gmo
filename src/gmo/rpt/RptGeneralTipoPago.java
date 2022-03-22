package gmo.rpt;

import app.RunMain;
import color.MaterialColor;
import gmo.utils.jkeys;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import jmugi.model.ImpAdminList;
import jmugi.voids.JMethods;
import kevin.component.defaults;
import kevin.component.dialog.SmartLoader;
import utils.ExecHTTP;

/**

 @author Asus
 */
public class RptGeneralTipoPago extends javax.swing.JInternalFrame implements ImpAdminList {

    CardLayout cardLayout;
    Window Frame;
    SmartLoader load;

    int EDITNUEVO = 0;

    Object[] FILA_SELECT;

    public RptGeneralTipoPago(Window w) {
        this.Frame = w;
        initComponents();
        cardLayout = (CardLayout) contenedor.getLayout();

        goData();

        initToolbar();

    }

    private void initToolbar() {
        toolbar.setAGREGAR_CALLBACK(this::Agregar);
        toolbar.setEDITAR_CALLBACK(this::Editar);
        toolbar.setGUARDAR_CALLBACK(this::Guardar);
        toolbar.setELIMINAR_CALLBACK(this::Eliminar);
        toolbar.setANULAR_CALLBACK(this::Anular);
        toolbar.setRECARGAR_CALLBACK(this::GetData);
        toolbar.setREGRESAR_CALLBACK(this::Cancelar);

        toolbar.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) Frame, "Exportacion datos a Excel...");
        });
    }

    @Override
    public void GetData() {
        goData();
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos dle Reporte",
                "Se estan Descargando los datos procesados de Cosecha",
                (Window frame) -> {
                    tabla.initHttp(
                            "90,90,260,60,90,115x8,95x3",
                            ("fecha, idtrabajador, trabajador, SEXO, fecha_ingreso, CLAMSHELL_BILOXI, CLAMSHELL_EMERALD, CLAMSHELL_OTRO, CLAMSHELL_VENTURA, GRANEL_BILOXI, GRANEL_EMERALD, GRANEL_OTRO, GRANEL_VENTURA, total_clamshell, total_granel, totaljabas").replace("_", "\n"),
                            "fecha, idtrabajador, trabajador, SEXO, fecha_ingreso, CLAMSHELL_BILOXI, CLAMSHELL_EMERALD, CLAMSHELL_OTRO, CLAMSHELL_VENTURA, GRANEL_BILOXI, GRANEL_EMERALD, GRANEL_OTRO, GRANEL_VENTURA, total_clamshell, total_granel, totaljabas",
                            "DateSQLx1,Stringx3,DateSQLx1,Doublex11",
                            RunMain.gettin_pages.api_get() + ExecHTTP.parseQL(
                            "exec GetRptGeneralTipoPago",
                            jkeys.IDDATABASE, jkeys.IDEMPRESA, cboCultivo.getSelectedItemString(), rbRendimiento.isSelected() ? "RENDIMIENTO" : "CONDICIONAL", date1.toStringDate(), date1.toStringDate())
                    );
                    tabla.GetDatosHTTP();
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.5f);
        load = null;
        tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatGeneralTipoPago());
        JMethods.updateInternalJTable(this, tabla);
    }

    @Override
    public void Eliminar() {
    }

    @Override
    public void Agregar() {

    }

    @Override
    public void Editar() {
    }

    @Override
    public void Editar(Object obj) {
    }

    @Override
    public void Actualizar() {
    }

    @Override
    public void Anular() {
    }

    @Override
    public void Guardar() {
    }

    @Override
    public void Cancelar() {
    }

    @Override
    public void goFilter() {
        cardLayout.show(contenedor, "cardFilter");
    }

    @Override
    public void goData() {
        cardLayout.show(contenedor, "cardGeneral");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        contenedor = new javax.swing.JPanel();
        panelData = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        panelFilter = new kevin.component.panel.Panel();
        jPanel1 = new javax.swing.JPanel();
        date1 = new kevin.component.date.MaterialDateChooser();
        etiquetaSmart1 = new kevin.component.label.EtiquetaSmart();
        etiquetaSmart2 = new kevin.component.label.EtiquetaSmart();
        date2 = new kevin.component.date.MaterialDateChooser();
        rbCondicional = new kevin.component.radiobox.RadioBox();
        rbRendimiento = new kevin.component.radiobox.RadioBox();
        cboMetrica = new kevin.component.combobox.ComboBox();
        etiquetaSmart3 = new kevin.component.label.EtiquetaSmart();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        etiquetaSmart4 = new kevin.component.label.EtiquetaSmart();
        cboCultivo = new kevin.component.combobox.ComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        btnConsultar = new kevin.component.button.Button();
        panelNuevoEditar = new javax.swing.JPanel();
        toolbar = new kevin.component.toolbar.Toolbar();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte General de Rendimiento");

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.setLayout(new java.awt.CardLayout());

        panelData.setOpaque(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setIsAutomaticResize(false);
        jScrollPane1.setViewportView(tabla);

        panelFilter.setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);

        etiquetaSmart1.setText("Fecha de Inicio");

        etiquetaSmart2.setText("Fecha de Fin");

        buttonGroup1.add(rbCondicional);
        rbCondicional.setText("Condicional");

        buttonGroup1.add(rbRendimiento);
        rbRendimiento.setSelected(true);
        rbRendimiento.setText("Rendimiento");

        cboMetrica.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DIA", "SEMANA", "MES" }));

        etiquetaSmart3.setText("Metrica");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        etiquetaSmart4.setText("Cultivo");

        cboCultivo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ARANDANO", "ESPARRAGO", "UVA" }));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(etiquetaSmart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaSmart2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboCultivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaSmart4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbCondicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbRendimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboMetrica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaSmart3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaSmart2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaSmart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(rbRendimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbCondicional, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaSmart3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboMetrica, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaSmart4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboCultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(5, 5, 5))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelFilter.add(jPanel1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
        panelData.setLayout(panelDataLayout);
        panelDataLayout.setHorizontalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(5, 5, 5))
        );
        panelDataLayout.setVerticalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        contenedor.add(panelData, "cardGeneral");

        panelNuevoEditar.setOpaque(false);

        javax.swing.GroupLayout panelNuevoEditarLayout = new javax.swing.GroupLayout(panelNuevoEditar);
        panelNuevoEditar.setLayout(panelNuevoEditarLayout);
        panelNuevoEditarLayout.setHorizontalGroup(
            panelNuevoEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 878, Short.MAX_VALUE)
        );
        panelNuevoEditarLayout.setVerticalGroup(
            panelNuevoEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
        );

        contenedor.add(panelNuevoEditar, "cardFilter");

        getContentPane().add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar.setBackground(new java.awt.Color(255, 255, 255));
        toolbar.setAGREGAR(false);
        toolbar.setEDITAR(false);
        toolbar.setELIMINAR(false);
        toolbar.setEXCEL(true);
        toolbar.setFILTRAR(true);
        toolbar.setIMPORTAR(false);
        toolbar.setIMPRIMIR(false);
        toolbar.setINDICADOR(false);
        toolbar.setIREPORT(false);
        toolbar.setRECARGAR(true);
        toolbar.setOpaque(true);
        getContentPane().add(toolbar, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        GetData();
    }//GEN-LAST:event_btnConsultarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.Button btnConsultar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private kevin.component.combobox.ComboBox cboCultivo;
    private kevin.component.combobox.ComboBox cboMetrica;
    private javax.swing.JPanel contenedor;
    private kevin.component.date.MaterialDateChooser date1;
    private kevin.component.date.MaterialDateChooser date2;
    private kevin.component.label.EtiquetaSmart etiquetaSmart1;
    private kevin.component.label.EtiquetaSmart etiquetaSmart2;
    private kevin.component.label.EtiquetaSmart etiquetaSmart3;
    private kevin.component.label.EtiquetaSmart etiquetaSmart4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel panelData;
    private kevin.component.panel.Panel panelFilter;
    private javax.swing.JPanel panelNuevoEditar;
    private kevin.component.radiobox.RadioBox rbCondicional;
    private kevin.component.radiobox.RadioBox rbRendimiento;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar;
    // End of variables declaration//GEN-END:variables

    class FormatGeneralTipoPago extends DefaultTableCellRenderer {

        public FormatGeneralTipoPago() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled());
            switch (column) {
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    if (Double.parseDouble(table.getValueAt(row, column).toString()) <= 0) {
                        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        setBackground(MaterialColor.REDA_200);
                        setForeground(MaterialColor.BLACK);
                    } else if (Double.parseDouble(table.getValueAt(row, column).toString()) > 0) {
                        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        setBackground(MaterialColor.GREENA_200);
                        setForeground(MaterialColor.BLACK);
                    } else {
                        setIcon(null);
                        setBackground(Color.WHITE);
                        setForeground(MaterialColor.GREY_800);
                    }
                    break;
//                case 13:
                case 15:
                    if (Double.parseDouble(table.getValueAt(row, column).toString()) <= 0) {
                        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        setBackground(MaterialColor.RED_400);
                        setForeground(MaterialColor.WHITE);
                    } else if (Double.parseDouble(table.getValueAt(row, column).toString()) > 0) {
                        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        setBackground(MaterialColor.TEAL_400);
                        setForeground(MaterialColor.WHITE);
                    } else {
                        setIcon(null);
                        setBackground(Color.WHITE);
                        setForeground(MaterialColor.GREY_800);
                    }
                    break;
                default:
                    setIcon(null);
                    setBackground(Color.WHITE);
                    setForeground(MaterialColor.GREY_800);
            }

            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            return this;
        }
    }

}
