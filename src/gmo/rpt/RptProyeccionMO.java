package gmo.rpt;

import app.*;
import gmo.utils.jkeys;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import jmugi.component.MaterialColor;
import jmugi.model.ImpAdminList;
import jmugi.voids.JMethods;
import kevin.component.defaults;
import kevin.component.dialog.SmartLoader;
import utils.ExecHTTP;

/**

 @author Asus
 */
public class RptProyeccionMO extends javax.swing.JInternalFrame implements ImpAdminList {

    CardLayout cardLayout;
    Window Frame;
    SmartLoader load;

    int EDITNUEVO = 0;

    public RptProyeccionMO(Window w) {
        this.Frame = w;
        initComponents();
        cardLayout = (CardLayout) contenedor.getLayout();

        ArrayList<Object> sem = RunMain.CONECT.getListObject("select semAnio from semana;");
//        ArrayList<Object[]> sem = RunMain.CONECT.getListObject("select semAnio,semAnio|| ' → '||ini||'-'||fin from semana;", 2);
        for (int i = 0; i < sem.size(); i++)
            cboSemana.addItem(sem.get(i).toString(), false);

        initToolbar();
        goData();
    }

    public void actions(String action) {
        switch (action) {
            case "Cancelar":
            case "Regresar":
            case "Guardar":
                toolbar.setAGREGAR(true);
                toolbar.setANULAR(true);
                toolbar.setEDITAR(true);
                toolbar.setEXCEL(true);
                toolbar.setELIMINAR(true);
                toolbar.setIMPORTAR(false);
                toolbar.setGUARDAR(false);
                toolbar.setRECARGAR(true);
                toolbar.setREGRESAR(false);
                break;
            case "Agregar":
            case "Editar":
                toolbar.setAGREGAR(false);
                toolbar.setANULAR(false);
                toolbar.setEDITAR(false);
                toolbar.setEXCEL(false);
                toolbar.setELIMINAR(false);
                toolbar.setIMPORTAR(true);
                toolbar.setGUARDAR(true);
                toolbar.setRECARGAR(false);
                toolbar.setREGRESAR(true);
                break;
        }
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

    private void gettin_data() {
        goData();
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos de Proyeccion de Mano de Obra y Ejecutado",
                "Se estan Descargando los Tareos y Programaciones",
                (Window frame) -> {
                    tabla.initHttp(
                            "0,1",
                            "iddatabase, idempresa, anio, semana, idresponsable, responsable, cultivo, variedad, ACT, actividad, LAB, labor, ratio, um_ratio, esquema, avance, um_avance, etapa, campo, LUN_P, LUN_E, LUN_D, LUN_V, MAR_P, MAR_E, MAR_D, MAR_V, MIE_P, MIE_E, MIE_D, MIE_V, JUE_P, JUE_E, JUE_D, JUE_V, VIE_P, VIE_E, VIE_D, VIE_V, SAB_P, SAB_E, SAB_D, SAB_V, DOM_P, DOM_E, DOM_D, DOM_V, prom_p, prom_e, prom_v",
                            "iddatabase, idempresa, anio, semana, idresponsable, responsable, cultivo, variedad, ACT, actividad, LAB, labor, ratio, um_ratio, esquema, avance, um_avance, etapa, campo, LUN_P, LUN_E, LUN_D, LUN_V, MAR_P, MAR_E, MAR_D, MAR_V, MIE_P, MIE_E, MIE_D, MIE_V, JUE_P, JUE_E, JUE_D, JUE_V, VIE_P, VIE_E, VIE_D, VIE_V, SAB_P, SAB_E, SAB_D, SAB_V, DOM_P, DOM_E, DOM_D, DOM_V, prom_p, prom_e, prom_v",
                            "Stringx2,Integerx2,Stringx8,Doublex1,Stringx2,Doublex1,Stringx3,Doublex31",
                            RunMain.gettin_pages.api_get() + ExecHTTP.parseQL("exec GetRptProyectadovsReal ", jkeys.IDDATABASE, jkeys.IDEMPRESA, edtAnio.getText(), cboSemana.getLabelText().replace(" ", ""), "", "", "", "", "PROVSEJE")
                    );
                    tabla.GetDatosHTTP();
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.5f);
        load = null;
        tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatMO());
        JMethods.updateInternalJTable(this, tabla);
    }

    @Override
    public void GetData() {
        gettin_data();
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

        contenedor = new javax.swing.JPanel();
        panelData = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        etiqueta1 = new kevin.component.label.Etiqueta();
        cboSemana = new kevin.component.combobox.ComboCheckBox();
        button1 = new kevin.component.button.Button();
        edtAnio = new kevin.component.edittext.EditText();
        panelNuevoEditar = new javax.swing.JPanel();
        toolbar = new kevin.component.toolbar.Toolbar();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte de Proyeccion de Mano de Obra");

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.setLayout(new java.awt.CardLayout());

        panelData.setOpaque(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        etiqueta1.setText("Semanas");

        cboSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSemanaActionPerformed(evt);
            }
        });

        button1.setText("Consultar");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        edtAnio.setText("2020");
        edtAnio.setDigit_limit(4);
        edtAnio.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_CANTIDAD);

        javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
        panelData.setLayout(panelDataLayout);
        panelDataLayout.setHorizontalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                .addGap(5, 5, 5))
            .addGroup(panelDataLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(edtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(etiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelDataLayout.setVerticalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataLayout.createSequentialGroup()
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDataLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDataLayout.createSequentialGroup()
                                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(etiqueta1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                    .addComponent(cboSemana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(4, 4, 4)))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(edtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(panelData, "cardGeneral");

        panelNuevoEditar.setOpaque(false);

        javax.swing.GroupLayout panelNuevoEditarLayout = new javax.swing.GroupLayout(panelNuevoEditar);
        panelNuevoEditar.setLayout(panelNuevoEditarLayout);
        panelNuevoEditarLayout.setHorizontalGroup(
            panelNuevoEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        panelNuevoEditarLayout.setVerticalGroup(
            panelNuevoEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );

        contenedor.add(panelNuevoEditar, "cardFilter");

        getContentPane().add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar.setBackground(new java.awt.Color(255, 255, 255));
        toolbar.setAGREGAR(false);
        toolbar.setCHART(true);
        toolbar.setEDITAR(false);
        toolbar.setELIMINAR(false);
        toolbar.setEXCEL(true);
        toolbar.setFILTRAR(false);
        toolbar.setIMPORTAR(false);
        toolbar.setIMPRIMIR(false);
        toolbar.setINDICADOR(false);
        toolbar.setIREPORT(false);
        toolbar.setRECARGAR(true);
        toolbar.setOpaque(true);
        getContentPane().add(toolbar, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSemanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSemanaActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        cboSemana.printEstados();
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.Button button1;
    private kevin.component.combobox.ComboCheckBox cboSemana;
    private javax.swing.JPanel contenedor;
    private kevin.component.edittext.EditText edtAnio;
    private kevin.component.label.Etiqueta etiqueta1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelData;
    private javax.swing.JPanel panelNuevoEditar;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar;
    // End of variables declaration//GEN-END:variables

    class FormatMO extends DefaultTableCellRenderer {

        public FormatMO() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled());
            switch (column) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    double x = Double.parseDouble(value.toString());
                    if (x > 0) {
                        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        setBackground(MaterialColor.GREENA_200);
                        setForeground(MaterialColor.GREEN_900);
                    } else if (x == 0) {
                        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        setBackground(MaterialColor.GREY_200);
                        setForeground(MaterialColor.BLACK);
                    } else {
                        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        setBackground(MaterialColor.REDA_100);
                        setForeground(MaterialColor.RED_900);
                    }
                    break;
                default:
                    setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    break;
            }
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            return this;
        }
    }
}
